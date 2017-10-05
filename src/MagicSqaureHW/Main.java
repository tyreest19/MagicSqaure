package MagicSqaureHW;
import java.util.*;
import java.lang.*;

public class Main {

    public static int GetUserEnteredInt(Scanner scanner) {
        // Prompts user to enter a int then returns the int.
        boolean userEnterValidInt = false;
        String userEnterNumber;
        do {
            System.out.println("Enter in the number of sides: ");
            userEnterNumber = scanner.next();
            userEnterValidInt = userEnterNumber.matches("^-?\\d+$");
            if (userEnterValidInt)
                userEnterValidInt = IsValidNumberOfSides(Integer.parseInt(userEnterNumber));
        } while (!userEnterValidInt);
        return Integer.parseInt(userEnterNumber);
    }

    public static char GetUserEnteredChar(Scanner scanner) {
        // Prompts user to enter a char then returns the char.
        boolean userEnterValidChar = false;
        char userEnterChar;
        do {
            userEnterValidChar = true;
            System.out.println("Enter (a-d) to get the state of the Magic Square. Also a is the default so if the Magic " +
                    "Square Appears wrong please select a.\nThe issue is not with my Magic Sqaure Generation but it is with " +
                    "Switching the states of my magic square.\nFor example, a maybe correct but c may not be: ");
            String validateLength = scanner.next();
            userEnterChar = validateLength.charAt(0);
            if (!(userEnterChar >= 'a' && userEnterChar <= 'd' && validateLength.length() == 1)) {
                userEnterValidChar = false;
                System.out.println("Please enter the letter a, b, c, or d.");
            }
        } while (!userEnterValidChar);
        return userEnterChar;
    }
    public static boolean YesOrNoPrompt(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String userInput = scanner.next().toLowerCase();
        boolean valid_input;
        do
        {
            valid_input = true;

            if (!(userInput.equals("yes") || userInput.equals("no")))
            {
                System.out.println("please enter yes or no. ");
                System.out.println(prompt);
                userInput = scanner.next().toLowerCase();
                valid_input = false;
            }
        }while (!valid_input);
        return userInput.equals("yes");
    }

    public static boolean IsValidNumberOfSides(int userEnterNumber) {
        // Validates whether the user enter a valid number of sides for the magic Square.
        // Args:
        //  userEnterNumber: Number of sides that the user choose.
        if (userEnterNumber == 2) {
            System.out.println("Not possible.");
            return false;
        }
        if (userEnterNumber == 6 || userEnterNumber == 10 || userEnterNumber == 14  || userEnterNumber < 1) {
            System.out.println("Don't press your luck");
            return false;
        }
        return true;
    }

    public static int[][] GenerateOddMagicSqaure(int size) {
        int matrix[][] = new int[size][size];
        int x_coordinate = size / 2;
        int y_coordinate = 0;
        matrix[y_coordinate][x_coordinate] = 1;
        for (int i = 2; i <= size * size; i++) {
            // move up formula = (current location - (size + 1)) % size
            // move to the right = (current location + 1) % size
            // move down formula = (current location  + (size + 1)) % size
            int potential_y_coordinate = Math.floorMod((y_coordinate - (size + 1)), size);
            int potential_x_coordinate = (x_coordinate + 1) % size;
            if (matrix[potential_y_coordinate][potential_x_coordinate] == 0) {
                x_coordinate = potential_x_coordinate;
                y_coordinate = potential_y_coordinate;
                matrix[y_coordinate][x_coordinate] = i;
            }
            else {
                y_coordinate = (y_coordinate  + (size + 1)) % size;
                matrix[y_coordinate][x_coordinate] = i;
            }
        }
        return matrix;
    }

    public static int[][] GenerateSignleMagicSqaure(int sides) {
        // Generates a magic squares for even numbers that are not divisible by 4
        if (sides < 6 || (sides - 2) % 4 != 0)
            throw new IllegalArgumentException("base must be a positive "
                    + "multiple of 4 plus 2");

        int size = sides * sides;
        int halfN = sides / 2;
        int subSquareSize = size / 4;

        int[][] subSquare = GenerateOddMagicSqaure(halfN);
        int[] quadrantFactors = {0, 2, 3, 1};
        int[][] result = new int[sides][sides];

        for (int r = 0; r < sides; r++) {
            for (int c = 0; c < sides; c++) {
                int quadrant = (r / halfN) * 2 + (c / halfN);
                result[r][c] = subSquare[r % halfN][c % halfN];
                result[r][c] += quadrantFactors[quadrant] * subSquareSize;
            }
        }

        int nColsLeft = halfN / 2;
        int nColsRight = nColsLeft - 1;

        for (int r = 0; r < halfN; r++)
            for (int c = 0; c < sides; c++) {
                if (c < nColsLeft || c >= sides - nColsRight
                        || (c == nColsLeft && r == nColsLeft)) {

                    if (c == 0 && r == nColsLeft)
                        continue;

                    int tmp = result[r][c];
                    result[r][c] = result[r + halfN][c];
                    result[r + halfN][c] = tmp;
                }
            }

        return result;
    }

    public static int[][]  GenerateDoubleMagicSqaure(int size) {
        // Generates a magic squares for even numbers that are divisible by 4
        // pattern of count-up vs count-down zones
        int N = size;

        int miniSqrNum = size/4; //size of boxes
        int cnt = 1; 	      //counter 1 to N*N
        int invCnt = N*N;     //counter N*N to 1
        int results[][] = new int[size][size];
        for(int i=0;i<N;i++){

            for(int j=0;j<N;j++){

                if(j>=miniSqrNum && j<N-miniSqrNum){
                    if(i>=miniSqrNum && i<N-miniSqrNum)
                        results[i][j] = cnt;    //central box
                    else
                        results[i][j] = invCnt; // up & down boxes

                }
                else if(i<miniSqrNum || i>=N-miniSqrNum){
                    results[i][j]=cnt;	         // 4 corners
                }
                else
                    results[i][j] = invCnt;  	// left & right boxes
                cnt++;
                invCnt--;
            }

        }
        return results;
    }

    public void RotateMagicSquareState(int[][] magicSquare, char id, int size)  {
        // Responsible for generating the 4 different magic square states.
        // Arguments:
        //  id: Indicates the square state.
        //  size: The size of the square so example a 4 by 4 square has a size 4.
        int switched_matrix = 0;
        if (id == 'a') // a is the default state of the square
            return;
        switch (id) {
            case 'b':
                switched_matrix = size - 1;
                break;
            case 'c':
                switched_matrix = 1;
                break;
            case 'd':
                switched_matrix = 2;
                break;
        }
        for (int i = 0; i < size; i++) {
            int temp = magicSquare[switched_matrix][i];
            magicSquare[switched_matrix][i] = magicSquare[0][i];
            magicSquare[0][i] = temp;
        }
    }

    public static String PrintMagicSqaure(int[][] magicSquare, boolean printWithLines, int size) {
        // Prints the Magic Square Object.
        // Args:
        //  id: Will be a, b, c, or d and this id will be used to find the magic square state that the implementer
        //  wishes to print.
        //  printWithLines: boolean value that detrimines if magic sqaure will be printed with our without lines.
        String matrix = "";
        if (printWithLines) {

            String lines = new String(new char[size]).replace("\0", " ---");
            for (int i = 0; i < size; i++) {
                matrix += lines + "\n";
                matrix += ("|");
                for (int j = 0; j < size; j++) {
                    String spaces;
                    if (magicSquare[i][j] / 10 == 0)
                        matrix += "  " + magicSquare[i][j] + " ";
                    else if (magicSquare[i][j] / 100 == 0)
                        matrix += " " + magicSquare[i][j] + " ";
                    else
                        matrix += magicSquare[i][j] + " ";
                }
                matrix += "|" +"\n";
            }
            matrix += lines + "\n";
        }
        else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (magicSquare[i][j] / 10 == 0)
                        matrix += "  " + magicSquare[i][j] + " ";
                    else if (magicSquare[i][j] / 100 == 0)
                        matrix += " " + magicSquare[i][j] + " ";
                    else
                        matrix += magicSquare[i][j] + " ";
                }
                matrix +="\n";
            }
        }
        return matrix;
    }

    public static void main(String args[]) {
        boolean createNewSquare;
        int sidesOfMagicSqaure;
        char magicSquareState;
        boolean continue_program;
        int[][] magicSquare;
        String endProgramPrompt = "Enter yes to make another magic square and no to end the program: ";
        String printWithLinesPrompt = "Enter yes to print your magic square with lines and no to print without lines: ";
        Scanner scanner = new Scanner(System.in);
        do {
            sidesOfMagicSqaure = GetUserEnteredInt(scanner);
            if (sidesOfMagicSqaure != 1)
                magicSquareState = GetUserEnteredChar(scanner);
            if (sidesOfMagicSqaure % 2 != 0)
                magicSquare = GenerateOddMagicSqaure(sidesOfMagicSqaure);
            else if ((sidesOfMagicSqaure % 4 != 0) && (sidesOfMagicSqaure > 6))
                magicSquare = GenerateSignleMagicSqaure(sidesOfMagicSqaure);
            else
                magicSquare = GenerateDoubleMagicSqaure(sidesOfMagicSqaure);
            boolean printWithLines = YesOrNoPrompt(scanner, printWithLinesPrompt);
            System.out.println(PrintMagicSqaure(magicSquare, printWithLines, sidesOfMagicSqaure));
            continue_program = YesOrNoPrompt(scanner, endProgramPrompt);
        } while (continue_program);
    }

}

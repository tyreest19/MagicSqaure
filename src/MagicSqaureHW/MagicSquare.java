package MagicSqaureHW;
import java.util.*;
import java.lang.*;

public class MagicSquare {

    Integer[][] magicSquare = new Integer[17][17];

    public void GenerateMagicSquareState(int size) {
        if (size % 2 != 0)
            magicSquare = GenerateOddMagicSqaure(size);
        else if ((size % 4 != 0) && (size > 6))
            magicSquare = GenerateSignleMagicSqaure(size);
        else
            magicSquare = GenerateDoubleMagicSqaure(size);
        //GenerateMagicSquareState('a', size);
        }

    private static Integer[][] GenerateOddMagicSqaure(int size) {
        Integer matrix[][] = new Integer[size][size];
        int x_coordinate = size / 2;
        int y_coordinate = 0;
        matrix[y_coordinate][x_coordinate] = 1;
        for (int i = 2; i <= size * size; i++) {
            // move up formula = (current location - (size + 1)) % size
            // move to the right = (current location + 1) % size
            // move down formula = (current location  + (size + 1)) % size
            int potential_y_coordinate = Math.floorMod((y_coordinate - (size + 1)), size);
            int potential_x_coordinate = (x_coordinate + 1) % size;
            if (matrix[potential_y_coordinate][potential_x_coordinate] == null) {
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

    private static Integer[][] GenerateSignleMagicSqaure(int sides) {
        // Generates a magic squares for even numbers that are not divisible by 4
        if (sides < 6 || (sides - 2) % 4 != 0)
            throw new IllegalArgumentException("base must be a positive "
                    + "multiple of 4 plus 2");

        int size = sides * sides;
        int halfN = sides / 2;
        int subSquareSize = size / 4;

        Integer[][] subSquare = GenerateOddMagicSqaure(halfN);
        Integer[] quadrantFactors = {0, 2, 3, 1};
        Integer[][] result = new Integer[sides][sides];

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

    private static Integer[][]  GenerateDoubleMagicSqaure(int size) {
        // Generates a magic squares for even numbers that are divisible by 4
        // pattern of count-up vs count-down zones
        int N = size;

        int miniSqrNum = size/4; //size of boxes
        int cnt = 1; 	      //counter 1 to N*N
        int invCnt = N*N;     //counter N*N to 1
        Integer results[][] = new Integer[size][size];
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


    private void GenerateMagicSquareState(char id, int size)  {
        // Responsible for generating the 4 different magic square states.
        // Arguments:
        //  id: Indicates the square state.
        //  size: The size of the square so example a 4 by 4 square has a size 4.
        int switched_matrix = 0;
        switch (id) {
            case 'a':
                switched_matrix = 3;
                break;
            case 'b':
                switched_matrix = 2;
                break;
            case 'c':
                switched_matrix = 1;
                break;
        }
        for (int i = 0; i < size; i++) {
            int temp = magicSquare[i][switched_matrix];
            magicSquare[i][switched_matrix] = magicSquare[i][0];
            magicSquare[i][0] = temp;
        }
        //PrintMagicSqaure('a', true, size);
    }

    public Integer[][] GetMagicSquare() {
        return magicSquare;
    }

    public void PrintMagicSqaure(char id, boolean printWithLines, int size) {
        // Prints the Magic Square Object.
        // Args:
        //  id: Will be a, b, c, or d and this id will be used to find the magic square state that the implementer
        //  wishes to print.
        //  printWithLines: boolean value that detrimines if magic sqaure will be printed with our without lines.
        //GenerateMagicSquareState(id, size);
        if (printWithLines) {
            String lines = new String(new char[size]).replace("\0", " ---");
            for (int i = 0; i < size; i++) {
                System.out.println(lines);
                System.out.print("|");
                for (int j = 0; j < size; j++) {
                    String spaces;
                    if (magicSquare[i][j] / 10 == 0)
                        System.out.print("  " + magicSquare[i][j] + " ");
                    else if (magicSquare[i][j] / 100 == 0)
                        System.out.print(" " + magicSquare[i][j] + " ");
                    else
                        System.out.print(magicSquare[i][j] + " ");
                }
                System.out.println("|");
            }
            System.out.println(lines);
        }
        else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    String spaces;
                    if (magicSquare[i][j] / 10 == 0)
                        System.out.print("  " + magicSquare[i][j] + " ");
                    else if (magicSquare[i][j] / 100 == 0)
                        System.out.print(" " + magicSquare[i][j] + " ");
                    else
                        System.out.print(magicSquare[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
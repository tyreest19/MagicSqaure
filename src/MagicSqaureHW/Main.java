package MagicSqaureHW;
import java.util.*;
import java.lang.*;

public class Main {

    public static int GetUserEnteredInt() {
        // Prompts user to enter a int then returns the int.
        boolean userEnterValidInt = false;
        String userEnterNumber;
        do {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter in the number of sides: ");
            userEnterNumber = reader.next();
            userEnterValidInt = userEnterNumber.matches("^-?\\d+$");
            if (userEnterValidInt)
                userEnterValidInt = IsValidNumberOfSides(Integer.parseInt(userEnterNumber));
        } while (!userEnterValidInt);
        return Integer.parseInt(userEnterNumber);
    }

    public static char GetUserEnteredChar() {
        // Prompts user to enter a char then returns the char.
        boolean userEnterValidChar = false;
        char userEnterChar;
        do {
            userEnterValidChar = true;
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter (a-d) to get the state of the Magic Square. Also a is the default so if the Magic " +
                    "Square Appears wrong please select a.\nThe issue is not with my Magic Sqaure Generation but it is with " +
                    "Switching the states of my magic square.\nFor example, a maybe correct but c may not be: ");
            String validateLength = reader.next();
            userEnterChar = validateLength.charAt(0);
            if (!(userEnterChar >= 'a' && userEnterChar <= 'd' && validateLength.length() == 1)) {
                userEnterValidChar = false;
                System.out.println("Please enter the letter a, b, c, or d.");
            }
        } while (!userEnterValidChar);
        return userEnterChar;
    }
    public static boolean ContinueProgram() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter yes to make another magic square and no to end the program: ");
        String userInput = reader.next().toLowerCase();
        boolean valid_input;
        do
        {
            valid_input = true;

            if (!(userInput.equals("yes") || userInput.equals("no")))
            {
                System.out.println("please enter yes or no. ");
                reader = new Scanner(System.in);  // Reading from System.in
                System.out.println("Enter yes to make another magic square and no to end the program: ");
                userInput = reader.next().toLowerCase();
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

    public static void main(String args[]) {
        boolean createNewSquare;
        int sidesOfMagicSqaure;
        char magicSquareState;
        boolean continue_program;
        do {
            sidesOfMagicSqaure = GetUserEnteredInt();
            if (sidesOfMagicSqaure != 1)
                magicSquareState = GetUserEnteredChar();
            else
                magicSquareState = 'a';
            MagicSquare magicSquare = new MagicSquare();
            magicSquare.GenerateMagicSquare(sidesOfMagicSqaure);
            magicSquare.GenerateMagicSquareState(magicSquareState, sidesOfMagicSqaure);
            magicSquare.PrintMagicSqaure(true, sidesOfMagicSqaure);
            continue_program = ContinueProgram();
        } while (continue_program);
    }
}

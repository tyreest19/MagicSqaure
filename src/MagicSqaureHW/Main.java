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
        } while (!userEnterValidInt);
        return Integer.parseInt(userEnterNumber);
    }

    public static char GetUserEnteredChar() {
        // Prompts user to enter a char then returns the char.
        boolean userEnterValidInt = false;
        char userEnterChar;
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter (a-d) to get the state of the board: ");
        userEnterChar = reader.next().charAt(0);
        return userEnterChar;
    }

    public static boolean IsValidNumberOfSides(int userEnterNumber) {
        // Validates whether the user enter a valid number of sides for the magic Square.
        // Args:
        //  userEnterNumber: Number of sides that the user choose.
        boolean wasNumberValid;
        if (userEnterNumber == 2) {
            System.out.println("Not possible.");
            wasNumberValid = false;
        }
        if (userEnterNumber == 6 || userEnterNumber == 10 || userEnterNumber == 14 || userEnterNumber > 17 ||
                userEnterNumber < 1) {
            System.out.println("Don't press your luck");
            wasNumberValid = false;
        }
        return true;
    }

    public static void main(String args[]) {
        boolean createNewSquare;
        int sidesOfMagicSqaure;
        char magicSquareState;
        sidesOfMagicSqaure = GetUserEnteredInt();
        magicSquareState = GetUserEnteredChar();
        MagicSquare magicSquare = new MagicSquare();
        magicSquare.GenerateMagicSquareState(sidesOfMagicSqaure);
        magicSquare.PrintMagicSqaure(magicSquareState, true, sidesOfMagicSqaure);
    }
}


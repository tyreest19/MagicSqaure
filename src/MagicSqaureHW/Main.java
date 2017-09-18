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

    public static void main(String args[]) throws MagicSquareStateDoesNotExist {
//        boolean createNewSquare;
//        do {
//            createNewSquare = false;
//            int sidesOfMagicSqaure = GetUserEnteredInt();
//
//            if (IsValidNumberOfSides(sidesOfMagicSqaure))
//                break;
//            break;
//        } while (createNewSquare);
        MagicSquare magicSquare = new MagicSquare();
        magicSquare.GenerateMagicSquareState('a', 3);
        magicSquare.PrintMagicSqaure('a', true);
    }
}


package MagicSqaureHW;
import java.util.*;
import java.lang.*;

public class MagicSquare {

     Map<Character, Integer[][]> magicSquareStates = new HashMap<Character, Integer[][]>();

    public void GenerateMagicSquareState(char id, int sides) {
        if (!magicSquareStates.containsKey(id)) {
            // Generates a magic square state for the id that they implementer enter.
            // Args:
            //  id: Will be a, b, c, or d and this id will be used to generate magic square state.
            if (sides % 2 != 0) {
                Integer matrix[][] = new Integer[sides][sides];
                int x_coordinate = sides / 2;
                int y_coordinate = 0;
                matrix[y_coordinate][x_coordinate] = 1;
                for (int i = 2; i <= sides * sides; i++) {
                    // move up formula = (current location - (sides + 1)) % sides
                    // move to the right = (current location + 1) % sides
                    // move down formula = (current location + (sides + 1)) % sides
                    int potential_y_coordinate = Math.floorMod((y_coordinate - (sides + 1)), sides);
                    int potential_x_coordinate = (x_coordinate + 1) % sides;
                    if (matrix[potential_y_coordinate][potential_x_coordinate] == null) {
                        x_coordinate = potential_x_coordinate;
                        y_coordinate = potential_y_coordinate;
                        matrix[y_coordinate][x_coordinate] = i;
                    }
                    else {
                        y_coordinate = (y_coordinate  + (sides + 1)) % sides;
                        matrix[y_coordinate][x_coordinate] = i;
                    }
                }
                magicSquareStates.put(id, matrix);
            }
        }
    }

    public Integer[][] GetMagicSquareState(char id) throws MagicSquareStateDoesNotExist {
        // Returns ArrayList that represents Magic Sqaure.
        // Args:
        //  id: Will be a, b, c, or d and this id will be used to find the magic square state that the implementer
        //  wishes to return.
        if (!magicSquareStates.containsKey(id))
            throw new MagicSquareStateDoesNotExist("Magic Sqaure State does not exist!");
        else
            return magicSquareStates.get(id);
    }

    public void PrintMagicSqaure(char id, boolean printWithLines) throws MagicSquareStateDoesNotExist {
        // Prints the Magic Square Object.
        // Args:
        //  id: Will be a, b, c, or d and this id will be used to find the magic square state that the implementer
        //  wishes to print.
        //  printWithLines: boolean value that detrimines if magic sqaure will be printed with our without lines.
        int size = magicSquareStates.get(id)[0].length;
        if (!magicSquareStates.containsKey(id))
            throw new MagicSquareStateDoesNotExist("Attempted to print a Magic Sqaure State that does not exist!");
        else if (printWithLines) {
            String lines = new String(new char[size]).replace("\0", " ---");
            for (int i = 0; i < size; i++) {
                System.out.println(lines);
                System.out.print("|");
                for (int j = 0; j < size; j++) {
                    String spaces;
                    if (magicSquareStates.get(id)[i][j] / 10 == 0)
                        System.out.print("  " + magicSquareStates.get(id)[i][j] + " ");
                    else if (magicSquareStates.get(id)[i][j] / 100 == 0)
                        System.out.print(" " + magicSquareStates.get(id)[i][j] + " ");
                    else
                        System.out.print(magicSquareStates.get(id)[i][j] + " ");
                }
                System.out.println("|");
            }
            System.out.println(lines);
        }
        else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    String spaces;
                    if (magicSquareStates.get(id)[i][j] / 10 == 0)
                        System.out.print("  " + magicSquareStates.get(id)[i][j] + " ");
                    else if (magicSquareStates.get(id)[i][j] / 100 == 0)
                        System.out.print(" " + magicSquareStates.get(id)[i][j] + " ");
                    else
                        System.out.print(magicSquareStates.get(id)[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
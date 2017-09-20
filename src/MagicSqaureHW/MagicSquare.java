package MagicSqaureHW;
import java.util.*;
import java.lang.*;

public class MagicSquare {

    Integer[][] magicSquare = new Integer[17][17];

    public void GenerateMagicSquareState(int size) {
        if (size % 2 != 0)
            magicSquare = GenerateOddMagicSqaure(size);
        else if (size % 4 != 0)
            magicSquare = GenerateSignleMagicSqaure(size);
        else
            magicSquare = GenerateDoubleMagicSqaure(size);

        }

    private static Integer[][] GenerateOddMagicSqaure(int size) {
        Integer matrix[][] = new Integer[size][size];
        int x_coordinate = size / 2;
        int y_coordinate = 0;
        matrix[y_coordinate][x_coordinate] = 1;
        for (int i = 2; i <= size * size; i++) {
            // move up formula = (current location - (size + 1)) % size
            // move to the right = (current location r + 1) % size
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

    private static Integer[][]  GenerateDoubleMagicSqaure(final int side) {
        // Generates a magic squares for even numbers that are divisible by 4
        // pattern of count-up vs count-down zones
        int bits = 0b1001_0110_0110_1001;
        int size = side * side;
        int mult = size / 4;  // how many multiples of 4

        Integer[][] result = new Integer[size][size];

        for (int r = 0, i = 0; r < size; r++) {
            for (int c = 0; c < size; c++, i++) {
                int bitPos = c / mult + (r / mult) * 4;
                result[r][c] = (bits & (1 << bitPos)) != 0 ? i + 1 : size - i;
            }
        }
        return result;
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
            int temp = magicSquare[switched_matrix][i];
            magicSquare[switched_matrix][i] = magicSquare[0][i];
            magicSquare[0][i] = temp;
        }
    }

    public void PrintMagicSqaure(char id, boolean printWithLines, int size) {
        // Prints the Magic Square Object.
        // Args:
        //  id: Will be a, b, c, or d and this id will be used to find the magic square state that the implementer
        //  wishes to print.
        //  printWithLines: boolean value that detrimines if magic sqaure will be printed with our without lines.
        GenerateMagicSquareState(id, size);
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
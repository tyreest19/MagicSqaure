package MagicSqaureHW;

import java.util.*;
import java.lang.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class MagicSquareTest {

    @Test
    public void ValidateMagicSquare() throws Exception {
        //int msgicConstant = n[((n*n)+1)/2]; //sides * [sides^2+1)/2]
        for (int i = 4; i <= 17; i++) {
            if (!(i == 2|| i == 6 || i == 10 || i == 14 || i == 1)) {
                MagicSquare magicSquare = new MagicSquare();
                magicSquare.GenerateMagicSquare(i);
                assertTrue(VerifyRow(magicSquare.GetMagicSquare(), i));
                assertTrue(VerifyDiagonalRow(magicSquare.GetMagicSquare(), i));
            }
        }
    }

    private boolean VerifyRow(Integer[][] magicSquare, int size) {
        int magicConstant = GenerateMagicConstant(size);
        for(int i = 0; i < size; i++) {
            int sumOfRow = 0;
            for (int j = 0; j < size; j++) {
               sumOfRow += magicSquare[i][j];
           }
           if (sumOfRow != magicConstant)
               return false;
        }
        return true;
    }

    private boolean VerifyDiagonalRow(Integer[][] magicSquare, int size) {
        int magicConstant = GenerateMagicConstant(size);
        int x_coordinate = 0;
        int y_coordinate = 0;
        int sumOfRow = 0;
        for(int i = 0; i < size; i++) {
            // move to the right = (current location + 1) % size
            // move down formula = (current location  + (size + 1)) % size
            y_coordinate = (y_coordinate  + (size + 1)) % size;
            x_coordinate = (x_coordinate + 1) % size;
            sumOfRow += magicSquare[y_coordinate][x_coordinate];
        }
        if (sumOfRow != magicConstant)
            return false;
        x_coordinate = size - 1;
        y_coordinate = 0;
        sumOfRow = magicSquare[y_coordinate][x_coordinate];
        for(int i = 0; i < size - 1; i++) {
            // move to the left = (current location - 1) % size
            // move down formula = (current location  + (size + 1)) % size
            y_coordinate = (y_coordinate  + (size + 1)) % size;
            x_coordinate = (x_coordinate - 1) % size;
            sumOfRow += magicSquare[y_coordinate][x_coordinate];
        }
        if (sumOfRow != magicConstant)
            return false;
        return true;
    }

    private int GenerateMagicConstant(int size) {
        int magicConstant = 0;
        if (size % 2 != 0)
            magicConstant = (size *((size * size) + 1))/2; //Odd formula: [n * (n^2 + 1)] / 2
        else if (((size % 4 != 0) && (size > 6)))
            magicConstant = size * (((size * size)+1)/2); //Singly Even formula: n[(n^2+1)/2]
        else
            magicConstant = (size *((size * size) + 1))/2; //Doubly Even formula: [4 * (4^2 + 1)] / 2
        return magicConstant;
    }

    @Test
    public void IsMagicSquareStateUnique() {
        for (int i = 3; i <= 17; i++) {
            MagicSquare magicSquare = new MagicSquare();
            magicSquare.GenerateMagicSquare(i);
            Integer[][] originalMagicSquare = magicSquare.GetMagicSquare();
            if (!(i == 6 || i == 10 || i == 14)) {
                for (char testedState = 'b'; testedState <= 'd'; testedState++) {
                    magicSquare.GenerateMagicSquareState(testedState, i);
                    assertFalse(Arrays.deepToString(originalMagicSquare) == Arrays.deepToString(magicSquare.GetMagicSquare()));
                }
            }
        }
    }

    @Test
    public void VerifyNewSquareStateIsMagicSquare() {
        for (int i = 3; i <= 17; i++) {
            MagicSquare magicSquare = new MagicSquare();
            magicSquare.GenerateMagicSquare(i);
            if (!(i == 2 || i == 6 || i == 10 || i == 14 || i == 1)) {
                for (char testedState = 'b'; testedState <= 'd'; testedState++) {
                    magicSquare.GenerateMagicSquareState(testedState, i);
                    assertTrue(VerifyRow(magicSquare.GetMagicSquare(), i));
                    assertTrue(VerifyDiagonalRow(magicSquare.GetMagicSquare(), i));
                }
            }
        }
    }
}
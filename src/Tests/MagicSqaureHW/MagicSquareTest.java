package MagicSqaureHW;

import java.util.*;
import java.lang.*;

import com.sun.source.tree.AssertTree;
import org.junit.Test;

import static org.junit.Assert.*;

public class MagicSquareTest {

    @Test
    public void ValidateMagicSquare() throws Exception {
        //int msgicConstant = n[((n*n)+1)/2]; //sides * [sides^2+1)/2]
        for (int i = 0; i < 17; i++) {
            if (!(i == 2|| i == 6 || i == 10 || i == 14 )) {
                MagicSquare magicSquare = new MagicSquare();
                magicSquare.GenerateMagicSquareState(i);
                assertTrue(IsRowAMagicSquare(magicSquare.GetMagicSquare(), i));
            }
        }
    }

    private boolean IsRowAMagicSquare(Integer[][] magicSquare, int size) {
        int magicConstant;
        if (size % 2 == 0)
            magicConstant = size * (((size * size)+1)/2); //Even formula: n[(n^2+1)/2]
        else
            magicConstant = (size *((size * size) + 1))/2; //Odd formula: [n * (n^2 + 1)] / 2
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
}
package MagicSqaureHW;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void isValidNumberOfSides() throws Exception {
        Main main = new Main();
        for (int testInput = 0; testInput < 30; testInput++) {
            if (testInput == 2|| testInput == 6 || testInput == 10 || testInput == 14 || testInput > 17 ||
                    testInput < 1) {
                assertEquals(false, main.IsValidNumberOfSides(testInput));
            }
            else
                assertEquals(true, main.IsValidNumberOfSides(testInput));
        }
    }

}
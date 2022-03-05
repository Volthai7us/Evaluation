/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package HW;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void testArrayListIsNull() {
        App classUnderTest = new App();

        int[] coef = null;
        int nTh = 2;
        char mode = 'd';

        assertNull(classUnderTest.evaluate(coef, nTh, mode));
    }
    @Test void testModeIsUnvalid() {
        App classUnderTest = new App();

        int[] coef = new int[]{1, 2, 3};
        int nTh = 2;
        char mode = 'a';

        assertNull(classUnderTest.evaluate(coef, nTh, mode));
    }

    @Test void testFirstDerivative() {
        App classUnderTest = new App();

        int[] coef = new int[]{5, 2, 3};
        int nTh = 1;
        char mode = 'd';

        int expected[] = {2, 6};

        assertArrayEquals(expected, classUnderTest.evaluate(coef, nTh, mode));
    }
}

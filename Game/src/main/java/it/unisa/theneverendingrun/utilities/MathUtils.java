package it.unisa.theneverendingrun.utilities;

public class MathUtils {

    /**
     * Computes the sum of (i^2) from i=1 to n with a direct formula
     *
     * @param n the upper bound of summation
     * @return the sum of (i^2) from i=1 to n
     */
    public static int sumSquares(int n) {
        return n * (n + 1) * (2 * n + 1) / 6;
    }
}

package it.unisa.theneverendingrun.utilities;

public final class MathUtils {

    public static final double DELTA = 1e-4;

    /**
     * Computes the sum of (i^2) from i=1 to n with a direct formula
     *
     * @param n the upper bound of summation
     * @return the sum of (i^2) from i=1 to n
     */
    public static float sumSquares(float n) {
        return n * (n + 1) * (2 * n + 1) / 6;
    }
}

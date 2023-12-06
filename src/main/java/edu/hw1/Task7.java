package edu.hw1;

/**
 * The {@code Task7} class provides methods {@link Task7#rotateLeft(int, int)} and
 * {@link Task7#rotateRight(int, int)} which performs cyclic bit shift operations.
 */
public final class Task7 {
    private static final int BIT_LENGTH = 32;
    private static final String ILLEGAL_ARGUMENT_MESSAGE = "The input integers must be greater than 0.";

    private Task7() {
    }

    /**
     * Performs a left or right cyclic shift on a given integer.
     *
     * @param n     the integer to be shifted
     * @param shift the number of positions to shift
     * @param left  {@code true} for left shift, {@code false} for right shift
     * @return the result of the cyclic shift
     * @throws IllegalArgumentException if 'n' or 'shift' is less than 0
     */
    private static int rotate(int n, int shift, boolean left) {
        if (n < 0 || shift < 0) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        int s = shift;
        int leadingZerosCount = Integer.numberOfLeadingZeros(n);
        int bitLength = BIT_LENGTH - leadingZerosCount;
        s = s % bitLength;

        if (left) {
            return ((n << shift) | (n >>> (bitLength - s))) & ((1 << bitLength) - 1);
        } else {
            return ((n >>> shift) | (n << (bitLength - s))) & ((1 << bitLength) - 1);
        }
    }

    /**
     * Performs a left cyclic shift on a given integer.
     *
     * @param n     the integer to be shifted
     * @param shift the number of positions to shift
     * @return the result of the left cyclic shift
     * @throws IllegalArgumentException if 'n' or 'shift' is less than 0
     */
    public static int rotateLeft(int n, int shift) {
        return rotate(n, shift, true);
    }

    /**
     * Performs a right cyclic shift on a given integer.
     *
     * @param n     the integer to be shifted
     * @param shift the number of positions to shift
     * @return the result of the right cyclic shift
     * @throws IllegalArgumentException if 'n' or 'shift' is less than 0
     */
    public static int rotateRight(int n, int shift) {
        return rotate(n, shift, false);
    }
}

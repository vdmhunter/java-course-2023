package edu.hw1;

/**
 * The Task7 class provides methods {@link Task7#rotateLeft(int, int)} and
 * {@link Task7#rotateRight(int, int)} which performs cyclic bit shift operations.
 */
public final class Task7 {
    private final static int BIT_LENGTH = 32;
    private final static String ILLEGAL_ARGUMENT_MESSAGE = "The input integers must be greater than 0.";

    private Task7() {
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
        if (n < 0 || shift < 0) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        int s = shift;
        int leadingZerosCount = Integer.numberOfLeadingZeros(n);
        int bitLength = BIT_LENGTH - leadingZerosCount;
        s = s % bitLength;

        return ((n << shift) | (n >>> (bitLength - s))) & ((1 << bitLength) - 1);
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
        if (n < 0 || shift < 0) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        int s = shift;
        int leadingZerosCount = Integer.numberOfLeadingZeros(n);
        int bitLength = BIT_LENGTH - leadingZerosCount;
        s = s % bitLength;

        return ((n >>> shift) | (n << (bitLength - s))) & ((1 << bitLength) - 1);
    }
}

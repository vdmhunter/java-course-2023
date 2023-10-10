package edu.hw1;

/**
 * The Task2 class provides a method {@link Task2#countDigits(int)}
 * which returns the number of digits in the decimal form of a number.
 */
public final class Task2 {
    private final static int BASE = 10;

    private Task2() {
    }

    /**
     * Calculates and returns the number of digits in the decimal representation of the input integer.
     *
     * @param num The integer whose digits are to be counted.
     * @return The number of digits in the decimal representation of 'num'.
     */
    public static int countDigits(int num) {
        if (num == 0) {
            return 1;
        }

        int result = 0;
        int n = num;

        while (n != 0) {
            n /= BASE;
            result++;
        }

        return result;
    }
}

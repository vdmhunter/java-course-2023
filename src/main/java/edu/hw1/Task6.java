package edu.hw1;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * The Task6 class provides a method {@link Task6#countK(int)}
 * which calculates the number of iterations required to reach the Kaprekar's constant.
 */
public final class Task6 {
    private final static int KAPREKAR_NUMBER = 6174;
    private final static int THOUSAND = 1000;
    private final static Pattern PATTERN;

    private Task6() {
    }

    static {
        PATTERN = Pattern.compile("^(?!(\\d)\\1{3}$)\\d{4}$");
    }

    /**
     * Calculates the number of iterations required to reach the Kaprekar's constant.
     * The input integer must be a four-digit number greater than 1000 and not all digits are the same.
     *
     * @param num The initial number.
     * @return The number of iterations required to reach the Kaprekar's constant.
     * @throws IllegalArgumentException If the input integer is not a four-digit number greater than 1000 or
     *                                  all digits are the same.
     */
    public static int countK(int num) {
        if (num <= THOUSAND || !PATTERN.matcher(String.valueOf(num)).matches()) {
            throw new IllegalArgumentException(
                "The input integer must be a four-digit number greater than 1000 and not all digits are the same.");
        }

        if (num == KAPREKAR_NUMBER) {
            return 0;
        } else {
            int asc = sort(num, true);
            int desc = sort(num, false);

            return 1 + countK(desc - asc);
        }
    }

    /**
     * Method to sort the digits of a number in ascending or descending order.
     *
     * @param num       The number to be sorted.
     * @param ascending A boolean flag indicating the sort order. If true, the number is sorted in ascending order.
     *                  If false, the number is sorted in descending order.
     * @return The sorted number.
     */
    private static int sort(int num, boolean ascending) {
        char[] digits = String.format("%04d", num).toCharArray();
        Arrays.sort(digits);

        if (!ascending) {
            for (int i = 0; i < digits.length / 2; i++) {
                char temp = digits[i];
                digits[i] = digits[digits.length - 1 - i];
                digits[digits.length - 1 - i] = temp;
            }
        }

        return Integer.parseInt(new String(digits));
    }
}

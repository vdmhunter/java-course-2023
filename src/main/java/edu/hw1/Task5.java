package edu.hw1;

/**
 * The Task5 class provides a method {@link Task5#isPalindromeDescendant(int)},
 * which checks if a number or any of its descendants is a palindrome.
 */
public final class Task5 {
    private final static int BASE = 10;

    private Task5() {
    }

    /**
     * Checks if a number or any of its descendants is a palindrome.
     *
     * @param num The input number to be checked. Must be a non-negative integer with an even number of digits.
     * @return true if the number or any of its descendants is a palindrome, false otherwise.
     * @throws IllegalArgumentException if the input number is less than or equal to 0,
     *                                  or if it has an odd number of digits.
     */
    public static boolean isPalindromeDescendant(int num) {
        int n = num;

        if (n <= 0) {
            throw new IllegalArgumentException("The input integer must be greater than 0.");
        }

        if (String.valueOf(n).length() % 2 != 0) {
            throw new IllegalArgumentException("The input integer must have an even number of digits.");
        }

        if (isPalindrome(n)) {
            return true;
        }

        while (n >= BASE) {
            n = descendant(n);

            if (isPalindrome(n)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Calculates the descendant of a number by summing every pair of neighboring digits.
     *
     * @param num The input number.
     * @return The descendant of the input number.
     */
    private static int descendant(int num) {
        int n = num;
        var sb = new StringBuilder();

        while (n >= BASE) {
            int lastDigit = n % BASE;
            n /= BASE;
            int secondLastDigit = n % BASE;
            sb.insert(0, lastDigit + secondLastDigit);
            n /= BASE;
        }

        return Integer.parseInt(sb.toString());
    }

    /**
     * Checks if a number is a palindrome.
     *
     * @param num The input number to be checked.
     * @return true if the number is a palindrome, false otherwise.
     */
    private static boolean isPalindrome(int num) {
        String str = String.valueOf(num);

        if (str.length() > 1) {
            String rev = new StringBuffer(str).reverse().toString();

            return str.equals(rev);
        } else {
            return false;
        }
    }
}

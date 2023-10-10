package edu.hw1;

import java.util.Arrays;
import java.util.Objects;

/**
 * The Task3 class provides a utility method {@link Task3#isNestable(int[], int[])}
 * to check if one array can be nested into another array.
 */
public final class Task3 {
    private Task3() {
    }

    /**
     * Checks if the first array can be nested into the second array.
     * An array a1 can be nested inside an array a2 if:
     * <ul>
     * <li>The minimum value in a1 is greater than the minimum value in a2</li>
     * <li>The maximum value in a1 is less than the maximum value in a2.</li>
     * </ul>
     *
     * @param a1 The first array, must not be null.
     * @param a2 The second array, must not be null.
     * @return true if a1 can be nested inside a2, false otherwise.
     * @throws NullPointerException if either input array is null.
     */
    public static boolean isNestable(int[] a1, int[] a2) {
        Objects.requireNonNull(a1);
        Objects.requireNonNull(a2);

        if (a1.length == 0 || a2.length == 0) {
            return false;
        }

        int minA1 = Arrays.stream(a1).min().getAsInt();
        int maxA1 = Arrays.stream(a1).max().getAsInt();
        int minA2 = Arrays.stream(a2).min().getAsInt();
        int maxA2 = Arrays.stream(a2).max().getAsInt();

        return minA1 > minA2 && maxA1 < maxA2;
    }
}

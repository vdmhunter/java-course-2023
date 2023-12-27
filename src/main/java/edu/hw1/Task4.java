package edu.hw1;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Task4} class provides a method {@link Task4#fixString(String)}
 * to fix strings where every pair of characters are swapped.
 */
public final class Task4 {
    private Task4() {
    }

    /**
     * This method fixes a string by swapping every pair of characters.
     * If the string length is odd, the last character remains in its place.
     *
     * @param str A non-null and not empty string to be fixed.
     * @return A new string where every pair of characters in the input string are swapped.
     * @throws NullPointerException     if the input string is {@code null}.
     * @throws IllegalArgumentException if the input string is empty.
     */
    public static @NotNull String fixString(String str) {
        Objects.requireNonNull(str);

        if (str.isEmpty()) {
            throw new IllegalArgumentException("Input string must not be empty.");
        }

        var length = str.length();
        var result = new StringBuilder(length);

        for (int i = 0; i < length; i += 2) {
            if (i + 1 < length) {
                result.append(str.charAt(i + 1)).append(str.charAt(i));
            } else {
                result.append(str.charAt(i));
            }
        }

        return result.toString();
    }
}

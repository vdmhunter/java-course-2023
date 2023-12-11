package edu.hw5.task6;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code SubsequenceChecker} is a utility class for determining whether a given string 's' is a subsequence of another
 * string 't'.
 * A subsequence is a sequence of characters that appear in the same order but not necessarily consecutively.
 *
 * <p>The class provides a static method {@link #isSubsequence(String, String)} to perform the subsequence
 * check using a regular expression.
 */
public final class SubsequenceChecker {
    private SubsequenceChecker() {
    }

    /**
     * Checks whether the input string 's' is a subsequence of the input string 't'.
     *
     * <p>A subsequence is a sequence of characters that appears in the same order in both strings,
     * but not necessarily consecutively.
     *
     * @param s the potential subsequence
     * @param t the string to check for the subsequence
     * @return {@code true} if 's' is a subsequence of 't', {@code false} otherwise
     * @throws NullPointerException if either 's' or 't' is {@code null}
     */
    public static boolean isSubsequence(String s, String t) {
        Objects.requireNonNull(s);
        Objects.requireNonNull(t);

        Pattern pattern = Pattern.compile(".*" + String.join(".*", s.split("")));
        Matcher matcher = pattern.matcher(t);

        return matcher.find();
    }
}

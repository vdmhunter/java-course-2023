package edu.hw5.task7;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * {@code RegexPatternMatcher} is a utility class that provides methods for matching strings
 * against specific regular expressions.
 *
 * <p>The class contains methods to check various patterns, including matching at least three characters
 * with the third one being '0', matching strings with the same start and end character ('0' or '1'),
 * and matching strings with a length between one and three characters.
 */
public final class RegexPatternMatcher {
    private RegexPatternMatcher() {
    }

    /**
     * Checks if the input string matches the pattern of having at least three characters, with the third one being '0'.
     *
     * @param input the input string to match
     * @return {@code true} if the input matches the pattern, {@code false} otherwise
     * @throws NullPointerException if the input string is {@code null}
     */
    public static boolean matchesAtLeastThreeCharsThirdIsZero(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");

        return pattern.matcher(input).matches();
    }

    /**
     * Checks if the input string matches the pattern of having the same start and end character ('0' or '1').
     *
     * @param input the input string to match
     * @return {@code true} if the input matches the pattern, {@code false} otherwise
     * @throws NullPointerException if the input string is {@code null}
     */
    public static boolean matchesSameStartAndEndChar(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("(^0.*0$|^1.*1$)");

        return pattern.matcher(input).matches();
    }

    /**
     * Checks if the input string matches the pattern of having a length between one and three characters.
     *
     * @param input the input string to match
     * @return {@code true} if the input matches the pattern, {@code false} otherwise
     * @throws NullPointerException if the input string is {@code null}
     */
    public static boolean matchesLengthBetweenOneAndThree(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("^[01]{1,3}$");

        return pattern.matcher(input).matches();
    }
}

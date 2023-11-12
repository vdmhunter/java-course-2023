package edu.hw5.task8;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * {@code RegexPatternMatcher} is a utility class that provides methods for matching strings from the alphabet {0, 1}
 * against specific regular expressions.
 */
public final class RegexPatternMatcher {
    private RegexPatternMatcher() {
    }

    /**
     * Checks if the input string has an odd length and contains only characters '0' or '1'.
     *
     * @param input The input string to be checked.
     * @return {@code true} if the input string has an odd length and contains only '0' or '1', {@code false} otherwise.
     */
    public static boolean matchesOddLength(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("^[01]([01]{2})*$");

        return pattern.matcher(input).matches();
    }

    /**
     * Checks if the input string either starts with '0' and has an odd length or
     * starts with '1' and has an even length.
     *
     * @param input The input string to be checked.
     * @return {@code true} if the conditions are met, {@code false} otherwise.
     */
    public static boolean matchesStartsWithZeroOddLengthOrStartsWithOneEvenLength(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("^0([01]{2})*$|^1[01]([01]{2})*$");

        return pattern.matcher(input).matches();
    }

    /**
     * Checks if the input string has a number of '0's that is a multiple of three.
     *
     * @param input The input string to be checked.
     * @return {@code true} if the number of '0's is a multiple of three, {@code false} otherwise.
     */
    public static boolean matchesZeroMultipleOfThree(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("^((1*+0){3})*+1*+$");

        return pattern.matcher(input).matches();
    }

    /**
     * Checks if the input string is not equal to "11" or "111".
     *
     * @param input The input string to be checked.
     * @return {@code true} if the input string is not "11" or "111", {@code false} otherwise.
     */
    public static boolean matchesNotElevenOrOneHundredEleven(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("^(?!11$|111$)[01]+$");

        return pattern.matcher(input).matches();
    }

    /**
     * Checks if every odd character in the input string is '1'.
     *
     * @param input The input string to be checked.
     * @return {@code true} if every odd character is '1', {@code false} otherwise.
     */
    public static boolean matchesEveryOddCharacterIsOne(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("^(1[01])*1?$");

        return pattern.matcher(input).matches();
    }

    /**
     * Checks if the input string has at least two '0's and no more than one '1'.
     *
     * @param input The input string to be checked.
     * @return {@code true} if the conditions are met, {@code false} otherwise.
     */
    public static boolean matchesAtLeastTwoZerosAndNoMoreThanOneOne(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("^(?=.*0.*0)(?!.*1.*1)[01]*$");

        return pattern.matcher(input).matches();
    }

    /**
     * Checks if there are no consecutive '1's in the input string.
     *
     * @param input The input string to be checked.
     * @return {@code true} if there are no consecutive '1's, {@code false} otherwise.
     */
    public static boolean matchesNoConsecutiveOnes(String input) {
        Objects.requireNonNull(input);
        Pattern pattern = Pattern.compile("^(?!.*11)[01]*$");

        return pattern.matcher(input).matches();
    }
}

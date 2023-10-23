package edu.hw3.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Encoder} class provides utility method {@link Encoder#atbash(String)}
 * for encoding strings using the Atbash cipher.
 */
public final class Encoder {
    private final static int LATIN_ALPHABET_LETTER_COUNT = 26;

    private Encoder() {
    }

    /**
     * Encodes a given string using the Atbash cipher.
     *
     * @param str the string to be encoded
     * @return the encoded string
     * @throws IllegalArgumentException if the input string is empty
     * @throws NullPointerException     if the input string is null
     */
    public static @NotNull String atbash(String str) {
        Objects.requireNonNull(str);

        if (str.isEmpty()) {
            throw new IllegalArgumentException("Input string must not be empty.");
        }

        List<Character> lowercaseAlphabet = new ArrayList<>(LATIN_ALPHABET_LETTER_COUNT);
        List<Character> uppercaseAlphabet = new ArrayList<>(LATIN_ALPHABET_LETTER_COUNT);

        for (char ch = 'a'; ch <= 'z'; ch++) {
            lowercaseAlphabet.add(ch);
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            uppercaseAlphabet.add(ch);
        }

        var result = new StringBuilder(str.length());

        for (char ch : str.toCharArray()) {
            if (isLatinAlphabet(ch)) {
                List<Character> alphabet;

                if (Character.isLowerCase(ch)) {
                    alphabet = lowercaseAlphabet;
                } else {
                    alphabet = uppercaseAlphabet;
                }

                int index = alphabet.indexOf(ch);

                if (index >= 0) {
                    char shiftedChar = alphabet.get(LATIN_ALPHABET_LETTER_COUNT - 1 - index);
                    result.append(shiftedChar);
                }
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    /**
     * Checks if a character is a letter in the Latin alphabet.
     *
     * @param ch the character to be checked
     * @return true if the character is a letter in the Latin alphabet, false otherwise
     */
    private static boolean isLatinAlphabet(char ch) {
        return Pattern.matches("[a-zA-Z]", Character.toString(ch));
    }
}

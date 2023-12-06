package edu.hw8.task3;

import edu.common.Generated;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code CharacterSubsetPasswordGenerator} class generates passwords using a subset of characters
 * from a given character set. It allows generating passwords within a specified range of indices
 * in the character set, iterating through all possible combinations.
 */
public final class CharacterSubsetPasswordGenerator {
    private final String characterSet;
    private final int[] currentIndex;
    private final int upperLimit;

    /**
     * Constructs a {@code CharacterSubsetPasswordGenerator} with the specified character set,
     * starting index, upper limit, and the desired password length.
     *
     * @param characterSet   The set of characters to be used for password generation.
     * @param startIndex     The starting index within the character set.
     * @param upperLimit     The upper limit for the indices in the character set.
     * @param passwordLength The length of the passwords to be generated.
     */
    CharacterSubsetPasswordGenerator(String characterSet, int startIndex, int upperLimit, int passwordLength) {
        this.characterSet = characterSet;
        this.currentIndex = new int[passwordLength];
        this.currentIndex[0] = startIndex;
        this.upperLimit = upperLimit;
    }

    /**
     * Generates the next password in the sequence.
     *
     * @return The generated password as a string.
     * @throws NoSuchElementException If there are no more passwords to generate.
     */
    public @NotNull String generatePassword() {
        checkPasswordAvailability();

        var password = new StringBuilder();

        for (int index : currentIndex) {
            password.append(characterSet.charAt(index));
        }

        incrementIndices();
        return password.toString();
    }

    /**
     * Checks if there are more passwords that can be generated.
     *
     * @return {@code true} if there are more passwords; {@code false} otherwise.
     */
    public boolean hasMorePasswords() {
        return currentIndex[0] != upperLimit + 1;
    }

    /**
     * Increments the current indices to prepare for the generation of the next password.
     */
    @Generated
    private void incrementIndices() {
        for (int i = currentIndex.length - 1; i >= 0; i--) {
            currentIndex[i]++;

            if (currentIndex[i] == characterSet.length()) {
                if (i == 0) {
                    currentIndex[i] = characterSet.length();
                } else {
                    currentIndex[i] = 0;
                }
            } else {
                break;
            }
        }
    }

    /**
     * Checks if there is at least one more password to generate.
     * Throws an exception if no more passwords are available.
     *
     * @throws NoSuchElementException If there are no more passwords to generate.
     */
    @Generated
    private void checkPasswordAvailability() {
        if (!hasMorePasswords()) {
            throw new NoSuchElementException("No more passwords available.");
        }
    }
}

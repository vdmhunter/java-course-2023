package edu.hw8.task3;

import edu.common.Generated;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code AlphabeticPasswordGenerator} class generates passwords using an alphabetic character set.
 * It allows generating passwords of a specified length, iterating through all possible combinations
 * of characters in the given character set.
 */
public final class AlphabeticPasswordGenerator {
    private final String characterSet;
    private final int[] currentIndex;
    private long remainingPasswordCount;

    /**
     * Constructs an {@code AlphabeticPasswordGenerator} with the specified character set
     * and the desired password length.
     *
     * @param characterSet   The set of characters to be used for password generation.
     * @param passwordLength The length of the passwords to be generated.
     */
    public AlphabeticPasswordGenerator(String characterSet, int passwordLength) {
        this.characterSet = characterSet;
        this.currentIndex = new int[passwordLength];

        int characterSetLength = this.characterSet.length();
        long result = 1;

        for (int i = 0; i < passwordLength; i++) {
            result *= characterSetLength;
        }

        this.remainingPasswordCount = result;
    }

    /**
     * Generates the next password in the sequence.
     *
     * @return The generated password as a string.
     * @throws NoSuchElementException If there are no more passwords to generate.
     */
    public @NotNull String generate() {
        checkNextPasswordAvailability();

        var password = new StringBuilder();

        for (int index : currentIndex) {
            password.append(characterSet.charAt(index));
        }

        decrementRemainingPasswordCount();

        return password.toString();
    }

    /**
     * Checks if there are more passwords that can be generated.
     *
     * @return {@code true} if there are more passwords; {@code false} otherwise.
     */
    public boolean hasNextPassword() {
        return remainingPasswordCount != 0;
    }

    /**
     * Decrements the count of remaining passwords and updates the current index array
     * to prepare for the generation of the next password.
     */
    @Generated
    private void decrementRemainingPasswordCount() {
        remainingPasswordCount--;

        for (int i = currentIndex.length - 1; i >= 0; i--) {
            currentIndex[i] = (currentIndex[i] + 1) % characterSet.length();

            if (currentIndex[i] != 0) {
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
    private void checkNextPasswordAvailability() {
        if (!hasNextPassword()) {
            throw new NoSuchElementException("No more passwords. Ensure hasNextPassword() "
                + "is checked before calling generate().");
        }
    }
}

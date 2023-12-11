package edu.hw5.task4;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code PasswordValidator} is a utility class for validating whether a password contains
 * certain required characters such as ~, !, @, #, $, %, ^, &, *, or |.
 *
 * <p>The validation is performed using a regular expression pattern.
 */
public final class PasswordValidator {
    private static final Pattern PATTERN = Pattern.compile(".*[~!@#$%^&*|].*");

    private PasswordValidator() {
    }

    /**
     * Validates whether the given password contains the required characters.
     *
     * <p>The required characters are ~, !, @, #, $, %, ^, &, *, or |.
     *
     * @param password the password to validate
     * @return {@code true} if the password contains at least one required character, {@code false} otherwise
     * @throws NullPointerException if the input password is {@code null}
     */
    public static boolean containsRequiredCharacters(String password) {
        Objects.requireNonNull(password);

        Matcher matcher = PATTERN.matcher(password);

        return matcher.matches();
    }
}

package edu.hw5.task5;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code CarPlateValidator} is a utility class for validating Russian car plate numbers.
 */
public final class CarPlateValidator {
    private static final Pattern PATTERN = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");

    private CarPlateValidator() {
    }

    /**
     * Validates whether the given car plate number follows the standard Russian car plate format.
     *
     * @param number the car plate number to validate
     * @return {@code true} if the car plate number follows the standard Russian format, {@code false} otherwise
     * @throws NullPointerException if the input car plate number is {@code null}
     */
    public static boolean isCorrectRussianCarPlate(String number) {
        Objects.requireNonNull(number);

        Matcher matcher = PATTERN.matcher(number);

        return matcher.matches();
    }
}

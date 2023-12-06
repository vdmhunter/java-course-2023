package edu.hw3.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for converting integer values to Roman numerals.
 */
public final class IntegerToRomanConverter {
    private static final List<Pair> INT_TO_ROMAN = new ArrayList<>();
    private static final int MIN_ALLOWED_VALUE = 1;
    private static final int MAX_ALLOWED_VALUE = 3999;
    private static final String ILLEGAL_ARGUMENT_MESSAGE = "The input integer must be between 1 and 3999.";

    static {
        // CHECKSTYLE:OFF: Disable MagicNumber check
        INT_TO_ROMAN.addAll(Arrays.asList(
            new Pair(1000, "M" ),
            new Pair(900 , "CM"),
            new Pair(500 , "D" ),
            new Pair(400 , "CD"),
            new Pair(100 , "C" ),
            new Pair(90  , "XC"),
            new Pair(50  , "L" ),
            new Pair(40  , "XL"),
            new Pair(10  , "X" ),
            new Pair(9   , "IX"),
            new Pair(5   , "V" ),
            new Pair(4   , "IV"),
            new Pair(1   , "I" )
        ));
        // CHECKSTYLE:ON: Enable MagicNumber check
    }

    private IntegerToRomanConverter() {
    }

    /**
     * Converts an integer to its Roman numeral representation.
     *
     * @param num The integer to be converted to a Roman numeral.
     * @return The Roman numeral representation of the input integer.
     * @throws IllegalArgumentException if the input integer is outside the allowed range [1 to 3999].
     */
    public static @NotNull String intToRoman(int num) {
        if (num < MIN_ALLOWED_VALUE || num > MAX_ALLOWED_VALUE) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }

        var result = new StringBuilder();
        int n = num;
        int i = 0;

        while (n > 0) {
            while (n >= INT_TO_ROMAN.get(i).value()) {
                n -= INT_TO_ROMAN.get(i).value();
                result.append(INT_TO_ROMAN.get(i).roman());
            }

            i++;
        }

        return result.toString();
    }
}

package edu.hw3.task4;

/**
 * The {@code Pair} record represents a pair of values, consisting of an integer value
 * and its corresponding Roman numeral.
 *
 * <p>The integer value must be between 1 and 3999 (inclusive) to ensure compatibility with Roman numerals.
 * The Roman numeral is a string representation of the corresponding integer value.
 *
 * @param value The integer value of the pair, representing a numerical quantity.
 * @param roman The Roman numeral representation corresponding to the integer value.
 */
record Pair(int value, String roman) {
}

package edu.hw6.task6;

// https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797#256-colors

/**
 * The {@code Color} enum represents ANSI escape codes for text color in the console.
 * It provides a range of color constants for rendering text in different colors.
 */
public enum Color {
    GREEN("\u001b[38;5;046m"), //0
    BLUE("\u001b[38;5;33m"),   //1
    RED("\u001b[38;5;124m");   //2

    private final String value;

    /**
     * Constructs a {@code Color} enum constant with the provided ANSI escape code value.
     *
     * @param value The ANSI escape code for the text color represented by the constant.
     */
    Color(String value) {
        this.value = value;
    }

    /**
     * Get the ANSI escape code value for the text color represented by the enum constant.
     *
     * @return The ANSI escape code for the text color.
     */
    public String getValue() {
        return this.value;
    }
}

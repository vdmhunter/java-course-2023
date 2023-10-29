package edu.hw4;

/**
 * The {@code Animal} class represents various animals and their attributes.
 */
public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    Boolean bites
) {
    /**
     * An enumeration representing the type of animal.
     */
    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    /**
     * An enumeration representing the sex of the animal.
     */
    public enum Sex {
        M, F
    }

    /**
     * Calculates the number of paws the animal has based on its type.
     *
     * @return The number of paws for the animal.
     */
    public int paws() {
        // CHECKSTYLE:OFF: Disable MagicNumber check
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
        // CHECKSTYLE:ON: Enable MagicNumber check
    }
}

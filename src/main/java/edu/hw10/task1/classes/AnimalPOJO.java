package edu.hw10.task1.classes;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

/**
 * The {@code AnimalPOJO} class represents a Plain Old Java Object (POJO) for an animal.
 * It encapsulates information about the name and the number of paws of the animal.
 * This class is designed with annotations to enforce constraints on its constructor parameters.
 */
public class AnimalPOJO {
    protected String name;
    protected int pawsCount;

    /**
     * Constructs a new {@code AnimalPOJO} with the specified name and number of paws.
     *
     * @param name      The name of the animal. Must not be {@code null}.
     * @param pawsCount The number of paws of the animal. Must be between 2 and 40 (inclusive).
     */
    public AnimalPOJO(@NotNull String name, @Min(2) @Max(40) int pawsCount) {
        this.name = name;
        this.pawsCount = pawsCount;
    }

    /**
     * Gets the name of the animal.
     *
     * @return The name of the animal.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of paws of the animal.
     *
     * @return The number of paws of the animal.
     */
    public int getPawsCount() {
        return pawsCount;
    }
}

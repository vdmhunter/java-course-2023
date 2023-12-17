package edu.hw10.task1.classes;

import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

/**
 * The {@code AnimalRecord} class represents an immutable record (Java 16 feature) for an animal.
 * It encapsulates information about the name and the number of paws of the animal.
 * This class is designed with annotations to enforce constraints on its record components.
 */
public record AnimalRecord(
    @NotNull
    String name,
    @Min(2)
    int pawsCount) {
}

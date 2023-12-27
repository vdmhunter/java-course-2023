package edu.hw10.task1.classes;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.NotNull;

/**
 * The {@code AnimalWithFactoryMethod} class represents an animal with a factory method for creation.
 * It encapsulates information about the name and the number of paws of the animal.
 * This class is designed with annotations to enforce constraints on its properties.
 */
public final class AnimalWithFactoryMethod {
    private String name;
    private int pawsCount;

    /**
     * Constructs a new {@code AnimalWithFactoryMethod} with default values.
     * The use of the default constructor is restricted.
     */
    private AnimalWithFactoryMethod() {
    }

    /**
     * Creates a new instance of {@code AnimalWithFactoryMethod} with the specified name and number of paws.
     *
     * @param name      The name of the animal. Must not be {@code null}.
     * @param pawsCount The number of paws of the animal. Must be less than or equal to 40.
     * @return A new instance of {@code AnimalWithFactoryMethod}.
     */
    public static @org.jetbrains.annotations.NotNull AnimalWithFactoryMethod create(
        @NotNull String name,
        @Max(40) int pawsCount
    ) {
        AnimalWithFactoryMethod animal = new AnimalWithFactoryMethod();
        animal.setName(name);
        animal.setPawsCount(pawsCount);

        return animal;
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
     * Sets the name of the animal.
     *
     * @param name The name of the animal. Must not be {@code null}.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the number of paws of the animal.
     *
     * @return The number of paws of the animal.
     */
    public int getPawsCount() {
        return pawsCount;
    }

    /**
     * Sets the number of paws of the animal.
     *
     * @param pawsCount The number of paws of the animal. Must be less than or equal to 40.
     */
    public void setPawsCount(int pawsCount) {
        this.pawsCount = pawsCount;
    }
}

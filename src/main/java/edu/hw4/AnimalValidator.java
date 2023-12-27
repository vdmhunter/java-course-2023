package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code AnimalValidator} class provides a set of static methods for validating {@link Animal} classes
 * and generating validation error messages based on specific criteria.
 * <p/>
 * This class is designed to validate the attributes of an animal and generate a list of validation errors when
 * the attributes do not meet the specified criteria.
 */
public final class AnimalValidator {
    private AnimalValidator() {
    }

    /**
     * Validates an {@link Animal} class by checking its age, height, and weight attributes.
     *
     * @param animal The {@link Animal} to be validated.
     * @return A {@link List} of {@link ValidationError} objects representing validation errors.
     */
    public static @NotNull List<ValidationError> getValidationErrors(@NotNull Animal animal) {
        List<ValidationError> errors = new ArrayList<>();

        if (animal.age() < 0) {
            errors.add(new ValidationError("Age must be greater than zero"));
        }

        if (animal.height() < 0) {
            errors.add(new ValidationError("Height must be greater than zero"));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError("Weight must be greater than zero"));
        }

        return errors;
    }
}

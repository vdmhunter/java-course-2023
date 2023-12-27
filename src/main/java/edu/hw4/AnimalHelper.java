package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code AnimalHelper} class provides a collection of static methods for various tasks related to
 * manipulating and analyzing lists of {@link Animal} objects.
 * <p/>
 * This utility class offers a wide range of operations for sorting, filtering, and computing statistics on
 * animal data, as well as performing validation checks on animals' attributes.
 */
public final class AnimalHelper {
    private AnimalHelper() {
    }

    /**
     * Task 1: Sort a list of animals by their height in ascending order.
     *
     * @param animals The list of animals to be sorted.
     * @return A new list of animals sorted by height in ascending order.
     */
    public static List<Animal> task1SortAnimalsByHeight(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    /**
     * Task 2: Sort a list of animals by weight in descending order and select the top 'k' animals.
     *
     * @param animals The list of animals to be sorted and selected from.
     * @param k       The number of top animals to select.
     * @return A new list of the top 'k' heaviest animals sorted by weight in descending order.
     */
    public static List<Animal> task2SortAndSelectTopByWeight(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    /**
     * Task 3: Count the number of animals for each animal type.
     *
     * @param animals The list of animals to be categorized and counted.
     * @return A map associating each animal type with the number of animals of that type.
     */
    public static Map<Animal.Type, Integer> task3CountAnimalsByType(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(e -> 1)));
    }

    /**
     * Task 4: Find the animal with the longest name in the list.
     *
     * @param animals The list of animals to search for the one with the longest name.
     * @return The animal with the longest name, or null if the list is empty.
     */
    public static Animal task4FindAnimalWithLongestName(@NotNull List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    /**
     * Task 5: Compare the genders (male vs. female) of the animals and determine if one gender is more
     * prevalent than the other.
     *
     * @param animals The list of animals to compare genders.
     * @return The dominant gender (Animal.Sex.M or Animal.Sex.F) or null if genders are equally prevalent.
     */
    public static @Nullable Animal.Sex task5CompareGenders(@NotNull List<Animal> animals) {
        Map<Animal.Sex, Long> genderCounts = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));

        long maleCount = genderCounts.getOrDefault(Animal.Sex.M, 0L);
        long femaleCount = genderCounts.getOrDefault(Animal.Sex.F, 0L);

        if (maleCount > femaleCount) {
            return Animal.Sex.M;
        } else if (femaleCount > maleCount) {
            return Animal.Sex.F;
        } else {
            return null; // Equal number of males and females
        }
    }

    /**
     * Task 6: Find the heaviest animal for each animal type.
     *
     * @param animals The list of animals to search for the heaviest animal of each type.
     * @return A map associating each animal type with the heaviest animal of that type.
     */
    public static Map<Animal.Type, Animal> task6FindHeaviestAnimalByType(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                (existing, replacement) -> existing.weight() > replacement.weight() ? existing : replacement
            ));
    }

    /**
     * Task 7: Find the k-th the oldest animal in the list.
     *
     * @param animals The list of animals to find the k-th the oldest animal from.
     * @param k       The position (k) of the oldest animal to retrieve.
     * @return The k-th the oldest animal or null if k is out of bounds.
     */
    public static @Nullable Animal task7FindKthOldestAnimal(@NotNull List<Animal> animals, int k) {
        List<Animal> sortedAnimals = animals.stream()
            .sorted(Comparator.comparingInt(Animal::age))
            .toList();

        if (k > 0 && k <= sortedAnimals.size()) {
            return sortedAnimals.get(k - 1);
        }

        return null; // Return null if k is out of bounds
    }

    /**
     * Task 8: Find the heaviest animal below a specified height.
     *
     * @param animals The list of animals to search for the heaviest animal below the specified height.
     * @param k       The maximum height for animals to be considered.
     * @return An optional containing the heaviest animal below the specified height, or an empty optional if
     *     no such animal is found.
     */
    public static @NotNull Optional<Animal> task8FindHeaviestAnimalBelowHeight(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    /**
     * Task 9: Calculate the sum of paws of all animals in the list.
     *
     * @param animals The list of animals to compute the sum of paws for.
     * @return The total sum of paws across all animals in the list.
     */
    public static @NotNull Integer task9SumOfPaws(@NotNull List<Animal> animals) {
        return animals.stream()
            .map(Animal::paws)
            .mapToInt(Integer::intValue)
            .sum();
    }

    /**
     * Task 10: Find animals whose age does not match the number of paws they have.
     *
     * @param animals The list of animals to search for animals with age-paw mismatch.
     * @return A list of animals with age-paw discrepancies.
     */
    public static List<Animal> task10FindAnimalsWithAgeNotMatchingPaws(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    /**
     * Task 11: Find animals that can bite and are taller than 100 centimeters.
     *
     * @param animals The list of animals to search for animals that meet the criteria.
     * @return A list of animals that can bite and are taller than 100 centimeters
     */
    public static List<Animal> task11FindAnimalsCanBiteAndTall(@NotNull List<Animal> animals) {
        final int height = 100;

        return animals.stream()
            .filter(animal -> (animal.bites() == null || animal.bites()) && animal.height() > height)
            .toList();
    }

    /**
     * Task 12: Count animals whose weight is greater than their height.
     *
     * @param animals The list of animals to count based on weight exceeding height.
     * @return The number of animals in the list where weight is greater than height.
     */
    public static Integer task12CountAnimalsWeightGreaterThanHeight(@NotNull List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    /**
     * Task 13: Find animals with names consisting of more than two words.
     *
     * @param animals The list of animals to search for animals with multi-word names.
     * @return A list of animals with names consisting of more than two words.
     */
    public static List<Animal> task13FindAnimalsWithNamesConsistingOfMoreThanTwoWords(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    /**
     * Task 14: Check if there is a dog taller than a specified height.
     *
     * @param animals The list of animals to search for a tall dog.
     * @param k       The minimum height required for a dog to be considered tall.
     * @return True if there is a dog taller than the specified height; otherwise, false.
     */
    public static boolean task14HasDogTallerThanK(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    /**
     * Task 15: Calculate the sum of weights for each animal type within a specified age range.
     *
     * @param animals The list of animals to calculate sum of weights.
     * @param k       The lower age limit for animals to be considered.
     * @param l       The upper age limit for animals to be considered.
     * @return A map associating each animal type with the total sum of weights of animals within the age range.
     */
    public static Map<Animal.Type, Integer> task15SumOfWeightsByTypeAndAgeRange(
        @NotNull List<Animal> animals,
        int k,
        int l
    ) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    /**
     * Task 16: Sort a list of animals by type, sex, and name.
     *
     * @param animals The list of animals to be sorted.
     * @return A new list of animals sorted by type, sex, and name.
     */
    public static List<Animal> task16SortAnimalsByTypeSexName(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    /**
     * Task 17: Check if spiders bite more often than dogs.
     *
     * @param animals The list of animals to compare the biting behavior of spiders and dogs.
     * @return True if spiders bite more often than dogs; otherwise, false.
     */
    public static boolean task17DoSpidersBiteMoreOften(@NotNull List<Animal> animals) {
        long spiderCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && (animal.bites() == null || animal.bites()))
            .count();

        long dogCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && (animal.bites() == null || animal.bites()))
            .count();

        return spiderCount > dogCount;
    }

    /**
     * Task 18: Find the heaviest fish among multiple lists of animals.
     *
     * @param animalLists A list of lists, where each inner list contains animals.
     * @return The heaviest fish among all the provided animal lists, or null if no fish is found.
     */
    public static Animal task18FindHeaviestFish(@NotNull List<List<Animal>> animalLists) {
        return animalLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    /**
     * Task 19: Get validation errors for a list of animals.
     *
     * @param animals The list of animals to validate.
     * @return A map associating each animal's name with a list of validation errors, if any.
     */
    public static Map<String, List<ValidationError>> task19GetValidationErrors(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                AnimalValidator::getValidationErrors
            ));
    }

    /**
     * Task 20: Get validation error messages for a list of animals.
     *
     * @param animals The list of animals to validate.
     * @return A map associating each animal's name with a concatenated string of validation error messages, if any.
     */
    public static Map<String, String> task20GetValidationErrorMessages(List<Animal> animals) {
        Map<String, List<ValidationError>> errors = task19GetValidationErrors(animals);

        return errors.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(Throwable::getMessage)
                    .collect(Collectors.joining(", "))
            ));
    }
}

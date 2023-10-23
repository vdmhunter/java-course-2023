package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ItemCounter} class provides a method {@link ItemCounter#freqDict(List)}
 * to generate a frequency map from a list of objects.
 */
public final class ItemCounter {
    private final static String EMPTY_INPUT_LIST_ERROR_MESSAGE = "Input list must not be empty.";

    private ItemCounter() {
    }

    /**
     * Generates a frequency map from a list of objects.
     *
     * @param list The input list of objects.
     * @return A map where keys represent unique objects from the input list, and values represent their frequencies.
     * @throws IllegalArgumentException if the input list is empty.
     * @throws NullPointerException     if the input list is null.
     */
    public static @NotNull Map<Object, Integer> freqDict(List<Object> list) {
        Objects.requireNonNull(list);

        if (list.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT_LIST_ERROR_MESSAGE);
        }

        Map<Object, Integer> frequencyMap = new HashMap<>();

        for (Object item : list) {
            if (frequencyMap.containsKey(item)) {
                frequencyMap.put(item, frequencyMap.get(item) + 1);
            } else {
                frequencyMap.put(item, 1);
            }
        }

        return frequencyMap;
    }
}

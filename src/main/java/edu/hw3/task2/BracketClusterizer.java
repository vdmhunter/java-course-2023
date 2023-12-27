package edu.hw3.task2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code BracketClusterizer} class provides a method {@link BracketClusterizer#clusterize(String)}
 * to cluster matching pairs of parentheses within a given string.
 */
@SuppressWarnings("SpellCheckingInspection")
public final class BracketClusterizer {
    private static final String EMPTY_INPUT_STRING_ERROR_MESSAGE = "Input string must not be empty.";
    private static final String UNBALANCED_PARENTHESES_ERROR_MESSAGE = "Unbalanced parentheses in the input string.";

    private BracketClusterizer() {
    }

    /**
     * Clusterizes matching pairs of parentheses in the given string.
     *
     * @param str The input string containing parentheses to be clusterized.
     * @return A list of strings, each representing a cluster of matching parentheses.
     * @throws IllegalArgumentException if the input string is empty or if the bracketed clusters are unbalanced.
     * @throws NullPointerException     if the input string is {@code null}.
     */
    public static @NotNull List<String> clusterize(String str) {
        Objects.requireNonNull(str);

        if (str.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT_STRING_ERROR_MESSAGE);
        }

        int openCount = 0;
        List<String> clusters = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
                openCount++;
            } else if (str.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    int start = stack.pop();
                    openCount--;

                    if (stack.isEmpty()) {
                        clusters.add(str.substring(start, i + 1));
                    }
                } else {
                    throw new IllegalArgumentException(UNBALANCED_PARENTHESES_ERROR_MESSAGE);
                }
            }
        }

        if (openCount != 0) {
            throw new IllegalArgumentException(UNBALANCED_PARENTHESES_ERROR_MESSAGE);
        }

        return clusters;
    }
}

package edu.hw3.task7;

import java.util.Comparator;

/**
 * The {@code NullSafeComparator} class is a generic comparator that allows comparing objects
 * with consideration for {@code null} values. It decorates an existing comparator to handle {@code null} values.
 *
 * @param <K> the type of objects to be compared
 */
public class NullSafeComparator<K> implements Comparator<K> {
    private final Comparator<K> comparator;

    public NullSafeComparator(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    /**
     * Compares two objects, handling null values appropriately.
     *
     * @param key1 the first object to compare
     * @param key2 the second object to compare
     * @return a negative integer, zero, or a positive integer as the first object is less than,
     *     equal to, or greater than the second
     */
    @Override
    public int compare(K key1, K key2) {
        if (key1 == null && key2 == null) {
            return 0;
        } else if (key1 == null) {
            return -1;
        } else if (key2 == null) {
            return 1;
        } else {
            return comparator.compare(key1, key2);
        }
    }
}

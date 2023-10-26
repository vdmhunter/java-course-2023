package edu.hw3.task5;

import java.util.Comparator;

/**
 * The {@code ContactComparator} class is a custom comparator for comparing Contact objects.
 * It allows sorting Contact objects based on the last name (or first name if the last name is empty)
 * in either ascending or descending order.
 */
public class ContactComparator implements Comparator<Contact> {
    private final boolean ascending;

    /**
     * Constructs a {@code ContactComparator} with the specified sorting order.
     *
     * @param ascending {@code true} to sort in ascending order, {@code false} to sort in descending order
     */
    public ContactComparator(boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * Compares two Contact objects based on their last names (or first names if the last name is empty).
     *
     * @param contact1 the first {@link Contact} object to compare
     * @param contact2 the second {@link Contact} object to compare
     * @return a negative integer, zero, or a positive integer as the first argument is less than,
     *     equal to, or greater than the second argument
     */
    @Override
    public int compare(Contact contact1, Contact contact2) {
        String key1 = contact1.getLastNameForSort();
        String key2 = contact2.getLastNameForSort();

        return ascending ? key1.compareTo(key2) : key2.compareTo(key1);
    }
}

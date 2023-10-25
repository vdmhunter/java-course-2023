package edu.hw3.task5;

import java.util.Arrays;

/**
 * The {@code ContactSorter} class provides methods for sorting an array of {@link Contact} objects
 * based on the last name (or first name if the last name is empty) in ascending or descending order.
 */
public final class ContactSorter {
    private ContactSorter() {
    }

    /**
     * Sorts an array of contact names and returns an array of {@link Contact} objects sorted by last name
     * (or first name if the last name is empty) in the specified order (ascending or descending).
     *
     * @param names an array of contact names to be sorted
     * @param order the sorting order, use "ASC" for ascending and "DESC" for descending
     * @return a new array of {@link Contact} objects sorted according to the specified order
     */
    public static Contact[] parseContacts(String[] names, String order) {
        if (names == null || names.length == 0) {
            return new Contact[0];
        }

        boolean ascending = order.equals("ASC");
        Contact[] contacts = new Contact[names.length];

        for (int i = 0; i < names.length; i++) {
            contacts[i] = new Contact(names[i]);
        }

        Arrays.sort(contacts, new ContactComparator(ascending));

        return contacts;
    }
}

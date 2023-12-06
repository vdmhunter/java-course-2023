package edu.hw3.task5;

import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Contact} class represents a contact with a first name and last name.
 * It is designed to store and manipulate contact information for sorting purposes.
 */
public final class Contact {
    private final String firstName;
    private final String lastName;

    /**
     * Constructs a {@code Contact} object from a full name.
     * The full name can include a first name and last name separated by a space.
     * If the full name contains both first and last names, they are extracted and stored separately.
     * If the full name contains only a first name, it is stored in the first name field,
     * and the last name is set to an empty string.
     *
     * @param fullName the full name of the contact
     */
    @Contract(pure = true)
    public Contact(@NotNull String fullName) {
        String[] parts = fullName.split(" ");

        if (parts.length == 2) {
            this.firstName = parts[0];
            this.lastName = parts[1];
        } else {
            this.firstName = fullName;
            this.lastName = "";
        }
    }

    /**
     * Returns the last name of the contact. If the last name is empty, it returns the first name.
     *
     * @return the last name of the contact, or the first name if the last name is empty
     */
    public String getLastNameForSort() {
        if (lastName.isEmpty()) {
            return firstName;
        } else {
            return lastName;
        }
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * This method compares the first name and last name of two Contact objects for equality.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is the same as the {@code o} argument;
     *     {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contact contact = (Contact) o;

        if (!Objects.equals(firstName, contact.firstName)) {
            return false;
        }

        return Objects.equals(lastName, contact.lastName);
    }

    /**
     * Returns a hash code value for the Contact object. The hash code is based on the first name and last name.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int result = Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);

        return result;
    }
}

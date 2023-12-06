package edu.hw7.task3;

import java.util.List;

/**
 * The {@code PersonDatabase} interface provides methods for adding, deleting, and finding {@link Person} objects.
 * This interface is designed to be implemented by classes that manage collections of {@link Person} objects.
 *
 * @see Person
 */
interface PersonDatabase {
    /**
     * Adds a {@link Person} object to the database.
     *
     * @param person the Person object to be added
     */
    void add(Person person);

    /**
     * Deletes a {@link Person} object from the database using its id.
     *
     * @param id the id of the {@link Person} object to be deleted
     */
    void delete(int id);

    /**
     * Finds {@link Person} objects in the database by their name.
     *
     * @param name the name of the {@link Person} objects to be found
     * @return a list of {@link Person} objects with the given name
     */
    List<Person> findByName(String name);

    /**
     * Finds {@link Person} objects in the database by their address.
     *
     * @param address the address of the {@link Person} objects to be found
     * @return a list of {@link Person} objects with the given address
     */
    List<Person> findByAddress(String address);

    /**
     * Finds {@link Person} objects in the database by their phone number.
     *
     * @param phone the phone number of the {@link Person} objects to be found
     * @return a list of {@link Person} objects with the given phone number
     */
    List<Person> findByPhone(String phone);
}

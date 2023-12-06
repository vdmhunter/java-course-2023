package edu.hw7.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@code PersonServiceSynchronized} class implements the {@link PersonDatabase} interface and provides
 * thread-safe methods for adding, deleting, and finding {@link Person} objects using synchronized methods.
 * <p>
 * This class uses Java's built-in synchronization to ensure that only one thread can access the database at a time.
 * This prevents data inconsistencies and race conditions when multiple threads try to modify
 * the database simultaneously.
 *
 * @see PersonDatabase
 * @see Person
 */
public class PersonServiceSynchronized extends PersonService implements PersonDatabase {
    /**
     * Adds a {@link Person} object to the database.
     * This method is thread-safe and uses the synchronized keyword to ensure data consistency.
     *
     * @param person the {@link Person} object to be added
     */
    public synchronized void add(Person person) {
        addPerson(person);
    }

    /**
     * Deletes a {@link Person} object from the database using its id.
     * This method is thread-safe and uses the synchronized keyword to ensure data consistency.
     *
     * @param id the id of the {@link Person} object to be deleted
     */
    public synchronized void delete(int id) {
        Person person = peopleById.remove(id);

        if (person != null) {
            nameIndex.get(person.name()).remove(person);
            addressIndex.get(person.address()).remove(person);
            phoneIndex.get(person.phoneNumber()).remove(person);
        }
    }

    /**
     * Finds {@link Person} objects in the database by their name.
     * This method is thread-safe and uses the synchronized keyword to ensure data consistency.
     *
     * @param name the name of the {@link Person} objects to be found
     * @return a list of {@link Person} objects with the given name
     */
    public synchronized List<Person> findByName(String name) {
        return new ArrayList<>(nameIndex.getOrDefault(name, Collections.emptyList()));
    }

    /**
     * Finds {@link Person} objects in the database by their address.
     * This method is thread-safe and uses the synchronized keyword to ensure data consistency.
     *
     * @param address the address of the {@link Person} objects to be found
     * @return a list of {@link Person} objects with the given address
     */
    public synchronized List<Person> findByAddress(String address) {
        return new ArrayList<>(addressIndex.getOrDefault(address, Collections.emptyList()));
    }

    /**
     * Finds {@link Person} objects in the database by their phone number.
     * This method is thread-safe and uses the synchronized keyword to ensure data consistency.
     *
     * @param phone the phone number of the {@link Person} objects to be found
     * @return a list of {@link Person} objects with the given phone number
     */
    public synchronized List<Person> findByPhone(String phone) {
        return new ArrayList<>(phoneIndex.getOrDefault(phone, Collections.emptyList()));
    }
}

package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The abstract base class for a service managing a collection of people.
 * Provides functionality to store people information and perform indexing
 * for quick retrieval based on different attributes such as name, address, and phone number.
 * Concrete subclasses are expected to implement additional functionality.
 * <p>
 * The class maintains three indices (nameIndex, addressIndex, and phoneIndex) for efficient
 * retrieval of people based on their respective attributes. It also keeps a map of people
 * by their unique identifiers (ids) for quick access by ID.
 *
 * @see Person
 */
abstract class PersonService {
    protected final Map<Integer, Person> peopleById = new HashMap<>();
    protected final Map<String, List<Person>> nameIndex = new HashMap<>();
    protected final Map<String, List<Person>> addressIndex = new HashMap<>();
    protected final Map<String, List<Person>> phoneIndex = new HashMap<>();

    /**
     * Adds a person to the service, indexing them by ID, name, address, and phone number.
     *
     * @param person The person to be added to the service.
     */
    protected void addPerson(Person person) {
        peopleById.put(person.id(), person);
        nameIndex.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
        addressIndex.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
        phoneIndex.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
    }
}

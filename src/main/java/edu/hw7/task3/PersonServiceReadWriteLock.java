package edu.hw7.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The {@code PersonServiceReadWriteLock} class implements the {@link PersonDatabase} interface and provides
 * thread-safe methods for adding, deleting, and finding {@link Person} objects using a {@link ReadWriteLock}.
 * <p>
 * This class uses separate Read and Write locks to allow multiple threads to read data simultaneously,
 * while ensuring that write operations are exclusive for data consistency.
 *
 * @see PersonDatabase
 * @see Person
 */
public class PersonServiceReadWriteLock implements PersonDatabase {
    private final Map<Integer, Person> peopleById = new HashMap<>();
    private final Map<String, List<Person>> peopleByName = new HashMap<>();
    private final Map<String, List<Person>> peopleByAddress = new HashMap<>();
    private final Map<String, List<Person>> peopleByPhone = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    /**
     * Adds a {@link Person} object to the database.
     * This method is thread-safe and uses a Write lock to ensure data consistency.
     *
     * @param person the {@link Person} object to be added
     */
    public void add(Person person) {
        writeLock.lock();

        try {
            peopleById.put(person.id(), person);
            peopleByName.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
            peopleByAddress.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
            peopleByPhone.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Deletes a {@link Person} object from the database using its id.
     * This method is thread-safe and uses a Write lock to ensure data consistency.
     *
     * @param id the id of the {@link Person} object to be deleted
     */
    public void delete(int id) {
        writeLock.lock();

        try {
            Person person = peopleById.remove(id);

            if (person != null) {
                peopleByName.get(person.name()).remove(person);
                peopleByAddress.get(person.address()).remove(person);
                peopleByPhone.get(person.phoneNumber()).remove(person);
            }
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Finds {@link Person} objects in the database by their name.
     * This method is thread-safe and uses a Read lock to allow simultaneous reads.
     *
     * @param name the name of the {@link Person} objects to be found
     * @return a list of {@link Person} objects with the given name
     */
    public List<Person> findByName(String name) {
        readLock.lock();

        try {
            return new ArrayList<>(peopleByName.getOrDefault(name, Collections.emptyList()));
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Finds {@link Person} objects in the database by their address.
     * This method is thread-safe and uses a Read lock to allow simultaneous reads.
     *
     * @param address the address of the {@link Person} objects to be found
     * @return a list of {@link Person} objects with the given address
     */
    public List<Person> findByAddress(String address) {
        readLock.lock();

        try {
            return new ArrayList<>(peopleByAddress.getOrDefault(address, Collections.emptyList()));
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Finds {@link Person} objects in the database by their phone number.
     * This method is thread-safe and uses a Read lock to allow simultaneous reads.
     *
     * @param phone the phone number of the {@link Person} objects to be found
     * @return a list of {@link Person} objects with the given phone number
     */
    public List<Person> findByPhone(String phone) {
        readLock.lock();

        try {
            return new ArrayList<>(peopleByPhone.getOrDefault(phone, Collections.emptyList()));
        } finally {
            readLock.unlock();
        }
    }
}

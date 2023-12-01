package edu.hw7.task3;

/**
 * The {@code Person} record represents a person with an id, name, address, and phone number.
 *
 * @param id the unique identifier for the person
 * @param name the name of the person
 * @param address the address of the person
 * @param phoneNumber the phone number of the person
 */
public record Person(int id, String name, String address, String phoneNumber) {
}

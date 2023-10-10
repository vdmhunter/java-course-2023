package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Task0 class provides a method {@link Task0#helloWord} which print the string "Привет, мир!".
 */
public final class Task0 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Task0() {
    }

    /**
     * Print the string "Привет, мир!"
     */
    public static void helloWord() {
        LOGGER.info("Привет, мир!");
    }
}

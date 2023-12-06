package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code Task0} class provides a method {@link Task0#helloWord} which print the string "Привет, мир!".
 */
@SuppressWarnings("unused")
public final class Task0 {

    private static final Logger LOGGER = LogManager.getLogger();

    private Task0() {
    }

    /**
     * Print the string "Привет, мир!"
     */
    public static void helloWord() {
        LOGGER.info("Привет, мир!");
    }
}

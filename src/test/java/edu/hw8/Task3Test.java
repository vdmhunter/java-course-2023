package edu.hw8;

import edu.hw8.task3.PasswordRecoveryService;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 8, Task 3
 */
class Task3Test {
    private static final int MAX_PASSWORD_LENGTH = 4;
    private static final int OPTIMAL_THREADS_NUMBER = Runtime.getRuntime().availableProcessors() - 1;
    @SuppressWarnings("SpellCheckingInspection")
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final Map<String, String> HASH_LOGIN_MAP = Map.of(
        "9003d1df22eb4d3820015070385194c8", "login1",
        "f2e49af795161e14acf9d9245473a368", "login2",
        "81dc9bdb52d04dc20036dbd8313ed055", "login3",
        "900150983cd24fb0d6963f7d28e17f72", "login4",
        "caf1a3dfb505ffed0d024130f58c5cfa", "login5"
    );

    private PasswordRecoveryService service;

    @BeforeEach
    void setUp() {
        service = new PasswordRecoveryService(
            HASH_LOGIN_MAP,
            CHARSET,
            MAX_PASSWORD_LENGTH
        );
    }

    @Test
    @DisplayName("Single-threaded password recovery test")
    void passwordRecoveryService_TestSingleThread() {
        // Arrange
        Map<String, String> expected = Map.of(
            "login1", "pwd",
            "login2", "a1b2",
            "login3", "1234",
            "login4", "abc",
            "login5", "321"
        );

        // Act
        service.runSingleThreadedPasswordRecovery();
        ConcurrentMap<String, String> actual = service.getPasswordDatabase();

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Multi-threaded password recovery test")
    void passwordRecoveryService_TestMultiThread() {
        // Arrange
        Map<String, String> expected = Map.of(
            "login1", "pwd",
            "login2", "a1b2",
            "login3", "1234",
            "login4", "abc",
            "login5", "321"
        );
        var pool = Executors.newFixedThreadPool(OPTIMAL_THREADS_NUMBER);

        // Act
        service.runMultiThreadedPasswordRecovery(
            pool,
            OPTIMAL_THREADS_NUMBER
        );
        ConcurrentMap<String, String> actual = service.getPasswordDatabase();

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}

package edu.hw2;

import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.connectionmanagers.DefaultConnectionManager;
import edu.hw2.task3.connectionmanagers.FaultyConnectionManager;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.StableConnection;
import edu.hw2.task3.exceptions.ConnectionException;
import edu.hw2.task3.randomgenerators.RandomGeneratorWithRuntimeException;
import edu.hw2.task3.randomgenerators.RandomGeneratorWithSeed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Tests for Homework 2, Task 3
 */
class Task3Test {
    @Test
    @DisplayName("Test DefaultConnectionManager with StableConnection")
    void defaultConnectionManager_TestWithStableConnection() {
        // Arrange
        var randomGenerator = new RandomGeneratorWithSeed(5L);
        var stableConnection = new DefaultConnectionManager(randomGenerator);

        //Act
        var connection = stableConnection.getConnection();

        //Assert
        Assertions.assertInstanceOf(StableConnection.class, connection);
    }

    @Test
    @DisplayName("Test DefaultConnectionManager with FaultyConnection")
    void defaultConnectionManager_TestWithFaultyConnection() {
        // Arrange
        var randomGenerator = new RandomGeneratorWithSeed(5555L);
        var faultyConnection = new DefaultConnectionManager(randomGenerator);

        //Act
        var connection = faultyConnection.getConnection();

        //Assert
        Assertions.assertInstanceOf(FaultyConnection.class, connection);
    }

    @Test
    @DisplayName("Test PopularCommandExecutor with StableConnection")
    void popularCommandExecutor_TestUpdatePackagesWithStableConnection() {
        // Arrange
        var randomGenerator = new RandomGeneratorWithSeed(4L);
        var connectionManager = new DefaultConnectionManager(randomGenerator);
        int maxAttempts = 4;
        Executable executable = () -> new PopularCommandExecutor(connectionManager, maxAttempts).updatePackages();

        // Act & Assert
        Assertions.assertDoesNotThrow(executable);
    }

    @Test
    @DisplayName("Test PopularCommandExecutor with failed FaultyConnection")
    void popularCommandExecutor_TestUpdatePackagesWithFailedFaultyConnection() {
        // Arrange
        var randomGenerator = new RandomGeneratorWithSeed(44444443L);
        var connectionManager = new FaultyConnectionManager(randomGenerator);
        int maxAttempts = 4;
        Executable executable = () -> new PopularCommandExecutor(connectionManager, maxAttempts).updatePackages();

        // Act & Assert
        Assertions.assertThrows(ConnectionException.class, executable);
    }

    @Test
    @DisplayName("Test PopularCommandExecutor with succeed FaultyConnection")
    void popularCommandExecutor_TestUpdatePackagesWithSucceedFaultyConnection() {
        // Arrange
        var randomGenerator = new RandomGeneratorWithSeed(7778L);
        var connectionManager = new FaultyConnectionManager(randomGenerator);
        int maxAttempts = 2;
        Executable executable = () -> new PopularCommandExecutor(connectionManager, maxAttempts).updatePackages();

        // Act & Assert
        Assertions.assertDoesNotThrow(executable);
    }

    @Test
    @DisplayName("Test PopularCommandExecutor throws RuntimeException with FaultyConnection")
    void popularCommandExecutor_TestUpdatePackagesThrowsRuntimeException() {
        // Arrange
        var randomGenerator = new RandomGeneratorWithRuntimeException();
        var connectionManager = new FaultyConnectionManager(randomGenerator);
        int maxAttempts = 1;
        Executable executable = () -> new PopularCommandExecutor(connectionManager, maxAttempts).updatePackages();

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, executable);
    }

    @Test
    @DisplayName("Test PopularCommandExecutor throws NullPointerException when ConnectionManager is null")
    void popularCommandExecutor_TestWhenConnectionManagerIsNull() {
        // Arrange
        Executable executable = () -> new PopularCommandExecutor(null, 1).updatePackages();

        // Act & Assert
        Assertions.assertThrows(NullPointerException.class, executable);
    }

    @Test
    @DisplayName("Test PopularCommandExecutor throws NullPointerException when MaxAttempts is less than 1")
    void popularCommandExecutor_TestWhenMaxAttemptsIsLessThanOne() {
        // Arrange
        var randomGenerator = new RandomGeneratorWithRuntimeException();
        var connectionManager = new FaultyConnectionManager(randomGenerator);
        int maxAttempts = 0;
        Executable executable = () -> new PopularCommandExecutor(connectionManager, maxAttempts).updatePackages();

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }
}

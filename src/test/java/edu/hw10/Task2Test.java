package edu.hw10;

import edu.hw10.task2.CacheProxy;
import edu.hw10.task2.FileHashMap;
import edu.hw10.task2.calculators.FactorialCalculator;
import edu.hw10.task2.calculators.FactorialCalculatorImpl;
import edu.hw10.task2.calculators.FibCalculator;
import edu.hw10.task2.calculators.FibCalculatorImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests for Homework 10, Task 2
 */
class Task2Test {
    private static final long[] FACTORIAL_NUMBERS = {
        1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L
    };

    private static final long[] FIBONACCI_NUMBERS = {
        0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L
    };

    private Path tempFile;
    private Path tempFileForFileHashMap;
    private FileHashMap fileHashMap;

    @TempDir Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile(tempDir, "cache", ".dat");

        tempFileForFileHashMap = Files.createTempFile("fileHashMapTest", null);
        fileHashMap = new FileHashMap(tempFileForFileHashMap.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
        Files.deleteIfExists(tempDir);

        Files.deleteIfExists(tempFileForFileHashMap);
    }

    @Test
    @DisplayName("Test Fibonacci Calculation with CacheProxy (persist = true)")
    void cacheProxy_TestFibonacciCalculator() {
        // Arrange
        FibCalculator fibCalculator = new FibCalculatorImpl();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class, tempFile.toString());

        for (int i = 0; i < FIBONACCI_NUMBERS.length; i++) {

            // Act && Assert
            int finalI = i;

            Assertions.assertAll(
                () -> Assertions.assertEquals(proxy.fib(finalI), FIBONACCI_NUMBERS[finalI]),
                () -> Assertions.assertEquals(proxy.fib(finalI), FIBONACCI_NUMBERS[finalI])
            );
        }
    }

    @Test
    @DisplayName("Test Factorial Calculation with CacheProxy (persist = false)")
    void cacheProxy_TestFactorialCalculator() {
        // Arrange
        FactorialCalculator factCalculator = new FactorialCalculatorImpl();
        FactorialCalculator proxy = CacheProxy.create(factCalculator, FactorialCalculator.class, tempFile.toString());

        for (int i = 0; i < FACTORIAL_NUMBERS.length; i++) {

            // Act && Assert
            int finalI = i;

            Assertions.assertAll(
                () -> Assertions.assertEquals(proxy.factorial(finalI), FACTORIAL_NUMBERS[finalI]),
                () -> Assertions.assertEquals(proxy.factorial(finalI), FACTORIAL_NUMBERS[finalI])
            );
        }
    }

    @Test
    @DisplayName("Test isEmpty operation in FileHashMap")
    void fileHashMap_TestIsEmpty() {
        // Arrange
        boolean expected = true;

        // Act
        boolean actual = fileHashMap.isEmpty();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test containsKey operation in FileHashMap")
    void fileHashMap_TestContainsKey() {
        // Arrange
        String key = "key1";
        String value = "value1";
        fileHashMap.put(key, value);

        // Act
        boolean actual = fileHashMap.containsKey(key);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test containsValue operation in FileHashMap")
    void fileHashMap_TestContainsValue() {
        // Arrange
        String key = "key1";
        String value = "value1";
        fileHashMap.put(key, value);

        // Act
        boolean actual = fileHashMap.containsValue(value);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test put and get operations in FileHashMap")
    void fileHashMap_TestPutAndGet() {
        // Arrange
        String key = "key1";
        String value = "value1";
        String expected = "value1";

        // Act
        fileHashMap.put(key, value);
        Object actual = fileHashMap.get("key1");

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test remove operation in FileHashMap")
    void fileHashMap_TestRemove() {
        // Arrange
        String key = "key1";
        String value = "value1";

        // Act
        fileHashMap.put(key, value);
        fileHashMap.remove(key);
        Object actual = fileHashMap.get(key);

        //Assert
        Assertions.assertNull(actual);
    }

    @Test
    @DisplayName("Test putAll operation in FileHashMap")
    void fileHashMap_TestPutAll() {
        // Arrange
        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");

        // Act
        fileHashMap.putAll(testMap);

        //Assert
        Assertions.assertEquals(testMap.size(), fileHashMap.size());
    }

    @Test
    @DisplayName("Test size operation in FileHashMap")
    void fileHashMap_TestSize() {
        // Arrange
        String key1 = "key1";
        String value1 = "value1";
        String key2 = "key2";
        String value2 = "value2";
        int expected = 2;

        // Act
        fileHashMap.put(key1, value1);
        fileHashMap.put(key2, value2);
        int actual = fileHashMap.size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test clear operation in FileHashMap")
    @SuppressWarnings("ConstantValue")
    void fileHashMap_TestClear() {
        // Arrange
        String key1 = "key1";
        String value1 = "value1";
        String key2 = "key2";
        String value2 = "value2";
        int expected = 0;

        // Act
        fileHashMap.put(key1, value1);
        fileHashMap.put(key2, value2);
        fileHashMap.clear();
        int actual = fileHashMap.size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test keySet operation in FileHashMap")
    void fileHashMap_TestKeySet() {
        // Arrange
        String key = "key1";
        String value = "value1";
        fileHashMap.put(key, value);

        // Act
        Set<String> keys = fileHashMap.keySet();

        //Assert
        Assertions.assertTrue(keys.contains(key));
    }

    @Test
    @DisplayName("Test values operation in FileHashMap")
    void fileHashMap_TestValues() {
        // Arrange
        String key = "key1";
        String value = "value1";
        fileHashMap.put(key, value);

        // Act
        Collection<Object> values = fileHashMap.values();

        //Assert
        Assertions.assertTrue(values.contains(value));
    }

    @Test
    @DisplayName("Test entrySet operation in FileHashMap")
    void fileHashMap_TestEntrySet() {
        // Arrange
        String key = "key1";
        String value = "value1";
        fileHashMap.put(key, value);

        // Act
        Set<Map.Entry<String, Object>> entries = fileHashMap.entrySet();

        //Assert
        Assertions.assertTrue(entries.stream()
            .anyMatch(entry -> entry.getKey().equals(key) && entry.getValue().equals(value)));
    }
}

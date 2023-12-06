package edu.hw6;

import edu.hw6.task1.DiskMap;
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

/**
 * Tests for Homework 6, Task 1
 */
class Task1Test {
    private DiskMap diskMap;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("diskMapTest", null);
        diskMap = new DiskMap(tempFile.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("Test isEmpty operation in DiskMap")
    void diskMap_TestIsEmpty() {
        // Arrange
        boolean expected = true;

        // Act
        boolean actual = diskMap.isEmpty();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test containsKey operation in DiskMap")
    void diskMap_TestContainsKey() {
        // Arrange
        String key = "key1";
        String value = "value1";
        diskMap.put(key, value);

        // Act
        boolean actual = diskMap.containsKey(key);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test containsValue operation in DiskMap")
    void diskMap_TestContainsValue() {
        // Arrange
        String key = "key1";
        String value = "value1";
        diskMap.put(key, value);

        // Act
        boolean actual = diskMap.containsValue(value);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test put and get operations in DiskMap")
    void diskMap_TestPutAndGet() {
        // Arrange
        String key = "key1";
        String value = "value1";
        String expected = "value1";

        // Act
        diskMap.put(key, value);
        String actual = diskMap.get("key1");

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test remove operation in DiskMap")
    void diskMap_TestRemove() {
        // Arrange
        String key = "key1";
        String value = "value1";

        // Act
        diskMap.put(key, value);
        diskMap.remove(key);
        String actual = diskMap.get(key);

        //Assert
        Assertions.assertNull(actual);
    }

    @Test
    @DisplayName("Test putAll operation in DiskMap")
    void diskMap_TestPutAll() {
        // Arrange
        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");

        // Act
        diskMap.putAll(testMap);

        //Assert
        Assertions.assertEquals(testMap.size(), diskMap.size());
    }

    @Test
    @DisplayName("Test size operation in DiskMap")
    void diskMap_TestSize() {
        // Arrange
        String key1 = "key1";
        String value1 = "value1";
        String key2 = "key2";
        String value2 = "value2";
        int expected = 2;

        // Act
        diskMap.put(key1, value1);
        diskMap.put(key2, value2);
        int actual = diskMap.size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test clear operation in DiskMap")
    @SuppressWarnings("ConstantValue")
    void diskMap_TestClear() {
        // Arrange
        String key1 = "key1";
        String value1 = "value1";
        String key2 = "key2";
        String value2 = "value2";
        int expected = 0;

        // Act
        diskMap.put(key1, value1);
        diskMap.put(key2, value2);
        diskMap.clear();
        int actual = diskMap.size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test keySet operation in DiskMap")
    void diskMap_TestKeySet() {
        // Arrange
        String key = "key1";
        String value = "value1";
        diskMap.put(key, value);

        // Act
        Set<String> keys = diskMap.keySet();

        //Assert
        Assertions.assertTrue(keys.contains(key));
    }

    @Test
    @DisplayName("Test values operation in DiskMap")
    void diskMap_TestValues() {
        // Arrange
        String key = "key1";
        String value = "value1";
        diskMap.put(key, value);

        // Act
        Collection<String> values = diskMap.values();

        //Assert
        Assertions.assertTrue(values.contains(value));
    }

    @Test
    @DisplayName("Test entrySet operation in DiskMap")
    void diskMap_TestEntrySet() {
        // Arrange
        String key = "key1";
        String value = "value1";
        diskMap.put(key, value);

        // Act
        Set<Map.Entry<String, String>> entries = diskMap.entrySet();

        //Assert
        Assertions.assertTrue(entries.stream()
            .anyMatch(entry -> entry.getKey().equals(key) && entry.getValue().equals(value)));
    }
}

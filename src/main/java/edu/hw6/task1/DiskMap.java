package edu.hw6.task1;

import edu.common.Generated;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code DiskMap} is a class that implements the {@link Map} interface and provides a persistent
 * key-value store backed by a disk file. The data is stored in a simple text file
 * where each line represents a key-value pair in the format "key:value".
 * <p>
 * This implementation utilizes a {@link HashMap} in memory to efficiently manage the key-value pairs.
 * Operations such as put, remove, and clear automatically trigger a corresponding update
 * to the disk file to maintain consistency between the in-memory map and the persisted data.
 * <p>
 * The file path for the disk storage is specified during the construction of the {@link DiskMap} object.
 * <p>
 * The class also incorporates logging using Log4j for error reporting during file I/O operations.
 *
 * @see Map
 * @see HashMap
 * @see BufferedReader
 * @see BufferedWriter
 * @see FileReader
 * @see FileWriter
 * @see LogManager
 * @see Logger
 */
public class DiskMap implements Map<String, String> {
    private final String filePath;
    private final Map<String, String> map;
    private static final Logger LOGGER = LogManager.getLogger();

    public DiskMap(String filePath) {
        this.filePath = filePath;
        this.map = new HashMap<>();

        loadFromDisk();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        String oldValue = map.put(key, value);
        saveToDisk();

        return oldValue;
    }

    @Override
    public String remove(Object key) {
        String result = map.remove(key);
        saveToDisk();

        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
        saveToDisk();
    }

    @Override
    public void clear() {
        map.clear();
        saveToDisk();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    /**
     * Loads key-value pairs from the specified disk file into the in-memory map.
     * Each line in the file is expected to be in the format "key:value". Lines
     * that do not conform to this format are ignored.
     * <p>
     * If an IOException occurs during the file reading process, an error message
     * is logged using Log4j, and the exception details are captured in the log.
     * <p>
     * This method is automatically called during the construction of the {@link DiskMap}
     * object to initialize the in-memory map with persisted data from the disk.
     *
     * @see BufferedReader
     * @see FileReader
     * @see IOException
     * @see LogManager
     * @see Logger
     */
    @Generated
    private void loadFromDisk() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");

                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Saves the current key-value pairs from the in-memory map to the specified
     * disk file. Each entry is written as a line in the format "key:value".
     * <p>
     * If an IOException occurs during the file writing process, an error message
     * is logged using Log4j, and the exception details are captured in the log.
     * <p>
     * This method is automatically triggered after operations that modify the
     * in-memory map, such as put, remove, putAll, and clear, to ensure that changes
     * are persisted to the disk for durability.
     *
     * @see BufferedWriter
     * @see FileWriter
     * @see IOException
     * @see LogManager
     * @see Logger
     */
    @Generated
    private void saveToDisk() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}

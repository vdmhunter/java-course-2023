package edu.hw10.task2;

import edu.common.Generated;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code FileHashMap} is a class that implements the {@link Map} interface and provides a persistent
 * key-value store backed by a disk file. The data is stored in a serialized format using ObjectInputStream
 * and ObjectOutputStream.
 * <p>
 * This implementation utilizes a {@link HashMap} in memory to efficiently manage the key-value pairs.
 * Operations such as put, remove, and clear automatically trigger a corresponding update
 * to the disk file to maintain consistency between the in-memory map and the persisted data.
 * <p>
 * The file path for the disk storage is specified during the construction of the {@link FileHashMap} object.
 * <p>
 * The class also incorporates logging using Log4j for error reporting during file I/O operations.
 */
public class FileHashMap implements Map<String, Object>, Serializable {
    private final String filePath;
    private transient Map<String, Object> map;
    @Serial
    private static final long serialVersionUID = 1376503750341073967L;
    private static final Logger LOGGER = LogManager.getLogger();

    public FileHashMap(String filePath) {
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
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        Object oldValue = map.put(key, value);
        saveToDisk();

        return oldValue;
    }

    @Override
    public Object remove(Object key) {
        Object result = map.remove(key);
        saveToDisk();

        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ?> m) {
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
    public Collection<Object> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    /**
     * Loads key-value pairs from the specified disk file into the in-memory map using serialization.
     * If an IOException or ClassNotFoundException occurs during the process,
     * an error message is logged using Log4j, and the exception details are captured in the log.
     * This method is automatically called during the construction of the {@link FileHashMap}
     * object to initialize the in-memory map with persisted data from the disk.
     */
    @Generated
    @SuppressWarnings("unchecked")
    private void loadFromDisk() {
        var file = new File(filePath);

        if (file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                //noinspection unchecked
                map = (HashMap<String, Object>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                LOGGER.error(e);
            }
        } else {
            map = new HashMap<>();
        }
    }

    /**
     * Saves the current key-value pairs from the in-memory map to the specified
     * disk file using serialization. If an IOException occurs during the file writing process,
     * an error message is logged using Log4j, and the exception details are captured in the log.
     * This method is automatically triggered after operations that modify the
     * in-memory map, such as put, remove, putAll, and clear, to ensure that changes
     * are persisted to the disk for durability.
     */
    @Generated
    private void saveToDisk() {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(map);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}

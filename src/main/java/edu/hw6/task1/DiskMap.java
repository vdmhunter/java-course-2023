package edu.hw6.task1;

import edu.hw6.Generated;
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

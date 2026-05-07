package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Utility class to read key-value pairs from config.properties
public class ConfigReader {

    // Holds all properties loaded from the config file
    private static Properties properties = new Properties();

    // Static block — loads config.properties once when the class is first accessed
    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("config.properties not found! " + e.getMessage());
        }
    }

    // Returns the value for the given key — throws exception if key is missing  from obj properties
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) throw new RuntimeException("Key '" + key + "' not found in config.properties!");
        return value;
    }
}
package config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        return System.getProperty("browser", properties.getProperty("browser"));
        // priority: -Dbrowser param > config.properties
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", properties.getProperty("headless")));
    }
}

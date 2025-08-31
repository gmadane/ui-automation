package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        if (isCI()) {
            // CI → headless + required flags
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            // 🔑 Unique user data dir
            try {
                Path tempProfile = Files.createTempDirectory("chrome-profile");
                options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath().toString());
            } catch (IOException e) {
                throw new RuntimeException("Failed to create temp chrome profile", e);
            }

        } else {
            // Local → open normal browser
            options.addArguments("--start-maximized");
        }

        return new ChromeDriver(options);
    }

    private static boolean isCI() {
        return "true".equalsIgnoreCase(System.getenv("CI"));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

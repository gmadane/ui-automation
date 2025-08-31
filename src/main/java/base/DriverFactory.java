package base;


import config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }
    private static boolean isCI() {
        // GitHub Actions and most CI systems set this environment variable
        return "true".equalsIgnoreCase(System.getenv("CI"));
    }
    public static void initDriver() {
        String browser = ConfigManager.getBrowser().toLowerCase();
        boolean headless = ConfigManager.isHeadless();

        WebDriver webDriver;

        switch (browser) {
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isCI()) {
                    // GitHub Actions / CI safe options
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--disable-software-rasterizer");

                    // 👇 fix for "user data directory is already in use"
                    chromeOptions.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());
                } else {
                    // Local execution
                    chromeOptions.addArguments("--start-maximized");
                }
                driver.set(new ChromeDriver(chromeOptions));
                break;
        }


    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

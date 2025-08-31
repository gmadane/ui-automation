package base;
import config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


    public class BaseTest {
        protected WebDriver driver;

        @BeforeClass
        public void setUp() {
            driver = DriverFactory.getDriver();   // ✅ correct method
        }

        @AfterClass
        public void tearDown() {
            DriverFactory.quitDriver();
        }
    }


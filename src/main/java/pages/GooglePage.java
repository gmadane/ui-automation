package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class GooglePage {
    private WebDriver driver;
    private By searchBox = By.name("q");

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.google.com");
    }

    public void search(String query) {
        driver.findElement(searchBox).sendKeys(query + "\n");
    }
}

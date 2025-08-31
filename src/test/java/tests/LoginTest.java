package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testGoogleTitle() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Google"), "Title verification failed!");
    }
}

package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void verifyPageTitle() {
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }

    @Test
    public void verifyValidLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "User was not taken to the inventory page after login");
    }
}

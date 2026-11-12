package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItems = By.className("cart_item");
    private By itemNames = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void waitForCartPage() {
        wait.until(ExpectedConditions.urlContains("cart"));
    }

    public int getItemCount() {
        waitForCartPage();
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNames() {
        waitForCartPage();
        List<String> names = new ArrayList<>();
        for (WebElement element : driver.findElements(itemNames)) {
            names.add(element.getText());
        }
        return names;
    }

    public void removeItem(String productId) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-" + productId))).click();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.className("title");
    private By cartLink = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Returns the heading shown after login ("Products")
    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    // productId example: "sauce-labs-backpack"
    public void addToCart(String productId) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-" + productId))).click();
    }

    public void removeFromCart(String productId) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-" + productId))).click();
    }

    // The badge disappears when the cart is empty, so no badge = 0
    public int getCartCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
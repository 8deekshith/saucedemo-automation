package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

public class CartTest extends BaseTest {

    private static final String BACKPACK_ID = "sauce-labs-backpack";
    private static final String BACKPACK_NAME = "Sauce Labs Backpack";

    private HomePage homePage;

    // Runs after BaseTest.setUp(), so the browser is already open
    @BeforeMethod
    public void loginBeforeTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        homePage = new HomePage(driver);
        homePage.getPageTitle();   // waits until the products page has loaded
    }

    @Test
    public void addToCart() {
        homePage.addToCart(BACKPACK_ID);
        Assert.assertEquals(homePage.getCartCount(), 1, "Cart badge should show 1");

        homePage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getItemNames().contains(BACKPACK_NAME),
                "Backpack should be in the cart");
    }

    @Test
    public void removeFromCart() {
        homePage.addToCart(BACKPACK_ID);
        homePage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem(BACKPACK_ID);
        Assert.assertEquals(cartPage.getItemCount(), 0, "Cart should be empty after removal");
    }

    @Test
    public void completeCheckout() {
        homePage.addToCart(BACKPACK_ID);
        homePage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillDetails("Deekshith", "D", "560001");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!");
    }

    @Test
    public void logout() {
        homePage.logout();
        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))).isDisplayed(),
                "Login button should be visible after logout");
    }
}
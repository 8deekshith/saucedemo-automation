package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getPageTitle(), "Products");
    }

    // Each row = username, password, part of the expected error message
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
            { "wrong_user", "wrong_pass", "Username and password do not match" },
            { "locked_out_user", "secret_sauce", "this user has been locked out" },
            { "", "secret_sauce", "Username is required" },
            { "standard_user", "", "Password is required" }
        };
    }

    // Runs once for every row in the DataProvider (4 times)
    @Test(dataProvider = "invalidLoginData")
    public void invalidLogin(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
                "Expected error containing: " + expectedError + " but got: " + actualError);
    }
}
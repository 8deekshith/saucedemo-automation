# SauceDemo Automation Framework

A test automation framework for [saucedemo.com](https://www.saucedemo.com), built with **Java, Selenium WebDriver, TestNG and Maven** using the **Page Object Model**.

## What it tests

| Area | Tests |
| --- | --- |
| Smoke | Page title, valid login |
| Login | Valid login, invalid logins (data-driven with `@DataProvider`: wrong credentials, locked-out user, empty username, empty password) |
| Cart | Add to cart, remove from cart |
| Checkout | Complete an order end to end |
| Logout | Logout returns to the login page |

**8 `@Test` methods, 11 test executions** (the data-driven login test runs once per data row).

## Framework design

- **Page Object Model:** `LoginPage`, `HomePage`, `CartPage` and `CheckoutPage` hold locators and actions, so a UI change is fixed in one place.
- **`BaseTest`:** opens and closes the browser around every test.
- **Explicit waits:** `WebDriverWait` instead of `Thread.sleep()`.
- **Data-driven testing:** `@DataProvider` feeds multiple credential sets into one test.
- **Screenshot on failure:** `TestListener` (a TestNG `ITestListener`) saves a screenshot to `screenshots/` when a test fails.
- **Suite execution:** `testng.xml` runs all test classes together.

## Project structure

```
src/main/java/pages/     LoginPage, HomePage, CartPage, CheckoutPage
src/test/java/tests/     BaseTest, FirstTest, LoginTest, CartTest, TestListener
testng.xml               Test suite
pom.xml                  Maven dependencies
```

## How to run

**Prerequisites:** JDK 17+, Maven (or Eclipse with Maven), Google Chrome.

- **Eclipse:** right-click `testng.xml` > Run As > TestNG Suite
- **Terminal:** `mvn test`

The TestNG report is generated at `test-output/index.html`.

## Report

![TestNG report](docs/report.png)

## Author

Deekshith D | [LinkedIn](https://linkedin.com/in/deekshith-d)
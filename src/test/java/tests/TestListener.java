package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

// Takes a screenshot whenever a test fails
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        if (!(testClass instanceof BaseTest)) {
            return;
        }

        WebDriver driver = ((BaseTest) testClass).driver;
        if (driver == null) {
            return;
        }

        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Path target = Paths.get("screenshots", result.getName() + "_" + time + ".png");
            Files.createDirectories(target.getParent());
            Files.copy(source.toPath(), target);
            System.out.println("Screenshot saved: " + target.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Could not save screenshot: " + e.getMessage());
        }
    }
}
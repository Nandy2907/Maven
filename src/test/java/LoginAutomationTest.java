package com.example.automation;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

class LoginAutomationTest {

    @Test
    void testLogin() {
        // Configure ChromeOptions for the WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-certificate-errors");

        // Set the path to chromedriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Create an instance of the ChromeDriver
        WebDriver driver = new ChromeDriver(options);

        // Set up an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to the OrangeHRM demo site
            driver.get("https://opensource-demo.orangehrmlive.com/");

            // Wait for the username field to be visible
            WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("username"))
            );

            // Locate the password field
            WebElement passwordField = driver.findElement(By.name("password"));

            // Input credentials
            usernameField.sendKeys("Admin");
            passwordField.sendKeys("admin123");

            // Locate and click the login button
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
            loginButton.click();

            // Wait until the dashboard page loads
            wait.until(ExpectedConditions.urlContains("/dashboard"));

            // Assert that the URL contains '/dashboard' after successful login
            String expectedUrlPart = "/dashboard";
            String actualUrl = driver.getCurrentUrl();
            assertTrue(actualUrl.contains(expectedUrlPart),
                "URL should contain '/dashboard' after successful login");

        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Rethrow exception to mark the test as failed
        } finally {
            // Quit the driver to close the browser
            driver.quit();
        }
    }
}

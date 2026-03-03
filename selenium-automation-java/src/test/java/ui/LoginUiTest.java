package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class LoginUiTest {

    WebDriver driver;
    LoginPage loginPage;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(description = "Login with valid credentials should redirect user to the profile page")
    public void testValidLogin() {
        loginPage.goToLoginPage();
        loginPage.login("testuser", "Test@123");
        wait.until(ExpectedConditions.urlContains("profile"));
        assertTrue(driver.getCurrentUrl().contains("profile"));
    }

    @Test(description = "Login with invalid credentials should display an authentication error message")
    public void testInvalidLogin() {
        loginPage.goToLoginPage();
        loginPage.login("invaliduser", "invalidpassword");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        String errorText = driver.findElement(By.id("name")).getText();
        assertTrue(errorText.contains("Invalid username or password!"));
    }

    @Test(description = "User should be able to log in with valid credentials and receive an error when logging in with invalid credentials")
    public void testValidAndInvalidLogin() {
        loginPage.goToLoginPage();
        loginPage.login("testuser", "Test@123");
        wait.until(ExpectedConditions.urlContains("profile"));
        assertTrue(driver.getCurrentUrl().contains("profile"));
        loginPage.logout();
        loginPage.goToLoginPage();
        loginPage.login("invaliduser", "invalidpassword");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        String errorText = driver.findElement(By.id("name")).getText();
        assertTrue(errorText.contains("Invalid username or password!"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

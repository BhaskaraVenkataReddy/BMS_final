package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Page class for the BookMyShow Login / Register page
public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Tab locators to switch between Register and Login forms
    By registerTab = By.id("register-tab");
    By loginTab    = By.id("login-tab");

    // Login form container locator
    By loginForm   = By.id("login-form");

    // Registration form field locators
    By regName     = By.id("reg-name");
    By regEmail    = By.id("reg-email");
    By regPass     = By.id("reg-password");
    By regBtn      = By.id("register-btn");

    // Login form field locators
    By logEmail    = By.id("login-email");
    By logPass     = By.id("login-password");
    By logBtn      = By.id("login-btn");

    // Status message shown after login or registration attempt
    By authMsg     = By.id("auth-message");

    // Constructor — initializes driver and wait
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Returns true if the Register tab is visible on the page
    public boolean isRegisterTabVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(registerTab)).isDisplayed();
        } catch (Exception e) {
            System.out.println("[FAIL] isRegisterTabVisible failed: " + e.getMessage());
            return false;
        }
    }

    // Returns true if the login form is visible on the page
    public boolean isLoginFormDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm)).isDisplayed();
        } catch (Exception e) {
            System.out.println("[FAIL] isLoginFormDisplayed failed: " + e.getMessage());
            return false;
        }
    }

    // Clicks Register tab, fills in name, email, password and submits the form
    public void registerUser(String name, String email, String pass) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(registerTab)).click();
            driver.findElement(regName).clear();
            driver.findElement(regName).sendKeys(name);
            driver.findElement(regEmail).clear();
            driver.findElement(regEmail).sendKeys(email);
            driver.findElement(regPass).clear();
            driver.findElement(regPass).sendKeys(pass);
            wait.until(ExpectedConditions.elementToBeClickable(regBtn)).click();
        } catch (Exception e) {
            System.out.println("[FAIL] registerUser failed: " + e.getMessage());
        }
    }

    // Clicks Login tab, fills in email and password and submits the form
    public void loginUser(String email, String pass) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginTab)).click();
            driver.findElement(logEmail).clear();
            driver.findElement(logEmail).sendKeys(email);
            driver.findElement(logPass).clear();
            driver.findElement(logPass).sendKeys(pass);
            wait.until(ExpectedConditions.elementToBeClickable(logBtn)).click();
        } catch (Exception e) {
            System.out.println("[FAIL] loginUser failed: " + e.getMessage());
        }
    }

    // Returns the status message displayed after login or registration
    public String getStatusMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(authMsg)).getText();
        } catch (Exception e) {
            System.out.println("[FAIL] getStatusMessage failed: " + e.getMessage());
            return "";
        }
    }
}
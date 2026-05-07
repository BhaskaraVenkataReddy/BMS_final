package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Page class for the BookMyShow Home Page
public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for home page elements
    By sportsLink = By.linkText("Sports");
    By moviesLink = By.linkText("Movies");
    By signInLink = By.partialLinkText("Sign");
    By bmsLogo    = By.className("logo");
    By searchBar  = By.id("main-search");
    By footerText = By.tagName("footer");

    // Constructor — initializes driver, wait and PageFactory
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Clicks the Sports navigation link
    public void goToSports() {
        wait.until(ExpectedConditions.elementToBeClickable(sportsLink)).click();
    }

    // Clicks the Movies navigation link
    public void goToMovies() {
        wait.until(ExpectedConditions.elementToBeClickable(moviesLink)).click();
    }

    // Clicks the Sign In navigation link
    public void goToSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
    }

    // Returns true if the BMS logo is visible on the page
    public boolean isLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bmsLogo)).isDisplayed();
    }

    // Types the given text into the search bar
    public void enterSearch(String t) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar)).sendKeys(t);
    }

    // Returns the text content of the footer
    public String getFooterContent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(footerText)).getText();
    }

    // Returns the current page title from the browser
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Returns the current value typed inside the search bar
    public String getSearchValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar)).getAttribute("value");
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

// Page class for the BookMyShow Movies page
public class MoviesPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    // Locators for movies page elements
    By allMovieCards   = By.className("movie-card");
    By movieNames      = By.className("movie-name");
    By topRatedRibbons = By.className("ribbon");

    // Constructor — initializes driver, wait and JavascriptExecutor
    public MoviesPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js     = (JavascriptExecutor) driver;
    }

    // Returns the total number of movie cards displayed on the page
    public int getTotalMovieCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(allMovieCards));
            return driver.findElements(allMovieCards).size();
        } catch (Exception e) {
            System.out.println("[FAIL] getTotalMovieCount failed: " + e.getMessage());
            return 0;
        }
    }

    // Clicks the section link (e.g. "Upcoming") to scroll to that section
    public void clickSectionLink(String sectionName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sectionName))).click();
        } catch (Exception e) {
            System.out.println("[FAIL] clickSectionLink failed: " + e.getMessage());
        }
    }

    // Scrolls the section into view and returns true if it is displayed
    public boolean isSectionInViewport(String sectionId) {
        try {
            WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sectionId)));
            js.executeScript("arguments[0].scrollIntoView(true);", section);
            Thread.sleep(1000);
            return section.isDisplayed();
        } catch (Exception e) {
            System.out.println("[FAIL] isSectionInViewport failed: " + e.getMessage());
            return false;
        }
    }

    // Returns the number of movie cards inside the Recommended section
    public int getRecommendedMovieCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#recommended .movie-card")));
            return driver.findElements(By.cssSelector("#recommended .movie-card")).size();
        } catch (Exception e) {
            System.out.println("[FAIL] getRecommendedMovieCount failed: " + e.getMessage());
            return 0;
        }
    }

    // Returns the title of the first movie card on the page
    public String getFirstMovieTitle() {
        try {
            List<WebElement> names = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(movieNames));
            return names.get(0).getText();
        } catch (Exception e) {
            System.out.println("[FAIL] getFirstMovieTitle failed: " + e.getMessage());
            return "";
        }
    }

    // Returns true if the TOP RATED ribbon badge is visible on any movie card
    public boolean hasTopRatedRibbon() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(topRatedRibbons)).isDisplayed();
        } catch (Exception e) {
            System.out.println("[FAIL] hasTopRatedRibbon failed: " + e.getMessage());
            return false;
        }
    }
}
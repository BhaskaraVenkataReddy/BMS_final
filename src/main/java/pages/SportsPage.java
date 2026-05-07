package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// Page class for the BookMyShow Sports page
public class SportsPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators for sports page elements
    By searchBox    = By.id("search-sports");
    By eventCount   = By.id("event-count");
    By weekendBtn   = By.xpath("//button[contains(text(),'Weekend')]");
    By clearBtn     = By.xpath("//button[contains(text(),'Clear')]");
    By sortDropdown = By.id("sort-select");
    By eventNames   = By.className("event-name");
    By eventPrices  = By.className("event-price");

    // Constructor — initializes driver and wait
    public SportsPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Clears the search box and types the given sport name
    public void searchFor(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).clear();
        driver.findElement(searchBox).sendKeys(name);
    }

    // Clicks the Weekend filter button
    public void clickWeekendFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(weekendBtn)).click();
    }

    // Clicks the Clear All button to reset all filters
    public void clickClearAll() {
        wait.until(ExpectedConditions.elementToBeClickable(clearBtn)).click();
    }

    // Returns the current event count shown on the page
    public int getCount() {
        return Integer.parseInt(
            wait.until(ExpectedConditions.visibilityOfElementLocated(eventCount))
                .getText().trim().replaceAll("[^0-9]", ""));
    }

    // Selects a sort option from the sort dropdown by visible text
    public void sortBy(String optionText) {
        new Select(wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)))
            .selectByVisibleText(optionText);
    }
}
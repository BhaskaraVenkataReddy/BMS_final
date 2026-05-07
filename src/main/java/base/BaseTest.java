package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.Extentreport;

// Base class for all test classes — handles browser setup and report lifecycle
public class BaseTest {

    // Shared WebDriver instance accessible by all child test classes
    protected static WebDriver driver;

    // Runs once before the entire test suite
    @BeforeSuite
    public void setup() {
        // Initialize the Extent Report
        Extentreport.setupReport();

        // Launch browser from config.properties and open the URL
        driver = DriverFactory.getDriver(ConfigReader.get("browser"));
        driver.get(ConfigReader.get("url"));
    }

    // Runs once after all tests in the suite have finished
    @AfterSuite
    public void tearDown() {
        // Quit the browser if it was successfully launched
        if (driver != null) DriverFactory.quitDriver();

        // Save the Extent Report to disk
        Extentreport.flushReport();
    }
}
package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import base.BaseTest;

// Utility class to capture and save browser screenshots
public class ScreenshotUtil extends BaseTest {

    // Captures a screenshot and saves it to the screenshots folder
    // Returns the absolute path of the saved file to attach to the report
    public static String getScreenshotpath(WebDriver driver, String fileName) {

        // Destination file path where the screenshot will be saved
        File dest = new File("screenshots/" + fileName + ".png");

        try {
            // Capture the current browser screen
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Copy the captured screenshot to the destination path
            FileHandler.copy(src, dest);

        } catch (IOException e) {
            System.out.println("[SCREENSHOT ERROR] Failed to save: " + fileName + " → " + e.getMessage());
        }

        // Return the absolute path so it can be attached to the Extent Report
        return dest.getAbsolutePath();
    }
    
    public static String getfailScreenshotpath(String fileName) {

        // Destination file path where the screenshot will be saved
        File dest = new File("screenshots/" + fileName + ".png");

        try {
            // Capture the current browser screen
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Copy the captured screenshot to the destination path
            FileHandler.copy(src, dest);

        } catch (IOException e) {
            System.out.println("[SCREENSHOT ERROR] Failed to save: " + fileName + " → " + e.getMessage());
        }

        // Return the absolute path so it can be attached to the Extent Report
        return dest.getAbsolutePath();
    }
}
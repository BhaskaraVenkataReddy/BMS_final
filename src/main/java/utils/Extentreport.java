package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Utility class to setup and manage the Extent HTML report
public class Extentreport {

    // ExtentReports — main report manager (one per suite)
    public static ExtentReports extent;

    // ExtentTest — represents one test node in the report
    public static ExtentTest test;

    // Initializes the Extent Report with a Spark HTML reporter
    public static void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    // Creates a new test node in the report with the given name
    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    // Flushes all logged data and saves the report to disk
    public static void flushReport() {
        extent.flush();
    }
}
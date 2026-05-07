package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerUtil implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
    	Extentreport.test.info("TEST PASSED-> " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	Extentreport.test.info("TEST FAILED-> " + result.getName());
    	Extentreport.test.addScreenCaptureFromPath(ScreenshotUtil.getfailScreenshotpath(result.getName()));
    }
}
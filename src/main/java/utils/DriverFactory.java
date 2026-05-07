package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

	private static WebDriver driver;

	public static WebDriver getDriver(String browser) {
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--disable-notifications");
			driver = new EdgeDriver(edgeOptions);
			break;
		default:
			System.out.println("Browser '" + browser + "' not supported. Defaulting to Chrome.");
			ChromeOptions chromeOptions1 = new ChromeOptions();
			chromeOptions1.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions1);
			break;
		}
		driver.manage().window().maximize();
		// Implicit wait applied once after browser launch
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}
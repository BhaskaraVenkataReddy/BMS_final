package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.HomePage;
import pages.SportsPage;
import utils.Extentreport;
import utils.ListenerUtil;
import utils.ScreenshotUtil;

// Test class for Sports page search, filter and sort functionality
@Listeners(ListenerUtil.class) 
public class SportsFilterTest extends BaseTest {

	// Page objects used across the tests
	SportsPage sportsPage;
	HomePage homePage;

	// Runs once before all tests — navigates to Sports page and initializes page
	// objects
	@BeforeClass
	public void prepareSportsPage() {
		Extentreport.createTest("SportsPageTest");
		homePage = new HomePage(driver);
		homePage.goToSports();
		sportsPage = new SportsPage(driver);
	}

	// TC1 — Verify the URL confirms navigation to the Sports page
	@Test(priority = 1)
	public void testSportsPageNavigation() {

		Assert.assertTrue(driver.getCurrentUrl().contains("sports"), "TC1 FAILED: Not on Sports page!");

	}

	// TC2 — Verify the Sports page displays exactly 20 events by default
	@Test(priority = 2)
	public void testTotalEventCount() {

		int count = sportsPage.getCount();
		Assert.assertEquals(count, 20, "TC2 FAILED: Expected 20 events, Found: " + count);

	}

	// TC3 — Search for "Cricket" and verify at least 1 result is returned
	// Soft — zero results is a content issue, not a crash
	@Test(priority = 3)
	public void testSearchFunctionality() throws Exception {

		SoftAssert sa = new SoftAssert();
		sportsPage.searchFor("Cricket");
		int count = sportsPage.getCount();
		sa.assertTrue(count > 0, "TC3 FAILED: No results for 'Cricket'!");
		String search_cricket = ScreenshotUtil.getScreenshotpath(driver, "sports_search_cricket");
		Extentreport.test.addScreenCaptureFromPath(search_cricket);
		sa.assertAll();

	}

	// TC4 — Apply Weekend filter and verify event count is reduced
	// Also prints the top 3 cheapest weekend sports events to console and report
	@Test(priority = 4)
	public void testWeekendFilter() {

		sportsPage.clickWeekendFilter();
		int count = sportsPage.getCount();
		Assert.assertTrue(count < 20, "TC4 FAILED: Filter did not reduce count!");
		System.out.println("Weekend filter applied. Events = " + count);

		// Fetch all event names and prices after filter is applied
		List<WebElement> names = driver.findElements(By.className("sport-title"));
		List<WebElement> prices = driver.findElements(By.className("sport-price"));

		// Print top 3 events with lowest price to console and report
		Extentreport.test.info("Top 3 Events (Price: Low to High)");
		System.out.println("\n=== Top 3 Events (Price: Low to High) ===");
		int limit = Math.min(3, names.size());
		for (int i = 0; i < limit; i++) {
			System.out.println((i + 1) + ". " + names.get(i).getText() + " - " + prices.get(i).getText());
			Extentreport.test.info((i + 1) + ". " + names.get(i).getText() + " - " + prices.get(i).getText());
		}
		System.out.println("==========================================\n");

	}

	// TC5 — Search for "Marathon", clear all filters and verify count resets to 20
	@Test(priority = 5)
	public void testClearAllFilters() throws Exception {

		sportsPage.searchFor("Marathon");
		sportsPage.clickClearAll();
		int count = sportsPage.getCount();
		Assert.assertEquals(count, 20, "TC5 FAILED: Clear All did not reset! Found: " + count);
		System.out.println("Clear All reset to " + count);
		String clear_all = ScreenshotUtil.getScreenshotpath(driver, "sports_clear_all");
		Extentreport.test.addScreenCaptureFromPath(clear_all);

	}
}
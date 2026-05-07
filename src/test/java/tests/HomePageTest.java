package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.HomePage;
import utils.Extentreport;
import utils.ListenerUtil;
import utils.ScreenshotUtil;

// Test class for Home Page UI and navigation functionality
@Listeners(ListenerUtil.class) 
public class HomePageTest extends BaseTest {

	// Page object for the home page
	HomePage homePage;

	// Runs once before all tests — initializes the HomePage and creates report node
	@BeforeClass
	public void prepareHomePage() {
		homePage = new HomePage(driver);
		Extentreport.createTest("HomePageTest");
	}

	// TC1 — Verify page title contains "BMS" and the logo is visible
	// Soft — checking two UI elements together
	@Test(priority = 1)
	public void testPageTitleAndLogo() {

		SoftAssert sa = new SoftAssert();
		String landing_page = ScreenshotUtil.getScreenshotpath(driver, "landing_page");
		Extentreport.test.addScreenCaptureFromPath(landing_page);
		sa.assertTrue(homePage.getPageTitle().contains("BMS"), "TC1 FAILED: Title mismatch!");
		sa.assertTrue(homePage.isLogoDisplayed(), "TC1 FAILED: Logo not visible!");
		sa.assertAll();

	}

	// TC2 — Verify search bar accepts and retains typed input
	// Hard — if search bar not interactable, test is broken
	@Test(priority = 2)
	public void testSearchBarInteraction() {

		homePage.enterSearch("Oppenheimer");
		String val = homePage.getSearchValue();
		Assert.assertEquals(val, "Oppenheimer", "TC2 FAILED: Search bar did not accept input!");

	}

	// TC3 — Verify the footer displays the copyright year 2026
	// Hard — single check, failure is clear
	@Test(priority = 3)
	public void testFooterCopyrightYear() {

		Assert.assertTrue(homePage.getFooterContent().contains("2026"), "TC3 FAILED: Footer year mismatch!");

	}

	// TC4 — Verify clicking Sign In navigates to the login page
	// Hard — navigation failure stops everything
	@Test(priority = 4)
	public void testNavigationToSignIn(){

		homePage.goToSignIn();
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "TC4 FAILED: Navigation to Sign In failed!");

	}

	// TC5 — Verify clicking Movies navigates to the movies section
	// Hard — navigation failure stops everything
	@Test(priority = 5)
	public void testMoviesLinkNavigation() {

		homePage.goToMovies();
		Assert.assertTrue(driver.getCurrentUrl().contains("movies"), "TC5 FAILED: Navigation to Movies failed!");

	}
}
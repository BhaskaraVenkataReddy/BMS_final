package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.HomePage;
import pages.MoviesPage;
import utils.Extentreport;
import utils.ListenerUtil;
import utils.ScreenshotUtil;

// Test class for Movies page functionality
@Listeners(ListenerUtil.class) 
public class MovieDashboardTest extends BaseTest {

	// Page objects used across the tests
	MoviesPage moviesPage;
	HomePage homePage;

	// Runs once before all tests — navigates to Movies page and initializes page
	// objects
	@BeforeClass
	public void prepareMoviesPage() {
		Extentreport.createTest("MoviePageTest");
		homePage = new HomePage(driver);
		homePage.goToMovies();
		moviesPage = new MoviesPage(driver);
	}

	// TC1 — Verify at least 12 movies are displayed on the page
	@Test(priority = 1)
	public void testTotalMoviesDisplayed() {

		int count = moviesPage.getTotalMovieCount();
		Assert.assertTrue(count >= 12, "TC1 FAILED: Expected >= 12 movies, Found: " + count);

	}

	// TC2 — Verify clicking Upcoming section link scrolls it into view
	@Test(priority = 2)
	public void testSectionNavigationScroll() throws Exception {

		moviesPage.clickSectionLink("Upcoming");
		Assert.assertTrue(moviesPage.isSectionInViewport("upcoming"), "TC2 FAILED: Upcoming section not in viewport!");
		String movies_upcoming = ScreenshotUtil.getScreenshotpath(driver, "movies_upcoming_scroll");
		Extentreport.test.addScreenCaptureFromPath(movies_upcoming);

	}

	// TC3 — Verify the Recommended section contains exactly 4 movies
	// Soft — count mismatch is noteworthy but not a blocker
	@Test(priority = 3)
	public void testRecommendedSectionCount() {

		SoftAssert sa = new SoftAssert();
		int count = moviesPage.getRecommendedMovieCount();
		sa.assertEquals(count, 4, "TC3 FAILED: Expected 4 recommended movies, Found: " + count);
		sa.assertAll();

	}

	// TC4 — Verify the TOP RATED ribbon badge is visible on the page
	// Soft — missing ribbon is a UI issue, not a functional blocker
	@Test(priority = 4)
	public void testTopRatedRibbonVisibility() throws Exception {

		SoftAssert sa = new SoftAssert();
		sa.assertTrue(moviesPage.hasTopRatedRibbon(), "TC4 FAILED: TOP RATED ribbon not visible!");
		sa.assertAll();

	}

	// TC5 — Verify the first movie title is "Oppenheimer"
	@Test(priority = 5)
	public void testMovieTitleIntegrity() {

		String title = moviesPage.getFirstMovieTitle();
		Assert.assertEquals(title, "Oppenheimer", "TC5 FAILED: Expected 'Oppenheimer', Found: " + title);

	}
}
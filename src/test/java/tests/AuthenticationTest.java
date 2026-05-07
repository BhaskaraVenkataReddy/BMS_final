package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.Extentreport;
import utils.ListenerUtil;
import utils.ScreenshotUtil;

// Test class for Login and Registration functionality
@Listeners(ListenerUtil.class) 
public class AuthenticationTest extends BaseTest {

	// Page objects used across the tests
	LoginPage loginPage;
	HomePage homePage;

	// Runs once before all tests — navigates to Sign In page and initializes page
	// objects
	@BeforeClass
	public void preparePage() {
		Extentreport.createTest("LoginPageTest");
		homePage = new HomePage(driver);
		homePage.goToSignIn();
		loginPage = new LoginPage(driver);
	}

	// TC1 — Verify the Register tab is visible on the Sign In page
	@Test(priority = 1)
	public void testRegistrationTabVisibility() {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(loginPage.isRegisterTabVisible(), "TC1 FAILED: Register tab not visible!");
		sa.assertAll();
	}

	// TC2 — Register a new user and verify the success message
	@Test(priority = 2)
	public void testSuccessfulRegistration() {
		SoftAssert sa = new SoftAssert();
		loginPage.registerUser("Bhaskar", ConfigReader.get("valid_email"), ConfigReader.get("valid_password"));
		String msg = loginPage.getStatusMessage();
		sa.assertTrue(msg.toLowerCase().contains("successful"), "TC2 FAILED: Msg: " + msg);
		String registration_success = ScreenshotUtil.getScreenshotpath(driver, "registration_success");
		Extentreport.test.addScreenCaptureFromPath(registration_success);
		sa.assertAll();
	}

	// TC3 — Login with invalid credentials and verify the error message
	@Test(priority = 3)
	public void testInvalidLoginErrorMessage() throws Exception {

		SoftAssert sa = new SoftAssert();
		loginPage.loginUser(ConfigReader.get("invalid_email"), "WrongPass123");
		String msg = loginPage.getStatusMessage();
		sa.assertTrue(msg.toLowerCase().contains("not found"), "TC3 FAILED: Msg: " + msg);
		String invalid_login_error = ScreenshotUtil.getScreenshotpath(driver, "invalid_login_error");
		Extentreport.test.addScreenCaptureFromPath(invalid_login_error);
		sa.assertAll();

	}

	// TC4 — Submit empty fields and verify user stays on the login page
	// Hard assert — if URL changed, login broke completely
	@Test(priority = 4)
	public void testEmptyFieldsValidation() {

		loginPage.loginUser("", "");
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "TC4 FAILED: Redirected away from login page!");

	}

	// TC5 — Login with valid credentials and verify the success message
	@Test(priority = 5)
	public void testSuccessfulLogin() throws Exception {

		SoftAssert sa = new SoftAssert();
		loginPage.loginUser(ConfigReader.get("valid_email"), ConfigReader.get("valid_password"));
		String msg = loginPage.getStatusMessage();
		sa.assertTrue(msg.toLowerCase().contains("successful"), "TC5 FAILED");
		String login_success = ScreenshotUtil.getScreenshotpath(driver, "auth_login_success");
		Extentreport.test.addScreenCaptureFromPath(login_success);
		sa.assertAll();

	}
}
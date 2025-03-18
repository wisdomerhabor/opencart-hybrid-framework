package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviderUtils;

public class TC003_User_AuthenticationDDT extends BaseClass {

	@Test(dataProvider = "loginDataP", dataProviderClass = DataProviderUtils.class, groups = {"DataDriven"})
	public void successful_login_DDT(String email, String password, String testdata_validity, String exp, String act) {
		logger.info(String.format(
		        "🔹 Received Test Data -> Email: %s | Password: %s | Validity: %s | Expected: %s | Actual: %s",
		        email, password, testdata_validity, exp, act
		    ));

		logger.info("****Starting Test : TC003_User_AuthenticationDDT****");

		// Homepage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		logger.info("🔹 Clicked on Login link");

		// Login Page with DDT
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(password);
		lp.clickLoginBtn();
		logger.info("🔹 Clicked on Login button");

		// Validation on MyAccount Page
		MyAccountPage ap = new MyAccountPage(driver);
		boolean ltPO = ap.isMyAccountPageExisting(); // is login successful? yet Login Target Page Object is Displayed
														// or Exists

		/*
		 * - Login data is Valid - Login Success - Logout - TEST PASSED - Login Failed -
		 * TEST FAILED
		 * 
		 * - Login data is Inalid - Login Success - Logout - TEST FAILED - Login Failed
		 * - TEST PASSED
		 */

		// *** Validate valid login ***

		if (testdata_validity.equalsIgnoreCase("Valid")) { // if login credentials is valid and
			// AND if
			if (ltPO == true) { // if login is successful becos login target page object (LTPO) exists
				logger.info("Successful login status : " + ltPO); // Log success

				ap.clickLogout(); // Click logout if login was successful

				Assert.assertTrue(true); // Assert test as PASSED

			} else { // If login was not successful i.e lTPO is false or not displayed
				logger.error("Successful login status : " + ltPO); // Log failure

				Assert.assertTrue(false); // Assert test as failed
			}
		}

		// *** Validate invalid login ***

		if (testdata_validity.equalsIgnoreCase("Invalid")) { // if login credentials is invalid and
			// AND if
			if (ltPO == true) { // if login is successful becos login target page object (LTPO) exists
				logger.error("Successful login status : " + false); // Log as a failure

				ap.clickLogout(); // Click logout if login was successful

				Assert.assertTrue(false); // Assert test as failed

			} else { // If login was not successful i.e lTPO is false or not displayed
				logger.info("Successful login status : " + ltPO); // Log as a success

				Assert.assertTrue(true); // Assert test as PASSED
			}
		}

		logger.info("****Finished Test : TC003_User_AuthenticationDDT****");
	}

}

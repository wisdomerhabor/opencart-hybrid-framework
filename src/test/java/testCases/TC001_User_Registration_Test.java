package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RandomUtils;

public class TC001_User_Registration_Test extends BaseClass {

	public RandomUtils ru = new RandomUtils();

	@Test(groups = {"Master","Regression"})
	public void successful_user_registration() {

		logger.info("****Starting Test : TC001_User_Registration****");

		HomePage hpage = new HomePage(driver);

		hpage.clickMyAccount();
		logger.info("🔹 Clicked on MyAccount link");

		hpage.clickRegister();
		logger.info("🔹 Clicked on Register link");

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

		logger.info("🔹 Providing customer details...");
		regpage.enterFirstName("Wisdom");
		regpage.enterLastName("Erhabor");
		regpage.enterEmail(ru.randomString().toLowerCase() + "@gmail.com"); // Random Email
		regpage.enterTelephone(ru.randomPhoneNumber()); // 08169228069
		regpage.enterPassword("Testing!23");
		regpage.confirmPassword("Testing!23");
		regpage.clickAgreeCheckbox();
		regpage.clickContinueButton();

		// Login Validation
		logger.info("🔹 Validating successful login...");
		String confMsg = regpage.getConfirmationMessage();

		if (confMsg.equals("Your Account Has Been Created!")) {
//			Assert.assertEquals(confMsg, "Your Account Has Been Created!!");

			Assert.assertTrue(true, "User was unable to validate successful login");
			System.out.println("Confirmation Message: " + confMsg);

		} else {
			logger.error("❌ Test Case Failed: "); // Log failure in Log4j
			logger.debug("Debug logs...");
			Assert.assertTrue(false);

		}

		logger.info("****Finished Test : TC001_User_Registration****");

	}

}

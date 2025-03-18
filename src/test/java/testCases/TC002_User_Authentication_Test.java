package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_User_Authentication_Test extends BaseClass{
	
	@Test(groups = {"Master","Sanity"})
	public void successful_login() {
		
		logger.info("****Starting Test : TC001_User_Authentication****");
		
		// Homepage
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();
		logger.info("🔹 Clicked on Login link");
		
		// Login Page
		LoginPage lp = new LoginPage(driver);
		
		lp.enterEmail(p.getProperty("email"));
		lp.enterPassword(p.getProperty("password"));
		lp.clickLoginBtn();
		logger.info("🔹 Clicked on Login button");
		
		// MyAccount Page
		MyAccountPage ap = new MyAccountPage(driver);
		
		boolean targetPage = ap.isMyAccountPageExisting();
		
		// validate successful login
		if(targetPage == true) {
		Assert.assertEquals(targetPage, true, "Login Failed: My account page not found");
		logger.info("Successful login status : "+targetPage);
		}
		else {
			logger.error("Successful login status : "+targetPage);
			Assert.fail();
			
		}
		
	}

}

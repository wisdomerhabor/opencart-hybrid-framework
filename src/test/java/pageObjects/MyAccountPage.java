package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	// constructor
	public MyAccountPage(WebDriver driver) {
		super(driver); // calling the basepage super constructor
	}

	// ====================== Locators / WebElements ======================

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement msg_myAccount;

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnk_logout;

	// ====================== Action Methods ======================
	// To validate successfully login use use an element in the myAccount page
	public boolean isMyAccountPageExisting() {
		try {
			// if the target element in my account page exist
			return msg_myAccount.isDisplayed();

		} catch (Exception e) {
			// else
			return false;
		}
	}
	
	public void clickLogout() {
		lnk_logout.click();
	}

}

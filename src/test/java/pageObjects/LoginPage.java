package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	// constructor
	public LoginPage(WebDriver driver) {
		super(driver); // calling the basepage super constructor
	}

	// ====================== Locators / WebElements ======================

	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txt_Email;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_Password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btn_Login;

	// ====================== Action Methods ======================
	public void enterEmail(String email) {
		txt_Email.clear();
		txt_Email.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		txt_Password.clear();
		txt_Password.sendKeys(password);
	}
	
	public void clickLoginBtn() {
		btn_Login.click();
	}


}

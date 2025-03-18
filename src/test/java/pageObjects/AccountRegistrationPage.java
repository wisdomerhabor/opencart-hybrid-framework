package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	// constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver); // calling the basepage super constructor
	}

	// ====================== Locators / WebElements ======================

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txt_firstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txt_lastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txt_telephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_password;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txt_passwordConfirm;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement cbx_agree;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btn_continue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msg_loginConfirmation;

	// ====================== Action Methods ======================

	// Enter First Name
	public void enterFirstName(String firstName) {
		txt_firstName.clear();
		txt_firstName.sendKeys(firstName);
	}

	// Enter Last Name
	public void enterLastName(String lastName) {
		txt_lastName.clear();
		txt_lastName.sendKeys(lastName);
	}

	// Enter Email
	public void enterEmail(String email) {
		txt_email.clear();
		txt_email.sendKeys(email);
	}

	// Enter Telephone
	public void enterTelephone(String telephone) {
		txt_telephone.clear();
		txt_telephone.sendKeys(telephone);
	}

	// Enter Password
	public void enterPassword(String password) {
		txt_password.clear();
		txt_password.sendKeys(password);
	}

	// Confirm Password
	public void confirmPassword(String passwordConfirm) {
		txt_passwordConfirm.clear();
		txt_passwordConfirm.sendKeys(passwordConfirm);
	}

	// Click on "Agree" Checkbox
	public void clickAgreeCheckbox() {
		if (!cbx_agree.isSelected()) {
			cbx_agree.click();
		}
	}

	// Click on Continue Button
	public void clickContinueButton() {
		btn_continue.click();
	}

	// Verify Registration Success Message
	public String getConfirmationMessage() {
		try {
			return msg_loginConfirmation.getText(); // Action: return the webelement text

		} catch (Exception e) { // Handle error in case of unsuccessful registration
			return e.getMessage();
		}

	}
}

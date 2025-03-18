package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	// constructor
	public HomePage(WebDriver driver) {
		super(driver); // calling the basepage super constructor
	}
	
	// ====================== Locators / WebElements ======================
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnk_MyAccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']") 
	WebElement lnk_Register;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") 
	WebElement lnk_Login;
	
	// ====================== Action Methods ======================
	public void clickMyAccount() {
		lnk_MyAccount.click();
	}
	
	public void clickRegister() {
		lnk_Register.click();
	}
	
	public void clickLogin() {
		lnk_Login.click();
	}
}

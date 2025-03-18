package demoPO;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPagePF {

	WebDriver driver;

	//1. Constructor - to intialize the driver // name is same as class name
	public LoginPagePF(WebDriver driver) {

		this.driver = driver; // class driver eq local driver
		PageFactory.initElements(driver, this);
	}

	//2. Locators >> WebElements

	// Use FindBy annotation to find the element and store it in a WebElement var
	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement txt_username;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement txt_password;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btn_login;
	
	// Using find by to capture a list of elemeents
	@FindBy(tagName="a")
	List<WebElement> links;
	
	// Using "How" class and using = locator
	@FindBy(how=How.XPATH, using = "//button[normalize-space()='Login']")
	WebElement btn_loginHow;

	
	
	//3. Action Methods for each WebElements
	public void enterUsername(String username) {
		txt_username.sendKeys(username);
	}

	public void enterPassword(String password) {
		txt_password.sendKeys(password);
	}

	public void clickLoginBtn() {
		btn_login.click();
	}

}

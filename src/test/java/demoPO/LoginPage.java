package demoPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	WebDriver driver;
	
	// Comstructor - to intialize the driver  // name is same as class name
	public LoginPage(WebDriver driver) {
		
		this.driver = driver; // class driver eq  local driver
	}
	
	// Locators
	By txt_username_loc = By.xpath("//input[@placeholder='Username']");
	By txt_password_loc = By.xpath("//input[@placeholder='Password']");
	By btn_login_loc = By.xpath("//button[normalize-space()='Login']");
	
	// Action Methods for each locator
	public void enterUsername(String username) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(txt_username_loc).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(txt_password_loc).sendKeys(password);
	}
	
	public void clickLoginBtn() {
		driver.findElement(btn_login_loc).click();
	}


}

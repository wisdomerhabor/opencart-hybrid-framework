package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	// Contains the super constructor that will be used by all page object classes
	WebDriver driver;
	
	public BasePage(WebDriver driver) { 
		this.driver = driver;
		PageFactory.initElements(driver, this); // initialize pagefactory with local and class drivers
	}
}

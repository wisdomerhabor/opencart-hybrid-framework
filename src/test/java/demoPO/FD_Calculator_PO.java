package demoPO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FD_Calculator_PO {

	WebDriver driver;

	// 1. Constructor - Initialize the driver
	public FD_Calculator_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// 2. Locators >> WebElements
	@FindBy(xpath = "//input[@id='principal']")
	WebElement txt_principal;

	@FindBy(xpath = "//input[@id='interest']")
	WebElement txt_interest;

	@FindBy(xpath = "//input[@id='tenure']")
	WebElement txt_period;

	@FindBy(xpath = "//select[@id='tenurePeriod']")
	WebElement dd_periodYMD;

	@FindBy(xpath = "//select[@id='frequency']")
	WebElement dd_frequency;

	@FindBy(xpath = "//img[contains(@src, 'btn_calcutate.gif')]") // Updated XPath
	WebElement btn_calculate;

	@FindBy(xpath = "//span[@id='resp_matval']//strong")
	WebElement val_actualMaturityValue;

	@FindBy(xpath = "//img[@class='PL5']")
	WebElement btn_clear;
	
	@FindBy(xpath = "//*[id='wzrk-cancel']")
	WebElement btn_popupDismiss;

	// 3. Action Methods
	public void enterPrincipal(String x) {
		txt_principal.sendKeys(x);
	}

	public void enterRateofInterest(String x) {
		txt_interest.sendKeys(x);
	}

	public void enterPeriod(String x) {
		txt_period.sendKeys(x);
	}

	public void selectPeriodYMD(String x) {
		Select dd = new Select(dd_periodYMD);
		dd.selectByVisibleText(x); // Fixed method
	}

	public void selectFrequency(String x) {
		Select dd = new Select(dd_frequency);
		dd.selectByVisibleText(x); // Fixed method
	}

	public void clickCalculateBtn() throws InterruptedException {
		Thread.sleep(1000);
//		btn_calculate.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_calculate);
	}

	public void clickClearBtn() throws InterruptedException {
		Thread.sleep(2000);
//		btn_clear.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_clear);
	}

	public String getMaturityValueText() {
		return val_actualMaturityValue.getText(); // get WebElement string text
	}
	
	public void handlePopup() {
		try {
			
		if(btn_popupDismiss.isDisplayed()==true) {
			btn_popupDismiss.click();
			System.out.println("Clicked popup button");
			
			Alert alert = driver.switchTo().alert(); // Try switching to an alert
			System.out.println("Alert detected: " + alert.getText());
			alert.accept(); // Accept the alert (click OK)
			
		}
		
		} catch(Exception e) {
			System.out.println("No popup button found,  continuing test...");
		}
	}
}

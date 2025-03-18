package demoPO;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_LoginTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void setup () {
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
	}
	
	@Test
	void testLogin() throws InterruptedException {
		
		LoginPage lp = new LoginPage(driver);
		lp.enterUsername("Admin");
		lp.enterPassword("admin123");
		lp.clickLoginBtn();
		
		Assert.assertEquals(driver.getTitle(),"OrangeHRM");
		
	}
	
	@AfterClass
	void teardown() {
		
		driver.quit();
	}
}

package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; // Log4j
	public Properties p; // properties class

	@BeforeClass(groups = {"Master","Sanity","Regression", "DataDriven"})
	@Parameters({ "os", "browser" })
	public void setup(String os, String browser) throws IOException {

		// *** config.properties file setup() ***
		// Load file
//		FileReader fr = new FileReader("./src/test/resources/config.properties");
		
		// Define the file path dynamically
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";

		// Initialize FileInputStream
		FileInputStream fi = new FileInputStream(filePath);

		// Initialize Properties class
		p = new Properties();

		// Load properties file as an object
		p.load(fi);

		// Close FileInputStream
		fi.close();

		// Return properties file object
//		return p;

		// *** Log4j setup ***
		logger = LogManager.getLogger(this.getClass());

		// *** Driver setup ***
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break; // break and continue execution

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Invalid brower name provided");
			return; // break and exit execution
		}
//		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Open URL
		driver.get(p.getProperty("appURL")); // using properties file
		driver.manage().window().maximize();
	}
	
	

	@AfterClass(groups = {"Master","Sanity","Regression", "DataDriven"})
	public void teardown() {
		System.out.println("✅ Test Case Run is completed");
		driver.quit();
	}

}

package demoPO;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.ExcelUtils_noConstructor;

public class TC002_FD_Calculator_DDT {

	WebDriver driver;
	
	@BeforeClass
	public void setup() {

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(
				"https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india/fixed-deposit-calculator-SBI-BSB001.html");
		driver.manage().window().maximize();
	}

	@Test
	public void vaidateFD_Calculator() throws IOException, InterruptedException {
		
		// Intantiate Utils and Initailize variables
		ExcelUtils_noConstructor eu = new ExcelUtils_noConstructor();
		FD_Calculator_PO fdp = new FD_Calculator_PO(driver);
		String filepath = System.getProperty("user.dir") + "\\testData\\caldata.xlsx";
		String sheetname = "Sheet1";
		
		// Handle popups
		fdp.handlePopup();
		
		// Get total number of rows in the excel file
		int rowCount = eu.getRowCount(filepath, sheetname);
		
		// loop For each row r ...
		for (int r = 1; r <=rowCount; r++) { // r0 = header row ; r1 = first row

			// read data from excel cell 0 for each row r
			String principal = eu.readCellData(filepath, sheetname, r, 0);
			String rateOfInterest = eu.readCellData(filepath, sheetname, r, 1);
			String period = eu.readCellData(filepath, sheetname, r, 2);
			String periodYMD = eu.readCellData(filepath, sheetname, r, 3);
			String frequency = eu.readCellData(filepath, sheetname, r, 4);
			String expMatVal = eu.readCellData(filepath, sheetname, r, 5);
			
			
			// pass above data into the application
			
			fdp.enterPrincipal(principal);
			fdp.enterRateofInterest(rateOfInterest);
			fdp.enterPeriod(period);
			fdp.selectPeriodYMD(periodYMD);
			fdp.selectFrequency(frequency);
			fdp.clickCalculateBtn();

			// perform validations
			String actualMatVal = fdp.getMaturityValueText();

			if (Double.parseDouble(actualMatVal) == Double.parseDouble(expMatVal)) {
				// returns the double value represented by the string argument.
				// Convert from the cell string value to double format
				System.out.println("TEST PASSED: Expected maturity value of > " + expMatVal
						+ " < is equal to Acutal Maturity Value : " + actualMatVal);

				// update excel file "actual results" column c7
				eu.setCellData(filepath, sheetname, r, 7, "Pass");
				eu.fillGreenColour(filepath, sheetname, r, 7);
			} else {

				System.out.println("TEST FAILED: Expected maturity value of > " + expMatVal
						+ " < is NOT equal to Acutal Maturity Value : " + actualMatVal);

				// update excel file "actual results" column c7
				eu.setCellData(filepath, sheetname, r, 7, "Fail");
				eu.fillRedColour(filepath, sheetname, r, 7);
			}
			// Click on clear btn for the next iteration
			fdp.clickClearBtn();
		}

	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}

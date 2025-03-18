package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import testBase.BaseClass;

public class ScreenshotUtils extends BaseClass {

	public String captureScreenshot(String testName) {
		/**
		 * Note: Import File class from Java.io not SeIenium Also Add Apache Commons IO
		 * to your dependencies – //
		 * https://mvnrepository.com/artifact/commons-io/commons-io/2.18.0
		 */

		// 1. Convert WebDriver object to TakesScreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		// 2. Capture screenshot as file
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		// 3. Define screenshot filename with time stamp
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());
		String srcFileName = "C:\\eclipse-workspace\\webportal\\screenshots\\" + testName + "_" + timestamp + ".png";

		// String srcFileName = "screenshots/" + testName + "_" + timestamp + ".png"; //
		// ./ is the project directory

		try {
			// 4. Save the capureD screenshot file to the defined path
			FileUtils.copyFile(srcFile, new File(srcFileName));
			System.out.println("Screenshot saved: " + srcFileName);
			return srcFileName; // ✅ Return the screenshot path

		} catch (IOException e) {
			System.out.println("Failed to save screenshot: " + e.getMessage());
			return null; // ✅ Return null if screenshot saving fails
		}

	}

	public String captureScreenshotwithoutTestName() {

		// 1. Convert WebDriver object to TakesScreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		// 2. Capture screenshot as file
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		// 3. Define screenshot filename with time stamp
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());
		String srcFileName = "C:\\eclipse-workspace\\webportal\\screenshots\\testScreenshot_" + timestamp + ".png";

		// String srcFileName = "screenshots/" + testName + "_" + time stamp + ".png";
		// //
		// ./ is the project directory

		try {
			// 4. Save the capureD screenshot file to the defined path
			FileUtils.copyFile(srcFile, new File(srcFileName));
			System.out.println("Screenshot saved: " + srcFileName);
			return srcFileName; // ✅ Return the screenshot path

		} catch (IOException e) {
			System.out.println("Failed to save screenshot: " + e.getMessage());
			return null; // ✅ Return null if screenshot saving fails
		}
	}

	public String captureScreen(String TCname) {
		// 1. Create custom timestamp
		Date date = new Date();
		String timestamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(date);

		// Set target file location
		String ssname = TCname + "_" + timestamp + ".png";
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + ssname;
		try {
		// 2. Convert WebDriver object to TakesScreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		// 3. Capture screenshot as a file
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		// 4. Create a target file
		File targetFile = new File(targetFilePath);

		// 5. Copy the source file and save it as target file
		sourceFile.renameTo(targetFile);

		return targetFilePath; // ✅ Return the screenshot path
		
		} catch(Exception e) {
			System.out.println("Failed to save screenshot: " + e.getMessage());
			return null; // ✅ Return null if screenshot saving fails
		}
	}

}

package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener implements ITestListener {

	// Declare the class provided by extent report
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // populate common test discriptive information on the the report
	public ExtentTest test; // create test case entries in the report and update test methods status
	public ScreenshotUtils su = new ScreenshotUtils();; // My screenshot utility class

	public String reportpath;
	public String reportname;

	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt;
	 */
	public void onStart(ITestContext context) {
		// *** Create custom timestamp for report name using the simple date
		// formatter***
		Date date = new Date();
		String timestamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(date);

		// Set report path and name
		reportpath = System.getProperty("user.dir") + "\\reports\\";
		reportname = "Test-Report_" + timestamp + ".html";

		// Create spark reporter object
		sparkReporter = new ExtentSparkReporter(reportpath + reportname); // Pass the specific report location and name

		// Configure the UI using sparkReporter
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("OpencartFunctional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		// Configure common test discriptive information
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter); // attach test reporter

		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("QA Engineer Name", "Wisdom Erhabor");
		extent.setSystemInfo("Appication Name", "Opercart");
		extent.setSystemInfo("Current Username", System.getProperty("user.name"));

		String os = context.getCurrentXmlTest().getParameter("os"); // from xml run file parameters
		extent.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser"); // from xml run file parameters
		extent.setSystemInfo("Browser Name", browser);

//		System.out.println("Available parameters: " + context.getCurrentXmlTest().getAllParameters()); 

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) { // ! = not >> If includedGroup is NOT empty

			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	/**
	 * Invoked each time a test succeeds.
	 * 
	 * getName() Returns: The test name if this result's related instance implements
	 * ITest or use @Test(testName=...), null otherwise.
	 */
	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups()); // display test group in the report
		
		test.log(Status.PASS, "Test case '"+result.getName()+"' has PASSED"); // update test status p/f/s
		
		logTestStatus(result);
	}

	/**
	 * Invoked each time a test fails.
	 */
	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups()); // display test group in the report	
		
		test.log(Status.FAIL, "Test case '"+result.getName()+"' has FAILED"); // update test status p/f/s
		test.log(Status.FAIL, "Test case '"+result.getName()+"' failure error message is : " + result.getThrowable().getMessage()); // get error msg for failure
		
		// *** Screnshot on test failure
		try {
		// call the method to capture ss and return the path after capture
		String screenshotPath = su.captureScreen(result.getName());
		
		test.addScreenCaptureFromPath(screenshotPath); // Add to extent report
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Invoked each time a test is skipped.
	 */
	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.SKIP, "Test case '"+result.getName()+"' has been SKIPPED"); // update test status p/f/s
		test.log(Status.INFO, result.getName()+" failure error message is:" + result.getThrowable().getMessage());
	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have
	 */
	public void onFinish(ITestContext context) {

		extent.flush(); // flush output to HTML file
		
		String fullReportPath = reportpath+reportname;
		// Create report file in the path
		File extentReportFile = new File(fullReportPath);
		
		
		// *** Open file automatically
		
		try {
		    Desktop desktop = Desktop.getDesktop();
		    if (desktop.isSupported(Desktop.Action.BROWSE)) {
		        desktop.browse(extentReportFile.toURI());
		    } else {
		        System.out.println("❌ BROWSE action is not supported.");
		    }
		} catch (IOException | SecurityException e) {
		    e.printStackTrace();
		}

		
//		try {
//			Desktop.getDesktop().browse(extentReportFile.toURI());
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		// *** Send email
//		EmailUtils eu = new EmailUtils();
//		// Example: Provide the absolute path to your Extent Report
//		eu.sendEmailWithReport(fullReportPath);
	}

	// HELPER METHODS
	private void logTestStatus(ITestResult result) {
		System.out.printf("📌 Status of test execution is: " +
		    (result.getStatus() == ITestResult.SUCCESS ? "PASSED" :
		     result.getStatus() == ITestResult.FAILURE ? "FAILED" :
		     result.getStatus() == ITestResult.SKIP ? "SKIPPED" : "UNKNOWN") + "<br>",
		     true);  
}
	}

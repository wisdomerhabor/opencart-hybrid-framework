package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


public class ReportNGListener extends ScreenshotUtils implements ITestListener {
	// THIS IS MY MAIN LISTENER
	
	public ScreenshotUtils su = new ScreenshotUtils();;

	@Override
	public void onTestStart(ITestResult result) {
		// System properties Set up
		System.setProperty("org.uncommons.reportng.title", "WEO Test Report");
		System.setProperty("org.uncommons.reportng.velocity-log", "true");
		System.setProperty("org.uncommons.reportng.logOutputReport", "true");
		
		Reporter.log("🟢 Test Started: " + result.getName() + "<br>", true);

	}

    @Override
    public void onTestSuccess(ITestResult result) {    	
        Reporter.log("✅ Test Passed: " + result.getName() + "<br>", true);
        logTestStatus(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	// set up to escape the <anchor tag
    	/**
    	 * Used to turn off escaping for log output in the reports (not recommended)
    	 * The default is for output to be escaped, since this prevents characters such as
    	 * '<' and '&' from causing mark-up problems.
    	 * If escaping is turned off, then log text is included as raw
    	 * HTML/XML, which allows for the insertion of hyper links and other nasty hacks.
    	 * 
    	 * **/
    	System.setProperty("org.uncommons.reportng.escape-output","false");
    	////

        // ✅ Capture screenshot and get the file path
        String screenshotPath = su.captureScreenshot(result.getName());

        Reporter.log("❌ Test Failed: " + result.getName() + "<br>", true);
        Reporter.log("📸 Screenshot captured for failed test: " + result.getName() + "<br>", true);

        // ✅ Add screenshot as a clickablE link in TestNG reports
        if (screenshotPath != null) {
            Reporter.log("<a href='" + screenshotPath + "' target='_blank'>📷 View Screenshot</a>", true);
        } else {
            Reporter.log("⚠️ Screenshot could not be saved." + "<br>", true);
        }
        Reporter.log("<br>", false);
        logTestStatus(result);
    }



    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("⚠️ Test Skipped: " + result.getName() + "<br>", true);
        logTestStatus(result);
    }

    @Override
    public void onStart(ITestContext context) {
        Reporter.log("🚀 Test Suite Started: " + context.getName() + "<br>", true);
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("🏁 Test Suite Finished: " + context.getName() + "<br>", true);
    }

    
    
    // HELPER METHODS
    private void logTestStatus(ITestResult result) {
        Reporter.log("📌 Status of test execution is: " +
            (result.getStatus() == ITestResult.SUCCESS ? "PASSED" :
             result.getStatus() == ITestResult.FAILURE ? "FAILED" :
             result.getStatus() == ITestResult.SKIP ? "SKIPPED" : "UNKNOWN") + "<br>",
            true);                                                           // <br> is a line break in HTML 
    }
}

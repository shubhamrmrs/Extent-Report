package testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setReport() {

		htmlReporter = new ExtentHtmlReporter("./reports/extent.html");

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("W4A Automation Reports");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Automation Tester", "Shubham");
		extent.setSystemInfo("Organization", "TCS");
		extent.setSystemInfo("Build No", "W4A-1234");
	}

	@AfterTest
	public void endRport() {
		extent.flush();

	}

	/* pass, fail, skip */

	@Test
	public void doLogin() {
		test = extent.createTest("Login Test");
		System.out.println("executing login test");
	}

	@Test
	public void userReg() {
		test = extent.createTest("user reg Test");
		Assert.fail("user reg test fail");
	}

	@Test
	public void isSkip() {
		test = extent.createTest("Skip Test");
		throw new SkipException("skipping the test case"); // To Skip forcefully
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			String methodName = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + "FAILED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			test.fail(m);

		} else if (result.getStatus() == ITestResult.SKIP) {

			String methodName = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + "SKIPPED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
			test.skip(m);

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			String methodName = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + "PASSED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.pass(m);

		}

	}

}

package testcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestCase2 {

	@Test
	public void doLogin() {
		System.out.println("executing login test");
	}

	@Test
	public void userReg() {
		Assert.fail("user reg test fail");
	}

	@Test
	public void isSkip() {
		throw new SkipException("skipping the test case"); // To Skip forcefully
	}

}

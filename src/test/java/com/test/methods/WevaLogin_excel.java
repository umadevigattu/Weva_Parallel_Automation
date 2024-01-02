package com.test.methods;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.weva.common.SeleniumUtilities;

@Listeners(com.weva.common.ExtentReportListeners.class)
public class WevaLogin_excel extends SeleniumUtilities {

	@Test
	public void LoginTest() throws Exception {

		WevaLogin_muliple();

	}

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			getScreenshot(getDriver(), "failed_screen");
		}
	}

}

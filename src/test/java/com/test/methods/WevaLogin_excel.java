package com.test.methods;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.weva.common.SeleniumUtilities;

@Listeners(com.weva.common.ExtentReportListeners.class)
public class WevaLogin_excel extends SeleniumUtilities {
	

	@Test
	public void LoginTest() throws Exception {

		WevaLogin_muliple();

	}

}

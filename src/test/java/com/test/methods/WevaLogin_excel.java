package com.test.methods;


import com.weva.common.Basetest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.weva.common.SeleniumUtilities;
import com.weva.common.IDriverFactory.BrowserType;

@Listeners(com.weva.common.ExtentReportListeners.class)
public class WevaLogin_excel extends SeleniumUtilities {
	

	@Test
	public void LoginTest() throws Exception {

		WevaLogin_muliple();

	}

}

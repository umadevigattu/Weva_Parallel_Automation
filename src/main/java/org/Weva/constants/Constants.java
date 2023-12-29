package org.Weva.constants;

import com.weva.common.CommonUtils;

public class Constants {

	public static final String TestData_Path = System.getProperty("user.dir") + "\\Config\\Configuration.properties";

	public static final String TestReportsPath = (System.getProperty("user.dir") + "\\extentReport\\WevaReports"
			+ CommonUtils.getCurrentDate() + ".html");
	public static final String LoginTestData = System.getProperty("user.dir") + "\\InputFiles\\LoginTestData.xlsx";

	public static final int SHORT_WAIT = 3000;
	public static final int MEDIUM_WAIT = 6000;
	public static final int LONG_WAIT = 10000;

}

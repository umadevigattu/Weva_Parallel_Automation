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

	public static final String BaseURI = "https://13.239.74.30/api/";
	public static final String BearerToken = "eyJhbGciOiJIUzI1NiJ9.eyJsb2dnZWRJblVzZXJUeXBlIjoxLCJzdWIiOiJzaXJpc2hhcGF0aWVudEB5b3BtYWlsLmNvbSIsInBlcm1pc3Npb25zIjpbeyJwZXJzb25hbCI6dHJ1ZSwib3JnYW5pc2F0aW9uTmFtZSI6bnVsbCwib3JnYW5pc2F0aW9uSWQiOm51bGwsIm9yZ2FuaXNhdGlvblR5cGUiOm51bGwsIm9yZ2FuaXNhdGlvbkxvZ28iOm51bGwsInBlcm1pc3Npb25zIjpbIkFTLlJXRCIsIk1SUy5SV0QiXX1dLCJsb2dnZWRJblVzZXJJZCI6IjY1NDlkY2E0ZGM5ZDRlM2M3ZDNkZjdiOCIsImV4cCI6MTcwNDE4MjM3MywiaWF0IjoxNzA0MDk1OTczfQ.Tnbj1geDQGgHQZFNB_VJX1VYmRwWT5_fSc2ySuGpJP8";

}

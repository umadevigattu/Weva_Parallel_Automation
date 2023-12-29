 package com.weva.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory implements IDriverFactory {
	public WebDriver CreateWebDriver(BrowserType browser) {
		try {

			WebDriver driver = null;
			switch (browser) {
			case chrome:

			      WebDriverManager.chromedriver().setup();
			      driver = new ChromeDriver();     
				break;

			default:
				break;

			}
			return driver;

	

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
}

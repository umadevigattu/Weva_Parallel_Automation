package com.test.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.weva.common.SeleniumUtilities;

@Listeners(com.weva.common.ExtentReportListeners.class)
public class ParallelTest extends SeleniumUtilities {

	SoftAssert softassert = new SoftAssert();

	@BeforeMethod

	public void setUp() {

		CreateWebDriver(BrowserType.chrome);

	}

	@Test
	public void test1() throws Exception {
		System.out.println(Thread.currentThread().getId());
		Weva_login();
		Thread.sleep(3000);
		WebElement homepage = getDriver().findElement(By.xpath("//a[text()='Find a Doctor']"));
		if (homepage.isDisplayed() == true) {
			softassert.assertEquals(homepage.getText(), "Find a Doctor");
			getScreenshot(getDriver(), "Login_Pass");
			System.out.println("execution first 1 " + Thread.currentThread().getId() + "Passed");

		}
		softassert.assertAll();

	}

	@Test
	public void test2() throws Exception {
		Weva_login();
		Thread.sleep(3000);
		WebElement homepage = getDriver().findElement(By.xpath("//a[text()='Find a Doctor']"));
		if (homepage.isDisplayed() == true) {

			softassert.assertEquals(homepage.getText(), "Find a Doctor");
			System.out.println("execution 2 " + Thread.currentThread().getId() + "Passed");
			getScreenshot(getDriver(), "Find a Doctor_screen");

		} else {
			getScreenshot(getDriver(), "Find a Doctor_screen");
		}
		softassert.assertAll();

	}

	@Test
	public void test3() throws Exception {
		Weva_login();
		Thread.sleep(3000);
		WebElement homepage = getDriver().findElement(By.xpath("//a[text()='Find a Doctor']"));
		if (homepage.isDisplayed() == true) {
			System.out.println("execution  3" + Thread.currentThread().getId() + "Passed");
			softassert.assertEquals(homepage.getText(), "Find a Doctor");
			getScreenshot(getDriver(), "Find a Doctor_screen");

		} else {
			getScreenshot(getDriver(), "Find a Doctor_screen");
		}
		softassert.assertAll();
	}

	@Test
	public void test4() throws Exception {
		Weva_login();

		Thread.sleep(5000);
		System.out.println("execution  4" + Thread.currentThread().getId() + "Passed");
	}

}

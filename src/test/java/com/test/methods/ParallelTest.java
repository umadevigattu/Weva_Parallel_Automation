package com.test.methods;

import org.checkerframework.common.reflection.qual.GetMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	public void Valid_UserName_Valid_Password() throws Exception {
		System.out.println(Thread.currentThread().getId());
		Weva_login("sirishapatient@yopmail.com", "Test@1234");
		Thread.sleep(3000);
		WebElement homepage = getDriver().findElement(By.xpath("//a[text()='Find a Doctor']"));
		if (homepage.isDisplayed() == true) {
			softassert.assertEquals(homepage.getText(), "Find a Doctor");
			getScreenshot(getDriver(), "Login_Pass");
			System.out.println("Valid_UserName_Valid_Password " + Thread.currentThread().getId() + "Passed");

		}
		softassert.assertAll();
		extentTest.info("Valid_UserName_Valid_Password Completed");

	}

	@Test
	public void Blank_UserName_Valid_Password() throws Exception {
		System.out.println(Thread.currentThread().getId());
		Weva_login("", "Test@1234");
		WebDriverWait wait=new WebDriverWait(getDriver(), 3);
		WebElement emailErrormessage = getDriver().findElement(By.xpath("//p[text()='Email is required!']"));
		if (emailErrormessage.isDisplayed() == true) {
			softassert.assertEquals(emailErrormessage.getText(), "Email is required!");
			getScreenshot(getDriver(), "Blank_UserName_Valid_Password_Pass");
			System.out.println("Blank_UserName_Valid_Password " + Thread.currentThread().getId() + "Passed");

		}
		softassert.assertAll();
		extentTest.info("Blank_UserName_Valid_Password Completed");
	}

	@Test
	public void Valid_UserName_Blank_Password() throws Exception {
		System.out.println(Thread.currentThread().getId());
		Weva_login("sirishapatient@yopmail.com", "");
		Thread.sleep(3000);
		WebElement pwdErrormessage = getDriver().findElement(By.xpath("//p[text()='Password is required']"));
		if (pwdErrormessage.isDisplayed() == true) {
			softassert.assertEquals(pwdErrormessage.getText(), "Password is required");
			getScreenshot(getDriver(), "Valid_UserName_Blank_Password_Passed");
			System.out.println("Valid_UserName_Blank_Password " + Thread.currentThread().getId() + "Passed");

		}
		softassert.assertAll();
		extentTest.info("Valid_UserName_Blank_Password Completed");

	}

	@Test
	public void Validate_Invalid_Xapth() throws Exception {
		Weva_login("sirishapatient@yopmail.com", "Test@1234");
		Thread.sleep(3000);
		WebElement homepage = getDriver().findElement(By.xpath("//a[text()='Find a Docto']"));
		if (homepage.isDisplayed() == true) {

			softassert.assertEquals(homepage.getText(), "Find a Doctor");
			System.out.println("execution 2 " + Thread.currentThread().getId() + "Passed");
			getScreenshot(getDriver(), "Find a Doctor_screen");

		} else {
			getScreenshot(getDriver(), "Find a Doctor_screen");
		}
		softassert.assertAll();
		extentTest.info("Validate_Invalid_Xapth Completed");

	}

	@Test
	public void Validate_Text_on_WebPage() throws Exception {
		Weva_login("sirishapatient@yopmail.com", "Test@1234");
		Thread.sleep(3000);
		WebElement homepage = getDriver().findElement(By.xpath("//a[text()='Find a Doctor']"));
		if (homepage.isDisplayed() == true) {
			System.out.println("execution  3" + Thread.currentThread().getId() + "Passed");
			softassert.assertEquals(homepage.getText(), "Find a Docto");
			getScreenshot(getDriver(), "Find a Doctor_screen");

		} else {
			getScreenshot(getDriver(), "Find a Doctor_screen");
		}
		softassert.assertAll();
		extentTest.info("Validate_Text_on_WebPage Completed");
	}

}

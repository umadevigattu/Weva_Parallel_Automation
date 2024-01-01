package com.weva.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.Weva.constants.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumUtilities extends Basetest implements IDriverFactory {
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	static String username = "";
	static String password = "";

	public WebDriver getDriver() {
		return (WebDriver) driver.get();

	}

	public WebDriver getDriver1() {
		return (WebDriver) driver.get();

	}

	@AfterMethod
	public void teardown() {
		getDriver().quit();
	}

	public void Click(String element, LocatorType locatorType) {
		FindElement(element, locatorType).click();
	}

	public void ClickElement(String element, LocatorType locatorType, String value) {
		FindElement(element, locatorType).click();
		FindElement(element, locatorType).sendKeys(value);
	}

	public void Weva_login(String username,String password) throws Exception {
		getDriver().get(properties.getProperty("Weva_Url"));
		getDriver().manage().window().maximize();
		Thread.sleep(Constants.SHORT_WAIT);
		extentTest.info("Enter valid Username as " + username);
		EnterText("email", LocatorType.name, username);
		extentTest.info("Entered valid Username as " + username);
		extentTest.info("Enter valid Password as " + username);
		EnterText("password", LocatorType.name, password);
		extentTest.info("Entered valid Password as " + username);
		Thread.sleep(Constants.SHORT_WAIT);
		extentTest.info("Click on Login button");
		Click("//button[@type='submit']", LocatorType.xpath);
		Thread.sleep(Constants.SHORT_WAIT);
		extentTest.info("Clicked on Login button");

	}
	
	

	public void WevaLogin_muliple() throws Exception {
		
		FileInputStream fis = new FileInputStream(Constants.LoginTestData);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum();
		for (int rownum = 1; rownum <= sheet.getLastRowNum(); rownum++) {
			CreateWebDriver(BrowserType.chrome);
			ThreadLocal<WebDriver> driver = new ThreadLocal<>();
			getDriver().get(properties.getProperty("Weva_Url"));
			CommonUtils.ExcelReader();

			Row row = sheet.getRow(rownum);
			username = row.getCell(0).getStringCellValue();
			extentTest.info("username is " + row.getCell(0).getStringCellValue());
			password = row.getCell(1).getStringCellValue();
			extentTest.info("Password is " + row.getCell(1).getStringCellValue());
			EnterText("//input[@name='email']", LocatorType.xpath, username);
			extentTest.info("User Entered Username as " + row.getCell(0).getStringCellValue());
			EnterText("//input[@name='password']", LocatorType.xpath, password);
			extentTest.info("User Entered Password as " + row.getCell(1).getStringCellValue());
			Click("//button[text()='Sign in']", LocatorType.xpath);
			extentTest.info("User Clicked on Submit");
			Thread.sleep(Constants.MEDIUM_WAIT);
			CloseWindow();
			extentTest.info("User Logged in Successfully");
		}
	}

	public void EnterText(String element, LocatorType locatorType, String value) {
		FindElement(element, locatorType).clear();
		FindElement(element, locatorType).sendKeys(value);
	}

	public void ClearText(String element, LocatorType locatorType) {
		FindElement(element, locatorType).clear();
	}

	public WebElement FindElement(String element, LocatorType elementType) {
		WebElement findElement = null;
		try {
			if (elementType == LocatorType.id) {
				findElement = getDriver().findElement(By.id(element));
			} else if (elementType == LocatorType.name) {
				findElement = getDriver().findElement(By.name(element));
			} else if (elementType == LocatorType.xpath) {
				findElement = getDriver().findElement(By.xpath(element));
			} else if (elementType == LocatorType.linktext) {
				findElement = getDriver().findElement(By.linkText(element));
			} else if (elementType == LocatorType.classname) {
				findElement = getDriver().findElement(By.className(element));
			} else if (elementType == LocatorType.tagname) {
				findElement = getDriver().findElement(By.tagName(element));
			} else if (elementType == LocatorType.cssselector) {
				findElement = getDriver().findElement(By.cssSelector(element));
			} else if (elementType == LocatorType.partiallinktext) {
				findElement = getDriver().findElement(By.partialLinkText(element));
			}
			return findElement;

		} catch (Exception e) {
			System.out.println(e);
			return null;

		}

	}

	public String GetText(String element, LocatorType locatorType) {
		return FindElement(element, locatorType).getText();
	}

	public void Sleep() throws InterruptedException {
		Thread.sleep(4000);
	}

	public void Refresh() {
		getDriver().navigate().refresh();
	}

	public void CloseWindow() {
		getDriver().quit();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/ScreenShots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	// To Select Radio Button.
	public void selectRadioButton(List<WebElement> element, String value) {
		for (WebElement elements : element) {
			if (elements.getText().equalsIgnoreCase(value)) {
				elements.click();
				break;
			}
		}
	}

	// To Handle Multiple Windows or Switch Between Multiple Windows.
	public void switchWindow(WebDriver driver, String firstWindow, String secondWindow) {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windows : windowHandles) {
			if (!windows.equals(firstWindow) && !windows.equals(secondWindow)) {
				driver.switchTo().window(windows);
			}
		}
	}

	// Element is displayed or not
	public Boolean IsElementDisplayed(String element, LocatorType locatorType) {
		try {
			WebElement ele = this.FindElement(element, locatorType);
			if (ele.isDisplayed()) {
				return true;

			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// Get text of a web element if element is displayed
	public String GetTextIfElementDisplayed(String element, LocatorType locatorType) {
		if (IsElementDisplayed(element, locatorType)) {

			String text = GetText(element, locatorType);
			System.out.println(text);
		}
		return null;

	}

	// Click on the element if element is displayed
	public void ClickElementIfDisplayed(String element, LocatorType locatorType) {
		if (IsElementDisplayed(element, locatorType)) {

			Click(element, locatorType);
		}

	}

	// To Check Element is Displayed or No.
	public static void isElementDisplayed(WebElement element) {
		boolean elementDisplayed = element.isDisplayed();
		if (elementDisplayed) {
			System.out.println("Element is Displayed");
		} else {
			System.out.println("Element is not Displayed");
		}
	}

	// To Check Element is Enabled or No.
	public static void isElementEnabled(WebElement element) {
		boolean elementEnabled = element.isEnabled();
		if (elementEnabled) {
			System.out.println("Element is Enabled");
		} else {
			System.out.println("Element is not Enabled");
		}
	}

	public List<WebElement> FindElements(String element, LocatorType elementType) {
		List<WebElement> findElements = null;
		try {
			if (elementType == LocatorType.id) {
				findElements = getDriver().findElements(By.id(element));
			} else if (elementType == LocatorType.name) {
				findElements = getDriver().findElements(By.name(element));
			} else if (elementType == LocatorType.xpath) {
				findElements = getDriver().findElements(By.xpath(element));
			} else if (elementType == LocatorType.cssselector) {
				findElements = getDriver().findElements(By.cssSelector(element));
			} else if (elementType == LocatorType.tagname) {
				findElements = getDriver().findElements(By.tagName(element));
			} else if (elementType == LocatorType.classname) {
				findElements = getDriver().findElements(By.className(element));
			} else if (elementType == LocatorType.linktext) {
				findElements = getDriver().findElements(By.linkText(element));
			} else if (elementType == LocatorType.partiallinktext) {
				findElements = getDriver().findElements(By.partialLinkText(element));
			}
			return findElements;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public WebDriver CreateWebDriver(BrowserType browser) {
		// TODO Auto-generated method stub

		try {

			switch (browser) {
			case chrome:

				WebDriverManager.chromedriver().setup();
				driver.set(new ChromeDriver());
				break;
			case firefox:

				WebDriverManager.firefoxdriver().setup();
				driver.set(new FirefoxDriver());
				break;

			default:
				break;

			}
			return (WebDriver) driver;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

}

package com.weva.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
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
	/*
	 * @BeforeMethod public void setUp() { CreateWebDriver(BrowserType.chrome);
	 * 
	 * }
	 */

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

	public void Weva_login() throws Exception {
		getDriver().get(properties.getProperty("Weva_Url"));
		Thread.sleep(6000);
		extentTest.info("Enter valid username as " + properties.getProperty("username"));
		EnterText("email", LocatorType.name, "sirishapatient@yopmail.com");
		extentTest.info("Enter valid username as " + properties.getProperty("username"));
		getDriver().findElement(By.name("password")).sendKeys("Test@1234");
		getDriver().findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(6000);

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

package Activities;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity5 {
	AppiumDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() throws MalformedURLException, URISyntaxException {
		// Desired Capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.android.chrome");
		options.setAppActivity("com.google.android.apps.chrome.Main");
		options.noReset();

		// Server URL
		URL serverURL = new URI("http://localhost:4723").toURL();

		// Driver initialization
		driver = new AndroidDriver(serverURL, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Open Selenium page
		driver.get("https://training-support.net/webelements");

	}

	@Test
	public void validLoginTest() {
//		Scroll to find the Login Form card and click it.
		String uiScrollable = "new UiScrollable(new UiSelector().scrollable(true))";
		driver.findElement(AppiumBy
				.androidUIAutomator(uiScrollable + ".scrollIntoView(" + "new UiSelector().text(\"Login Form\"))"))
				.click();
		
//		Once the page loads, find the username and password input fields on the page and enter the following credentials in it:
		WebElement username = driver
				.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]"));
		WebElement password = driver
				.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"));

		username.clear();
		password.clear();

		username.sendKeys("admin");
		password.sendKeys("password");

		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Submit\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Login Success!\"]")));

		String message = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Login Success!\"]"))
				.getText();
		assertEquals(message, "Login Success!");

	}

	@Test
	public void invalidLoginTest() {
//		Scroll to find the Login Form card and click it.
		String uiScrollable = "new UiScrollable(new UiSelector().scrollable(true))";
		driver.findElement(AppiumBy
				.androidUIAutomator(uiScrollable + ".scrollIntoView(" + "new UiSelector().text(\"Login Form\"))"))
				.click();
		
//		Once the page loads, find the username and password input fields on the page and enter the following credentials in it:
		WebElement username = driver
				.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]"));
		WebElement password = driver
				.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"));

		username.clear();
		password.clear();

		username.sendKeys("admin");
		password.sendKeys("passwrd");
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Submit\"]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"subheading\"]")));
		String message = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"subheading\"]")).getText();
		assertEquals(message, "Invalid credentials");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}

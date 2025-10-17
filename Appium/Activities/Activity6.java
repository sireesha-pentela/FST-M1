package Activities;

import static Activities.ActionsBase.doTap;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity6 {
	AppiumDriver driver;
	WebDriverWait wait;

	@BeforeClass
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
	public void test() {
		// Get the size of the screen
		Dimension dims = driver.manage().window().getSize();
		// Set the start and end points
		Point start = new Point((int) (dims.getWidth() * .45), (int) (dims.getHeight() * .24));
				
		String uiScrollable = "new UiScrollable(new UiSelector().scrollable(true))";
        driver.findElement(AppiumBy.androidUIAutomator(
                uiScrollable + ".scrollForward(15).scrollIntoView(new UiSelector().text(\"Popups\"))"
        )).click();
		
//		Once the page loads, find the button on the page to open a popup with the login form.
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"launcher\"]")).click();
//		Find the username and password input fields in the popup and enter the following credentials in it:
//		Username: admin
//		Password: password
		doTap(driver, start);
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]"))).sendKeys("admin");
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"))).sendKeys("password");
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Submit\"]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Login Success!\"]")));

		String message = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Login Success!\"]"))
				.getText();
		assertEquals(message, "Login Success!");
		

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

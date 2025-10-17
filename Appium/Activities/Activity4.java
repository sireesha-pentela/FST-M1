package Activities;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity4 {
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
	public void toDoListTest() {
//		Scroll to find the To-Do List card and click it.
		String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
		driver.findElement(AppiumBy.androidUIAutomator(UiScrollable + ".flingForward()"));
		driver.findElement(AppiumBy.accessibilityId("To-Do List Elements get added at runtime!")).click();

//		Once the page loads, find the input field on the page and enter the following three tasks:
//		Add tasks to list
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"todo-input\"]"))).sendKeys("Add tasks to list");
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"todo-add\"]")).click();

//		Get number of tasks
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"todo-input\"]"))).sendKeys("Get number of tasks");
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"todo-add\"]")).click();

//		Clear the list
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"todo-input\"]"))).sendKeys("Clear the list");
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"todo-add\"]")).click();

//		Click on each of the tasks added to strike them out.
		driver.findElement(AppiumBy
				.xpath("//android.widget.ListView/android.view.View[3]/android.view.View/android.widget.CheckBox"))
				.click();
		driver.findElement(AppiumBy
				.xpath("//android.widget.ListView/android.view.View[4]/android.view.View/android.widget.CheckBox"))
				.click();
		driver.findElement(AppiumBy
				.xpath("//android.widget.ListView/android.view.View[5]/android.view.View/android.widget.CheckBox"))
				.click();

//		Add assertions to verify the count of the number of tasks in the list. Hint: There are 2 tasks already present in the list.
		List<WebElement> activities = driver.findElements(AppiumBy.className("android.widget.CheckBox"));
		assertEquals(activities.size(), 5);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}


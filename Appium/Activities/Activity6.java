package Activities;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity6 {
    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;

    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {
    	 	
        // Step 1: Create an object to define desired capabilities for the Android device
        UiAutomator2Options options = new UiAutomator2Options();

        // Step 2: Set the platform name (Android)
        options.setPlatformName("android");

        // Step 3: Set the automation engine (UiAutomator2 is the most commonly used for Android)
        options.setAutomationName("UiAutomator2");
    	
		options.setAppPackage("com.android.chrome");
		options.setAppActivity("com.google.android.apps.chrome.Main");

        // Step 5: Do not reset app data between sessions (useful for faster tests)
        options.noReset();

        // Step 6: Set the Appium server URL (Appium must be running on this address/port)
        URL serverURL = new URI("http://localhost:4723").toURL();

        // Step 7: Initialize the Android driver with the server URL and capabilities
        driver = new AndroidDriver(serverURL, options);        
    	
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
 
		// Open Selenium page
		driver.get("https://training-support.net/webelements/sliders");
    }
    
    @Test
	public void volume75Test() {
		// Wait for page to load
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.SeekBar")));
		// Get the size of the screen
		Dimension dims = driver.manage().window().getSize();
		// Set the start and end points
		Point start = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .72));
		Point end = new Point((int) (dims.getWidth() * .67), (int) (dims.getHeight() * .72));
		// Perform swipe
		ActionsBase.doSwipe(driver, start, end, 2000); //2000 milli seconds
 
		// Get the volume level
		String volumeText = driver
			.findElement(AppiumBy.xpath("//android.view.View/android.widget.TextView[contains(@text, '%')]"))
			.getText();
 
		// Assertions
		assertTrue(volumeText.contains("75%"));
	}
 
	@Test
	public void volume25Test() {
		// Wait for page to load
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.SeekBar")));
		// Get the size of the screen
		Dimension dims = driver.manage().window().getSize();
		// Set the start and end points
		Point start = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .72));
		Point end = new Point((int) (dims.getWidth() * .335), (int) (dims.getHeight() * .72));
		// Perform swipe
		ActionsBase.doSwipe(driver, start, end, 2000);
 
		// Get the volume level
		String volumeText = driver
			.findElement(AppiumBy.xpath("//android.view.View/android.widget.TextView[contains(@text, '%')]"))
			.getText();
 
		// Assertions
		assertTrue(volumeText.contains("25%"));
	}
 
	@AfterClass
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}
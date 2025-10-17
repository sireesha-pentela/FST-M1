package Project;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Act6_Popup {
    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;
    
    static final PointerInput pointer = new PointerInput(Kind.TOUCH,"pointer");

    // Set up method
    @BeforeTest
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
		driver.get("https://training-support.net/webelements");
    }
    
 // Test method
    @Test (priority = 1)
    public void popUPLogin() {
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='WebElements']")));
        
    	Dimension dims = driver.manage().window().getSize();
    	Point start = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .95));
    	Point end = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .05));

        Sequence swipe = new Sequence(pointer, 1)
                .addAction(pointer.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(pointer.createPointerDown(LEFT.asArg()))
                .addAction(pointer.createPointerMove(ofMillis(400), viewport(), end.getX(), end.getY()))
                .addAction(pointer.createPointerUp(LEFT.asArg()));
        
        // Perform the actions
        driver.perform(Arrays.asList(swipe));
    	
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Popups']"))).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id=\"launcher\"]"))).click();
        //use tap to enter username & password
        
        start = new Point((int) (dims.getWidth() * .25), (int) (dims.getHeight() * .25));
        Sequence tap = new Sequence(pointer, 1)
                .addAction(pointer.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(pointer.createPointerDown(LEFT.asArg()))
                .addAction(new Pause(pointer, Duration.ofMillis(80)))
                .addAction(pointer.createPointerUp(LEFT.asArg()));
        
        driver.perform(Arrays.asList(tap));        
        
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']"))).sendKeys("admin");
        
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys("password");
        
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Submit']")).click();
        
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AppiumBy.xpath("//android.widget.TextView[@text='Login Success!']")));
        
        String message = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Login Success!']")).getText();
        
        Assert.assertEquals(message, "Login Success!");          
       		
    }
    
    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}
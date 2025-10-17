package Project;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Act4_ToDoPage {
    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;
    
    static final PointerInput pointer = new PointerInput(Kind.TOUCH,"pointer");

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
		driver.get("https://training-support.net/webelements");
    }
    
 // Test method
    @Test
    public void todoList() {
    	    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='WebElements']")));
        
    	Dimension dims = driver.manage().window().getSize();
    	Point start = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .90));
    	Point end = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .55));

        Sequence swipe = new Sequence(pointer, 1)
                .addAction(pointer.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(pointer.createPointerDown(LEFT.asArg()))
                .addAction(pointer.createPointerMove(ofMillis(100), viewport(), end.getX(), end.getY()))
                .addAction(pointer.createPointerUp(LEFT.asArg()));
        
        // Perform the actions
        driver.perform(Arrays.asList(swipe));
    	
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='To-Do List']"))).click();
        
      //  driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='To-Do List']")).click();
        
        List<String> todolist = new ArrayList<String>();
        todolist.add("Add tasks to list");
        todolist.add("Get number of tasks");
        todolist.add("Clear the list");
        
        for(String list : todolist) {
        	
        	wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='todo-input']"))).sendKeys(list);
        	wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='todo-add']"))).click();
        }
    	
        List<WebElement> listnames = driver.findElements(AppiumBy.className("android.widget.CheckBox"));
        
        listnames.get(2).click();
        listnames.get(3).click();
        listnames.get(4).click();
        
        Assert.assertEquals(5, listnames.size());       		
        		
        } 
 
    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}

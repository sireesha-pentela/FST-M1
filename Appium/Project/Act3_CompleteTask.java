package Project;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Activities.ActionsBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Act3_CompleteTask {
    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;
	static PointerInput finger = new PointerInput(Kind.TOUCH, "finger");


    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {
    	
    	File apkFile = new File("src/test/resources/ts-todo-list-v1.apk");
    	
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("android");

        options.setAutomationName("UiAutomator2");

        options.setApp(apkFile.getAbsolutePath());

        options.noReset();

        URL serverURL = new URI("http://localhost:4723").toURL();
        
        driver = new AndroidDriver(serverURL, options);
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
  
    @Test(priority = 1)
    //Goal: Edit a task to add a deadline.
    public void edittsk() {
    	
    	Dimension dims = driver.manage().window().getSize();
    	Point start = new Point((int) (dims.getWidth() * .45), (int) (dims.getHeight() * .10));
    	    	
    	Sequence longPress = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(finger.createPointerDown(LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(600)))
                .addAction(finger.createPointerUp(LEFT.asArg()));

    	// Perform the actions
        driver.perform(Arrays.asList(longPress));
        
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']")).click();
        
		// Set the start and end points
		start = new Point((int) (dims.getWidth() * .28), (int) (dims.getHeight() * .54));
		Point end = new Point((int) (dims.getWidth() * .92), (int) (dims.getHeight() * .54));
		// Perform swipe
		ActionsBase.doSwipe(driver, start, end, 2000);
             
        driver.findElement(AppiumBy.id("bt_new_task_ok")).click(); 
        
        //2nd task completion
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("tv_exlv_task_name")));
        
    	start = new Point((int) (dims.getWidth() * .45), (int) (dims.getHeight() * .20));
    	
    	longPress = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(finger.createPointerDown(LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(700)))
                .addAction(finger.createPointerUp(LEFT.asArg()));
    	
    	driver.perform(Arrays.asList(longPress));
    	
    	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']")).click();
    	
    	start = new Point((int) (dims.getWidth() * .28), (int) (dims.getHeight() * .54));
		end = new Point((int) (dims.getWidth() * .92), (int) (dims.getHeight() * .54));
		// Perform swipe
		ActionsBase.doSwipe(driver, start, end, 2000);
             
        driver.findElement(AppiumBy.id("bt_new_task_ok")).click(); 
        
        //3rd task 50% completion  
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("tv_exlv_task_name")));
        
    	start = new Point((int) (dims.getWidth() * .45), (int) (dims.getHeight() * .27));
    	
    	longPress = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(finger.createPointerDown(LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(700)))
                .addAction(finger.createPointerUp(LEFT.asArg()));
    	
    	driver.perform(Arrays.asList(longPress));
    	
    	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']")).click();
    	
    	start = new Point((int) (dims.getWidth() * .28), (int) (dims.getHeight() * .54));
		end = new Point((int) (dims.getWidth() * .60), (int) (dims.getHeight() * .54));
		// Perform swipe
		ActionsBase.doSwipe(driver, start, end, 2000);
             
        driver.findElement(AppiumBy.id("bt_new_task_ok")).click(); 
        
        List<WebElement> element = driver.findElements(AppiumBy.className("android.widget.CheckBox"));
        
        element.get(0).click();
        element.get(1).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView[@content-desc='More options']"))).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/title\" and @text='Completed tasks']"))).click();
        
        List<WebElement> checkboxcount = driver.findElements(AppiumBy.className("android.widget.CheckBox"));
        
        Assert.assertEquals(2, checkboxcount.size());
        
//        
//        String deadlineDate = driver.findElement(AppiumBy.id("tv_exlv_task_deadline")).getText();
//        
//        Assert.assertEquals(deadlineDate, "Deadline: 31.10.2025");
        
    }
    
    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }

}
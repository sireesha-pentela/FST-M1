package Project;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Act1_AddToDoList {
    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;


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
    
    // Test method
    @Test(priority = 1)
    //Goal: Use the To-Do list app to create a list of tasks that need to be completed.
    public void toDotasks() {
    	
    	int i=0;
    	List<String> stringList = new ArrayList<>();
    	stringList.add("Complete Activity 1 with priority high");
    	stringList.add("Complete Activity 2 with priority medium");
    	stringList.add("Complete Activity 3 with priority low");
    	
    	for(String listname: stringList) {
    		
            driver.findElement(AppiumBy.id("fab_new_task")).click();
            
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("et_new_task_name"))).sendKeys(listname);        

            driver.findElement(AppiumBy.id("bt_new_task_ok")).click();		
    		
    	}
   	
   	
    	List<WebElement> task = driver.findElements(AppiumBy.id("tv_exlv_task_name"));
		System.out.println(task);
    	for (WebElement element : task)  {
	    	
    		Assert.assertEquals(stringList.get(i), element.getText());
    		System.out.println(stringList.get(i));
    		System.out.println(element.getText());
    		i++;
    	}
	    	
   }

}

package Activities;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity1 {
	AndroidDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {

		File apkFile = new File("src/test/resources/ts-todo-list-v1.apk");

		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setApp(apkFile.getAbsolutePath());
		options.noReset();

		URL serverUrl = new URI("http://localhost:4723").toURL();
		driver = new AndroidDriver(serverUrl, options);
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void addTaskTest() {
//		Click the button to add a new task.
//		Complete Activity 1 with priority high
		driver.findElement(AppiumBy.id("fab_new_task")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("et_new_task_name"))).sendKeys("Complete Activity 1 with priority high");
		driver.findElement(AppiumBy.id("tv_new_task_priority")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"High\"]")).click();
		driver.findElement(AppiumBy.id("bt_new_task_ok")).click();


		
//		Complete Activity 2 with priority medium
		driver.findElement(AppiumBy.id("fab_new_task")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("et_new_task_name"))).sendKeys("Complete Activity 2 with priority medium");
		driver.findElement(AppiumBy.id("bt_new_task_ok")).click();
		

//		Complete Activity 3 with priority low
		driver.findElement(AppiumBy.id("fab_new_task")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("et_new_task_name"))).sendKeys("Complete Activity 3 with priority low");
		driver.findElement(AppiumBy.id("tv_new_task_priority")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Low\"]")).click();
		driver.findElement(AppiumBy.id("bt_new_task_ok")).click();
		
//		Write an assertion to ensure all three tasks have been added to the list.
		
		String activity1 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_name\" and @text='Complete Activity 1 with priority high']")).getText();
		String activity2 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_name\" and @text='Complete Activity 2 with priority medium']")).getText();

		String activity3 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_name\" and @text='Complete Activity 3 with priority low']")).getText();

		assertEquals(activity1, "Complete Activity 1 with priority high");
		assertEquals(activity2, "Complete Activity 2 with priority medium");
		assertEquals(activity3, "Complete Activity 3 with priority low");
		
		
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
package Activities;

import static org.testng.Assert.assertEquals;
import static Activities.ActionsBase.doLongPress;

import java.io.File;
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

public class Activity2 {
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

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void addTaskTest() {
		// Get the size of the screen
		Dimension dims = driver.manage().window().getSize();
		// Set the start and end points
		Point start = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .12));

//	Long press the first task in the list to edit its details.
		doLongPress(driver, start);

//		Set a deadline for the task to be completed by next Saturday.
		driver.findElement(AppiumBy
				.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Edit To-Do Task\"]"))
				.click();
		driver.findElement(AppiumBy.id("tv_todo_list_deadline")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@content-desc=\"18 October 2025\"]"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("bt_deadline_ok"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("bt_new_task_ok"))).click();

//		Save the edited details and add assertion to confirm that the deadline is set.
		String deadline = driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_deadline\" and @text=\"Deadline: 18.10.2025\"]"))
				.getText();
		assertEquals("Deadline: 18.10.2025", deadline);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

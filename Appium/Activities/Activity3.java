package Activities;

import static Activities.ActionsBase.doLongPress;
import static Activities.ActionsBase.doSwipe;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity3 {
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
	public void test() {
		// Get the size of the screen
		Dimension dims = driver.manage().window().getSize();
		// Set the start and end points
		Point start1 = new Point((int) (dims.getWidth() * .45), (int) (dims.getHeight() * .28));
		Point start = new Point((int) (dims.getWidth() * .28), (int) (dims.getHeight() * .54));
		Point end = new Point((int) (dims.getWidth() * .602), (int) (dims.getHeight() * .539));

//	Mark the first two tasks as complete.
		wait.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"com.app.todolist:id/cb_task_done\"])[1]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"com.app.todolist:id/cb_task_done\"])[2]")))
				.click();
		
//	Long press the third task to edit it. In the edit details, slide the progress bar to 50% and save the details.
		doLongPress(driver, start1);
		driver.findElement(AppiumBy
				.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Edit To-Do Task\"]")).click();
		doSwipe(driver, start, end, 1500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("bt_new_task_ok"))).click();

//	Click the option icon and select “Completed tasks”.
		driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"com.app.todolist:id/title\" and @text=\"Completed tasks\"]")))
				.click();

//	Make an assertion to ensure that only 2 tasks as shown here.
		List<WebElement> activity = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AppiumBy.className("android.widget.CheckBox")));
		assertEquals(activity.size(), 2);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

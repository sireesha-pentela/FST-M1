package Project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectActivity1 {
	WebDriver driver;

	// Setup function
	@BeforeClass
	public void setUp() {
		// Initialize Firefox driver
		driver = new FirefoxDriver();
		// Open the page
		driver.get("https://alchemy.hguy.co/jobs/");

	}

	// Test function
	@Test(priority = 1)
	public void homePageTest() {
		// Assert page title
		String actualTitle = driver.getTitle();
		System.out.println("✅ Title matches: " + actualTitle);
		Assert.assertEquals(driver.getTitle(), "Alchemy jobs – Job Board Application");

	}

	@AfterClass
	public void tearDown() {
		// Step e: Close the browser
		if (driver != null) {
			driver.quit();
		}
	}
}
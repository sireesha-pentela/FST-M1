package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectActivity2 {
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
		public void HeadingPageTest() {
		// assert page headline
			WebElement headingElement = driver.findElement(By.xpath("//h1[text()='Welcome to Alchemy Jobs']"));
	      	        String actualHeading = headingElement.getText();
		
		Assert.assertEquals(headingElement.getText(), "Welcome to Alchemy Jobs");
		System.out.println("✅ Heading matches: " + actualHeading);
	}
		@AfterClass
		public void tearDown() {
			// Step e: Close the browser
			if (driver != null) {
				driver.quit();
			}
}
}

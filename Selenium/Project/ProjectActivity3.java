package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectActivity3 {
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
	public void UrlPageTest() {
	WebElement headerImage = driver.findElement(By.xpath("//img[@class='attachment-large size-large wp-post-image']"));
		String imageUrl = headerImage.getAttribute("src");
		System.out.println("✅ Header Image URL: " + imageUrl);

	}

	@AfterClass
	public void tearDown() {
		// Step e: Close the browser
		if (driver != null) {
			driver.quit();
		}
	}
}

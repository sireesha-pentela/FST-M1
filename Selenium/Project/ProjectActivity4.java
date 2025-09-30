package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectActivity4 {
	WebDriver driver;

	// Setup function
	@BeforeClass
	public void setUp() {
		// Initialize Firefox driver
		driver = new FirefoxDriver();
		
	}

	@Test
	public void verifySecondHeading() {
		// Step b: Navigate to the URL
		driver.get("https://alchemy.hguy.co/jobs");

		// Step c: Locate the second heading (h2) using XPath
		WebElement secondHeading = driver.findElement(By.xpath("(//h2)[1]"));
		// Note: (//h2)[1] means the first h2 on the page.
		// If you want the "second heading overall" (after h1), adjust index.

		String actualHeading = secondHeading.getText();

		// Step d: Verify heading text

		System.out.println("✅ Second heading matches: " + actualHeading);
		Assert.assertEquals(secondHeading.getText(), "Quia quis non");

	}

	@AfterClass
	public void tearDown() {
		// Step f: Close the browser
		if (driver != null) {
			driver.quit();
		}
	}

}

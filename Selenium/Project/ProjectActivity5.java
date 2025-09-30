package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectActivity5 {
	WebDriver driver;

	// Setup function
	@BeforeClass
	public void setUp() {
		// Initialize Firefox driver
		driver = new FirefoxDriver();
		
	}

	@Test
	public void navigateToJobsPage() {
		// Step b: Navigate to the site
		driver.get("https://alchemy.hguy.co/jobs");

		// Step c & d: Find the "Jobs" menu item in the navigation bar and click it
		WebElement jobsMenu = driver.findElement(By.linkText("Jobs"));
		jobsMenu.click();

		// Step e: Read the page title

		String actualTitle = driver.getTitle();
		System.out.println("✅ Successfully navigated to Jobs page. Title: " + actualTitle);

		// Verify the title
		Assert.assertEquals(driver.getTitle(), "Jobs – Alchemy Jobs");

	}
	@AfterClass
    public void tearDown() {
        // Step f: Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}

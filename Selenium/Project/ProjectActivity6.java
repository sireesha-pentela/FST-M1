package Project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectActivity6 {
	WebDriver driver;
	WebDriverWait wait;

	// Setup function
	@BeforeClass
	public void setUp() {
		// Initialize Firefox driver
		driver = new FirefoxDriver();

	}

	@Test
	public void applyToJob() {

		// ✅ Initialize WebDriverWait here
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		// Step a: Navigate to Jobs page
		driver.get("https://alchemy.hguy.co/jobs");
		driver.findElement(By.linkText("Jobs")).click();

		// Verify we are on Jobs page
		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, "Jobs – Alchemy Jobs");

		// Step b: Search for a job (e.g., "Banking")
		WebElement searchBox = driver.findElement(By.id("search_keywords"));
		searchBox.sendKeys("Banking");
		driver.findElement(By.cssSelector("input[value='Search Jobs']")).click();

		// Wait for job listings to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='job_listings']/li[1]")));
		// Get and click the first job
		WebElement firstJob = driver.findElement(By.cssSelector("ul.job_listings li a div.position h3"));
		System.out.println("Job Found: " + firstJob.getText());
		firstJob.click();
		// Wait for Job Description page to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("job_description")));
		System.out.println("Opened Job Page Title: " + driver.getTitle());
		// Click Apply for Job button
		WebElement applyButton = driver.findElement(By.className("application_button"));
		applyButton.click();
		// Wait for the application details to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("job_application")));
		// Get application email (or link)
		WebElement applicationEmail = driver.findElement(By.className("job_application"));
		System.out.println("Application details: " + applicationEmail.getText());

		// Get the email displayed
		WebElement emailElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("job_application_email")));
		String email = emailElement.getText();
		// Print email to console

		System.out.println("To apply for this job email your details to " + email);

		// Optional assertion
		Assert.assertEquals(email, "abhiram@cklabs.com", "Email does not match expected value!");

	}

	@AfterClass
	public void tearDown() {
		// Step e: Close browser
		if (driver != null) {
			driver.quit();
		}
	}

}
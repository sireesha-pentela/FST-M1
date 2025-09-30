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

public class ProjectActivity7 {
	WebDriver driver;
	WebDriverWait wait;
	
	// Setup function
		@BeforeClass
		public void setUp() {
			// Initialize Firefox driver
			driver = new FirefoxDriver();

		}

		 @Test
		public void postNewJob() {
		        // Step 1: Open site
		        driver.get("https://alchemy.hguy.co/jobs");
		        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		        // Step 2: Click "Post a Job"
		        driver.findElement(By.linkText("Post a Job")).click();

		        // Step 3: Wait for form to load
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit-job-form")));

		        // Step 4: Fill job title
		        WebElement jobTitle = driver.findElement(By.id("job_title"));
		        jobTitle.sendKeys("TestNG Automation Engineer");

		        // Step 5: Fill email
		        WebElement email = driver.findElement(By.id("create_account_email"));
		        email.sendKeys("testnguser@example.com");

		        // Step 6: Fill company name
		        WebElement companyName = driver.findElement(By.id("company_name"));
		        companyName.sendKeys("Alchemy TestNG Solutions ");

		        // Step 7: Fill job description (inside iframe)
		        driver.switchTo().frame("job_description_ifr");
		        WebElement body = driver.findElement(By.id("tinymce"));
		        body.sendKeys("We are looking for a TestNG Automation Engineer with Selenium experience.");
		        driver.switchTo().defaultContent();

		        // Step 8: Click Preview
		        driver.findElement(By.name("submit_job")).click();

		        // Step 9: Wait and submit listing
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("job_preview_submit_button")));
		        driver.findElement(By.id("job_preview_submit_button")).click();

		        // Step 10: Verify job was posted
		        WebElement confirmation = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(By.className("job_listing"))
		        );
		        String jobText = confirmation.getText();
		        System.out.println("Job successfully posted: " + jobText);

		        // Assertion
		        Assert.assertTrue(jobText.contains("TestNG Automation Engineer"), "Job not posted successfully!");
		    }

		    @AfterClass
		    public void tearDown() {
		        driver.quit();
		    }
		}


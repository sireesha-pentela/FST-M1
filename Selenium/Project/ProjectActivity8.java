package Project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProjectActivity8 {
	WebDriver driver;
	WebDriverWait wait;

	// Setup function
	@BeforeClass
	public void setUp() {
		// Initialize Firefox driver
		driver = new FirefoxDriver();
		// Initialize the wait object
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		// Open the site
		driver.get("https://alchemy.hguy.co/jobs/wp-admin");

	}

	@Test
	@Parameters({ "username","password"})
	public void siteBackendLogin(String username,String password) throws InterruptedException  {

		// Step c: Find username field and enter username
		WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_login")));
		usernameField.clear();
		usernameField.sendKeys(username);

		// Step d: Find password field and enter password
		WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_pass")));
		passwordField.clear();
		//sysout.println(password);
		passwordField.sendKeys(password);
		Thread.sleep(5000);

		// Step e: Find login button and click
		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("wp-submit")));
		loginButton.click();

		// Step f: Verify login (check URL or admin bar)
		wait.until(ExpectedConditions.urlContains("/wp-admin/"));
		System.out.println("Login successful!");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
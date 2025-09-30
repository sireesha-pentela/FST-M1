package Project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Goal: Visit the site’s backend and login
public class ProjectActivity9 {
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	
	@BeforeClass
	public void beforeclass() {
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://alchemy.hguy.co/jobs/wp-admin");
	}
	
	@Test
	public void backEndCreateJob() throws InterruptedException {
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("wp-submit")).click();
		
		driver.findElement(By.xpath("//div[text()='Job Listings']")).click();
		
		driver.findElement(By.linkText("Add New")).click();
		
		actions.sendKeys(Keys.TAB).perform();
        actions.click().perform();
        
        String jobname = "SampleJob1";
        
        driver.findElement(By.id("post-title-0")).sendKeys(jobname);
        
        driver.findElement(By.xpath("//input[@name='_job_location']")).sendKeys("Hyderabad");
        
        driver.findElement(By.xpath("//input[@name='_company_name']")).sendKeys("IBM");
        
        driver.findElement(By.xpath("//button[text()='Publish…']")).click();
        
        Thread.sleep(2000);
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[text()='Publish']")));
        
        driver.findElement(By.xpath("//button[text()='Publish']")).click();
        
        Thread.sleep(2000);
        
        driver.findElement(By.linkText("View Job")).click();      
        
        assertEquals(jobname, driver.findElement(By.cssSelector("h1.entry-title")).getText());        
		
	}
	
	@AfterClass
	public void close() {
		driver.quit();
	}

}
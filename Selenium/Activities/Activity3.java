package Activities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity3 {

	public static void main(String[] args) {
		// Create a driver object
		WebDriver driver =new FirefoxDriver();
		//make program to wait 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//open the page
		driver.get("https://training-support.net/webelements/login-form");
		//print the title of the page
		System.out.println("page title: " + driver.getTitle());
		
		//find the username field and type in the username
		//driver.findElements(By.xpath("//input[@placeholder='Username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("admin");
		//find the password field and type password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(h2[contains(@class, 'text-gray-800'])));
		//String message = driver.findElement(By)
		String message = driver.findElement(By.xpath("//h2[contains(@class, 'text-gray-800']")).getText();
		System.out.println(message);
		driver.quit();
		//

	}

}

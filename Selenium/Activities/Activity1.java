package Activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity1 {

	public static void main(String[] args) {
		// Create a driver object
		WebDriver driver =new FirefoxDriver();
		//open the page
		driver.get("https://training-support.net");
		//verify using the page title
		System.out.println("Page Title is: " + driver.getTitle());
		//locate and click the about us link
		driver.findElement(By.linkText("About Us")).click();
		//print the title of the about us page
		System.out.println("New page title is: " + driver.getTitle());
		
		//close the browser
		//driver.quit();
		

	}

}

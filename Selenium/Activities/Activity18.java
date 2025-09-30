package Activities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity18 {

	public static void main(String[] args) {
		// Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();

        // Open the page
        driver.get("https://training-support.net/webelements/alerts");
        // Print the title of the page
        System.out.println("Page title: " + driver.getTitle());
        
        //find and click the simple button
        driver.findElement(By.id("simple")).click();
        //switch focus to the simple alert
        Alert simpleAlert = driver.switchTo().alert();
        //print the text from the alert
        System.out.println(simpleAlert.getText());
        //click ok on the alert
        simpleAlert.accept();
        //print the message
        System.out.println(driver.findElement(By.id("result")).getText());
        //close the browser
        driver.quit();

	}

}

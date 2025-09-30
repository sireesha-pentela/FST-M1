package Activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Activity16 {

	public static void main(String[] args) {
		//create an instance of firefox driver
		WebDriver driver = new FirefoxDriver();
		//open the web page
		driver.get(" https://training-support.net/webelements/selects");
		
		//verify the page title
		System.out.println("page title:" + driver.getTitle());
		
		//find the dropdown and create a select object
		WebElement select1 = driver.findElement(By.cssSelector("select.h-10"));
		Select dropdown = new Select(select1);
		//Select the second option using the visible text.
		dropdown.selectByVisibleText("One");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		//Select the third option using the index.
		dropdown.selectByIndex(2);
		System.out.println(dropdown.getFirstSelectedOption().getText());
		//Select the fourth option using the value.
		dropdown.selectByValue("three");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		//Get all the options and print them to the console.
		for(WebElement option : dropdown.getOptions()) {
			System.out.println(option.getText());
		}
		//close the browser
		driver.quit();

	}

}

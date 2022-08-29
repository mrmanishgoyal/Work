package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import resources.base;

public class StudentProgPO extends base {
	WebDriver driver;

	
	private String lnkSSOxpath= "//span[text()='Student Sales Order']/following::span[@force-lookup_lookup]";
	


	// Declaring Constructor
	public StudentProgPO(WebDriver driver) {
		this.driver = driver;
	}
	

	//Click on Student Sales Order
	public void ClickSSO() throws InterruptedException {
	 driver.findElement(By.xpath(lnkSSOxpath)).click();
	  Thread.sleep(3000);
	}
	  

}

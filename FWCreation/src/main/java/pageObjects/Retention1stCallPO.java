package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.base;

public class Retention1stCallPO extends base {
	WebDriver driver;

	private String btnSavexpath = "//div/span[text()='Retention - First Call']/following::span[text()='Save']";
	
	// Declaring Constructor
	public Retention1stCallPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Select the First call status as Call Completed 
	public void EnterCompletedcall() throws InterruptedException {
		driver.findElement(By.xpath("//div/span[text()='Retention - First Call']/following::span[text()='Call Status']/following::button")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//div/span[text()='Retention - First Call']/following::span[text()='Call Status']/following::a[@class='select']")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//div/span[text()='Retention - First Call']/following::span[text()='Call Status']/following::a[text()='Call Completed']")).click();
		Thread.sleep(800);
		
	}
    	
   
    //Click Save button
	public void ClickSave() throws InterruptedException {
		driver.findElement(By.xpath(btnSavexpath)).click();
		Thread.sleep(4000);
	}
		

	
}

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import resources.base;

public class StudentSalesOrderPO extends base {
	WebDriver driver;

	
	private String lnkSSOxpath= "//span[text()='Student Sales Order']/following::span[@force-lookup_lookup]";
	


	// Declaring Constructor
	public StudentSalesOrderPO(WebDriver driver) {
		this.driver = driver;
	}
	 
	//Select the OMS Order Status as Partial Delivered
	public void SelectPartialDel() throws InterruptedException {
	  driver.findElement(By.xpath("//span[text()='OMS Order Status']/following::button")).click();
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//label[text()='OMS Order Status']/following::span")).click();
	  Thread.sleep(800);
	  
	  driver.findElement(By.xpath("//label[text()='OMS Order Status']/following::span[text()='Partial Delivered']")).click();
	  Thread.sleep(800);
	}
	
	//Click Save Button
	public void ClickSave() throws InterruptedException {
	  driver.findElement(By.xpath("//button[text()='Save']")).click();
	  Thread.sleep(2000);
	}
	  
	  


}

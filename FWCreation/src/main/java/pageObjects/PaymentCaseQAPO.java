package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import resources.ExcelData;

public class PaymentCaseQAPO {
	WebDriver driver;

	
	//private String btnNextxpath = "//span[text()='Next']";
	

	// Declaring Constructor
	public PaymentCaseQAPO(WebDriver driver) {
		this.driver = driver;
	}

	//Click on New Case Button
	public void ClickCaseNewButton(String PaymentRecord) throws InterruptedException {
		
	driver.findElement(By.xpath("//a[text()='"+PaymentRecord+"']/following::div[@title='New']")).click();
	Thread.sleep(2000);
	}
	

}

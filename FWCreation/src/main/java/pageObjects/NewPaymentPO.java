package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import resources.ExcelData;

public class NewPaymentPO {
	WebDriver driver;
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	
	private String btnNextxpath = "//span[text()='Next']";
	

	// Declaring Constructor
	public NewPaymentPO(WebDriver driver) {
		this.driver = driver;
	}

	
	
	//Selecting Payment option for New payment
	public void SelectPaymentOptn(String Paymenttype) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='"+Paymenttype+"']")).click();
		Thread.sleep(500);
	}
	
	//Next button click
	public void ClickNext() throws InterruptedException {
		driver.findElement(By.xpath(btnNextxpath)).click();
		Thread.sleep(2000);
	}


}

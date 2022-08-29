package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import resources.ExcelData;

public class NewPaymentRecordPO {
	WebDriver driver;
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();

	
	private String lblPayRecdxpath = "//lightning-formatted-text[contains(text(),'Payment-')]";
	private String lnkCasesxpath = "//span[@lst-listviewmanagerheader_listviewmanagerheader][@title='Cases']";
	

	// Declaring Constructor
	public NewPaymentRecordPO(WebDriver driver) {
		this.driver = driver;
	}

	//Selecting Capture newly created Payment record ID
	public String CapturePaymentRcdID() throws InterruptedException {
	String PaymentRecord=driver.findElement(By.xpath(lblPayRecdxpath)).getText();
	Thread.sleep(300);
	return PaymentRecord;
	}
	
	//Selecting Capture newly created Payment record ID
	public void ClickCasesQA() throws InterruptedException {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("scroll(0,650);");
	Thread.sleep(3000);
	WebElement Cases=driver.findElement(By.xpath(lnkCasesxpath));
	Cases.click();
	Thread.sleep(3000);
	}
	

}

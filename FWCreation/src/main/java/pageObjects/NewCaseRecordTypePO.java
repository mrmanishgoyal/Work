package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import resources.ExcelData;
import resources.base;

public class NewCaseRecordTypePO extends base {
	WebDriver driver;

	
	private String btnNextxpath = "//span[text()='Next']";
	private String btnSavexpath = "//span[text()='Save']";
	

	// Declaring Constructor
	public NewCaseRecordTypePO(WebDriver driver) {
		this.driver = driver;
	}

	//Click on New Case Button
	public void SelectCaseRecordType(String val) throws InterruptedException {
	Thread.sleep(1000);
	WebElement ele = driver.findElement(By.xpath("//span[text()='"+val+"']")); 
	scrollIntoView(ele);
	ele.click();
	Thread.sleep(800);
	}
	
	//Click Next button
	public void ClickNext() throws InterruptedException {
	driver.findElement(By.xpath(btnNextxpath)).click();
	Thread.sleep(2000);
	
	}
	
	//Click Save button
	public void ClickRetentionSave() throws InterruptedException {
	driver.findElement(By.xpath("//h2[text()='New Task: Retention Initial Call']/following::span[text()='Save']")).click();
	Thread.sleep(2000);
	
	}

	//Click Save button
	public void ClickSave() throws InterruptedException {
	driver.findElement(By.xpath("//h2[text()='New Task: Inbound']/following::span[text()='Save']")).click();
	Thread.sleep(2000);
	
	}
	//Click on New Case Button and select Inbound option
	public void SelectCaseRecordTypeInbound() throws InterruptedException {	
	WebElement ele = driver.findElement(By.xpath("//span[text()='Inbound']")); 
	scrollIntoView(ele);
	ele.click();
	Thread.sleep(800);
	}

	//Click on New Case Button and select Retention Initial Call option
	public void SelectCaseRecdTypeRetenInitialCall() throws InterruptedException {	
	WebElement ele = driver.findElement(By.xpath("//span[text()='Retention Initial Call']")); 
	scrollIntoView(ele);
	ele.click();
	Thread.sleep(800);
	}
	
	//Click on New Case Button and select SDC option
	public void SelectCaseRecordTypeSDC() throws InterruptedException {	
	WebElement ele = driver.findElement(By.xpath("//span[text()='SDC Task']")); 
	scrollIntoView(ele);
	ele.click();
	Thread.sleep(800);
	}
	//Click on New Case Button and select Retention Refund Request option
	public void SelectCaseRecordTypeRRR() throws InterruptedException {	
	WebElement ele = driver.findElement(By.xpath("//span[text()='Retention Refund Request']")); 
	scrollIntoView(ele);
	ele.click();
	Thread.sleep(800);
	}
	
	//Select Case status as Closed
	public void SelectCaseStatusClosed() throws InterruptedException {
		driver.findElement(By.xpath("//span/span[text()='Status']/following::a")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//span/span[text()='Status']/following::a[text()='Closed']")).click();
		Thread.sleep(800);
	}
	
	//Enter Subject details
	public void EnterSubject(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Subject']/following::input")).sendKeys(val);
		Thread.sleep(800);
	
	}

	//Enter Subject details for SDC
	public void EnterSubject_SDC(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Subject']/following::input")).sendKeys(val);
		Thread.sleep(800);
		driver.findElement(By.xpath("//h2[text()='New Task: SDC Task']/following::span[text()='Save']")).click();
		Thread.sleep(2000);
	
	}
	
	//Enter Order details
	public void EnterOrder(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Orders']/following::input")).sendKeys(val);
		Thread.sleep(800);
	
	}
	
	//Enter Value for Reason For Refund
	public void EnterResnFrRefnd(String val) throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//div[text()='Reason For Refund']/following::span[text()='"+val+"']"));
		scrollIntoView(ele);
		ele.click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//div[text()='Reason For Refund']/following::lightning-primitive-icon")).click();
		Thread.sleep(800);
	
	}	
	// Scroll element to view
	public void scrollIntoView(WebElement element) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			System.out.println("Page scrolled down");
		} catch (Exception e){
			System.out.println("=============================================================");
			System.out.println("Exception-scrollIntoView(): "+e.getMessage());
			takeScreenShot();
			e.printStackTrace();
			System.out.println("=============================================================");
		}    
	}
}

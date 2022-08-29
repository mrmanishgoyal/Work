package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import resources.base;

public class ForeClosurePO extends base {
	WebDriver driver;

	private String lnkAssignedToxpath = "//h1/following::span[text()='Foreclosure Approval']/following::span[text()='Assigned To']/following::a";
	
	// Declaring Constructor
	public ForeClosurePO(WebDriver driver) {
		this.driver = driver;
	}

	public void ChangeAssignedTo(String val) throws InterruptedException {
		driver.findElement(By.xpath("//div[text()='Task']/following::span[text()='Assigned To']/following::button[@title='Edit Assigned To']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='deleteIcon']")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//div[text()='Task']/following::span[text()='Assigned To']/following::input[@aria-describedby]")).sendKeys(val);
		Thread.sleep(800);
		driver.findElement(By.xpath("//div[text()='Task']/following::span[text()='Assigned To']/following::div[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"+val+"')]")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		Thread.sleep(2500);
	}
	
	//Selecting Nav Menu
	public void ClickAssignedToPerson() throws InterruptedException {
		driver.findElement(By.xpath(lnkAssignedToxpath)).click();
		Thread.sleep(2000);
	}
	
	//Selecting Manager for other login
	public void ClickAssignedToMPerson(String val) throws InterruptedException {
		driver.findElement(By.xpath("//h1//following::span[text()='Related To']//following::a[@title='"+val+"']//preceding::span[text()='Assigned To']//following::a[@data-refid][@id]")).click();
	Thread.sleep(2000);
	}
	
	//Mark Manager Approval as Approved
	public void SelectMangerAppr(String val) throws InterruptedException {
	driver.findElement(By.xpath("//button[@title='Edit Manager Approval']")).click();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//span/span[text()='Manager Approval']/following::a")).click();
	Thread.sleep(800);
	driver.findElement(By.xpath("//ul[@role='presentation']/following::a[@title='"+val+"']")).click();
	Thread.sleep(800);
	}
	
	//Mark Status as Completed
	public void SelectStatus(String val) throws InterruptedException {
	driver.findElement(By.xpath("//span/span[text()='Status']/following::a")).click();
	Thread.sleep(800);
	driver.findElement(By.xpath("//ul[@role='presentation']/following::a[@title='"+val+"']")).click();
	Thread.sleep(800);
	}
	
	//Click Save button
	public void ClickSave() throws InterruptedException {
	driver.findElement(By.xpath("//button[@title='Save']")).click();
	Thread.sleep(3000);
	}

	public void RefreshWindow() throws Exception {
		driver.get(driver.getCurrentUrl());
		Thread.sleep(4000);
	}
}

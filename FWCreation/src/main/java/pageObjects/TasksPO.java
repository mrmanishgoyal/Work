package pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.base;

public class TasksPO extends base {
	WebDriver driver;

	//private String btnNavMenuxpath = 
	
	// Declaring Constructor
	public TasksPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Navigate to main Task Screen
	public void TaskScreen() throws InterruptedException {
	//driver.findElement(By.xpath("//span[@class='slds-truncate'][text()='Tasks']")).click();
	//Thread.sleep(2000);
	WebElement ele= driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-down slds-button__icon slds-icon_container forceIcon']"));
	jsClick(ele);
	Thread.sleep(1000);
	}
	
	//Navback to Task screen
	public void NavBackToTask() throws InterruptedException {
		WebElement ele5 = driver.findElement(By.xpath("//button[@title='Show Navigation Menu']"));
		jsClick(ele5);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//span[text()='Tasks'][@class='menuLabel slds-listbox__option-text slds-listbox__option-text_entity']")).click();
		Thread.sleep(2000);
	}
	
	//Open the My Assigned Task Screen
	public void MyAssignedSelctn() throws InterruptedException {
	WebElement ele = driver.findElement(By.xpath("//input[@role='combobox']"));
	jsClick(ele);
	Thread.sleep(800);
	driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("My Assigned Task");
	Thread.sleep(1200);
	driver.findElement(By.xpath("//mark[text()='My Assigned Task']")).click();
	Thread.sleep(2500);
	}
	
	//Select the Case Record
	public void SelectCR(String val) throws InterruptedException {
	driver.findElement(By.xpath("//td/following::a[text()='"+val+"']")).click();
	Thread.sleep(4000);
	}
	
	//Capture Task Type
	public String TaskType() {
	String TaskType=driver.findElement(By.xpath("//div[text()='Task']/following::span[@class='uiOutputText']")).getText();
	return TaskType;
	}
	
    public void jsClick(WebElement el) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click();", el);
			System.out.println("Element clicked");
		} catch (Exception e){
			System.out.println("=============================================================");
			System.out.println("Exception-jsClick(): "+e.getMessage());
			takeScreenShot();
			e.printStackTrace();
			System.out.println("=============================================================");
		}
	}
    
	// Take screenshot of page and save in screenshots folder
	public void takeScreenShot() {
		Date date=new Date();
		String screenshotFile=date.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=System.getProperty("user.dir")+"screenshots\\"+screenshotFile;

		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

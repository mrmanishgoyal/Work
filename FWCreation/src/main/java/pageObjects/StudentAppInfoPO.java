package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import resources.base;

public class StudentAppInfoPO extends base {
	WebDriver driver;

	private String btnDrpdwnxpath = "//button[contains(@title,'Actions for')]";
	private String btnRefreshxpath = "//li[@title='Refresh Tab']/a";
	private String lnkExamidxpath = "//tr[1]//span[1]/a[1][@data-refid]";
	private String lblRcdTypexpath = "//td//span/span";
	private String btnExamDeletexpath = "//button[text()='Delete']";
	private String btnDeletexpath = "//span[text()='Delete']";
	private String lnkTabExamxpath = "//a[@title='Exam Details'][@class='tabHeader slds-tabs--default__link slds-p-right--small slds-grow ']";
	
	// Declaring Constructor
	public StudentAppInfoPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Refresh tab
	public void RefreshTab() throws InterruptedException {
	driver.findElement(By.xpath(btnDrpdwnxpath)).click();
	Thread.sleep(900);
	
	driver.findElement(By.xpath(btnRefreshxpath)).click();
	Thread.sleep(1500);
	}
	
	//Capture the Exam id created
	public void CheckBlockStatus() throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//tr/th/span/a"));
		for(int i=0; i<list.size();i++) {
			
			list.get(i).click();
			Thread.sleep(1500);
			String BlockStatus= driver.findElement(By.xpath("//span[text()='Block Status']/following::lightning-formatted-text")).getText();
			String BlockType= driver.findElement(By.xpath("//span[text()='Block Type']/following::lightning-formatted-text")).getText();
			Thread.sleep(500);
			Assert.assertEquals(BlockStatus, "Hard Block");
			Assert.assertEquals(BlockType, "Refund");
			driver.findElement(By.xpath("//span[@class='title slds-truncate'][text()='Student App Informations']")).click();
			Thread.sleep(1000);
		}
	}
	
	//Deleting the App info records
	public void DeleteStudentAppInfolastbtn() throws InterruptedException{
		 
		driver.findElement(By.xpath("//div[text()='Student App Information']//following::runtime_platform_actions-actions-ribbon//button/lightning-primitive-icon")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath(btnDeletexpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//h2[contains(text(),'Delete')]/following::span[text()='Delete']")).click();
		Thread.sleep(1500);
	}
	
	
	//Deleting the App info records
	public void DeleteSchoolInfolastbtn() throws InterruptedException{
		 
		driver.findElement(By.xpath("//div[text()='School Information']//following::runtime_platform_actions-actions-ribbon//button/lightning-primitive-icon")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath(btnDeletexpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//h2[contains(text(),'Delete')]/following::span[text()='Delete']")).click();
		Thread.sleep(1500);
	}
	
	
    public void closeTabWindows() throws InterruptedException {
		List<WebElement> list= driver.findElements(By.xpath("//ul[@class='tabBarItems slds-grid']//div[@class='close slds-col--bump-left slds-p-left--none slds-context-bar__icon-action ']"));
		if (list.size() == 0) {
			System.out.println("There are no open tabs");
		} else {
			for (int i = 0; i < list.size(); i++) {
				try {
				list.get(i).click();
				Thread.sleep(500);
				}
				catch(Throwable t) {
					System.out.println("More tabs on page loaded later");
					t.printStackTrace();
				}
			}
		}
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
}

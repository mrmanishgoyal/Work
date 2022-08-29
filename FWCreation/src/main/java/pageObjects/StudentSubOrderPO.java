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

import resources.base;

public class StudentSubOrderPO extends base {
	WebDriver driver;

	private String btnDrpdwnxpath = "//button[contains(@title,'Actions for')]";
	private String btnRefreshxpath = "//li[@title='Refresh Tab']/a";
	private String lnkExamidxpath = "//tr[1]//span[1]/a[1][@data-refid]";
	private String lblRcdTypexpath = "//td//span/span";
	private String btnExamDeletexpath = "//button[text()='Delete']";
	private String btnDeletexpath = "//span[text()='Delete']";
	private String lnkTabExamxpath = "//a[@title='Exam Details'][@class='tabHeader slds-tabs--default__link slds-p-right--small slds-grow ']";
	
	// Declaring Constructor
	public StudentSubOrderPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Refresh tab
	public void RefreshTab() throws InterruptedException {
	driver.findElement(By.xpath(btnDrpdwnxpath)).click();
	Thread.sleep(900);
	
	driver.findElement(By.xpath(btnRefreshxpath)).click();
	Thread.sleep(1500);
	}
	
	//Click Order
	public void ClickOrder() throws InterruptedException {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Order']/following::a"));
		jsClick(ele);
		Thread.sleep(2000);
	}
	
	//Click Student Sales Order
	public void ClickStudentSalesOrder() throws InterruptedException {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Student Sales Order']/following::a"));
		jsClick(ele);
		Thread.sleep(2000);
	}
	
	//Click Order
	public void ClickOrderinOrders() throws InterruptedException {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Orders']/following::a[2]"));
		jsClick(ele);
		Thread.sleep(2000);
	}
	//Select  Status as Delivered or Partial deliverd
	public void SelectSSOStatus() throws InterruptedException {
		driver.findElement(By.xpath("//div[text()='Student Sub Orders']/following::span[text()='Status']/following::button")).click();
		Thread.sleep(1200);
		driver.findElement(By.xpath("//div[text()='Student Sub Orders']/following::label[text()='Status']/following::button")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//div[text()='Student Sub Orders']/following::label[text()='Status']/following::span[text()='Delivered']")).click();
		Thread.sleep(800);
		
	}
	
	//Click Save
	public void ClickSave() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(4000);
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

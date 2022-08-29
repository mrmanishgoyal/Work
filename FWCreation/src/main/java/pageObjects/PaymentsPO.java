package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import resources.base;

public class PaymentsPO extends base {
	WebDriver driver;

	private String btnNavMenuxpath = "//button[@title='Show Navigation Menu']";
	private String btnPaymntOptnxpath = "//span[text()='Payments'][@class='menuLabel slds-listbox__option-text slds-listbox__option-text_entity']";
	private String btnNewPaymntxpath="//div[text()='New']";
	private String lnkRecentViewxpath= "//ul/following::span[text()='Recently Viewed']";
	private String lnkDeletexpath = "//a[@title='Delete']";
	private String btnDeletexpath = "//button/span[text()='Delete']";
	
	// Declaring Constructor
	public PaymentsPO(WebDriver driver) {
		this.driver = driver;
	}

	//Selecting Nav Menu
	public void NavMenuClick() throws InterruptedException {
	driver.findElement(By.xpath(btnNavMenuxpath)).click();
	Thread.sleep(1000);
	}

	//Selecting Payment option from Nav Menu
	public void PaymntNavMenuClick() throws InterruptedException {
	driver.findElement(By.xpath(btnPaymntOptnxpath)).click();
	Thread.sleep(1000);
	}
	
	//Creating New Payment
	public void NewPaymentClick() throws InterruptedException {
	jsClick(driver.findElement(By.xpath(btnNewPaymntxpath)));
	Thread.sleep(3000);
	}
	
	//Navigate to Case tab
	public void NavCaseTab(String val) throws InterruptedException {
	driver.findElement(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//span[@class='title slds-truncate'][text()='"+val+"']")).click();
	Thread.sleep(1000);
	}
	
	//Navigate to Cases tab
	public void NavCasesTab() throws InterruptedException {
	driver.findElement(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//span[@class='title slds-truncate'][text()='Cases']")).click();
	Thread.sleep(2000);
	}
	
	//Delete the created Payment record
	public void DeletePayRecord(String val) throws InterruptedException {
		//closeTabWindows();
		Thread.sleep(1000);
		WebElement IcnDownxpath = driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-down slds-button__icon slds-icon_container forceIcon']"));
	    jsClick(IcnDownxpath);
		Thread.sleep(1000);
		driver.findElement(By.xpath(lnkRecentViewxpath)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+val+"']/following::span[@class='slds-icon_container slds-icon-utility-down']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(lnkDeletexpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(btnDeletexpath)).click();
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
				//takeScreenShot();
				e.printStackTrace();
				System.out.println("=============================================================");
			}
		}
}

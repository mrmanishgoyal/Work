package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import resources.base;

public class CollectionAssistantLoginPO extends base {
	WebDriver driver;


	private String lblLogoutxpath = "//a[contains(text(),'Log out')]";

	// Declaring Constructor
	public CollectionAssistantLoginPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Logout code
	public void Logout() throws InterruptedException {
		Thread.sleep(1000);
		jsClick(driver.findElement(By.xpath("//span[@class='uiImage']")));
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		Thread.sleep(1500);
		
	}
	
    public void CloseNotification() throws InterruptedException {
    	
    	List<WebElement> a= driver.findElements(By.xpath("//a[@class='slds-notification__target slds-media']/following::button[@title='Dismiss notification']"));
    	for(int i=0;i<a.size();i++) {
    		a.get(i).click();
    		Thread.sleep(300);
    	}
    }
	
	//Click Notification bell icon
	public void ClickBellicon() throws InterruptedException {
	Thread.sleep(2500);
	closeTabWindows();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-notification slds-button__icon slds-global-header__icon slds-icon_container forceIcon']")).click();
	Thread.sleep(3000);
	}
	
	//Click Notification bell icon
	public void ClickBelliconFC() throws InterruptedException {
	Thread.sleep(1500);
	driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-notification slds-button__icon slds-global-header__icon slds-icon_container forceIcon']")).click();
	Thread.sleep(3500);
	}
	//Click on Assigned task notification
	public void SelectAssignedTask(String val) throws InterruptedException {
	driver.findElement(By.xpath("//span[contains(text(),'"+val+"assigned')]")).click();
	Thread.sleep(3000);
	}
	
	//Click on Logout button
	public void ClickLogout() throws InterruptedException {
	driver.findElement(By.xpath(lblLogoutxpath)).click();
	Thread.sleep(5000);
	}
	
	//Click on Discounted Foreclosure approval task
	public void SelectAssignedFCTask(String val) throws InterruptedException {
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[text()='Discounted Foreclosure Approved - #"+val+"']")).click();
	Thread.sleep(3000);
	}

	//Click on Follow Up Call - PTP task
	public void SelectAssignedPTPTask() throws InterruptedException {
	driver.findElement(By.xpath("//span[text()='Follow Up Call - PTP']")).click();
	Thread.sleep(3000);
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
	   public void closeTabWindows() throws InterruptedException {
			List<WebElement> list= driver.findElements(By.xpath("//ul[@class='tabBarItems slds-grid']//div[@class='close slds-col--bump-left slds-p-left--none slds-context-bar__icon-action ']"));
			if (list.size() == 0) {
				System.out.println("There are no open tabs");
			} else {
				for (int i = 0; i < list.size(); i++) {

					list.get(i).click();
					Thread.sleep(500);

				}
			}
	    }
}

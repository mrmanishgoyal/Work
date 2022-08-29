package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import resources.ExcelData;
import resources.base;

public class NewCaseDetailsPO extends base{
	WebDriver driver;

	
	private String btnSavexpath = "//button[@data-aura-class='uiButton forceActionButton']/span[text()='Save']";
	

	// Declaring Constructor
	public NewCaseDetailsPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Click Save button
	public void ClickSave() throws InterruptedException {
	driver.findElement(By.xpath(btnSavexpath)).click();
	Thread.sleep(4000);
	
	}
	
	//Capture the Issue Category
	public String CaptureIssueCategory() {
	String IssueCategory= driver.findElement(By.xpath("//span[text()='Category']/following::lightning-formatted-text")).getText();
	return IssueCategory;
	}
	
	//Capture the Device
	public String CaptureDevice() {
	String Device= driver.findElement(By.xpath("//span[text()='Device']/following::lightning-formatted-text")).getText();
	return Device;
	}
	
	//Capture the Issue Sub Type
	public String CaptureIssueSubType() {
	String IssueSubType= driver.findElement(By.xpath("//span[text()='Issue Sub Type']/following::lightning-formatted-text")).getText();
	return IssueSubType;
	}
	
	//Click Delete button
	public void ClickDeleteonCase() throws InterruptedException {
	driver.findElement(By.xpath("//button[text()='Delete']")).click();
	Thread.sleep(1000);
	}
	
	//Click Delete button
	public void ClickDeleteToComplete() throws InterruptedException {
	driver.findElement(By.xpath("//span[text()='Delete']")).click();
	Thread.sleep(2000);
	}
	
	//Click on Open Activities drop down
	public void ClickOpenActiDD() {
	  try {
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			  	js.executeScript("scroll(0,750);");
			  	Thread.sleep(2000);
		  WebElement ele1 =  driver.findElement(By.xpath("//div[text()='Case']/following::span[text()='Open Activities']/following::lightning-icon"));
      	Thread.sleep(1000);
		Actions ac= new Actions(driver);
		ac.moveToElement(ele1).click().build().perform();
		Thread.sleep(800);
      	//ele1.click();
		ClickNewTask();
	  }
		catch(Throwable t){
			t.printStackTrace();
		}
	  try {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  	js.executeScript("scroll(0,850);");
		  	Thread.sleep(2000);
		 // Scrollpagedown();
		  Thread.sleep(2000);
          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']/following::lightning-icon"));
			Actions ac= new Actions(driver);
			ac.moveToElement(ele1).click().build().perform();
			Thread.sleep(800);
			ClickNewTask();
	  }
	  catch(Throwable t) {
		  t.printStackTrace();
	  }
	}
	
	//Click on Open Activities drop down
	public void ClickOpenActiDD_Prod() {
	  try {
		  WebElement ele1 =  driver.findElement(By.xpath("//div[text()='Case']/following::span[text()='Open Activities']/following::lightning-icon"));
      	Thread.sleep(1000);
		Actions ac= new Actions(driver);
		ac.moveToElement(ele1).click().build().perform();
		Thread.sleep(800);
      	//ele1.click();
		ClickNewTask();
	  }
		catch(Throwable t){
			t.printStackTrace();
		}
	  try {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  	js.executeScript("scroll(0,550);");
		  	Thread.sleep(2000);
		  //Scrollpagedown();
          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']/following::lightning-icon"));
			Actions ac= new Actions(driver);
			ac.moveToElement(ele1).click().build().perform();
			Thread.sleep(800);
			ClickNewTask();
	  }
	  catch(Throwable t) {
		  t.printStackTrace();
	  }
	}
	//Click on Open Activities New Task Option
	public void ClickNewTask() throws InterruptedException {
		Thread.sleep(1000);
	WebElement ele= driver.findElement(By.xpath("//span[text()='Open Activities']/following::lightning-icon/following::a[@title='New Task']"));
	Actions ac=new Actions(driver);
	ac.moveToElement(ele).build().perform();
	jsClick(ele);
	Thread.sleep(2000);
	}
	
	//Click on Capture Retention Details 
	public void ClickCaptureRetentnDetail() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Capture Retention Details']")).click();
		Thread.sleep(3000);
	}
	
	//Enter value for Subject
	public void EnterSubject(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//span[text()='Subject']/following::input")));
		driver.findElement(By.xpath("//span[text()='Subject']/following::input")).sendKeys(val);
		Thread.sleep(800);
	}
	
	//Enter value for Subject
	public void EnterSubject2(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Subject']/following::input")));
		Thread.sleep(300);
		driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Subject']/following::input")).sendKeys(val);
		Thread.sleep(300);
	}
	
	//Enter value for Orders
	public void EnterOrders(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Orders']/following::input")));
		Thread.sleep(300);
		driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Orders']/following::input")).sendKeys(val);
		Thread.sleep(300);
	}
	
	//Enter value for Status
	public void EnterStatus(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Status']/following::a")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Status']/following::a[text()='"+val+"']")));
		Thread.sleep(300);
	}
	
	//Enter value for Case Origin
	public void EnterCaseOrigin(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Case Origin']/following::a")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Case Origin']/following::a[text()='"+val+"']")));
		Thread.sleep(300);
	}
	
	//Enter value for Type Of Order Punched
	public void EnterTypeOfOrderPunched(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Type Of Order Punched']/following::a")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Type Of Order Punched']/following::a[text()='"+val+"']")));
		Thread.sleep(300);
	}
	
	//Enter value for Type of Order
	public void EnterTypeOfOrder(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Type of Order']/following::a")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Type of Order']/following::a[text()='"+val+"']")));
		Thread.sleep(300);
	}
	
	//Enter value for Case Category
	public void EnterCaseCategory(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Case Category']/following::a")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Case Category']/following::a[text()='"+val+"']")));
		Thread.sleep(300);
	}
	
	//Enter value for Order Landing Selected
	public void EnterOrderLandingSelected(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Order Landing Selected']/following::a")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Order Landing Selected']/following::a[text()='"+val+"']")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Resolution Responsibility']")));
	}
	
	//Enter value for Product Category
	public void EnterProductCategory(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Product Category']/following::input")));
		Thread.sleep(300);
		driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Product Category']/following::input")).sendKeys(val);
		Thread.sleep(300);
	}
	
	//Enter value for Reason for dispatch
	public void EnterReasonForDispatch(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Reason for dispatch']/following::input")));
		Thread.sleep(300);
		driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Reason for dispatch']/following::input")).sendKeys(val);
		Thread.sleep(300);
	}
	
	//Enter value for Validity as per OH/OMS
	public void EnterValidityOHorOMS(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Validity as per OH/OMS']/following::input")));
		Thread.sleep(300);
		driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Validity as per OH/OMS']/following::input")).sendKeys(val);
		Thread.sleep(300);
	}
	
	//Enter value for Pincode is serviceable for exchange
	public void EnterPincodeServicable(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Pincode is serviceable for exchange']/following::input")));
		Thread.sleep(300);
		driver.findElement(By.xpath("//h2[contains(text(),'New Case')]/following::span[text()='Pincode is serviceable for exchange']/following::input")).sendKeys(val);
		Thread.sleep(300);
	}
	
	//Enter value for Orders
	public void EnterOrders(int randomNum) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//span[text()='Orders']/following::input")));
		driver.findElement(By.xpath("//span[text()='Orders']/following::input")).sendKeys("ORD"+randomNum);
		Thread.sleep(800);
	}
	
	//Select the value for Reason For Refund
	public void SelectReasonForRefund(String val) throws InterruptedException {
		scrollIntoView(driver.findElement(By.xpath("//div[text()='Reason For Refund']")));
		jsClick(driver.findElement(By.xpath("//li[@class='slds-listbox__item']/div[@data-value='"+val+"']")));
		jsClick(driver.findElement(By.xpath("//lightning-button-icon/button[@title='Move selection to Chosen']/lightning-primitive-icon")));
		Thread.sleep(800);
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
  
	public void Scrollpagedown() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(1500);
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

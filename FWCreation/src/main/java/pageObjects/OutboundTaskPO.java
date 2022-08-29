package pageObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.base;

public class OutboundTaskPO extends base {
	WebDriver driver;

	private String btnNextxpath= "//button[text()='Next']";
	private String lblAmtPaidbyCusxpath= "//label[text()='Amount Paid By Customer']/following::input";
	private String btnListboxMovexpath = "//button[@title='Move selection to Selected']";
	private String txtPayRefxpath = "//label[text()='Payment Reference']/following::input";
	private String ddCollectionChnnelxpath = "//label[text()='Collection Channel']/following::button";
	private String lblErrorMessxpath = "//div[@class='slds-text-color_error']";
	private String ddNoEMICollxpath = "//select[@name='No_Of_EMI_Collected']";
	private String ddTOCollAPxpath = "//select[@name='Type_Of_Collection_alreadyPaid']";
	private String ddEligfrRefxpath = "//select[@name='Eligible_For_Refund']";


	// Declaring Constructor
	public OutboundTaskPO(WebDriver driver) {
		this.driver = driver;
	}
	

	//Click on Capture detail button
	public void ClickCaptureDetail() throws InterruptedException {
  jsClick(driver.findElement(By.xpath("//div[text()='Capture Call Details']")));
  Thread.sleep(3000);
	}
	
	//Select DNP as option
	public void SelectDNP() throws InterruptedException {
  driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='DNP']")).click();
  Thread.sleep(1200);
  driver.findElement(By.xpath("//button[text()='Next']")).click();
  Thread.sleep(2000);
	}
	
	//Select Proceed as option
	public void SelectProceed() throws InterruptedException {
  driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Proceed']")).click();
  Thread.sleep(1000);
  driver.findElement(By.xpath("//button[text()='Next']")).click();
  Thread.sleep(1000);
	}
	
	//Select Proceed as option
	public void SelectProceed_iframe() throws Exception {
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame1=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
		Thread.sleep(800);
		driver.switchTo().frame(frame1);
		visibleText(By.xpath("//lightning-formatted-rich-text/span[text()='Proceed']"));
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Proceed']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(1000);
	}
	//---------------------------------------------------------------------------------------------//
	
	//Select value for Proceed - Spoke To
	public void SelectProceedST(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Spoken To']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
	}
	
	//Select value for Proceed - On-Boarding Survey Done?
	public void SelectOnboardingSurvey(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='On-Boarding Survey Done?']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
	}
	
	//Select value for Proceed - Study Plan Status
	public void SelectStudyPlanStatus(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Study Plan Status']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
	}
	
	//Select value for Proceed - Primary Caretaker Of Studies
	public void SelectPriCaretakerStudies(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Primary Caretaker Of Studies']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
	}
	
	
	//Select value for Proceed - Is There Any Issue?
	public void SelectProceedIsThereIssue(String val) throws InterruptedException {
		visibleText(By.xpath("//label[text()='Is There Any Issue?']"));
		Select sel = new Select(driver.findElement(By.xpath("//select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(2000);
		
	}
	//Select value for Proceed - Is There Any Issue?
	public void SelectProceedIsThereIssue2(String val) throws InterruptedException {
		visibleText(By.xpath("//span[text()='Is There Any Issue?']"));
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Is There Any Issue?']/following::select")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
  //Navigate back to Account screen
	public void NavBackAccount() throws InterruptedException {
		try
	    {
			  driver.findElement(By.xpath("//span[text()='Name']/following::a")).click();
			  Thread.sleep(2500);
	    }
	    catch(WebDriverException e)
	    {
	    	  driver.findElement(By.xpath("//span[text()='Name']/following::a")).click();
	    	  Thread.sleep(2500);
	    }
	    catch(Exception ee)
	    {
	        ee.printStackTrace();
	        throw ee;
	    }
	}
	  
	//PE Follow up Call header
	public String FollowUpHeader() {
		
	String header = driver.findElement(By.xpath("//lightning-formatted-rich-text/following::p/b")).getText();
	return header;
	}
	
	//PE Follow up Call header
	public String FollowUpHeader_BTLP() {
		
	String header = driver.findElement(By.xpath("//lightning-formatted-rich-text/span/p/b")).getText();
	return header;
	}
	
	//PE Follow up Call header
	public String FollowUpHeader_iframe() {
		
	String header = driver.findElement(By.xpath("//flowruntime-display-text-lwc/lightning-formatted-rich-text/span/p/b")).getText();
	//String header = driver.findElement(By.xpath("//header[contains(text(),'Follow Up Call')]")).getText();
	return header;
	}
	
	//Click ELA pitched?
	public void SelectELA(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='ELA pitched?']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
	}
	  
	//Click Next
	public void ClickNext() throws InterruptedException {
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(2000);
	}
	
	//Change the Due date
	public void ChangeDueDate() throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Due Date']/following::button")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//label[text()='Due Date']/following::input")).clear();
		Thread.sleep(500);
		 Date date = DateUtils.addDays(new Date(), -5);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 driver.findElement(By.xpath("//label[text()='Due Date']/following::input")).sendKeys(sdf.format(date));
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//span[text()='Save']")).click();
		 Thread.sleep(3500);
	}
	
	public boolean visibleText(By element)
	{
		WebDriverWait wait= new WebDriverWait(driver, 100);
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		
		System.out.println("Element is visible");
		return false;
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

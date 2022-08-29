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

public class MentorFollowUpTaskPO extends base {
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
	public MentorFollowUpTaskPO(WebDriver driver) {
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
	
	//Select value for Proceed - Spoke To
	public void SelectProceedST(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Spoke To']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
	}
	
	//Select value for Proceed - Study Plan Status
	public void SelectProceedSPS(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Study Plan Status']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
	}
	
	//-------------------------------//
	//Select value for Proceed - Spoke To
	public void SelectLProceedST(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Spoke To']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Spoke To']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Select value for Proceed - Student's App Usage
	public void SelectLProceedSAU(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'App Usage')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'App Usage')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Select value for Proceed - Is the Tutor Feedback Report Discussed?
	public void SelectLProceedITTFRD(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Feedback Report Discussed?')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Feedback Report Discussed?')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Select value for Proceed - Is the class attendance Discussed?
	public void SelectLProceedITCAD(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Is the class attendance Discussed?')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Is the class attendance Discussed?')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Select value for Proceed - Pre & Post Class Discussion Done?
	public void SelectLProceedPPCDD(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Pre & Post Class Discussion Done?')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Pre & Post Class Discussion Done?')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	
	
	//Select value for Proceed - Notes
	public void SelectLProceedNotes(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Notes']/following::textarea")).sendKeys(val);
		Thread.sleep(500);
	}
	
	//Is the Student Attending Classes since last outbound call?
	public void SelectLProceedACSLOC(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Attending Classes since last outbound call?')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Attending Classes since last outbound call?')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Rate the experience - Product
	public void SelectLProceedRateProduct(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Rate the experience - Product')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Rate the experience - Product')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//What all did you not like about the product
	public void SelectLProceedNotLikeProduct(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'not like about the product')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'not like about the product')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Rate the experience - Teacher
	public void SelectLProceedRateTeacher(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Rate the experience - Teacher')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Rate the experience - Teacher')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//What all did you not like about the teacher
	public void SelectLProceedNotLikeTeacher(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'not like about the teacher')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'not like about the teacher')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Rate the experience - Content
	public void SelectLProceedRateContent(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Rate the experience - Content')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Rate the experience - Content')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	
	//What all did you not like about the content
	public void SelectLProceedNotLikeContent(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[contains(text(),'not like about the content')]/following::button")));
		Thread.sleep(1000);
		jsClick(driver.findElement(By.xpath("//label[contains(text(),'not like about the content')]/following::button/following::span[text()='"+val+"']")));
		Thread.sleep(500);	
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(3000);
		
	}
	
	//Is the MPR Shared?
	public void SelectLProceedMRPShared(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Is the MPR Shared?')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Is the MPR Shared?')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Is the MPR Discussion Done?
	public void SelectLProceedMRPDiscussionDone(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Is the MPR Discussion Done?')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Is the MPR Discussion Done?')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}

	//Reason For Not Attending Classes
	public void SelectLProceedRFNAC(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Reason For Not Attending Classes')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Reason For Not Attending Classes')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(3000);
	}
	
	//--------------------------------------//
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Select value for Proceed - Student's App Usage
	public void SelectProceedSAU(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[contains(text(),'App Usage')]/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
	}
	

	//Select value for Proceed - Notes
	public void SelectProceedNotes(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Notes']/following::textarea")).sendKeys(val);
		Thread.sleep(300);
	}
	
	//Select value for Proceed - Do you want to book a zoom session to discuss the MPR report for the next month’s follow-up call task?
	public void SelectProceedZoom(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[contains(text(),'zoom session to discuss the MPR')]/following::select[@required]")).sendKeys(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - School Name
	public void SelectProceedSchoolName(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Notes']/following::input")).sendKeys(val);
		Thread.sleep(800);
	}

	//Select value for Proceed - School Address
	public void SelectProceedSchoolAddr(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='School Address']/following::textarea")).sendKeys(val);
		Thread.sleep(800);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(1000);
	}
	
	
	
	//Select value for Proceed - Is There Any Issue?
	public void SelectProceedIsThereIssue(String val) throws InterruptedException {
		visibleText(By.xpath("//label[text()='Is There Any Issue?']"));
		Select sel = new Select(driver.findElement(By.xpath("//select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(3000);
		
	}
	
	//Select value for Proceed - Is There Any Issue?
	public void SelectProceedIsThereIssue2(String val) throws InterruptedException {
		visibleText(By.xpath("//span[text()='Is There Any Issue?']"));
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Is There Any Issue?']/following::select")));
		sel.selectByVisibleText(val);
		Thread.sleep(300);
	}
	
	
	
	//Select Student has attended any class since last outbound call?
	public void SelectProceedSHAAC(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(2000);
		
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

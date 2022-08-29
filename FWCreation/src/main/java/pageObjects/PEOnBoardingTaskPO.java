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

public class PEOnBoardingTaskPO extends base {
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
	public PEOnBoardingTaskPO(WebDriver driver) {
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
		Thread.sleep(3000);
	}
	
	//Select value for Proceed - Spoke To
	public void SelectProceedST(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[contains(text(),'Spoke To')]/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Spoke_To
	public void SelectProceedS_T(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[contains(text(),'Spoke_To')]/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Have they pitched the Mentor Connect App
	public void SelectProceedHTPTMCA(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[contains(text(),'Have they pitched the Mentor Connect App')]/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Student's App Usage
	public void SelectProceedSAU(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Student_s_App_Usage']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Student's App Usage 
	public void SelectProceedSAU_iframe(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[contains(text(),'App Usage')]/following::select")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Issue Resolved?
	public void SelectProceedIR(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='IsTheIssueResolved']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	

	//Select value for Proceed - Notes
	public void SelectProceedNotes(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Notes']/following::textarea")).sendKeys(val);
		Thread.sleep(800);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(2200);
	}
	
	//Select value for Neo Classes feedback screen - Notes
	public void EnterNotes(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Notes']/following::textarea")).sendKeys(val);
		Thread.sleep(800);
	}
	
	//Select Student has attended any class since last outbound call?
	public void SelectProceedSHAAC(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(2000);
		
	}
	
	
	//Select value for Proceed - Is There Any Issue?
	public void SelectProceedIsThereIssue(String val) throws InterruptedException {
		visibleText(By.xpath("//label[text()='Is There Any Issue?']"));
		Select sel = new Select(driver.findElement(By.xpath("//select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(4000);
		
	}
	
	//Change the Due date
	public void ChangeDueDate() throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='PE - Onboarding Call']/following::span[text()='Due Date']/following::button")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//span[text()='PE - Onboarding Call']/following::label[text()='Due Date']/following::input")).clear();
		Thread.sleep(500);
		 Date date = DateUtils.addDays(new Date(), -5);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 driver.findElement(By.xpath("//span[text()='PE - Onboarding Call']/following::label[text()='Due Date']/following::input")).sendKeys(sdf.format(date));
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//span[text()='PE - Onboarding Call']/following::span[text()='Save']")).click();
		 Thread.sleep(3000);
	}
	
	//Student has attended any class since last outbound call?
	public void SelectSHAACLOC(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Student has attended any class since last outbound call?']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//1. Product
	public void SelectProductRating(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='1. Product']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//What all did you not like about the product
	public void SelectNotLikeProduct(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//span[text()='What all did you not like about the product']/following::span[text()='"+val+"']")));
		Thread.sleep(500);
	}
	
	//2. Teacher
	public void SelectTeacherRating(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='2. Teacher']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//What all did you not like about the teacher
	public void SelectNotLikeTeacher(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//span[text()='What all did you not like about the teacher']/following::span[text()='"+val+"']")));
		Thread.sleep(500);
	}
	
	//3. Content (Coverage, sync with school, relevance, quiz/polls)
	public void SelectContentRating(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='3. Content (Coverage, sync with school, relevance, quiz/polls)']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//What all did you not like about the content
	public void SelectNotLikeContent(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//span[text()='What all did you not like about the product']/following::span[text()='"+val+"']")));
		Thread.sleep(500);
	}
	
	//Student's App Usage
	public void SelectStudentAppUsage(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[contains(text(),'App Usage')]/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Is the TLP product Onboarding done?
	public void SelectTLPOnboardngDone(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[contains(text(),'Is the TLP product Onboarding done?')]/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
		  driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(3000);
	}
	
	//Capture the Assigned To of PE Onboarding Call
	public String CapturePEOnBoardingAssigned() {
		String PEOnBoardingOwner= driver.findElement(By.xpath("//span[text()='Assigned To']/following::a")).getText();
		return PEOnBoardingOwner;
	}
	//Click Next button
	public void ClickOnboardingScreenNext() throws InterruptedException {
		  jsClick(driver.findElement(By.xpath("//header[contains(text(),'Onboarding Call')]/following::button[text()='Next']")));
		  Thread.sleep(2500);
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
	  
		public boolean visibleText(By element)
		{
			WebDriverWait wait= new WebDriverWait(driver, 10000);
			
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
			
			System.out.println("Element is visible");
			return false;
		}
}

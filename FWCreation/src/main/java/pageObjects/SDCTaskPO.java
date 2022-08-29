package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import resources.base;

public class SDCTaskPO extends base {
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
	public SDCTaskPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Click Capture call details
	public void ClickCaptureDetails() throws Exception {
    driver.findElement(By.xpath("//a[@title='Capture Call Details']")).click();
    Thread.sleep(3500);
	}
    //Select the value for Are the customer details correct?
	public void SelectProceed() throws Exception {
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame1=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
		Thread.sleep(800);
		driver.switchTo().frame(frame1);
		Thread.sleep(1200);
	driver.findElement(By.xpath("//span[text()='Proceed']")).click();
	Thread.sleep(800);
	driver.findElement(By.xpath(btnNextxpath)).click();
	Thread.sleep(1500);
	}
	
    //Select the value for Are the customer details correct?
	public void SelectATCDCasN() throws Exception {
	retryForDetachedFrame(driver, "//iframe", 0);
	WebElement frame1=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
	Thread.sleep(800);
	driver.switchTo().frame(frame1);
	Thread.sleep(1200);
	Select sel = new Select(driver.findElement(By.xpath("//span[text()='Are the customer details correct?']/following::select[@required]")));
	sel.selectByVisibleText("No");
	Thread.sleep(800);
	}
	
    //Select the value for Are the customer details correct?
	public void SelectATCDCasY() throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath("//select[@required]")));
	sel.selectByVisibleText("Yes");
	Thread.sleep(800);
	}
	
	
	//Select the value for Who did you discuss this with?
	public void SelectvalDiscus(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Who did you discuss this with?']/following::lightning-formatted-rich-text/span[text()='"+val+"']")).click();
		Thread.sleep(700);		
	}
	
    //Select the value for Did the customer agree? as No
	public void SelectDCAasN() throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath("//select[@name='Did_the_customer_agree']")));
	sel.selectByVisibleText("No");
	Thread.sleep(800);
	}
	
	//Check Visibility of What is the reason for customer declining
	public WebElement VisResnCD() {
		WebElement ele= driver.findElement(By.xpath("//span[text()='What is the reason for customer declining']"));
		return ele;
		
	}
	
	//Check Visibility of Comments
	public WebElement VisComments() {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Comments']"));
		return ele;
		
	}
	
    //Select the value for Did the customer agree? as Will decide and confirm
	public void SelectDCAasWillDcde() throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath("//select[@name='Did_the_customer_agree']")));
	sel.selectByVisibleText("Will decide and confirm");
	Thread.sleep(800);
	}
	
	//Check Visibility of Comments
	public WebElement VisTentveDate() {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Tentative date for confirmation']"));
		return ele;
		
	}
	
    //Select the value for Did the customer agree? as Yes
	public void SelectDCAasY() throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath("//select[@name='Did_the_customer_agree']")));
	sel.selectByVisibleText("Yes");
	Thread.sleep(800);
	}
	
	//Please select the subjects the student would be attending the classes for
	public void SelectSubjects(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Please select the subjects the student would be attending the classes for']/following::lightning-formatted-rich-text/span[text()='"+val+"']")).click();
		Thread.sleep(700);		
	}
	
	//Check Visibility of Please select the preferred batch
	public WebElement VisPSPB() {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Please select the preferred batch']"));
		return ele;
		
	}
	
	//Select value for Please select the preferred batch
	public void SelectPB() throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath("//select[@name='Please_select_the_preferred_batch']")));
	sel.selectByIndex(1);
	Thread.sleep(800);
	}	
	
	
	//Check Visibility of Batch Start Date
	public WebElement VisBSD() {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Batch Start Date']"));
		return ele;
		
	}
	
	//Enter the Batch Start Date
	public void EnterSD() throws InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		 Date date = new Date();
		 String date1= dateFormat.format(date);
		 driver.findElement(By.xpath("//span[text()='Batch Start Date']/following::input")).sendKeys(date1);
		 Thread.sleep(700);
	}
	
	//Check Visibility of Please enter the name of the Batch Assigned
	public WebElement VisNOBA() {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Please select the preferred batch']"));
		return ele;
		
	}
	
	//Enter the name of Batch Assigned
	public void EnterBatchAss(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Please enter the name of the Batch Assigned']/following::input")).sendKeys(val);
		Thread.sleep(700);
	}
	
	
	//Set Address and Grade to be corrected
	public void SelectAddrandGrade() throws InterruptedException {
	driver.findElement(By.xpath("//span[text()='Address needs to be corrected']/preceding::span[contains(@class,'checkbox')]")).click();
	Thread.sleep(800);
	WebElement ele =driver.findElement(By.xpath("//input[@name='Grade_needs_to_be_corrected']"));
	jsClick(ele);
	Thread.sleep(800);
	}
	
	//Check Visibility of Street
	public WebElement VisStreet() {
		WebElement ele= driver.findElement(By.xpath("//label[text()='Street']"));
		return ele;
		
	}
	
	//Check Visibility of City
	public WebElement VisCity() {
		WebElement ele= driver.findElement(By.xpath("//label[text()='City']"));
		return ele;
		
	}
	
	//Check Visibility of State/Province
	public WebElement VisState() {
		WebElement ele= driver.findElement(By.xpath("//label[text()='State/Province']"));
		return ele;
		
	}
	
	//Check Visibility of Zip/Postal Code
	public WebElement VisZip() {
		WebElement ele= driver.findElement(By.xpath("//label[text()='Zip/Postal Code']"));
		return ele;
		
	}
	
	//Check Visibility of Country
	public WebElement VisCountry() {
		WebElement ele= driver.findElement(By.xpath("//label[text()='Country']"));
		return ele;
		
	}
	
	//Check Visibility of Grade
	public WebElement VisGrade() {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Grade']"));
		return ele;
		
	}
	
	//Click Finish
	public void ClickFinish() throws InterruptedException {
	
		driver.findElement(By.xpath("//button[text()='Finish']")).click();
		Thread.sleep(3000);
	}
	
	//Navigate back to Account screen
	public void NavBackAccount() throws InterruptedException {
		try {
		driver.findElement(By.xpath("//span[text()='Name']/following::a")).click();
		Thread.sleep(2500);
		}
		catch(WebDriverException e){
			driver.findElement(By.xpath("//span[text()='Name']/following::a")).click();	
			Thread.sleep(3000);
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
			takeScreenShot();
			e.printStackTrace();
			System.out.println("=============================================================");
		}
	}
	  

}

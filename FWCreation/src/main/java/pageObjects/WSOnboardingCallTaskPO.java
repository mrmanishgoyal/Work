package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.base;

public class WSOnboardingCallTaskPO extends base {
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
	public WSOnboardingCallTaskPO(WebDriver driver) {
		this.driver = driver;
	}
	

	//Click on Capture detail button
	public void ClickCaptureDetail() throws InterruptedException {
  driver.findElement(By.xpath("//div[text()='Capture Call Details']")).click();
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
	public void SelectProceed() throws Exception {
	retryForDetachedFrame(driver, "//iframe", 0);
	WebElement frame1=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
	Thread.sleep(800);
	driver.switchTo().frame(frame1);
    driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Proceed']")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//button[text()='Next']")).click();
    Thread.sleep(1000);
	}
	
	//Select Is the mentor service discussed in detail?
	public void SelectITMSDID(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Is the mentor service discussed in detail?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Is the mentor service discussed in detail?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	}
	
	//Select Is APP1 or APP2 installed?
	public void SelectIAPPI(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Is APP1 or APP2 installed?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Is APP1 or APP2 installed?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	}
	
	//Select Have the topics been discussed and pre-class task given?
	public void SelectHTTBD(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Have the topics been discussed and pre-class task given?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Have the topics been discussed and pre-class task given?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	}
	
	//Select Did you remind the customer about the Class Schedule and Portal Login?
	public void SelectDYRTCPL(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Did you remind the customer about the Class Schedule and Portal Login?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Did you remind the customer about the Class Schedule and Portal Login?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	}
	
	//Select Will the student attend the class?
	public void SelectWSAC(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Will the student attend the class?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Will the student attend the class?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);

	}
	
	//Select Is there any issue?
	public void SelectITAI(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Is there any issue?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Is there any issue?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	    driver.findElement(By.xpath("//button[text()='Next']")).click();
	    Thread.sleep(3000);
	}
	
	//Enter Comments Section
	public void SelectCS(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Comment Section']/following::textarea")).sendKeys(val);
		Thread.sleep(500);
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
	  
	  
	public void ClickOn(By by) throws InterruptedException {

		WebElement element = driver.findElement(by);
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(element));
		element.click(); // Expected condition for the element to be clickable
		Thread.sleep(3000);
	}
	
	//To wait until element is visible
	public WebElement waitForElementToVisible(WebElement element) {
		WebElement find;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);
		find = wait.until(ExpectedConditions.visibilityOf(element));
		return find;
	}
}

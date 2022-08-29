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

public class WSWelcomeCallTaskPO extends base {
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
	public WSWelcomeCallTaskPO(WebDriver driver) {
		this.driver = driver;
	}
	

	//Click on Capture detail button
	public void ClickCaptureDetail() throws InterruptedException {
  driver.findElement(By.xpath("//div[text()='Capture Call Details']")).click();
  Thread.sleep(3000);
	}
	
	//Click on Capture detail button
	public void ClickCaptureDetail_Prod() throws InterruptedException {
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
	
	//Select Spoke to?
	public void SelectST(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Spoke to?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Spoke to?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	}
	
	//Select Customer awareness about the product?
	public void SelectCAATP(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Customer awareness about the product?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Customer awareness about the product?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	}
	
	//Select Informed about Live Workshop Start Date?
	public void SelectIALWSD(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Informed about Live Workshop Start Date?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Informed about Live Workshop Start Date?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	}
	
	//Select Explained about Portal?
	public void SelectEAP(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Explained about Portal?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Explained about Portal?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	}
	
	//Select Is there any issue?
	public void SelectITAI(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Is there any issue?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Is there any issue?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	}
	
	//Enter Comments Section
	public void SelectCS(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Comments Section']/following::textarea")).sendKeys(val);
		Thread.sleep(500);
	}
	
	//Is the issue resolved?
	public void SelectITIR(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Is the issue resolved?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Is the issue resolved?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
	    driver.findElement(By.xpath("//button[text()='Next']")).click();
	    Thread.sleep(3000);
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

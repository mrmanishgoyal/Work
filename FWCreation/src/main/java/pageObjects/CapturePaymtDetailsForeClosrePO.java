package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import resources.base;

public class CapturePaymtDetailsForeClosrePO extends base {
	WebDriver driver;

	private String btnNextxpath= "//button[text()='Next']";
	private String txtAmtCollxpath= "//span[text()='Amount Collected']/following::input";
	private String lblFCMessxpath = "//span[text()='Foreclosure payment capture is completed.']";
	private String btnFinishxpath= "//button[text()='Finish']";
	private String SelStatusxpath = "//select[@name='Status']";
	private String SelCallStatusxpath = "//select[@name='Call_Status']";
	private String lblErrorMessxpath = "//span[contains(text(),'task without Manager')]";
	private String txtPayMethdxpath = "//span[text()='Payment Methods']/following::input";
	private String txtPayRefxpath = "//span[text()='Payment Reference']/following::input";
	private String txtCollBrachxpath = "//span[text()='Collection Branch']/following::input";

	// Declaring Constructor
	public CapturePaymtDetailsForeClosrePO(WebDriver driver) {
		this.driver = driver;
	}
	
	//public WebElement PRerror1= driver.findElement(By.xpath("//div[@class='slds-text-color_error']"));
	
	//Providing Rating to the user
	public void EnterRating(String val) throws Exception {
	retryForDetachedFrame(driver, "//iframe", 0);
	WebElement frame1=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
	Thread.sleep(800);
	driver.switchTo().frame(frame1);
	driver.findElement(By.xpath("//label[text()='"+val+"']")).click();
	Thread.sleep(1000);
	}
	
	//Click next to submit customer rating
	public void ClickNext() throws InterruptedException {
	driver.findElement(By.xpath(btnNextxpath)).click();
	Thread.sleep(1500);
	}
	
	//Select Payment Option
	public void SelectPaymntOptn(String val) throws InterruptedException {
	driver.findElement(By.xpath("//span[text()='"+val+"']")).click();
	Thread.sleep(1000);
	}
	
	//Enter the Amount Collected Value
	public void EnterAmntColl(String val) throws InterruptedException {
	 driver.findElement(By.xpath(txtAmtCollxpath)).sendKeys(val);
		Thread.sleep(1000);
	}

	//Capture the Foreclosure payment message
	public String CaptureFCPayMess() throws InterruptedException {
		String FCMessage= driver.findElement(By.xpath(lblFCMessxpath)).getText();
		Thread.sleep(800);
		return FCMessage;
	}
	
	
	//Click finish to submit FC details
	public void ClickFinish() throws Exception {
		driver.findElement(By.xpath(btnFinishxpath)).click();
		Thread.sleep(4000);
		//retryForDetachedFrame(driver, "//iframe", 0);
		//driver.switchTo().defaultContent();		
		Thread.sleep(1000);
		try {
			driver.findElement(By.xpath("//span[text()='First Connect']")).click();
		}
		catch(WebDriverException e) {
			e.printStackTrace();
		}
		try {
			driver.findElement(By.xpath("//span[text()='First Connect']")).click();
		}
		catch(Exception ee) {
			ee.printStackTrace();
			throw ee;
		}
	}
		
	//Click finish to submit FC details
	public void ClickNextSwitchDefault() throws Exception {
		driver.findElement(By.xpath(btnNextxpath)).click();
		Thread.sleep(4000);
		//retryForDetachedFrame(driver, "//iframe", 0);
		//driver.switchTo().defaultContent();		
		//Thread.sleep(1000);
	}
	//Select Status for ForeClosure
	public void SelectStatusFC(String val) throws Exception {
	retryForDetachedFrame(driver, "//iframe", 0);
	WebElement frame2=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
	Thread.sleep(1000);
	driver.switchTo().frame(frame2);
	Select sel = new Select(driver.findElement(By.xpath(SelStatusxpath)));
	sel.selectByVisibleText(val);
	Thread.sleep(800);
	}
	
	//Select Call Status for ForeClosure
	public void SelectCallStatusFC(String val) throws InterruptedException {
	Select sel1 = new Select(driver.findElement(By.xpath(SelCallStatusxpath)));
	sel1.selectByVisibleText(val);
	Thread.sleep(800);
	}

	//Capture Error message for submission before manager approval
	public String SubmissionBeforeManAppr() throws InterruptedException {
		Thread.sleep(1000);
		String SubBefManApprError = driver.findElement(By.xpath(lblErrorMessxpath)).getText();
		Thread.sleep(800);
		return SubBefManApprError;
	}
	
	//Enter value for Payment Method
	public void SelectPaymentMethod(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Payment Methods']/following::select/option[text()='"+val+"']")).click();
		Thread.sleep(800);
	}

	//Enter value for Payment Date
	public void EnterPaymentdate(String val) throws InterruptedException {
		driver.findElement(By.xpath(txtPayMethdxpath)).sendKeys(val);
		Thread.sleep(800);
	}

	//Enter value for Payment reference
	public void EnterPaymentRef(int randomNum) throws InterruptedException {
		driver.findElement(By.xpath(txtPayRefxpath)).sendKeys("PR"+randomNum);
		Thread.sleep(800);
	}

	//Enter value for Collection Branch
	public void EnterCollBrnch(String val) throws InterruptedException {
		driver.findElement(By.xpath(txtCollBrachxpath)).sendKeys(val);
		Thread.sleep(800);
	}

}

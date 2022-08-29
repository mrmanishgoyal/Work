package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import resources.base;

public class CapturePaymentDetailsEMIPO extends base {
	WebDriver driver;
	int counter=0;
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
	public CapturePaymentDetailsEMIPO(WebDriver driver) {
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
	
	//Enter Amount Paid By Customer
	public void EnterAmtpaid(String val) throws InterruptedException {
	driver.findElement(By.xpath(lblAmtPaidbyCusxpath)).sendKeys(val);
	Thread.sleep(800);
	}
	
	//Enter Payment method
	public void SelectPaymentMethod(String val) throws InterruptedException {
	driver.findElement(By.xpath("//ul[@role='listbox']/li/div[@data-value='"+val+"']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath(btnListboxMovexpath)).click();
	Thread.sleep(800);
	}
	

	//Repeated Payment reference for validation UAT
	public void EnterDupPaymentRef() throws InterruptedException {
	driver.findElement(By.xpath(txtPayRefxpath)).sendKeys("BTR7910");
	Thread.sleep(800);
	}
	
	//Select the Collection Channel value
	public void SelectCollectionchannel(String val) throws InterruptedException {
	driver.findElement(By.xpath(ddCollectionChnnelxpath)).click();
	Thread.sleep(800);
	WebElement a6=driver.findElement(By.xpath("//label[text()='Collection Channel']/following::span[text()='"+val+"']"));
	scrollIntoView(a6);
	a6.click();
	}
	
	//Cepture the Error message
	public String CaptureErrorMess() throws InterruptedException {
	String PRerror= driver.findElement(By.xpath(lblErrorMessxpath)).getText();
	Thread.sleep(300);
	return PRerror;
	}
	
	
	//Enter Payment Reference value
	public void EnterPaymntRefVal(int randomNum) throws InterruptedException {
	driver.findElement(By.xpath(txtPayRefxpath)).clear();
	Thread.sleep(200);
	driver.findElement(By.xpath(txtPayRefxpath)).sendKeys("BTR1"+randomNum+counter);
	Thread.sleep(800);
	counter++;
	}
	
	
	//Select Value for No of EMI collected
	public void SelectEMIcoll(String val) throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath(ddNoEMICollxpath)));
	sel.selectByVisibleText(val);
	Thread.sleep(800);
	}
	
	//Select Value for Type of collection already paid
	public void SelectTypofCollPaid(String val) {
	Select sel1 = new Select(driver.findElement(By.xpath(ddTOCollAPxpath)));
	sel1.selectByVisibleText(val);
	}
	
	
	//Select value for Eligible for refund
	public void SelectElgfrRef(String val) {
	Select sel2 = new Select(driver.findElement(By.xpath(ddEligfrRefxpath)));
	sel2.selectByVisibleText(val);
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

package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import resources.base;

public class CapturePaymtDetailsFPPO extends base {
	WebDriver driver;

	private String btnNextxpath= "//button[text()='Next']";
	private String SelEMIRTPxpath = "//select[@name='No_of_EMIs_Ready_to_pay']";
	

	private String DatePPDxpath = "//span[text()='Promised Payment Date']/following::input";
	private String TimePPDxpath = "//span[text()='Promised Payment Date']";
	private String txtStreetxpath = "//label[text()='Street']/following::textarea";
	private String txtCityxpath = "//label[text()='City']/following::input";
	private String txtStatexpath = "//label[text()='State/Province']/following::input";
	private String txtZipxpath = "//label[text()='Zip/Postal Code']/following::input";
	private String txtCountryxpath = "//label[text()='Country']/following::input";
	private String txtAlterPhnexpath = "//label[text()='Alternate Phone']/following::input";
	private String txtFPckUpxpath = "//span[text()='FieldPickUpComments']/following::textarea";
	
	// Declaring Constructor
	public CapturePaymtDetailsFPPO(WebDriver driver) {
		this.driver = driver;
	}
	
	
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
		
		
	//Select the No Of EMI Collected
	public void SelectNoofEMI(String val) throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath(SelEMIRTPxpath)));
	sel.selectByVisibleText(val);
	Thread.sleep(800);
	}
	
	
	
	//ReEnter correct date for Date/Time for PTP value
	public void EnterDateTimePPD() throws InterruptedException {
	 DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
	 Date date = new Date();
	 String date1= dateFormat.format(date);
	 
	 driver.findElement(By.xpath(DatePPDxpath)).sendKeys(date1);
	Thread.sleep(1000);
	driver.findElement(By.xpath(TimePPDxpath)).click();
	Thread.sleep(500);
	}
	
	//Enter the Street value
	public void EnterStreetDetails(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtStreetxpath)).sendKeys(val);
	Thread.sleep(800);
	}
	
	//Enter the City value
	public void EnterCityDetails(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtCityxpath)).sendKeys(val);
	Thread.sleep(800);
	}
	
	//Enter the State value
	public void EnterStateDetails(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtStatexpath)).sendKeys(val);
	Thread.sleep(800);
	}
	
	//Enter the Pincode value
	public void EnterPinDetails(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtZipxpath)).sendKeys(val);
	Thread.sleep(800);
	}
	
	//Enter the Country value
	public void EnterCountryDetails(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtCountryxpath)).sendKeys(val);
	Thread.sleep(800);
	}
	
	//Enter the Alternate Phone details
	public void EnterAPhoneDetails(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtAlterPhnexpath)).sendKeys(val);
	Thread.sleep(800);
	}
	
	
	//Enter the Field Pick up details
	public void EnterFPCommentDetails(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtFPckUpxpath)).sendKeys(val);
	Thread.sleep(800);
	}
		
	//Click finish to submit FC details
	public void ClickNextSwitchDefault() throws Exception {
		driver.findElement(By.xpath(btnNextxpath)).click();
		Thread.sleep(4000);
		//retryForDetachedFrame(driver, "//iframe", 0);
		//driver.switchTo().defaultContent();		
		//Thread.sleep(1000);
	}
	

}

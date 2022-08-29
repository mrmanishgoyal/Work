package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import resources.base;

public class CapturePaymtDetailsPTPPO extends base {
	WebDriver driver;

	private String btnNextxpath= "//button[text()='Next']";
	private String SelNoofMnthxpath = "//select[@name='No_of_Months']";
	private String txtPhnNoxpath = "//span[text()='Phone No']/following::input";
	private String DateCapptpxpath = "//span[text()='Capture Date/Time for PTP']/following::input";
	private String txtCommxpath = "//span[text()='Comments']/following::input";
	private String SelPTPGivnbyxpath = "//select[@name='PTP_Given_By']";
	private String lblErrorMessxpath = "//span[contains(text(),'within today only.')]";
	


	// Declaring Constructor
	public CapturePaymtDetailsPTPPO(WebDriver driver) {
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
		
		
	//Select the No Of EMI Collected
	public void SelectNoofEMI(String val) throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath(SelNoofMnthxpath)));
	sel.selectByVisibleText(val);
	Thread.sleep(800);
	}
	
	
	//Enter the value for Phone number
	public void EnterPhoneNo(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtPhnNoxpath)).sendKeys(val);
	Thread.sleep(800);
	}
	
	//Enter Capture Date/Time for PTP value for error
	public void EnterDateerrormess() throws InterruptedException {
	driver.findElement(By.xpath(DateCapptpxpath)).sendKeys("Apr 11, 2022");
	Thread.sleep(800);
	}
	
	//Enter Comments
	public void EnterComments(String val) throws InterruptedException {
		driver.findElement(By.xpath(txtCommxpath)).sendKeys(val);
		Thread.sleep(800);
	}

	
	//Select the value for PTP Given By
	public void SelectPTPGivenBy(String val) throws InterruptedException {
	Select sel1 = new Select(driver.findElement(By.xpath(SelPTPGivnbyxpath)));
	sel1.selectByVisibleText(val);
	Thread.sleep(800);
	}
	
	//Capture Error message for date before today
	public String CapturDateError() throws InterruptedException {
	String DateErrorMess = driver.findElement(By.xpath(lblErrorMessxpath)).getText();
	Thread.sleep(800);
	return DateErrorMess;
	}
	
	
	
	//ReEnter correct date for Date/Time for PTP value
	public void EnterDateTimePTP() throws InterruptedException {
	 DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
	 Date date = new Date();
	 String date1= dateFormat.format(date);
	 
	 driver.findElement(By.xpath(DateCapptpxpath)).clear();
	 Thread.sleep(800);
	 driver.findElement(By.xpath(DateCapptpxpath)).sendKeys(date1);
	Thread.sleep(1000);
	String[] Time=driver.findElement(By.xpath("//input[@name='PTPDateTime'][contains(@data-value,':')]")).getAttribute("value").split(":");
	int i= Integer.parseInt(Time[0])+1;
	String Atime=i+":"+Time[1].replaceAll("AM", "PM");

	Actions action = new Actions(driver);
	WebElement ele= driver.findElement(By.xpath("//input[@name='PTPDateTime'][contains(@data-value,':')]"));
	ele.click();
	
	action.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(Atime).build().perform();
	Thread.sleep(800);
	
	Thread.sleep(500);
	}
	
		
	//Click finish to submit FC details
	public void ClickNextSwitchDefault() throws Exception {
		driver.findElement(By.xpath(btnNextxpath)).click();
		Thread.sleep(4000);
		//retryForDetachedFrame(driver, "//iframe", 0);
		driver.switchTo().defaultContent();		
		//Thread.sleep(1000);
	}
	

}

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

public class OpenActivitiesPO extends base {
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
	public OpenActivitiesPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Click Capture call details
	public void ClickCaptureDetails() throws Exception {
    driver.findElement(By.xpath("//a[@title='Capture Call Details']")).click();
    Thread.sleep(3500);
	}
	
	//Select Latest Open Activities Task
	public void LatestOpenActivity() throws InterruptedException {
		driver.findElement(By.xpath("//tr/th/span/a")).click();
		Thread.sleep(3000);
	}
 
    //Capture Assigned To 
	public String CaptureAssignedTo() throws InterruptedException {
		String AssignedTo= driver.findElement(By.xpath("//span[text()='Task Information']/following::span[text()='Assigned To']/following::span//span")).getText();
		return AssignedTo;
	}
	
	//Capture Action Type
	public String CaptureActionType() throws InterruptedException {
		String ActionType= driver.findElement(By.xpath("//div[text()='Task']/following::span[text()='Action Type']/following::span/span")).getText();
		return ActionType;
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

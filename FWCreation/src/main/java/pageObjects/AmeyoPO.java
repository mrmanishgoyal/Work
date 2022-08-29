package pageObjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.ExcelData;
import resources.base;

public class AmeyoPO extends base {
	WebDriver driver;

	private String txtUsernamexpath = "//input[@id='username']";
	private String txtPasswordxpath = "//input[@id='password']";
	private String btnLoginxpath = "//input[@type='submit']";
	private String btnNextxpath = "//button[text()='Next']";
	private String txtLoginReasonxpath = "//textarea";
	
	
	
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	
	//Declaring Constructor
	public AmeyoPO(WebDriver driver) {
		this.driver=driver;
	}
	
	
	//Click on Phone icon
	public void ClickPhoneIcon() {
		driver.findElement(By.xpath("//span[text()='Phone'][contains(@class,'itemTitle')]")).click();
	}
	
	//Login to Salesforce
	public void LoginAmeyo() throws Exception {
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame1=driver.findElement(By.xpath("//iframe[@name='sfdcSoftphone']"));
		Thread.sleep(800);
		driver.switchTo().frame(frame1);
		visibleText(By.xpath("//img[@id='loginViaSalesforceSSO']"));

		driver.findElement(By.xpath("//img[@id='loginViaSalesforceSSO']")).click();
		Thread.sleep(5000);
		if(driver.findElement(By.xpath("//h4[text()='Confimation']")).isDisplayed()) {
			visibleText(By.xpath("//h4[text()='Confimation']/following::button[text()='Login']"));
			jsClick(driver.findElement(By.xpath("//h4[text()='Confimation']/following::button[text()='Login']")));
			visibleText(By.xpath("//span[text()='WFO (Work From Office)']"));
			driver.findElement(By.xpath("//span[text()='WFO (Work From Office)']/following::span")).click();
			clickButton(driver.findElement(By.xpath("//button[text()='Next']")));
		}
		else {
		visibleText(By.xpath("//span[text()='WFO (Work From Office)']"));
		driver.findElement(By.xpath("//span[text()='WFO (Work From Office)']/following::span")).click();
		clickButton(driver.findElement(By.xpath("//button[text()='Next']")));
		//driver.findElement(By.xpath("//button[text()='Next']")).click();
		}
	}
	
	//Select Campaing
	public void SelectCampaing(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//span[@class='campaign-name'][text()='"+val+"']/following::input")));
		Thread.sleep(1000);
	}
	
	//Click Next button on Campaing
	public void ClickNext_Campaing() throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Select Campaign']/following::button[text()='Next']")).click();
		Thread.sleep(1500);
	}
	
	//Select Extension 
	public void Select_Extension(String Extension, String PhoneNo) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Select an option']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(Extension);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//li[text()='"+Extension+"']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[text()='Phone Number*']/following::input")).sendKeys(PhoneNo);
		Thread.sleep(1000);
		
		jsClick(driver.findElement(By.xpath("//extension-selection//button[@id='doneBtn']")));
		Thread.sleep(1500);
		
	}
	
	//Set the Status to Available
	public void SetStatusAvailable() throws InterruptedException {
		driver.findElement(By.xpath("//button[contains(text(),'Just')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Available')]")).click();
		Thread.sleep(1000);
	}
	
	//Capture Status
	public String CaptureStatus() {
		String Status= driver.findElement(By.xpath("//button[@id='agentStatus']")).getText();
		return Status;
	}
	
	//Selection page
	public void SelectionFlowPage() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open()");
		switchWindowArrayLast();
		driver.get(System.getProperty("user.dir") +"\\src\\main\\java\\resources\\IVRSelectionCheck.html");
		visibleText(By.xpath("//p"));
		driver.close();
	}
	
	public void switchWindowArrayLast() throws InterruptedException{
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    int i=tabs2.size()-1;
		driver.switchTo().window(tabs2.get(i));
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
	
	public void clickButton(WebElement element)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
		
		System.out.println("Element is clickable");
		element.click();
	}
	
	public boolean visibleText(By element)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		
		System.out.println("Element is visible");
		return false;
	}
	
	 public void retryForDetachedFrame(final WebDriver driver, final String elementXpath, final int frameId) throws Exception {
	        int webDriverExceptionCounter = 0;
	        while (webDriverExceptionCounter < 5) {
	            try {
	                driver.findElement(By.xpath(elementXpath));
	                break;
	            } catch (final WebDriverException ex) {
	                webDriverExceptionCounter++;
	                if (webDriverExceptionCounter == 5) {
	                   // log.error("WebDriverException, not trying again: {}", webDriverExceptionCounter);
	                    throw ex;
	                } else {
	                   // log.info("WebDriverException, retrying: {}", webDriverExceptionCounter);
	                    Thread.sleep(500);
	                    final WebDriverWait wait = new WebDriverWait(driver, 15);
	                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
	                }
	            } catch (final Exception e) {
	               // log.error("Exception: {}", e.getClass());
	                throw e;
	            }
	        }
	    }
	 
	 
}

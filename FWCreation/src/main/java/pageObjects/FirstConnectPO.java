package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.base;

public class FirstConnectPO extends base {
	WebDriver driver;

	private String lblFirstConnectCasexpath = "//div[text()='Related To']/following-sibling::div/span";
	private String btnCapturePxpath = "//div[text()='Capture Payment']";
	private String btnCompleteFCxpath = "//div[text()='Complete FC']";
	
	private String lblStatusxpath = "//div[text()='Status']/following-sibling::div/span";
	private String lblCallStatusxpath = "//div[text()='Call Status']/following-sibling::div/span";
	private String btnDropDownTabxpath = "//button[@title='Actions for First Connect']";
	private String lnkRefreshxpath = "//li[@title='Refresh Tab']/a";
	
	// Declaring Constructor
	public FirstConnectPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Capture the First connect Case id
	public String CaptureFCcaseid() throws InterruptedException {
	String Firstconnect_caseid=driver.findElement(By.xpath(lblFirstConnectCasexpath)).getText();
	Thread.sleep(2200);
	return Firstconnect_caseid;
	}
	
	//Click on Capture Payment button
	public void ClickCapturePayment() throws InterruptedException {
	driver.findElement(By.xpath(btnCapturePxpath)).click();
	Thread.sleep(8000);
	}
	

	//Checking the status of First Connect
	public String CheckFCStatus() throws Exception {
	//retryForDetachedFrame(driver,"//iframe" , 0);
	//driver.switchTo().defaultContent();
	Thread.sleep(1000);
    try
    {
    Thread.sleep(1000);
    driver.get(driver.getCurrentUrl());
   Thread.sleep(2000);
    driver.findElement(By.xpath(lblStatusxpath)).click();
    
    }
    catch(WebDriverException e)
    {
    	driver.get(driver.getCurrentUrl());
    	Thread.sleep(2000);
    	driver.findElement(By.xpath(lblStatusxpath)).click();
    	//driver.get(driver.getCurrentUrl());
    	//Thread.sleep(3000);
    }
    catch(Exception ee)
    {
        ee.printStackTrace();
        throw ee;
    }
	String Firstconnect_status=driver.findElement(By.xpath(lblStatusxpath)).getText();
	Thread.sleep(800);
	return Firstconnect_status;
	}
	
	//Logout code
	public void Logout() throws InterruptedException {
		
		driver.findElement(By.xpath("//span[@class='uiImage']")).click();
		Thread.sleep(1200);
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		Thread.sleep(1500);
		
	}
	
	//Checking the status of Call Status
	public String CheckFCCallStatus() throws InterruptedException {
	String Firstconnect_callstatus=driver.findElement(By.xpath(lblCallStatusxpath)).getText();
	Thread.sleep(800);
	return Firstconnect_callstatus;
	}
	
	//Checking if able to Complete FC with out Collection Manager approval
	public void ClickCompleteFC() throws InterruptedException {
	driver.findElement(By.xpath(btnCompleteFCxpath)).click();
	Thread.sleep(5000);
	}
	
	//Refresh First Connect Screen
	public void RefreshFCScreen() throws Exception {
        int webDriverExceptionCounter = 0;
        while (webDriverExceptionCounter < 5) {
            try {
            	driver.get(driver.getCurrentUrl());
    			Thread.sleep(3000);
                driver.findElement(By.xpath(btnCompleteFCxpath)).click();
                break;
            } catch (final WebDriverException ex) {
                webDriverExceptionCounter++;
                if (webDriverExceptionCounter == 5) {
                    log.error("WebDriverException, not trying again: {}", webDriverExceptionCounter);
                    throw ex;
                } else {
                    log.info("WebDriverException, retrying: {}", webDriverExceptionCounter);
                    driver.get(driver.getCurrentUrl());
        			Thread.sleep(3000);
                    driver.findElement(By.xpath(btnCompleteFCxpath)).click();
                }
            } catch (final Exception e) {
                log.error("Exception: {}", e.getClass());
                throw e;
            }
        }
		}
		

    	
   
    
	//To refresh the FC tab for updated values
		public void RefreshTab() throws InterruptedException {
			try
		    {
		    Thread.sleep(1000);
		    driver.findElement(By.xpath(btnDropDownTabxpath)).click();
		    Thread.sleep(1000);
		    }
		    catch(WebDriverException e)
		    {
		    	driver.findElement(By.xpath(btnDropDownTabxpath)).click();
		    	Thread.sleep(1000);
		    }
		    catch(Exception ee)
		    {
		        ee.printStackTrace();
		        throw ee;
		    }
			driver.findElement(By.xpath(lnkRefreshxpath)).click();
			Thread.sleep(1500);
		}
		
		//To refresh the FC tab for updated values
		public void RefreshPage() throws InterruptedException {
			try
		    {
		    //Thread.sleep(1000);
				driver.get(driver.getCurrentUrl());
		    }
		    catch(WebDriverException e)
		    {
		    	driver.get(driver.getCurrentUrl());
		    }
		    catch(Exception ee)
		    {
		        ee.printStackTrace();
		        throw ee;
		    }
			
			Thread.sleep(2500);
		}
		

	
}

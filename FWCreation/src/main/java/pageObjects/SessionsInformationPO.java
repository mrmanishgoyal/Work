package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import resources.base;

public class SessionsInformationPO extends base {
	WebDriver driver;

	private String btnDrpdwnxpath = "//button[contains(@title,'Actions for')]";
	private String btnRefreshxpath = "//li[@title='Refresh Tab']/a";
	private String lnkSessionidxpath = "//tr[1]//span[1]/a[1][@data-refid]";
	private String lblSessiontxtxpath = "//div[text()='Sessions_Information']/following::span[text()='Type of Activity']/following::lightning-formatted-text";
	private String lblTypexpath= "//span[text()='Type']/following::lightning-formatted-text";
	private String btnSessionDeletexpath = "//div[text()='Sessions_Information']/following::button[text()='Delete']";
	private String btnDeletexpath = "//span[text()='Delete']";
	private String lnkTabSessionxpath = "//a[@title='Sessions_Information'][@class='tabHeader slds-tabs--default__link slds-p-right--small slds-grow ']";
	
	// Declaring Constructor
	public SessionsInformationPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Refresh tab
	public void RefreshTab() throws InterruptedException {
	driver.findElement(By.xpath(btnDrpdwnxpath)).click();
	Thread.sleep(900);
	
	driver.findElement(By.xpath(btnRefreshxpath)).click();
	Thread.sleep(1500);
	}
	
	//Capture the Session id created
	public String CaptureSessionid() {
	String SessionAttendedid=driver.findElement(By.xpath(lnkSessionidxpath)).getText();
	return SessionAttendedid;
	}
	
	//Capture the Session id created
	public String CaptureSessionid_UAT() {
	String SessionAttendedid=driver.findElement(By.xpath("//span[text()='Sessions Information']/following::span[@force-lookup_lookup]")).getText();
	return SessionAttendedid;
	}

	//Capture the Session id created
	public String CaptureSessionid_UAT2() {
	String SessionAttendedid=driver.findElement(By.xpath("//span[text()='Sessions_Information']/following::span[@force-lookup_lookup]")).getText();
	return SessionAttendedid;
	}
	
	//Capture the Student School Informations created
	public String CaptureStuSchlInfo() {
	String CaptureStuSchlInfo=driver.findElement(By.xpath("//span[text()='Student School Informations']/following::span[@force-lookup_lookup]")).getText();
	return CaptureStuSchlInfo;
	}
	//span[text()='Sessions Information']/following::span[@force-lookup_lookup]
	
	//Click the created Session id
	public void ClickSessionid() throws InterruptedException {
	driver.findElement(By.xpath(lnkSessionidxpath)).click();
	Thread.sleep(3000);
	}
	
	//Click the created Session id
	public void ClickSessionid_UAT() throws InterruptedException {
	jsClick(driver.findElement(By.xpath("//span[text()='Sessions Information']/following::span[@force-lookup_lookup]")));
	Thread.sleep(3000);
	}
	
	//Click the created Session id
	public void ClickSessionid_UAT2() throws InterruptedException {
	jsClick(driver.findElement(By.xpath("//span[text()='Sessions_Information']/following::span[@force-lookup_lookup]")));
	Thread.sleep(3000);
	}
	
	//Click the created Student School Informations id
	public void ClickStuSchlInfo() throws InterruptedException {
	jsClick(driver.findElement(By.xpath("//span[text()='Student School Informations']/following::span[@force-lookup_lookup]")));
	Thread.sleep(3000);
	}
	
	//Capture the text for type of Session
	public String CaptureSessiontext() throws InterruptedException {
	String SessionAttend=driver.findElement(By.xpath(lblSessiontxtxpath)).getText();
	Thread.sleep(800);
	return SessionAttend;
	}
	
	//Capture the text for type of Session
	public String CaptureTypeofSessiontext() throws InterruptedException {
	String SessionAttend=driver.findElement(By.xpath(lblTypexpath)).getText();
	Thread.sleep(800);
	return SessionAttend;
	}
	
	//Delete the created Session id
	public void DeleteSessionInfoCreated() throws InterruptedException {

		driver.findElement(By.xpath(btnSessionDeletexpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(btnDeletexpath)).click();
		Thread.sleep(1500);
		}
	
	public void DeleteSessionInfolastbtn() throws InterruptedException{
		 
			jsClick(driver.findElement(By.xpath("//div[text()='Sessions_Information']//following::runtime_platform_actions-actions-ribbon//button/lightning-primitive-icon")));
			Thread.sleep(800);
			jsClick(driver.findElement(By.xpath(btnDeletexpath)));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//h2[contains(text(),'Delete')]/following::span[text()='Delete']")).click();
			Thread.sleep(1500);
		}
	
	//Delete Student School Informations created
	public void DeleteStudentSchoolInfolastbtn() throws InterruptedException{
		 
		jsClick(driver.findElement(By.xpath("//div[text()='Student School Information']//following::runtime_platform_actions-actions-ribbon//button/lightning-primitive-icon")));
		Thread.sleep(800);
		jsClick(driver.findElement(By.xpath(btnDeletexpath)));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//h2[contains(text(),'Delete')]/following::span[text()='Delete']")).click();
		Thread.sleep(1500);
	}
	
	
	//Nav back to Session screen
	public void NavSessionInfo() throws InterruptedException {
		driver.findElement(By.xpath(lnkTabSessionxpath)).click();
		Thread.sleep(1000);
	}
	
	
	
    public void closeTabWindows() throws InterruptedException {
		List<WebElement> list= driver.findElements(By.xpath("//ul[@class='tabBarItems slds-grid']//div[@class='close slds-col--bump-left slds-p-left--none slds-context-bar__icon-action ']"));
		if (list.size() == 0) {
			System.out.println("There are no open tabs");
		} else {
			for (int i = 0; i < list.size(); i++) {
				try {
				list.get(i).click();
				Thread.sleep(500);
				}
				catch(Throwable t) {
					System.out.println("More tabs on page loaded later");
					t.printStackTrace();
				}
			}
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

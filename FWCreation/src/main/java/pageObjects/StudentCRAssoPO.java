package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.base;

public class StudentCRAssoPO extends base {
	WebDriver driver;

	private String btnDrpdwnxpath = "//button[contains(@title,'Actions for')]";
	private String btnRefreshxpath = "//li[@title='Refresh Tab']/a";
	private String lnkExamidxpath = "//tr[1]//span[1]/a[1][@data-refid]";
	private String lblRcdTypexpath = "//td//span/span";
	private String btnExamDeletexpath = "//button[text()='Delete']";
	private String btnDeletexpath = "//span[text()='Delete']";
	private String lnkTabExamxpath = "//a[@title='Exam Details'][@class='tabHeader slds-tabs--default__link slds-p-right--small slds-grow ']";
	
	// Declaring Constructor
	public StudentCRAssoPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Refresh tab
	public void RefreshTab() throws InterruptedException {
	driver.findElement(By.xpath(btnDrpdwnxpath)).click();
	Thread.sleep(900);
	
	driver.findElement(By.xpath(btnRefreshxpath)).click();
	Thread.sleep(1500);
	}
	
	//Capture the Exam id created
	public String CaptureStuCRAid() {
	String Examid=driver.findElement(By.xpath(lnkExamidxpath)).getText();
	return Examid;
	}
	
	//Click the created Exam id
	public void ClickStuCRAid() throws InterruptedException {
	driver.findElement(By.xpath(lnkExamidxpath)).click();
	Thread.sleep(3000);
	}
	
	//Click Student Classroom Record
	public void ClickStuClassRoomRcd() {
		jsClick(driver.findElement(By.xpath("//span[text()='Student Classroom']/following::a")));
	}
	
	//Change the Subscription Type to Regular
	public void ChangeSTtoRegular() throws InterruptedException {
	
		scrollIntoView(driver.findElement(By.xpath("//div[text()='Student Classroom']/following::span[text()='Subscription Type']/following::button")));
		Actions ac=new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("//div[text()='Student Classroom']/following::span[text()='Subscription Type']/following::button"))).build().perform();
		jsClick(driver.findElement(By.xpath("//div[text()='Student Classroom']/following::span[text()='Subscription Type']/following::button")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Student Classroom']/following::label[text()='Subscription Type']/following::span")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//div[text()='Student Classroom']/following::label[text()='Subscription Type']/following::span[text()='Regular']")).click();
		Thread.sleep(300);
	}
	
	//Change the Classroom Program Type to Classroom
	public void ChangeCRPTtoClassroom() throws InterruptedException {
		//visibleText(By.xpath("//div[text()='Student Classroom']/following::span[text()='Classroom Program Type']"));
		//scrollIntoView(driver.findElement(By.xpath("//div[text()='Student Classroom']/following::span[text()='Classroom Program Type']/following::button")));
		//Actions ac=new Actions(driver);
		//ac.moveToElement(driver.findElement(By.xpath("//div[text()='Student Classroom']/following::span[text()='Classroom Program Type']/following::button"))).build().perform();
		//jsClick(driver.findElement(By.xpath("//div[text()='Student Classroom']/following::span[text()='Classroom Program Type']/following::button")));
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Student Classroom']/following::label[text()='Classroom Program Type']/following::span")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//div[text()='Student Classroom']/following::label[text()='Classroom Program Type']/following::span[text()='Classroom']")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//div[text()='Student Classroom']/following::button[text()='Save']")).click();
		Thread.sleep(4000);
	}
	
	//Capture the text for type of Session
	public String CaptureOwner() throws InterruptedException {
		Actions ac=new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("//div[text()='Student Classroom']/following::span[text()='Owner']"))).build().perform();
		Thread.sleep(2500);
		RefreshTab();
		String ClassroomOwner=driver.findElement(By.xpath("//div[text()='Student Classroom']/following::span[text()='Owner']/following::a/following::span")).getText().replaceAll("Open ", "").replaceAll(" Preview", "");
		Thread.sleep(800);
	return ClassroomOwner;
	}
	
	//Capture the text for type of Session
	public String CaptureRecordtypetext() throws InterruptedException {
		String RecordType=driver.findElement(By.xpath(lblRcdTypexpath)).getText();
		Thread.sleep(800);
	return RecordType;
	}
	
	//Delete the created Session id
	public void DeleteExamInfoCreated() throws InterruptedException {
		driver.findElement(By.xpath(btnExamDeletexpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(btnDeletexpath)).click();
		Thread.sleep(1500);
	}
	
	public void DeleteExamDetailslastbtn() throws InterruptedException{
		 
		driver.findElement(By.xpath("//div[text()='Exam Detail']//following::runtime_platform_actions-actions-ribbon//button/lightning-primitive-icon")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath(btnDeletexpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//h2[contains(text(),'Delete')]/following::span[text()='Delete']")).click();
		Thread.sleep(1500);
	}

	//Nav back to Session screen
	public void NavExamInfo() throws InterruptedException {
		driver.findElement(By.xpath(lnkTabExamxpath)).click();
		Thread.sleep(1000);
	}
	
	//Check the Post60Check
	public WebElement Post60Check() {
		WebElement ele= driver.findElement(By.xpath("//span[text()='Post60Check']/following::input"));
		return ele;
	}
	
	//Check the isPostAssignmentDone
	public WebElement PostAssignmentDoneCheck() {
		WebElement ele= driver.findElement(By.xpath("//span[text()='isPostAssignmentDone']/following::input"));
		return ele;
	}
	
	//Enter Session Missed as 0
	public void EnterSessionMissed() throws InterruptedException {
		
		jsClick(driver.findElement(By.xpath("//span[text()='Sessions Missed']/following::button")));
		Thread.sleep(1500);
		driver.findElement(By.xpath("//label[text()='Sessions Missed']/following::input")).sendKeys("0");
		Thread.sleep(700);
	}
	
	//Enter Session No as 1
	
	public void EnterSessionNo() throws InterruptedException {
		
		driver.findElement(By.xpath("//label[text()='Session No']/following::input")).sendKeys("1");
		Thread.sleep(700);
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2500);
	}
	
	//Click delete button
	public void ClickDelete() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Delete']")).click();
		Thread.sleep(1200);
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		Thread.sleep(1200);
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
	    
	    
		public boolean visibleText(By element)
		{
			WebDriverWait wait= new WebDriverWait(driver, 100);
			
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
			
			System.out.println("Element is visible");
			return false;
		}
		
		public void Scrollpageup() throws InterruptedException {

			driver.findElement(tag).sendKeys(Keys.PAGE_UP);
			Thread.sleep(1200);
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

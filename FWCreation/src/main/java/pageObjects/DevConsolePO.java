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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.ExcelData;

public class DevConsolePO {
	WebDriver driver;

	private String txtUsernamexpath = "//input[@id='username']";
	private String txtPasswordxpath = "//input[@id='password']";
	private String btnLoginxpath = "//input[@type='submit']";
	private String btnNextxpath = "//button[text()='Next']";
	private String txtLoginReasonxpath = "//textarea";
	
	
	
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	
	//Declaring Constructor
	public DevConsolePO(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void switchWindow() throws InterruptedException{
		Set<String> winid = driver.getWindowHandles();
        Iterator<String> iter = winid.iterator();
        iter.next();
        String tab = iter.next();
        Thread.sleep(3000);
        driver.switchTo().window(tab);
	}
	
	public void switchWindowArrayLast() throws InterruptedException{
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    int i=tabs2.size()-1;
		driver.switchTo().window(tabs2.get(i));
	}
	
	public void GetDevConsole(String val) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open()");
		switchWindowArrayLast();
		if(val.equalsIgnoreCase("UAT")) {
			driver.get("https://byjusprod--byjusuat.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
			Thread.sleep(3500);
		}
		else if (val.equalsIgnoreCase("QA")) {
			driver.get("https://byjusprod--byjusqa.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
			Thread.sleep(3500);
		}
		else {
			driver.get("https://byjusprod.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
			Thread.sleep(3500);
		}
		List<WebElement> list= driver.findElements(By.xpath("//a[@class='x-tab-close-btn']"));
		for(int i=0;i<list.size();i++) {
			list.get(i).click();
			Thread.sleep(300);
		}
	}
	
	public void RunBatch(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Logs']")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//span[text()='Debug']")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//div[text()='Open Execute Anonymous Window']")).click();
		Thread.sleep(1000);
		Actions ac1=new Actions(driver);
		ac1.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
		ac1.sendKeys(Keys.BACK_SPACE).build().perform();
		ac1.sendKeys(val).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Enter Apex Code']/following::span[text()='Execute'][contains(@class,'inner')]")).click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//span[text()='Enter Apex Code']/following::img[@role][contains(@class,'close')]")).click();
	}
	
	public boolean visibleText(By element)
	{
		WebDriverWait wait= new WebDriverWait(driver, 1000);
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		
		System.out.println("Element is visible");
		return false;
	}
	
	//Execute query
	public void ExecuteQuery(String val) throws InterruptedException {
		GetDevConsole("UAT");
		driver.findElement(By.xpath("//span[text()='Query Editor']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter SOQL')]")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter SOQL')]")).sendKeys(val);
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[text()='Execute']")).click();
		Thread.sleep(5000);
	}
	
	//Determine User Capcity
	public boolean DecideCapacity() {
		int counter1=0;
		int counter2=0;
		int counter3=0;
		int count=0;
		List<WebElement> list=driver.findElements(By.xpath("//div//tr/td[3]/div"));
		for(int i=0;i<list.size();i++) {
			String Chattype=list.get(i).getText();
			if(Chattype.contains("Student One-to-One")) {
				counter1++;
				System.out.println("The No of Student One-to-One sessions is: "+counter1);
				
			}
			else if(Chattype.contains("Group Chat")) {
				counter2++;
				System.out.println("The No of Group sessions is: "+counter2);
				
			}
			else if(Chattype.contains("Parent One-to-One")) {
				counter3++;
				System.out.println("The No of Parent One-to-One sessions is: "+counter3);
			}
			else if(Chattype.contains("Parent One-to-One") || Chattype.contains("Student One-to-One")) {
				String getcount = driver.findElement(By.xpath("//div//tr/td[1]/div")).getText();
				count= Integer.parseInt(getcount);
				
			}
			
		}
		//conditions An Agent can take a max of configurable X=3 IC or  (1 IC + 1  GC) or 1GC. 
		//Each Group Chat can have multiple group tasks. Mentor can’t take more than 1 GC at any time.
		
		if(counter1+counter3>=3 || counter1+counter2>=2 || counter3+counter2>=2 || count>=3) {
			System.out.println("Mentor capacity if full");
			return false;
		}
		else {
			System.out.println("Capacity is available");
			return true;
		}
	}
	public void CheckBatchRunSuccess() throws InterruptedException {
		visibleText(By.xpath("//tr/td[4]"));
		Thread.sleep(3000);
		List<WebElement> Operationlist =driver.findElements(By.xpath("//tr/td[4]"));
		List<WebElement> Status =driver.findElements(By.xpath("//tr/td[6]"));
		for(int i=0;i<Operationlist.size();i++) {
			
			String OperationValue=Operationlist.get(i).getText();
		//	System.out.println(OperationValue);
			if(OperationValue.contains("Batch Apex")) {
				Assert.assertEquals(Status.get(i).getText(), "Success");
				System.out.println("The Batch has ran successfully");
				break;
			}
		}
	}
}

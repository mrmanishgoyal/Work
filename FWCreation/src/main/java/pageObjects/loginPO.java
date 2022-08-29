package pageObjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ExcelData;

public class loginPO {
	WebDriver driver;

	private String txtUsernamexpath = "//input[@id='username']";
	private String txtPasswordxpath = "//input[@id='password']";
	private String btnLoginxpath = "//input[@type='submit']";
	private String btnNextxpath = "//button[text()='Next']";
	private String txtLoginReasonxpath = "//textarea";
	
	
	
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	
	//Declaring Constructor
	public loginPO(WebDriver driver) {
		this.driver=driver;
	}
	
	//Method to enter Username
	public void EnterUserName(String UN) throws InterruptedException {
		
		driver.findElement(By.xpath(txtUsernamexpath)).sendKeys(UN);
		Thread.sleep(1000);
	}
	
	//Method to enter Password
	public void EnterPassword(String Pass) throws InterruptedException {
		
		driver.findElement(By.xpath(txtPasswordxpath)).sendKeys(Pass);
		Thread.sleep(1000);
	}
	
	//Method to click on Login button
	public void ClickLoginbtn() throws InterruptedException {
		
		driver.findElement(By.xpath(btnLoginxpath)).click();
		Thread.sleep(1000);
	}
	
	//Login as logic
	public void LoginUser(String val) throws InterruptedException, IOException {
		if(val.contains("PE")) {
			LoginAsPE_UAT();
		}
		else if(val.contains("Mentor")) {
			LoginAsMentor_UAT();
		}
		else if(val.contains("IRT")) {
			LoginAsIRT_UAT();
		}
		else if(val.contains("BLC")) {
			LoginAsBLC_UAT();
		}
		else if(val.contains("PE-FC")) {
			LoginAsPE_UATFC();
		}
		else if(val.contains("Mentor-FC")) {
			LoginAsMentor_UATFC();
		}
		else if(val.contains("IRT-FC")) {
			LoginAsIRT_UATFC();
		}
		else if(val.contains("BLC-FC")) {
			LoginAsBLC_UATFC();
		}
	}
	
	public void LoginAsAdmin_UAT() throws InterruptedException, IOException {
		al = excelData.getData("Admin", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		//Thread.sleep(1000);
		//if(driver.findElement(By.xpath("//span[text()='This is Production Environment. ']")).isDisplayed()) {
		//driver.findElement(By.xpath(txtLoginReasonxpath)).sendKeys("Logging in for QA activities");
		//Thread.sleep(300);
		//driver.findElement(By.xpath(btnNextxpath)).click();
		//}
		Thread.sleep(5000);
	}
	
	public void LoginAsAdmin_UAT1() throws InterruptedException, IOException {
		al = excelData.getData("Admin1", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsAdmin_UAT2() throws InterruptedException, IOException {
		al = excelData.getData("Admin2", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsPE_UAT() throws InterruptedException, IOException {
		al = excelData.getData("PE UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsPE_UATFC() throws InterruptedException, IOException {
		al = excelData.getData("PE UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsMentor_UAT() throws InterruptedException, IOException {
		al = excelData.getData("Mentor UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsMentor_UATFC() throws InterruptedException, IOException {
		al = excelData.getData("Mentor UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsIRT_UAT() throws InterruptedException, IOException {
		al = excelData.getData("IRT UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsIRT_UATFC() throws InterruptedException, IOException {
		al = excelData.getData("IRT UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsBLC_UAT() throws InterruptedException, IOException {
		al = excelData.getData("BLC UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsBLC_UATFC() throws InterruptedException, IOException {
		al = excelData.getData("BLC UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsColltnAssociate_UAT() throws InterruptedException, IOException {
		al = excelData.getData("Collection Assistant UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(15000);
	}
	
	public void LoginAsColltnAssociate_UATFC() throws InterruptedException, IOException {
		al = excelData.getData("Collection Assistant UATFC", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	
	public void LoginAsColltnAssociateManger_UAT() throws InterruptedException, IOException {
		al = excelData.getData("Collection Manager UAT", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(6500);
	}
	
	public void LoginAsColltnAssociateManger_UATFC() throws InterruptedException, IOException {
		al = excelData.getData("Collection Manager UATFC", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(6500);
	}
	
	
	public void LoginAsAdmin_QA() throws InterruptedException, IOException {
		al = excelData.getData("AdminQA", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
		
	}
	
	public void LoginAsAdmin_Dev() throws InterruptedException, IOException {
		al = excelData.getData("AdminDev02", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
		
	}
	
	public void LoginAsAdmin_QA2() throws InterruptedException, IOException {
		al = excelData.getData("AdminQA2", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(3000);
		driver.findElement(By.xpath(txtLoginReasonxpath)).sendKeys("Logging in for QA activities");
		Thread.sleep(300);
		driver.findElement(By.xpath(btnNextxpath)).click();
		Thread.sleep(5000);
		
	}
	public void LoginAsAdmin_Prod() throws InterruptedException, IOException {
		al = excelData.getData("AdminProd", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(3000);
		//driver.findElement(By.xpath(txtLoginReasonxpath)).sendKeys("Logging in for QA activities");
		//Thread.sleep(800);
		//driver.findElement(By.xpath(btnNextxpath)).click();
		Thread.sleep(25000);
		//CookieSession();
		
	}
	
	public void LoginAsAdmin_UATFC() throws InterruptedException, IOException {
		al = excelData.getData("Adminuatfc", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}
	public void RefreshURL() throws InterruptedException {
		driver.get(driver.getCurrentUrl());
		Thread.sleep(2000);
	}
	public void CookieSession() {
        // create file named Cookies to store Login Information		
        File file = new File("Cookies.data");							
        try		
        {	  
            // Delete old file if exists
			file.delete();		
            file.createNewFile();			
            FileWriter fileWrite = new FileWriter(file);							
            BufferedWriter Bwrite = new BufferedWriter(fileWrite);							
            // loop for getting the cookie information 		
            	
            // loop for getting the cookie information 		
            for(Cookie ck : driver.manage().getCookies())							
            {			
                Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));																									
                Bwrite.newLine();             
            }			
            Bwrite.close();			
            fileWrite.close();	
            
        }
        catch(Exception ex)					
        {		
            ex.printStackTrace();			
        }		
    }

	public void LoginAsColltnAssociate_QA() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		al = excelData.getData("Collection Assistant QA", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}

	public void LoginAsColltnAssociate_Prod() {
		// TODO Auto-generated method stub
		
	}

	public void LoginAsColltnAssociateManger_QA() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		al = excelData.getData("Collection Manager QA", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
	}

	public void LoginAsColltnAssociateManger_Prod() {
		// TODO Auto-generated method stub
		
	}		
	
	public void InternetConnectionIssue() {
		try {
			
			driver.findElement(By.xpath("//button[text()='Try Again']")).click();
			Thread.sleep(1000);
			
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void SessionLogin() {
		try {
			
			driver.findElement(By.xpath("//button[text()='Log In']")).click();
			driver.get(driver.getCurrentUrl());
			Thread.sleep(3000);
			
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void FromMiddleUAT(String val) throws InterruptedException, IOException {
		driver.get(val);
		al = excelData.getData("Admin", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
		
	}
	
	public void FromMiddleUATFC(String val) throws InterruptedException, IOException {
		driver.get(val);
		al = excelData.getData("Adminuatfc", "Login", "Type");
		EnterUserName(al.get(1));
		EnterPassword(al.get(2));
		ClickLoginbtn();
		Thread.sleep(5000);
		
	}
	
	public void switchWindow() throws InterruptedException{
		Set<String> winid = driver.getWindowHandles();
        Iterator<String> iter = winid.iterator();
        iter.next();
        String tab = iter.next();
        Thread.sleep(3000);
        driver.switchTo().window(tab);
	}
	
	public void GetDevConsole(String val) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open()");
		switchWindow();
		if(val.equalsIgnoreCase("UAT")) {
			driver.get("https://byjusprod--byjusuat.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
			Thread.sleep(3500);
		}
		else if (val.equalsIgnoreCase("QA")) {
			driver.get("https://byjusprod--byjusqa.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
			Thread.sleep(3500);
		}
		else if(val.equalsIgnoreCase("UATFC")) {
			driver.get("https://byjusprod--byjusuatfc.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
			Thread.sleep(3500);
		}
		else {
			driver.get("https://byjusprod.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
			Thread.sleep(3500);
		}
	}
	
	public void SwitchUser(String val) throws Exception {
		jsClick(driver.findElement(By.xpath("//a[contains(@class,'menuTriggerLink')]")));
		Thread.sleep(2000);
		//jsClick(driver.findElement(By.xpath("//ul[@class='scrollable']/li[1]")));
		driver.findElement(By.xpath("//ul[@class='scrollable']/li[@id='related_setup_app_home']")).click();
		Thread.sleep(2000);
		switchWindow();
		driver.findElement(By.xpath("//input[@placeholder='Search Setup']")).sendKeys(val);
		//Thread.sleep(2000);
		visibleText(By.xpath("//span[@title='"+val+"']"));
		driver.findElement(By.xpath("//span[@title='"+val+"']")).click();
		Thread.sleep(2000);
		
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe"));
		Thread.sleep(800);
		driver.switchTo().frame(frame);
		Thread.sleep(800);
		driver.findElement(By.xpath("//input[@title='Login']")).click();
		Thread.sleep(8000);
		retryForDetachedFrame(driver, "//iframe", 0);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);		
	}
	
	public void SwitchUsernProfile_Prod(String val) throws Exception {
		jsClick(driver.findElement(By.xpath("//a[contains(@class,'menuTriggerLink')]")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='scrollable']/li[1]")).click();
		Thread.sleep(2000);
		switchWindow();
		driver.findElement(By.xpath("//input[@placeholder='Search Setup']")).sendKeys("Testing User");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@title='Testing User']")).click();
		Thread.sleep(2000);
		
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe"));
		Thread.sleep(800);
		driver.switchTo().frame(frame);
		Thread.sleep(800);
		driver.findElement(By.xpath("//input[@title='Edit']")).click();
		Thread.sleep(3000);
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame1=driver.findElement(By.xpath("//iframe"));
		Thread.sleep(800);
		driver.switchTo().frame(frame1);
		Thread.sleep(800);
		Select sel= new Select(driver.findElement(By.xpath("//select[@id='Profile']")));
		sel.selectByVisibleText(val);
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		Thread.sleep(3000);
		retryForDetachedFrame(driver, "//iframe[@force-alohapage_alohapage]", 0);
		WebElement frame2=driver.findElement(By.xpath("//iframe[@force-alohapage_alohapage]"));
		Thread.sleep(800);
		driver.switchTo().frame(frame2);
		Thread.sleep(800);
		driver.findElement(By.xpath("//input[@title='Login']")).click();
		Thread.sleep(8000);
		retryForDetachedFrame(driver, "//iframe", 0);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);		
	}
	
	//Logout and switch to home
	public void Logouthome() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//a[contains(text(),'Log out')]")).click();
			Thread.sleep(3000);
			}
			catch(WebDriverException e){
				Thread.sleep(500);
				driver.findElement(By.xpath("//a[contains(text(),'Log out')]")).click();	
				Thread.sleep(3000);
			}
			 catch(Exception ee)
		    {
		        ee.printStackTrace();
		        throw ee;
		    }
		Thread.sleep(1000);
		ReturntoMain();
	}
	
    public void ReturntoMain() throws InterruptedException {
    	Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Search apps and items...']")).sendKeys("Service Console_");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span/p/b[contains(text(),'Service Console_')]")).click();
		Thread.sleep(6000);
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
	   
		public boolean visibleText(By element)
		{
			WebDriverWait wait= new WebDriverWait(driver, 10000);
			
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
			
			System.out.println("Element is visible");
			return false;
		}
	   
	    public void jsClick(WebElement el) {
			try {
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click();", el);
				System.out.println("Element clicked");
			} catch (Exception e){
				System.out.println("=============================================================");
				System.out.println("Exception-jsClick(): "+e.getMessage());
				//takeScreenShot();
				e.printStackTrace();
				System.out.println("=============================================================");
			}
		}
}

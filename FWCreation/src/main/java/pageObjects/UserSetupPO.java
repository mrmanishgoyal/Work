package pageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.Queries;
import resources.base;

public class UserSetupPO extends base {
	WebDriver driver;

	private String btnLoginxpath = "//input[@title='Login']";
	private String btnEditxpath = "//input[@title='Edit']";
	private String lnkProfilexpath = "//td[text()='Profile']/following::a";

	// Declaring Constructor
	public UserSetupPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Logout code
	public void Logout() throws InterruptedException {
		driver.get(driver.getCurrentUrl());
		Thread.sleep(2000);
		try
	    {
	    Thread.sleep(1000);
			driver.findElement(By.xpath("//span[@class='uiImage']")).click();
	    }
	    catch(WebDriverException e)
	    {
	    	driver.findElement(By.xpath("//span[@class='uiImage']")).click();
	    }
	    catch(Exception ee)
	    {
	        ee.printStackTrace();
	        throw ee;
	    }
		
		Thread.sleep(1200);
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		Thread.sleep(1500);
		
	}

	//Clicking on User Detail Button
	public void ClickLoginbutton() throws Exception {
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe"));
		Thread.sleep(800);
		driver.switchTo().frame(frame);
		Thread.sleep(800);
		driver.findElement(By.xpath(btnLoginxpath)).click();
		Thread.sleep(8000);
		retryForDetachedFrame(driver, "//iframe", 0);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
	}

   //Capture Profile value
	public String CaptureProfileVal() throws Exception {
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe"));
		Thread.sleep(800);
		driver.switchTo().frame(frame);
		Thread.sleep(800);
		String Profile=driver.findElement(By.xpath(lnkProfilexpath)).getText();
		Thread.sleep(800);
		return Profile;
	}
	
	//Capture value for MKApp and Ameyo Availability
	public void MKandAmeyoAppCheck() throws Exception {
		Thread.sleep(1000);
		retryForDetachedFrame(driver, "//iframe[contains(@title,'User')]", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe[contains(@title,'User')]"));
		Thread.sleep(800);
		driver.switchTo().frame(frame);
		Thread.sleep(800);
		visibleText(By.xpath("//h2[text()='User Detail']"));
		scrollIntoView(driver.findElement(By.xpath("//td[text()='MKApp Availability Status']")));
		String MKApp=driver.findElement(By.xpath("//td[text()='MKApp Availability Status']/following::img")).getAttribute("alt");
		String AmeyoApp=driver.findElement(By.xpath("//td[text()='Ameyo Availability Status']/following::img")).getAttribute("alt");
		if(MKApp.equalsIgnoreCase("Checked") && AmeyoApp.equalsIgnoreCase("Checked")) {
			System.out.println("MKApp Availability Status and Ameyo Availability Status are already Checked");
		}
		else {
			driver.findElement(By.xpath(btnEditxpath)).click();
			Thread.sleep(4000);
			retryForDetachedFrame(driver, "//iframe", 0);
			WebElement frame1=driver.findElement(By.xpath("//iframe"));
			Thread.sleep(800);
			driver.switchTo().frame(frame1);
			Thread.sleep(800);
			driver.findElement(By.xpath("//td/label[text()='Middle Name']")).click();
			scrollIntoView(driver.findElement(By.xpath("//td/label[text()='MKApp Availability Status']")));
			WebElement ele = driver.findElement(By.xpath("//label[text()='MKApp Availability Status']/following::input"));
			
			WebElement ele1 = driver.findElement(By.xpath("//label[text()='Ameyo Availability Status']/following::input")); 
			if(!ele.isSelected()) {
			driver.findElement(By.xpath("//label[text()='MKApp Availability Status']")).click();
			Thread.sleep(500);
			}
			if(!ele1.isSelected()) {
				driver.findElement(By.xpath("//label[text()='Ameyo Availability Status']")).click();
				Thread.sleep(500);
			}
			
			driver.findElement(By.xpath("//input[@title='Save']")).click();
			Thread.sleep(3000);
		}
		
		//driver.navigate().back();
		//Thread.sleep(3000);
		
		try {
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		
		ReturntoMain();
		}
		catch(WebDriverException e) {
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			
			ReturntoMain();	
		}
		catch(Exception ee) {
			  ee.printStackTrace(); 
			  throw ee; 
			  }
		/*
		 * try { Thread.sleep(1500);
		 * driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click(); }
		 * catch(WebDriverException e) { ReturntoMain(); } catch(Exception ee) {
		 * ee.printStackTrace(); throw ee; }
		 */
		 
	}
	
	
	//Capture value for MKApp and Ameyo Availability
	public void KnowledgeUserCheck() throws Exception {
		Thread.sleep(1000);
		retryForDetachedFrame(driver, "//iframe[contains(@title,'User')]", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe[contains(@title,'User')]"));
		Thread.sleep(800);
		driver.switchTo().frame(frame);
		Thread.sleep(800);
		visibleText(By.xpath("//h2[text()='User Detail']"));
		scrollIntoView(driver.findElement(By.xpath("//td[text()='Knowledge User']")));
		String KnowledgeCheck=driver.findElement(By.xpath("//td[text()='Knowledge User']/following::img")).getAttribute("alt");
		if(KnowledgeCheck.equalsIgnoreCase("Checked")) {
			System.out.println("Knowledge User is already checked");
		}
		else {
			driver.findElement(By.xpath(btnEditxpath)).click();
			Thread.sleep(3000);
			retryForDetachedFrame(driver, "//iframe", 0);
			WebElement frame1=driver.findElement(By.xpath("//iframe"));
			Thread.sleep(800);
			driver.switchTo().frame(frame1);
			Thread.sleep(800);
			driver.findElement(By.xpath("//td/label[text()='Middle Name']")).click();
			scrollIntoView(driver.findElement(By.xpath("//td/label[text()='Knowledge User']")));
			WebElement ele = driver.findElement(By.xpath("//label[text()='Knowledge User']/following::input"));
			 
			if(!ele.isSelected()) {
			driver.findElement(By.xpath("//label[text()='Knowledge User']")).click();
			Thread.sleep(500);
			}
			
			driver.findElement(By.xpath("//input[@title='Save']")).click();
			Thread.sleep(3000);
		}
		
		SelectPermissionSet("Knowledge Author");
		
		
		//driver.navigate().back();
		//Thread.sleep(3000);
		
		try {
		driver.switchTo().defaultContent();
    	Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//input[@placeholder='Search apps and items...']")).sendKeys("Knowledge");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span/p/b[contains(text(),'Knowledge')]")).click();
		Thread.sleep(4500);
		}
		catch(WebDriverException e) {
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			
	    	Thread.sleep(1500);
			driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
			Thread.sleep(1500);
			driver.findElement(By.xpath("//span/p[contains(text(),'Knowledge')]")).click();
			Thread.sleep(6000);
		}
		catch(Exception ee) {
			  ee.printStackTrace(); 
			  throw ee; 
			  }
		 
	}
	
	//Selecting Permission set
	public void SelectPermissionSet(String val) throws Exception {
		retryForDetachedFrame(driver, "//iframe[contains(@title,'User')]", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe[contains(@title,'User')]"));
		Thread.sleep(800);
		driver.switchTo().frame(frame);
		Thread.sleep(800);
		jsClick(driver.findElement(By.xpath("//h3[text()='Permission Set Assignments']/following::input[@name='editPermSetAssignments']")));
		Thread.sleep(2000);
		retryForDetachedFrame(driver, "//iframe[contains(@title,'Permission')]", 0);
		WebElement frame1=driver.findElement(By.xpath("//iframe[contains(@title,'Permission')]"));
		Thread.sleep(800);
		driver.switchTo().frame(frame1);
		Thread.sleep(800);
		if(driver.findElement(By.xpath("//label[text()='Available Permission Sets']/following::select/option[@title='"+val+"']")).isDisplayed()) {
		scrollIntoView(driver.findElement(By.xpath("//label[text()='Available Permission Sets']/following::select/option[@title='"+val+"']")));
		Thread.sleep(800);
		Select sel = new Select(driver.findElement(By.xpath("//label[text()='Available Permission Sets']/following::select[@class='dueling ']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
		//jsClick(driver.findElement(By.xpath("//label[text()='Available Permission Sets']/following::select/option[@title='"+val+"']")));
		//Thread.sleep(500);
		jsClick(driver.findElement(By.xpath("//img[@alt='Add']")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//input[@value='Save']")));
		Thread.sleep(2500);
		}
		else {
			System.out.println("The Permission set "+val+" is already assigned ");
			jsClick(driver.findElement(By.xpath("//input[@value='Cancel']")));
			Thread.sleep(2500);
		}
	}
	
	//Click Login for manager
	public void ClickMangerLoginbutton() throws Exception {
		
		driver.findElement(By.xpath(btnLoginxpath)).click();
		Thread.sleep(8000);
	}
	
	//Go to Queues
	public void GotoUserQueue(String val) throws Exception {
		Thread.sleep(1000);
		jsClick(driver.findElement(By.xpath("//a[contains(@class,'menuTriggerLink')]")));
		Thread.sleep(1500);
		driver.findElement(By.xpath("//ul[@role='presentation']/li[1]")).click();
		Thread.sleep(2500);
		switchWindow();
		
		driver.findElement(By.xpath("//input[@placeholder='Quick Find']")).sendKeys("Queues");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//mark[text()='Queues']")).click();
		Thread.sleep(3500);
		
		retryForDetachedFrame(driver, "//iframe[contains(@title,'Queue')]", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe[contains(@title,'Queue')]"));
		Thread.sleep(800);
		driver.switchTo().frame(frame);
		Thread.sleep(800);
		
		
		driver.findElement(By.xpath("//a[@class='listItem']/span[text()='I']")).click();
		Thread.sleep(2500);
		
		retryForDetachedFrame(driver, "//iframe[contains(@title,'Queue')]", 0);
		WebElement frame1=driver.findElement(By.xpath("//iframe[contains(@title,'Queue')]"));
		Thread.sleep(800);
		driver.switchTo().frame(frame1);
		Thread.sleep(800);
		
		//Actions ac1= new Actions(driver);
		//ac1.moveToElement(driver.findElement(By.xpath("//tr//th/a[text()='Inbound Collections']"))).build().perform();
		scrollIntoView(driver.findElement(By.xpath("//tr//th/a[text()='"+val+"']")));
		driver.findElement(By.xpath("//tr//th/a[text()='"+val+"']")).click();
		Thread.sleep(2000);
		
		//retryForDetachedFrame(driver, "//iframe[contains(@title,'Queue')]", 0);
		//WebElement frame2=driver.findElement(By.xpath("//iframe[contains(@title,'Queue')]"));
		//Thread.sleep(800);
		//driver.switchTo().frame(frame2);
		//Thread.sleep(800);
	}
	
	//Capture 15 digit id from user
	public String Capture15digitid() {
		String[] id= driver.findElement(By.xpath("//span[text()='Account Name']/following::a")).getAttribute("href").split("/");
		System.out.println(id[5]);
		return id[5];
	}

	//Select the user by making MKApp and AmeyoApp change if needed.
	public void SelectUsertoAssign(String val) throws Exception {
		String Mainwin2= driver.getWindowHandle();
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame2=driver.findElement(By.xpath("//iframe[contains(@title,'Queue')]"));
		Thread.sleep(800);
		driver.switchTo().frame(frame2);
		Thread.sleep(1000);
		/*
		 * try { Thread.sleep(1000);
		 * driver.findElement(By.xpath("//th[text()='Name']")).click(); }
		 * catch(WebDriverException e) {
		 * driver.findElement(By.xpath("//th[text()='Name']")).click(); }
		 * catch(Exception ee) { ee.printStackTrace(); throw ee; }
		 */
		
		jsClick(driver.findElement(By.xpath("//a[contains(text(),'Show')]")));
		Thread.sleep(1200);
		List<WebElement> list= driver.findElements(By.xpath("//tr/th/a"));
		for(int j=0;j<list.size();j++) {
			String user=list.get(j).getText();
			if(!user.equalsIgnoreCase(val)) {
				DevConsolePO dc= new DevConsolePO(driver);
				String[] a= list.get(j).getAttribute("href").split("%2F");
				//String a= list.get(j).getAttribute("href");
				//System.out.println(a);
				System.out.println(a[0]+" and 2 "+a[1]);
				String[] id = a[1].split("%3F");
				System.out.println("User url val"+id[0]);
				dc.ExecuteQuery(Queries.Chatcount(id[0]));
				
				if(dc.DecideCapacity()==true) {
				System.out.println("Moving forward with user");
				driver.close();
				driver.switchTo().window(Mainwin2);
				driver.switchTo().frame(frame2);
				list.get(j).click();
				UserSetupPO us = new UserSetupPO(driver);
				us.MKandAmeyoAppCheck();
				break;
				}
				else {
				System.out.println("Trying another user");
				driver.switchTo().window(Mainwin2);
				Thread.sleep(1000);
				driver.switchTo().frame(frame2);
				}
			}
		}
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
	
	public boolean visibleText(By element)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		
		System.out.println("Element is visible");
		return false;
	}
	
    public void ReturntoMain() throws InterruptedException {
    	Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//span/p[contains(text(),'Service Console_')]")).click();
		Thread.sleep(6000);
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
    
	//To switch the top most window
	public void switchWindow() throws InterruptedException{
		Set<String> winid = driver.getWindowHandles();
        Iterator<String> iter = winid.iterator();
        iter.next();
        String tab = iter.next();
        Thread.sleep(3000);
        driver.switchTo().window(tab);
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

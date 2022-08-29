package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.base;

public class KnowledgePO extends base {
	WebDriver driver;

	private String btnNavMenuxpath = "//button[@title='Show Navigation Menu']";
	private String btnPaymntOptnxpath = "//span[text()='Cases'][@class='menuLabel slds-listbox__option-text slds-listbox__option-text_entity']";
	private String btnNewPaymntxpath="//div[text()='New']";
	private String lnkRecentViewxpath= "//ul/following::span[text()='Recently Viewed']";
	private String lnkDeletexpath = "//a[@title='Delete']";
	private String btnDeletexpath = "//button/span[text()='Delete']";
	private String btnDrpdwnxpath = "//div[@role='tablist']//a[@title='Cases'][contains(@class,'tabHeader')]/following::button[contains(@title,'Actions for')]";
	private String btnRefreshxpath = "//li[@title='Refresh Tab']/a";
	
	// Declaring Constructor
	public KnowledgePO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Click Button
	public void ClickButton(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//span[text()='Knowledge']/following::ul[contains(@class,'branding')]/li/a/div[text()='"+val+"']")));
		Thread.sleep(2000);
	}
	
	//Select the RecordType
	public void SelectRecordType(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[text()='New Knowledge']/following::span[contains(@class,'slds-form-element__label')][text()='"+val+"']")));
		Thread.sleep(500);
		jsClick(driver.findElement(By.xpath("//span[text()='Next']")));
		Thread.sleep(2000);
	}
	
	//Enter Title
	public void EnterTitle(String val) throws InterruptedException {
		driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::span[text()='Title']/following::input")).sendKeys(val);
		Thread.sleep(300);
	}
	
	//Select Issue Category
	public void SelectIssueCategory(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::div[text()='Issue Category']/following::span[text()='"+val+"']")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::div[text()='Issue Category']/following::span[text()='Move selection to Chosen']")));
		Thread.sleep(300);
	}
	
	//Enter URL Name
	public void EnterURLName(String val) throws InterruptedException {
		driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::span[text()='URL Name']/following::input")).sendKeys(val);
		Thread.sleep(300);
	}
		
	//Select Sub  Category
	public void SelectSubCategory(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::div[text()='Sub Category']/following::span[text()='"+val+"']")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::div[text()='Sub Category']/following::span[text()='Move selection to Chosen']")));
		Thread.sleep(300);
	}
	
	//Enter SummaryUse 
	public void EnterSummary(String val) throws InterruptedException {
		driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::span[text()='Summary']/following::textarea")).sendKeys(val);
		Thread.sleep(300);
	}
	
	//Select Issue Type
	public void SelectIssueType(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::div[text()='Issue Type']/following::span[text()='"+val+"']")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::div[text()='Issue Type']/following::span[text()='Move selection to Chosen']")));
		Thread.sleep(300);
	}
	
	//Select Issue Sub-Type
	public void SelectIssueSubType(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::span[text()='Issue Sub-Type']/following::a")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::span[text()='Issue Sub-Type']/following::a[text()='"+val+"']")));
		Thread.sleep(300);
	}
	
	//Select Article Type
	public void SelectArticleType(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::span[text()='Article Type']/following::a")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::span[text()='Article Type']/following::a[text()='"+val+"']")));
		Thread.sleep(300);
	}
	
	//Select Your Customer might say this
	public void EnterYourCustomerDetail(String val) throws Exception {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::span[text()='Your Customer might say this']/following::button[text()='Edit']")));
		Thread.sleep(1000);
		retryForDetachedFrame(driver, "//iframe[contains(@class,'cke_wysiwyg_frame cke_reset')]", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe[contains(@class,'cke_wysiwyg_frame cke_reset')]"));
		driver.switchTo().frame(frame);
		//driver.findElement(By.xpath("")).click();
		driver.findElement(By.xpath("//html[@dir='ltr']/head/following::body[@id='editor_rta_body']/br")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//html[@dir='ltr']/head/following::body[@id='editor_rta_body']/br")).sendKeys(val);
		Thread.sleep(800);
		driver.switchTo().defaultContent();
		Thread.sleep(800);
	}//
	
	//Select Article Body
	public void EnterArticleBody(String val) throws Exception {
		jsClick(driver.findElement(By.xpath("//h2[contains(text(),'New')]/following::span[text()='Article Body']/following::button[text()='Edit']")));
		Thread.sleep(1000);
		retryForDetachedFrame(driver, "//iframe[contains(@title,'Article Body Text Editor Container')]", 0);
		WebElement frame=driver.findElement(By.xpath("//iframe[contains(@title,'Article Body Text Editor Container')]"));
		Thread.sleep(800);
		driver.switchTo().frame(frame);
		//driver.findElement(By.xpath("//body[@id='editor_rta_body']")).click();
		driver.findElement(By.xpath("//html[@dir='ltr']/head/following::body[@id='editor_rta_body']/br")).click();
		Thread.sleep(300);
		//driver.findElement(By.xpath("//body[@id='editor_rta_body']")).sendKeys(val);
		driver.findElement(By.xpath("//html[@dir='ltr']/head/following::body[@id='editor_rta_body']/br")).sendKeys(val);
		Thread.sleep(800);
		driver.switchTo().defaultContent();
		Thread.sleep(800);
	}
	
	//Close all cases open
	public void CloseAllCases() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> Closebutton=driver.findElements(By.xpath("//tr/td[6]"));
		for(int n=0;n<Closebutton.size();n++) {
			WebElement ele= driver.findElement(By.xpath("//h1[text()='Cases']/following::span[@class='slds-icon_container slds-icon-utility-down']"));
			jsClick(ele);
			Thread.sleep(1200);
			jsClick(driver.findElement(By.xpath("//a[@title='Delete']")));
			Thread.sleep(1200);
			driver.findElement(By.xpath("//button/span[text()='Delete']")).click();
			Thread.sleep(2000);
		}
	}
	
	//Recently viewed
	public void RecentlyViewedlist() throws InterruptedException {

		WebElement IcnDownxpath = driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-down slds-button__icon slds-icon_container forceIcon']"));
	    jsClick(IcnDownxpath);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul/following::span[text()='Recently Viewed']")).click();
		Thread.sleep(2000);
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
				//takeScreenShot();
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
}

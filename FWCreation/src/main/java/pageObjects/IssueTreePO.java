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

public class IssueTreePO extends base {
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
	public IssueTreePO(WebDriver driver) {
		this.driver = driver;
	}
	
	//*********************Academics/Content*****************************
	
	//Program Selector
	public void ProgramSelector() throws InterruptedException {
		visibleText(By.xpath("//label[text()='Program Selector']"));
		jsClick(driver.findElement(By.xpath("//label[text()='Program Selector']/following::button")));
		Thread.sleep(200);
		//visibleText(By.xpath("//label[text()='Program Selector']/following::button/span[contains(text(),'Byju') or span[contains(text(),'BYJU')]"));
		jsClick(driver.findElement(By.xpath("//label[text()='Program Selector']/following::span[contains(text(),'Byjus')] | //label[text()='Program Selector']/following::span[contains(text(),'BYJUS')] | //label[text()='Program Selector']/following::span[contains(text(),'Learning')] | //label[text()='Program Selector']/following::span[contains(text(),'Aakash')] | //label[text()='Program Selector']/following::span[contains(text(),'Program')] | //label[text()='Program Selector']/following::span[contains(text(),'Series')] | //label[text()='Program Selector']/following::span[contains(text(),'Neo')]")));
		Thread.sleep(800);
	}
	
	//Premium Id Selector
	public void PremiumidSelector() throws InterruptedException {
		visibleText(By.xpath("//label[text()='Premium Id Selector']"));
		jsClick(driver.findElement(By.xpath("//label[text()='Premium Id Selector']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Premium Id Selector']/following::span[1]")));
		Thread.sleep(200);
	}
	
	//Issue Category
	public void IssueCategory(String val) throws InterruptedException {
		visibleText(By.xpath("//label[text()='Issue Category']"));
		clickButton(driver.findElement(By.xpath("//label[text()='Issue Category']/following::button[@name]")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//label[text()='Issue Category']/following::button[@name]")));
		Thread.sleep(1000);
		//visibleText(By.xpath("//label[text()='Issue Category']/following::span[text()='"+val+"']"));
		jsClick(driver.findElement(By.xpath("//label[text()='Issue Category']/following::span[text()=\""+val+"\"]")));
	}
	
	//Issue Sub Category
	public void IssueSubCategory(String val) throws InterruptedException {
		visibleText(By.xpath("//label[text()='Issue Sub Category']"));
		Thread.sleep(300);
		//clickButton(driver.findElement(By.xpath("//label[text()='Issue Sub Category']/following::button[@name]")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//label[text()='Issue Sub Category']/following::button[@name]")));
		Thread.sleep(800);
		jsClick(driver.findElement(By.xpath("//label[text()='Issue Sub Category']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}
	
	//Issue Type
	public void IssueType(String val) throws InterruptedException {
		visibleText(By.xpath("//label[text()='Issue Type']"));
		Thread.sleep(1000);
		clickButton(driver.findElement(By.xpath("//label[text()='Issue Type']/following::button[@name]")));
		Thread.sleep(300);
		jsClick(driver.findElement(By.xpath("//label[text()='Issue Type']/following::button[@name]")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Issue Type']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}
	
	//Issue Sub Type
	public void IssueSubType(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Issue Sub Type']/following::button[@name]")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Issue Sub Type']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}
	
	//Issue Notes
	public void IssueNotes(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Issue Sub Type']/following::textarea")).sendKeys(val);
		Thread.sleep(200);
	}
	
	//Is the Issue Resolved Currently?
	public void IstheIssueResolved(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Is the Issue Resolved Currently?']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Is the Issue Resolved Currently?']/following::span[text()='"+val+"']")));
		Thread.sleep(200);
	}
	
	//Click Next
	public void ClickNext() throws InterruptedException {
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(1500);
	}
	
	//Click Next
	public void ClickNext2() throws InterruptedException {
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(500);
	}
	
	//Click Next
	public void ClickNext3() throws InterruptedException {
		if(driver.findElement(By.xpath("//button[text()='Next']")).isDisplayed()) {
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(500);
		}
		else {
			System.out.println("No next button displayed");
		}
	}
	
	//*********************Logistics*****************************
	
	//Product Details - 0
	public void ProductDetails(String val) throws InterruptedException {
		visibleText(By.xpath("//label[text()='Product Details']"));
		clickButton(driver.findElement(By.xpath("//label[text()='Product Details']/following::button")));
		jsClick(driver.findElement(By.xpath("//label[text()='Product Details']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Product Details']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}

	//Order ID - 1
	public void OrderID() throws InterruptedException {
		visibleText(By.xpath("//label[text()='Order ID']"));
		clickButton(driver.findElement(By.xpath("//label[text()='Order ID']/following::button")));
		jsClick(driver.findElement(By.xpath("//label[text()='Order ID']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Order ID']/following::span[contains(text(),'SSO')]")));
		Thread.sleep(200);
	}
	
	//Updated Address - 2
	public void UpdatedAddress(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Updated Address']/following::input")).sendKeys(val);
		Thread.sleep(200);
	}
	
	//Pin Code - 3
	public void PinCode(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Pin Code']/following::input")).sendKeys(val);
		Thread.sleep(200);
	}	
	
	//Reason for Re-dispatch - 4
	public void ReasonForRedispatch(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Reason for Re-dispatch']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Reason for Re-dispatch']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}	
	
	//Product category - 5
	public void Productcategory(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Product category']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Product category']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}
	
	//Courier partner - 6
	public void CourierPartner(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Courier partner']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Courier partner']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}
	
	//Contact number (customer used to contact helpline number) - 7
	public void Contactnumber(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Contact number (customer used to contact helpline number)']/following::input")).sendKeys(val);
		Thread.sleep(200);
	}	
	
	//Program details (Initial class to final class with validity& Board) - 8
	public void Programdetails(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Program details (Initial class to final class with validity& Board)']/following::input")).sendKeys(val);
		Thread.sleep(200);
	}	
	
	//Customer iratness - 9
	public void Customeriratness(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Customer iratness']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Customer iratness']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}
	
	//Replacement reason - 10
	public void Replacementreason(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Replacement reason']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Replacement reason']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}
	
	//Will address remains same - 11
	public void Willaddressremainssame(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Will address remains same']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Will address remains same']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}
	
	//Hold time - 12
	public void Holdtime(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Hold time']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Hold time']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
	}
	
	//Approved by - 13
	public void Approvedby(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Approved by']/following::input")).sendKeys(val);
		Thread.sleep(200);
	}

	//Payment details - 14
	public void Paymentdetails(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Payment details']/following::input")).sendKeys(val);
		Thread.sleep(200);
	}
	
	//Date of order delivery - 15
	public void Dateoforderdelivery(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Date of order delivery']/following::input")).sendKeys(val);
		Thread.sleep(200);
	}
	
	//Name of sibling - 16
	public void Nameofsibling() throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Name of sibling']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Name of sibling']/following::span[contains(text(),'Auto')] | //label[text()='Name of sibling']/following::span[contains(text(),'Dummy')]")));
		Thread.sleep(200);
	}
	
	
	//*********************K3 Tech & Program*****************************
	
	//*********************K4-10 Tech & Program*****************************
	
	//Session in which the issue occured? - 1
	public void Sessionissueoccured(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Date']/following::input")).sendKeys(val);
		Thread.sleep(200);
		//driver.findElement(By.xpath("//label[text()='Time']/following::input")).clear();
		//Thread.sleep(200);
		//driver.findElement(By.xpath("//label[text()='Time']/following::input")).sendKeys("3:05 pm");
		//Thread.sleep(200);	
	}
		
	//Which internet are you using? - 2
	public void Whichinternet(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Which internet are you using?']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Which internet are you using?']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);	
	}
	
	//Are you using an anti-virus? - 3
	public void Antivirus(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Are you using an anti-virus?']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Are you using an anti-virus?']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);	
	}
	
	//Which antivirus are you using? -4 
	public void Whichantivirus(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Which antivirus are you using?']/following::input")).sendKeys(val);
		Thread.sleep(200);
	}
	
	//Product - 5 
	public void Product(String val) throws InterruptedException {
		visibleText(By.xpath("//label[text()='Product']"));
		jsClick(driver.findElement(By.xpath("//label[text()='Product']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Product']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);	
	}
	
	//Variant -6 
	public void Variant(String val) throws InterruptedException {
		//visibleText(By.xpath("//label[text()='Variant']"));
		//Thread.sleep(200);
		//clickButton(driver.findElement(By.xpath("//label[text()='Variant']/following::button")));
		//Thread.sleep(200);
		//jsClick(driver.findElement(By.xpath("//label[text()='Variant']")));
		//Thread.sleep(200);
		ClickNext2();
		jsClick(driver.findElement(By.xpath("//label[text()='Variant']/following::button[@aria-describedby]")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Variant']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);
		ClickNext2();
	}
	
	//*********************K11-12 Tech & Program*****************************
	
	//*********************Digital Finance*****************************
	
	//*********************Refund Requests*****************************
	
	//*********************Sales*****************************
	
	//*********************Renewal/Upgrades*****************************
	
	//Language
	public void Language(String val) throws InterruptedException {
		visibleText(By.xpath("//label[text()='Language']"));
		jsClick(driver.findElement(By.xpath("//label[text()='Language']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Language']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);	
	}
	
	//Product Pitch Done?
	public void ProductPitchDone(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Product Pitch Done?']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Product Pitch Done?']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);	
	}
	
	//Price Pitched?
	public void PricePitched(String val) throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//label[text()='Price Pitched?']/following::button")));
		Thread.sleep(200);
		jsClick(driver.findElement(By.xpath("//label[text()='Price Pitched?']/following::span[text()=\""+val+"\"]")));
		Thread.sleep(200);	
	}
	
	//Price Value
	public void PriceValue(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Price Value']/following::input")).sendKeys(val);
		Thread.sleep(200);
	}
	
	
	//*********************Marketing & Promotional Campaigns*****************************
	
	//*********************Mentor Issues*****************************
	
	//*********************BTC Issues*****************************
	
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

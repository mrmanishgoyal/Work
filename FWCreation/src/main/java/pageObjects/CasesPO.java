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

public class CasesPO extends base {
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
	public CasesPO(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//
	public void OpenURL(String MailName, String CaseNumber, String Pid) throws InterruptedException {
		  String LSPDURL="https://dev.byjusorders.com/cso/create-request?emailId%3D"+MailName+"%40byjus.com%26source%3Dsalesforce%26studentId%3D"+Pid+"%26caseId%3D"+CaseNumber+"%26issueType%3DSD%20card%20Issues%26issueSubType%3DSD%20Card(FOC%2FLocked)";
		  System.out.println(LSPDURL);
		  driver.get(LSPDURL);
		  Thread.sleep(2500);
	}
	
	//Page Launch text
	public String PageLaunch() {
		 String PageLaunch=driver.findElement(By.xpath("//h5")).getText();
		 return PageLaunch;
	}
	//Capture Case Number
	public String CaptureCaseNo() {
		String ele=driver.findElement(By.xpath("//p[text()='Case Number']/following::lightning-formatted-text")).getText();
		return ele;
	}
	//Selecting Nav Menu
	public void NavMenuClick() throws InterruptedException {
	driver.findElement(By.xpath(btnNavMenuxpath)).click();
	Thread.sleep(1000);
	}

	//Selecting Payment option from Nav Menu
	public void CaseNavMenuClick() throws InterruptedException {
	driver.findElement(By.xpath(btnPaymntOptnxpath)).click();
	Thread.sleep(1500);
	}
	
	//Creating New Payment
	public void NewCaseClick() throws InterruptedException {
	driver.findElement(By.xpath(btnNewPaymntxpath)).click();
	Thread.sleep(3000);
	}
	
	//Creating New Case with in Account
	public void NewCaseInAccountClick() throws InterruptedException {
	driver.findElement(By.xpath("//h1[text()='Cases']/following::div[text()='New']")).click();
	Thread.sleep(3000);
	}
	//Navigate to Case tab
	public void NavCaseTab(String val) throws InterruptedException {
	driver.findElement(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//span[@class='title slds-truncate'][text()='"+val+"']")).click();
	Thread.sleep(1000);
	}
	
	//Navigate to Cases tab
	public void NavCasesTab() throws InterruptedException {
		try
	    {
	    Thread.sleep(1000);
			driver.findElement(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//span[@class='title slds-truncate'][text()='Cases']")).click();
			Thread.sleep(1000);
	    }
	    catch(WebDriverException e)
	    {
	    	driver.findElement(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//span[@class='title slds-truncate'][text()='Cases']")).click();
	    	Thread.sleep(1000);
	    }
	    catch(Exception ee)
	    {
	        ee.printStackTrace();
	        throw ee;
	    }

	}
	
	//Refresh tab
	public void RefreshTab() throws InterruptedException {
	driver.get(driver.getCurrentUrl());
	Thread.sleep(3000);
	}
	
	//Delete the Case record created
	public void DeleteCCaseRecord(String val) throws InterruptedException {
		driver.findElement(By.xpath("//a[@title='Cases'][contains(@class,'tabHeader')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='"+val+"']/following::span[@class='slds-icon_container slds-icon-utility-down']")).click();
		Thread.sleep(800);
		WebElement ele = driver.findElement(By.xpath("//a[text()='"+val+"']/following::a[@role='button']/following::div[text()='Delete']"));
		jsClick(ele);
		Thread.sleep(800);
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		Thread.sleep(2000);
	}

	//Delete the created Case record
	public void DeleteCaseRecord(String val) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='"+val+"']/following::span[@class='slds-icon_container slds-icon-utility-down']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath(lnkDeletexpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(btnDeletexpath)).click();
		Thread.sleep(1500);
	}
	
	//Delete all the created My Case record
	public void DeleteAllMyCaseRecord() throws InterruptedException {
		driver.findElement(By.xpath("//lightning-icon[contains(@class,'close')]")).click();
		Thread.sleep(1000);
		driver.get(driver.getCurrentUrl());
		Thread.sleep(3500);
		List<WebElement> list=driver.findElements(By.xpath("//tr/td[10]"));
		for(int i=0;i<list.size();i++) {
			//try {
		//jsClick(list.get(i));
		//	}
		//	catch(org.openqa.selenium.StaleElementReferenceException ex) {
			//	ex.printStackTrace();
				//driver.findElement(By.xpath("//button[@name]/lightning-primitive-icon")).click();
			//	Thread.sleep(1000);
			//	list.get(i).click();
			//}//
		WebElement ele = driver.findElement(By.xpath("//span[@class='slds-icon_container slds-icon-utility-down']"));
		jsClick(ele);
		Thread.sleep(1500);
		driver.findElement(By.xpath(lnkDeletexpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(btnDeletexpath)).click();
		Thread.sleep(3500);
		}
		driver.findElement(By.xpath("//button[@title='Show filters']")).click();
		Thread.sleep(1000);
	}
	
	//Remove all the filters added
	public void RemveFlterAdded() throws InterruptedException {
	List<WebElement> list =driver.findElements(By.xpath("//a[@class='closeX']"));
	for(int i=0;i<list.size();i++) {
		list.get(i).click();
		Thread.sleep(500);
	}
	
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	Thread.sleep(2000);
	}
	
	//Adding filter for Status
	public void NavNEOL2Queue() throws InterruptedException {
	WebElement IcnDownxpath = driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-down slds-button__icon slds-icon_container forceIcon']"));
    jsClick(IcnDownxpath);
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@role='combobox'][@aria-owns]")).click();
	Thread.sleep(800);
	driver.findElement(By.xpath("//input[@role='combobox'][@aria-owns]")).sendKeys("Neo L2 Queue");
	Thread.sleep(1200);
	driver.findElement(By.xpath("//mark[text()='Neo L2 Queue']")).click();
	Thread.sleep(2500);
	}
	
	//Adding filter for Status
	public void NavNEOL2QueueProd() throws InterruptedException {
	WebElement IcnDownxpath = driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-down slds-button__icon slds-icon_container forceIcon']"));
    jsClick(IcnDownxpath);
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@role='combobox'][@aria-owns]")).click();
	Thread.sleep(800);
	driver.findElement(By.xpath("//input[@role='combobox'][@aria-owns]")).sendKeys("Automation Test Queue");
	Thread.sleep(1200);
	driver.findElement(By.xpath("//mark[text()='Automation Test Queue']")).click();
	Thread.sleep(2500);
	}
	
	public void FilterbyMyCases() throws InterruptedException {
		visibleText(By.xpath("//span[text()='Updated a few seconds ago']"));
		driver.findElement(By.xpath("//button[@title='Show filters']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()='Filter by Owner']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='My cases']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[text()='Done']")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(3500);
	}
	
	//Capture the Filter text
	public String CaptureFilterText() {
		String FilterText=driver.findElement(By.xpath("//div[@role='status']/span")).getText();
		return FilterText;
	}
	
	public void SetBackDefault() throws InterruptedException {
		driver.findElement(By.xpath("//div[text()='Filter by Owner']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='All cases']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[text()='Done']")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(3000);
	}
	
	public void AddFilterStatus() throws InterruptedException {
		visibleText(By.xpath("//span[text()='Updated a few seconds ago']"));
	driver.findElement(By.xpath("//button[@title='Show filters']")).click();
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//a[text()='Add Filter']")).click();
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//label[text()='Field']/following::button")).click();
	Thread.sleep(800);
	WebElement ele = driver.findElement(By.xpath("//label[text()='Field']/following::span[text()='Status']"));
	scrollIntoView(ele);
	ele.click();
	
	driver.findElement(By.xpath("//div[text()='Value']/following::a")).click();
	Thread.sleep(800);
	WebElement ele1 = driver.findElement(By.xpath("//div[text()='Value']/following::a[text()='Open']"));
	ele1.click();
	WebElement ele2 = driver.findElement(By.xpath("//div[text()='Value']/following::a[text()='Closed']"));
	scrollIntoView(ele2);
	ele2.click();
	driver.findElement(By.xpath("//span[text()='Done']")).click();
	Thread.sleep(1000);
	}
	
	//Refresh tab
	public void RefreshCurrentTab() throws InterruptedException {
	driver.findElement(By.xpath(btnDrpdwnxpath)).click();
	Thread.sleep(900);
	
	driver.findElement(By.xpath(btnRefreshxpath)).click();
	Thread.sleep(1500);
	}
	
	//Adding filter for Last Modified Date
	public void AddFilterDateTimeOpen() throws InterruptedException {
	driver.findElement(By.xpath("//a[text()='Add Filter']")).click();
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//label[text()='Field']/following::button")).click();
	Thread.sleep(800);
	WebElement ele3 = driver.findElement(By.xpath("//label[text()='Field']/following::span[text()='Date/Time Opened']"));
	scrollIntoView(ele3);
	ele3.click();
	
	driver.findElement(By.xpath("//label[text()='Operator']/following::button")).click();
	Thread.sleep(800);
	WebElement ele = driver.findElement(By.xpath("//label[text()='Operator']/following::span[text()='greater or equal']"));
	scrollIntoView(ele);
	ele.click();
	
	//DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	// Date date = new Date();
	 //String date1= dateFormat.format(date);
	 //System.out.println(date1);
	
	 Date date = DateUtils.addDays(new Date(), -1);
	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	// SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	// if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		 driver.findElement(By.xpath("//span[text()='Value']/following::input")).sendKeys(sdf.format(date)); 
	 //}
	//driver.findElement(By.xpath("//span[text()='Value']/following::input")).sendKeys(sdf.format(date));
	Thread.sleep(800);
	
	driver.findElement(By.xpath("//span[text()='Done']")).click();
	Thread.sleep(1000);
	}
	
	//Adding filter for Last Modified Date
	public void AddFilterDateTimeOpen_UAT() throws InterruptedException {
	driver.findElement(By.xpath("//a[text()='Add Filter']")).click();
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//label[text()='Field']/following::button")).click();
	Thread.sleep(800);
	WebElement ele3 = driver.findElement(By.xpath("//label[text()='Field']/following::span[text()='Date/Time Opened']"));
	scrollIntoView(ele3);
	ele3.click();
	
	driver.findElement(By.xpath("//label[text()='Operator']/following::button")).click();
	Thread.sleep(800);
	WebElement ele = driver.findElement(By.xpath("//label[text()='Operator']/following::span[text()='greater or equal']"));
	scrollIntoView(ele);
	ele.click();
	
	//DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	// Date date = new Date();
	 //String date1= dateFormat.format(date);
	 //System.out.println(date1);
	
	 Date date = DateUtils.addDays(new Date(), -1);
	 SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	 driver.findElement(By.xpath("//span[text()='Value']/following::input")).sendKeys(sdf1.format(date)); 
	 Thread.sleep(800);
	
	driver.findElement(By.xpath("//span[text()='Done']")).click();
	Thread.sleep(1000);
	}
	
	//Adding filter for all owners
		public void AddFilterOwnerName() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Add Filter']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//label[text()='Field']/following::button")).click();
		Thread.sleep(800);
		WebElement ele3 = driver.findElement(By.xpath("//label[text()='Field']/following::span[text()='Owner Name']"));
		scrollIntoView(ele3);
		ele3.click();
		
		driver.findElement(By.xpath("//span[text()='Value']/following::input")).sendKeys("Rakesh Kr Soni, Shashi Kant, Rohit Prajapati, Dipak Biswal, Krishna Dev Singh, Ajit Singh, Mohammad Suhail Ahmad, Jai Saheb, Deepak Kapasia, Himangi Goyal, Satyajeet Rao, Ribi John, Chinmoy Kalita, Sonali Behera, Karthik Kumbar, Neo L2 Queue");
		Thread.sleep(800);
		
		driver.findElement(By.xpath("//span[text()='Done']")).click();
		Thread.sleep(1000);
		}
		
		//Adding filter for Parent Case Number
		public void AddFilterParentCaseNo() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Add Filter']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//label[text()='Field']/following::button")).click();
		Thread.sleep(800);
		WebElement ele3 = driver.findElement(By.xpath("//label[text()='Field']/following::span[text()='Parent Case Number']"));
		scrollIntoView(ele3);
		ele3.click();
		
		driver.findElement(By.xpath("//label[text()='Operator']/following::button")).click();
		Thread.sleep(800);
		WebElement ele = driver.findElement(By.xpath("//label[text()='Operator']/following::span[text()='less or equal']"));
		scrollIntoView(ele);
		ele.click();
		
		driver.findElement(By.xpath("//span[text()='Value']/following::input")).sendKeys("0");
		Thread.sleep(800);
		
		driver.findElement(By.xpath("//span[text()='Done']")).click();
		Thread.sleep(1000);
		}	
		
		//Click Rentention first call for Rentention user
		public void ClickRetentionFirstCall() throws InterruptedException {
			//WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']"));
			//Actions ac=new Actions(driver);
			//ac.moveToElement(ele).build().perform();
			//jsClick(ele);
			//Thread.sleep(1000);
			jsClick(driver.findElement(By.xpath("//span[text()='Open Activities']/following::a[text()='Retention - First Call']")));
			Thread.sleep(2000);
		}
	
	//To click on Save Button
	public void ClickSave() throws InterruptedException {
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	Thread.sleep(3000);
	}
	
	//To capture the Record Count in queue
	public String[] RecrdCnt() throws InterruptedException {
	Thread.sleep(1000);	
	String[] RecordCount=driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText().split(" item");
	System.out.println(RecordCount[0]);
	Thread.sleep(2000);
	return RecordCount;
	}
	
	
	//Capture the Case Record number
	public String CaseRN() {
	String CaseNumber= driver.findElement(By.xpath("//span/a[@data-refid]")).getText();
	return CaseNumber;
	}
	
	//Click On Case option
	public void CaseOptnSel() throws InterruptedException {
	driver.findElement(By.xpath("//span/a[@data-refid]")).click();
	Thread.sleep(3000);
	}
	
	//Check for DP to be Refunded to Wallet
	public void CheckDPRefdWall() {
		List<WebElement> list=driver.findElements(By.xpath("//tr/following::td[5]/span/a"));
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getText().equalsIgnoreCase("DP to be Refunded to Wallet")) {
				list.get(i).click();
			}
		}
	}
	
	//Check for DP to be Refunded to Wallet
	public void CheckOARefdWall() {
		List<WebElement> list1=driver.findElements(By.xpath("//tr/following::td[5]/span/a"));
		for(int m=0;m<list1.size();m++) {
			if(list1.get(m).getText().equalsIgnoreCase("Other Amount To Be Refunded To Wallet")) {
				list1.get(m).click();
			}
		}
	}
	
	//Capture Case for DP
	public String CaptureCaseNoDP() throws InterruptedException {
		String CaseNumberfrDP= driver.findElement(By.xpath("//lightning-formatted-text[contains(text(),'DP to be')]/following::p[text()='Case Number']/following::lightning-formatted-text")).getText();
		Thread.sleep(700);
		return CaseNumberfrDP;

	}
	
	//Capture Case owner detail
	public String CaptureCaseOwnerDP() throws InterruptedException {
		String Owner=driver.findElement(By.xpath("//lightning-formatted-text[contains(text(),'DP to be')]/following::span[text()='Case Owner']/following::slot[@force-lookup_lookup]")).getText();
		Thread.sleep(700);
		return Owner;

	}
	
	//Capture Parent Case detail
	public String CaptureParentCaseOwnerDP() throws InterruptedException {
		String ParentCase=driver.findElement(By.xpath("//lightning-formatted-text[contains(text(),'DP to be')]/following::span[text()='Parent Case']/following::slot[@force-lookup_lookup]/span")).getText();
		//Thread.sleep(700);
		return ParentCase;

	}
	
	//Capture Case for Other Amount
	public String CaptureCaseNoOA() throws InterruptedException {
		String CaseNumberfrOA= driver.findElement(By.xpath("//lightning-formatted-text[contains(text(),'Other Amount To Be')]/following::p[text()='Case Number']/following::lightning-formatted-text")).getText();
		Thread.sleep(700);
		return CaseNumberfrOA;
		
	}
		
	//Capture Case owner detail OA
	public String CaptureCaseOwnerOA() throws InterruptedException {
		String Owner1=driver.findElement(By.xpath("//lightning-formatted-text[contains(text(),'Other Amount To Be')]/following::span[text()='Case Owner']/following::slot[@force-lookup_lookup]")).getText();
		Thread.sleep(700);
		return Owner1;

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

package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.base;


public class CreatedAccountPO extends base{
	WebDriver driver;
	public static Logger log = LogManager.getLogger(CreatedAccountPO.class.getName());

	private String lnk_SProgamBTLA_xpath = "//span[text()='Student Programs'][@class='slds-truncate slds-m-right--xx-small']"
			+ "/following-sibling::span[@class='lds-shrink-none slds-m-right--xx-small']/"
			+ "following::span[@force-lookup_lookup][@data-proxy-id][@id='window'][text()='Byjus The Learning App']";

	private String lnk_Sprogram_xpath = "//article[@class='slds-card slds-card_boundary']//span[text()='Student Programs']/following::span[@id='window']";
	private String lnk_Sorder_xpath = "//span[text()='Student Orders']/following::span[contains(text(),'Student Order :')][@force-lookup_lookup]";
	private String lnk_StunSSorder_xpath = "//span[text()='Student Sales Orders']/following::span[@force-lookup_lookup]";
	private String btnDrpdwnxpath = "//button[contains(@title,'Actions for')]";
	private String btnRefreshxpath = "//li[@title='Refresh Tab']/a";
	
	String[] Studentorder1val = null;
	String[] Studentorder2val = null;
	int counter=1;
	
	// Declaring Constructor
	public CreatedAccountPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Check Visibility Student Order id 
		public boolean CheckVisStudentOrderid() {
			WebElement Studentpayments=driver.findElement(By.xpath(lnk_Sorder_xpath));
			return Studentpayments.isDisplayed();
		}
		
	//Check Visibility Student and Student Sales Orders
	public boolean CheckVisSalesStudentOrderid() {
		WebElement Studentpayments=driver.findElement(By.xpath(lnk_StunSSorder_xpath));
		return Studentpayments.isDisplayed();
	}
	
	//Capture the Student and Student Sales Orders created
		public void CaptureStudentSalesOrder() throws InterruptedException {
		List<WebElement> Studentorder=driver.findElements(By.xpath(lnk_StunSSorder_xpath));
		for(int i=0;i<Studentorder.size();i++) {
			
			System.out.println("The Student order created is "+i+ " " +Studentorder.get(i).getText());
			log.info("The Student order created is "+i+ " " +Studentorder.get(i).getText());
			//Studentorder1val = Studentorder.get(0).getText().trim().split(": ");
			//Studentorder2val = Studentorder.get(1).getText().trim().split(": ");
			Thread.sleep(500);
		}
		//System.out.println(Studentorder1val[1]);
		//System.out.println(Studentorder2val[1]);
		}
		
		//Capture the Student and Student Sales Orders created
				public void CaptureStudentAppInfo() throws InterruptedException {
				List<WebElement> StudentAppInfo=driver.findElements(By.xpath("//span[text()='Student App Informations']/following::span[contains(text(),'SAI-')][@force-lookup_lookup]"));
				for(int i=0;i<StudentAppInfo.size();i++) {
					
					System.out.println("The Student order created is "+i+ " " +StudentAppInfo.get(i).getText());
					log.info("The Student order created is "+i+ " " +StudentAppInfo.get(i).getText());
					//Studentorder1val = Studentorder.get(0).getText().trim().split(": ");
					//Studentorder2val = Studentorder.get(1).getText().trim().split(": ");
					Thread.sleep(500);
				}
				//System.out.println(Studentorder1val[1]);
				//System.out.println(Studentorder2val[1]);
				}
	
	//Capture the Student Order id's created
	public void CaptureStudentOrder() throws InterruptedException {
	List<WebElement> Studentorder=driver.findElements(By.xpath(lnk_Sorder_xpath));
	for(int i=0;i<Studentorder.size();i++) {
		
		System.out.println("The Student order created is "+i+ " " +Studentorder.get(i).getText());
		log.info("The Student order created is "+i+ " " +Studentorder.get(i).getText());
		Studentorder1val = Studentorder.get(0).getText().trim().split(": ");
		Studentorder2val = Studentorder.get(1).getText().trim().split(": ");
		Thread.sleep(500);
	}
	System.out.println(Studentorder1val[1]);
	System.out.println(Studentorder2val[1]);
	}
	

	public void CaptureStudentOrderIndvid() {
	String OrderNo1 = driver.findElement(By.xpath("//span[text()='Student Orders']/following::span[contains(text(),'"+Studentorder1val[1]+"')][@force-lookup_lookup]/following::dt[text()='Order No:']/following-sibling::dd/lst-template-list-field/lst-formatted-text")).getText();
	String OrderNo2 = driver.findElement(By.xpath("//span[text()='Student Orders']/following::span[contains(text(),'"+Studentorder2val[1]+"')][@force-lookup_lookup]/following::dt[text()='Order No:']/following-sibling::dd/lst-template-list-field/lst-formatted-text")).getText();
	System.out.println(OrderNo1);
	System.out.println(OrderNo2);
	}
	
	//Click Student Order
	public void ClickStudentOrder() throws InterruptedException {
	WebElement ele = driver.findElement(By.xpath(lnk_Sorder_xpath));
	Actions ac=new Actions(driver);
	ac.moveToElement(ele).build().perform();
	jsClick(ele);
	Thread.sleep(3000);
	//driver.findElement(By.xpath("//tr[1]/th/span/a")).click();
	//Thread.sleep(2000);
	driver.findElement(By.xpath("//span[text()='Order']/following::a")).click();
	Thread.sleep(2000);
	}
	
	//Click Student Order
	public void ClickStudentSalesOrder() throws InterruptedException {
	WebElement ele = driver.findElement(By.xpath("//span[text()='Student Sales Orders']"));
	Actions ac=new Actions(driver);
	Scrollpageup();
	ac.moveToElement(ele).build().perform();
	jsClick(ele);
	Thread.sleep(3000);
	driver.findElement(By.xpath("//td[2]/span/a")).click();
	Thread.sleep(2000);
	}
	
	//Check Existing Profile field is present
	public boolean CheckExistingProfile() {
		/*
		 * boolean a; try { if(driver.findElement(By.
		 * xpath("//span[text()='Existing Profile']/following::button[contains(@title,'Edit')]"
		 * )).isDisplayed()) { a=true; return a; } } catch(Throwable t) {
		 * t.printStackTrace(); a=false; return a; }
		 */
		return isDisplayed(By.xpath("//span[text()='Existing Profile']/following::button[contains(@title,'Edit')]"));
	}
	
	
	//Check Visibility Student Payment id
	public boolean CheckVisStudentPayid() throws InterruptedException {
		Scrollpagedown();
		WebElement Studentpayments=driver.findElement(By.xpath("//span[text()='Student Payments']/following::span[contains(text(),'SP-')][@force-lookup_lookup]"));
		return Studentpayments.isDisplayed();
	}
	//Capture the Student Payment id's
	public void CaptureAllStudentPayid() throws InterruptedException {
	List<WebElement> Studentpayments=driver.findElements(By.xpath("//span[text()='Student Payments']/following::span[contains(text(),'SP-')][@force-lookup_lookup]"));
	for(int j=0;j<Studentpayments.size();j++) {
		
		System.out.println("The Student payment created are "+j+ " " +Studentpayments.get(j).getText());		
		Thread.sleep(500);
		
	}
	
	}
	//Capture the Premium ID
	public String CapturePremiumID() {
	WebElement ele2=driver.findElement(By.xpath("//span[text()='Premium ID']/following::lightning-formatted-text"));		
	String PremiumID = ele2.getText();
	System.out.println("The Premium ID is: "+PremiumID);
	return PremiumID;
	}

	//Check Visibility Student Program id
	public boolean CheckVisStudentPrgid() {
		WebElement StudentProgramCreated=driver.findElement(By.xpath("//span[text()='Student Programs']/following::span[@force-lookup_lookup]"));
		return StudentProgramCreated.isDisplayed();
	}
	
	//Capture Student Program details	
	public void CaptureAllStudentProgDetails() throws InterruptedException {
	List<WebElement> StudentProgramCreated=driver.findElements(By.xpath("//span[text()='Student Programs']/following::span[@force-lookup_lookup]"));
	
	for(int k=0;k<StudentProgramCreated.size();k++) {
		
		System.out.println("The Student payment created are "+k+ " " +StudentProgramCreated.get(k).getText());
		log.info("The Student payment created are "+k+ " " +StudentProgramCreated.get(k).getText());
		Thread.sleep(500);
	}
	}
	
	//Check created Program
	public String CapturePrgCreated() {
	String Progval = driver.findElement(By.xpath("//span[text()='Student Programs']/following::span[@force-lookup_lookup]")).getText();
	return Progval;
	}
	
	//Check the Super status
	public String CaptureSuperStatus() {
	String SuperStatus = driver.findElement(By.xpath("//div[@records-highlightsdetailsitem_highlightsdetailsitem]/p[@title='Super Status']/following::lightning-formatted-text")).getText();
	return SuperStatus;
	}
	
	//Check Status
	public String CaptureStatus() {
	String Status = driver.findElement(By.xpath("//div[@records-highlightsdetailsitem_highlightsdetailsitem]/p[@title='Status']/following::lightning-formatted-text")).getText();
	return Status;
	}
	
	//Verify the PE shipped call is present
	
	public boolean CheckVisPEShippedCall() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("scroll(0,650);");
		  Thread.sleep(2000);
		  
		  WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='PE - Shipped Call']"));
		 return ele.isDisplayed();
	}
	
	//Verify the Workshop - Welcome Call is present
	
	public boolean CheckVisWSWelcomeCall() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("scroll(0,600);");
		  Thread.sleep(2000);
		  
		  WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='Workshop - Welcome Call']"));
		 return ele.isDisplayed();
	}

	//Verify the PE shipped call is present
	
	public boolean CheckVisProdPEShippedCall() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("scroll(0,400);");
		  Thread.sleep(2000);
		  
		  WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='PE - Shipped Call']"));
		 return ele.isDisplayed();
	}
	
	public boolean CheckVisProdPEShippedCall_UAT() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("scroll(0,650);");
		  Thread.sleep(2000);
		  
		  WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='PE - Shipped Call']"));
		 return ele.isDisplayed();
	}
	
	//Check BTC Welcome Call
	public boolean CheckVisBTCWelcomeCall_UAT() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("scroll(0,650);");
		  Thread.sleep(2000);
		  
		  WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='BTC Welcome Call']"));
		 return ele.isDisplayed();
	}
  
	 //Click on PE shipped task
	public void ClickPEShipped() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='PE - Shipped Call']"));
		Actions ac= new Actions(driver);
		//waitForElementToVisible(ele);
		ac.moveToElement(ele).build().perform();
		jsClick(ele);
		Thread.sleep(1000);
		//ele.click();
	}
	
	 //Click on BTC Welcome Call
	public void ClickBTCWelcomeCall() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='BTC Welcome Call']"));
		Actions ac= new Actions(driver);
		//waitForElementToVisible(ele);
		ac.moveToElement(ele).build().perform();
		jsClick(ele);
		Thread.sleep(1000);
		//ele.click();
	}
	
	 //Click on Workshop - Welcome Call task
	public void ClickWSWelcomeCall() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='Workshop - Welcome Call']"));
		Actions ac= new Actions(driver);
		//waitForElementToVisible(ele);
		ac.moveToElement(ele).build().perform();
		jsClick(ele);
		Thread.sleep(3000);
		//ele.click();
	}
	  
	//Click on Student Prog
	public void ClickStudentProg() throws InterruptedException {
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("scroll(0,200);");
	  Thread.sleep(2000);
	  
	  driver.findElement(By.xpath("//span[text()='Student Programs']/following::span[@force-lookup_lookup]")).click();
	  Thread.sleep(3000);
	}
	
	//Click on Student Prog
	public void ClickStudentProg2() throws InterruptedException {
		WebElement ele1 = driver.findElement(By.xpath("//span[text()='Student Programs']/following::span[@force-lookup_lookup]"));
		Actions ac= new Actions(driver);
		ac.moveToElement(ele1).build().perform();
		Thread.sleep(2000);
		jsClick(ele1);
		Thread.sleep(3000);
	}
	
	//Click Student sales order in Student Program
	public void ClickSSOinStuProg() throws InterruptedException {
	  driver.findElement(By.xpath("//span[text()='Student Sales Order']/following::span[@force-lookup_lookup]")).click();
	  Thread.sleep(2000);
	}
	
	//Capture Account Name
	public String CaptureAccOwnrNam() {
	return driver.findElement(By.xpath("//span[text()='Account Name']/following::lightning-formatted-name")).getText();
	}
	
	//Capture Account Owner Name
	public String CaptureAccOwnerNam() throws InterruptedException {
	Scrollhome();
	return driver.findElement(By.xpath("//span[text()='Account Owner']/following::a/following::span")).getText().replaceAll("Open ", "").replaceAll(" Preview", "");
	}

	//Capture 15 digit id from user
	public String Capture15digitid() {
		String[] id= driver.findElement(By.xpath("//span[text()='Account Name']/following::a")).getAttribute("href").split("/");
		System.out.println(id[5]);
		return id[5];
	}
	//Capture Account Owner Name
	public void ClickAccOwnerNam() throws InterruptedException {
	//Actions ac = new Actions(driver);
	//ac.moveToElement(driver.findElement(By.xpath("//span[text()='Account Owner']/following::a"))).build().perform();
	//Thread.sleep(500);
	jsClick(driver.findElement(By.xpath("//span[text()='Account Owner']/following::a")));
	Thread.sleep(3000);
	}
	
	//Navigate to Account Owner Tab
	public void ClickAccOwnrTab() throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//a[@title='"+CaptureAccOwnrNam()+"'][@class='tabHeader slds-tabs--default__link slds-p-right--small slds-grow ']")));
		Thread.sleep(2000);
	}
	
	//Navigate to Account Owner Tab
	public void ClickAccOwnrTab2(String val) throws InterruptedException {
		driver.findElement(By.xpath("//a[@title='"+val+"'][@class='tabHeader slds-tabs--default__link slds-p-right--small slds-grow ']")).click();
		Thread.sleep(2000);
	}
	

	
	//Verify the PE OnBoarding call is present
	
	public boolean CheckVisPEOnboardingCall() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("scroll(0,750);");
		  Thread.sleep(2000);
		  
		  WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='PE - Onboarding Call']"));
		 return ele.isDisplayed();
	}
	
	//Verify the Workshop - Onboarding Call is present
	
	public boolean CheckVisWSOnboardingCall() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("scroll(0,550);");
		  Thread.sleep(2000);
		  
		  WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='Workshop - Onboarding Call']"));
		 return ele.isDisplayed();
	}
  
	 //Click on Workshop - Onboarding Call task
	public void ClickWSOnboardngCall() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='Workshop - Onboarding Call']"));
		jsClick(ele);
		Thread.sleep(2200);
	}
	
	 //Click on PE OnBoarding task
	public void ClickPEOnboardngCall() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='PE - Onboarding Call']"));
		jsClick(ele);
		Thread.sleep(2200);
	}
	
	//Verify the PE OnBoarding call is present
	
	public boolean CheckVisPEFollowUpCall() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("scroll(0,500);");
		  Thread.sleep(2000);
		  
		  WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[contains(text(),'PE - Follow Up')]"));
		 return ele.isDisplayed();
	}
	
	//Verify the PE OnBoarding call is present
	
	public boolean CheckVisFollowUpTutorandMentorCall() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("scroll(0,750);");
		  Thread.sleep(2000);
		  
		  //WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='Followup "+counter+"/Tutor & Mentor Feedback']"));
		  WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[contains(text(),'Follow')]"));
		 return ele.isDisplayed();
	}
	
	 //Click on PE OnBoarding task
	public void ClickPEFollowUpCall() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[contains(text(),'PE - Follow Up')]"));
		jsClick(ele);
		Thread.sleep(3500);
	}
	
	 //Click on Followup 1/Tutor & Mentor Feedback
	public void ClickFollowUpTutorandMentorCall() throws InterruptedException {
		//WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[text()='Followup "+counter+"/Tutor & Mentor Feedback']"));
		WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']//following::a[contains(text(),'Follow')]"));
		jsClick(ele);
		Thread.sleep(3500);
		counter++;
	}
  
	//Click Open Activities -> New Task
	public void ClickOpenActivitiestoNewTask() throws InterruptedException {
		Scrollpagedown();
		WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']"));
      	//Thread.sleep(800);
		Actions ac= new Actions(driver);
		ac.moveToElement(ele1).build().perform();
		Thread.sleep(2000);
		jsClick(ele1);
		Thread.sleep(800);
		jsClick(driver.findElement(By.xpath("//h1[text()='Open Activities']/following::div[text()='New Task']")));
		Thread.sleep(2500);
	}
	
	//Click on Open Activities drop down
	public void ClickOpenActiDD() {
	  try {
		  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']/following::lightning-icon"));
      	//Thread.sleep(800);
		Actions ac= new Actions(driver);
		ac.moveToElement(ele1).build().perform();
		Thread.sleep(1200);
		jsClick(ele1);
      	//ele1.click();
	  }
		catch(Throwable t){
			t.printStackTrace();
		}
	  try {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  	js.executeScript("scroll(0,750);");
		  	Thread.sleep(2000);
          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']/following::lightning-icon"));
			Actions ac= new Actions(driver);
			ac.moveToElement(ele1).click().build().perform();
			Thread.sleep(1200);
	  }
	  catch(Throwable t) {
		  t.printStackTrace();
	  }
	}
	
	//Click on Open Activities drop down
		public void ClickOpenActiDD2() throws InterruptedException {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
			  	js.executeScript("scroll(0,775);");
			  	Thread.sleep(2000);
			  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']/following::lightning-icon"));
	      	//Thread.sleep(800);
			Actions ac= new Actions(driver);
			ac.moveToElement(ele1).build().perform();
			Thread.sleep(1200);
			jsClick(ele1);
	      	//ele1.click();
		 
		}
	
	//Click on Open Activities drop down
	public void ClickOpenActiDD_Prod() {
	  try {
		  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']/following::lightning-icon"));
      	//Thread.sleep(800);
		Actions ac= new Actions(driver);
		ac.moveToElement(ele1).click().build().perform();
		Thread.sleep(1200);
      	//ele1.click();
	  }
		catch(Throwable t){
			t.printStackTrace();
		}
	  try {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  	js.executeScript("scroll(0,525);");
		  	Thread.sleep(2000);
          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']/following::lightning-icon"));
			Actions ac= new Actions(driver);
			ac.moveToElement(ele1).click().build().perform();
			Thread.sleep(1200);
	  }
	  catch(Throwable t) {
		  t.printStackTrace();
	  }
	}
	
	//Click on Cases drop down
	public void ClickCasesiDD() {
	  try {
		  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Cases']/following::lightning-icon"));
      	//Thread.sleep(800);
		Actions ac= new Actions(driver);
		ac.moveToElement(ele1).click().build().perform();
		Thread.sleep(800);
      	//ele1.click();
	  }
		catch(Throwable t){
			t.printStackTrace();
		}
	  try {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  	js.executeScript("scroll(0,600);");
		  	Thread.sleep(2000);
          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Cases']/following::lightning-icon"));
			Actions ac= new Actions(driver);
			ac.moveToElement(ele1).click().build().perform();
			Thread.sleep(800);
	  }
	  catch(Throwable t) {
		  t.printStackTrace();
	  }
	}
	
	//Click on Cases New Task Option
	public void ClickNew() throws InterruptedException {
	driver.findElement(By.xpath("//span[text()='Cases']/following::lightning-icon/following::a[@title='New']")).click();
	Thread.sleep(2000);
	}
	
	//Click on Open Activities New Task Option
	public void ClickNewTask() throws InterruptedException {
	WebElement ele = driver.findElement(By.xpath("//span[text()='Open Activities']/following::lightning-icon/following::a[@title='New Task']"));
	Actions ac= new Actions(driver);
	ac.moveToElement(ele).build().perform();
	jsClick(ele);
	Thread.sleep(2000);
	}
	
	//Click on Cases Mobile card
	public void ClickCasesMC() throws InterruptedException {
	Thread.sleep(800);
	Actions ac = new Actions(driver);
	ac.moveToElement(driver.findElement(By.xpath("//span[text()='Cases']")));
	jsClick(driver.findElement(By.xpath("//span[text()='Cases']")));
	Thread.sleep(3000);
	}
	
	//Click on Cases Mobile card
	public void ClickCasesMC2() throws InterruptedException {
	Scrollpagedown();
	Scrollpagedown();
	Thread.sleep(800);
	Actions ac = new Actions(driver);
	ac.moveToElement(driver.findElement(By.xpath("//span[text()='Cases']")));
	jsClick(driver.findElement(By.xpath("//span[text()='Cases']")));
	Thread.sleep(3000);
	}
	
	//Delete the created Student Prog
	public void DeleteCreatedStuProg() throws InterruptedException {
		WebElement ele4= driver.findElement(By.xpath("//span[text()='Student Programs']/following::span[@force-lookup_lookup]/following::lightning-button-menu"));
		Actions ac=new Actions(driver);
		ac.moveToElement(ele4).build().perform();
		jsClick(ele4);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//span[text()='Student Programs']/following::span[@force-lookup_lookup]/following::lightning-button-menu/following::a[@title='Delete']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		Thread.sleep(3000);
	}
	
	//Navback to Account screen
	public void NavBackToAccount() throws InterruptedException {
		WebElement ele5 = driver.findElement(By.xpath("//button[@title='Show Navigation Menu']"));
		jsClick(ele5);
		Thread.sleep(1000);
		
		jsClick(driver.findElement(By.xpath("//span[text()='Accounts'][@class='menuLabel slds-listbox__option-text slds-listbox__option-text_entity']")));
		Thread.sleep(2000);
	}
	  
	
	//Delete the Created Account
	public void DeleteAccountCreated(String val) throws InterruptedException {

		WebElement IcnDownxpath = driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-down slds-button__icon slds-icon_container forceIcon']"));
	    jsClick(IcnDownxpath);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul/following::span[text()='Recently Viewed']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+val+"']/following::span[@class='slds-icon_container slds-icon-utility-down']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button/span[text()='Delete']")).click();
		Thread.sleep(1500);
	}
	
	//Refresh tab
	public void RefreshTab() throws InterruptedException {
	driver.findElement(By.xpath(btnDrpdwnxpath)).click();
	Thread.sleep(900);
	
	driver.findElement(By.xpath(btnRefreshxpath)).click();
	Thread.sleep(1500);
	}
	
	//Navigate to Session
	public void ClickSessionInformation() {
		 try {
			  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Sessions_Information']"));
	      	//Thread.sleep(800);
			Actions ac= new Actions(driver);
			ac.moveToElement(ele1).build().perform();
			jsClick(ele1);
			Thread.sleep(800);
	      	//ele1.click();
		  }
			catch(Throwable t){
				t.printStackTrace();
			}
		  try {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
			  	js.executeScript("scroll(0,600);");
			  	Thread.sleep(2000);
	          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Sessions_Information']"));
				Actions ac= new Actions(driver);
				ac.moveToElement(ele1).click().build().perform();
				Thread.sleep(800);
		  }
		  catch(Throwable t) {
			  t.printStackTrace();
		  }
	}
	
	//Navigate to Session
		public void ClickSessionInformation_UAT() {
			 try {
				  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Sessions Information']"));
		      	//Thread.sleep(800);
				Actions ac= new Actions(driver);
				ac.moveToElement(ele1).build().perform();
				//jsClick(ele1);
				Thread.sleep(800);
		      	ele1.click();
			  }
				catch(Throwable t){
					t.printStackTrace();
				}
			  try {
				  JavascriptExecutor js = (JavascriptExecutor) driver;
				  	js.executeScript("scroll(0,600);");
				  	Thread.sleep(2000);
		          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Sessions Information']"));
					Actions ac= new Actions(driver);
					ac.moveToElement(ele1).click().build().perform();
					Thread.sleep(800);
			  }
			  catch(Throwable t) {
				  t.printStackTrace();
			  }
		}
	
	public void ClickOn(By by) throws InterruptedException {

		WebElement element = driver.findElement(by);
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(element));
		element.click(); // Expected condition for the element to be clickable
		Thread.sleep(3000);
	}
	
	
	//Navigate to Exam Details
	public void ClickExamDetails() {
		 try {
			  Scrollpagedown();
			  //Scrollpagedown();
			  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Exam Details']"));
	      	//Thread.sleep(800);
			Actions ac= new Actions(driver);
			ac.moveToElement(ele1).click().build().perform();
			Thread.sleep(800);
	      	//ele1.click();
		  }
			catch(Throwable t){
				t.printStackTrace();
			}
		  try {
			  Scrollpagedown();
	          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Exam Details']"));
				jsClick(ele1);
				Thread.sleep(800);
		  }
		  catch(Throwable t) {
			  t.printStackTrace();
		  }
	}
	
	//Navigate to Student Classroom Associations
	public void ClickStudentClassroomAssociations() {
		 try {
			  Scrollpagedown();
			  //Scrollpagedown();
			  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Student Classroom Associations']"));
	      	//Thread.sleep(800);
			Actions ac= new Actions(driver);
			ac.moveToElement(ele1).click().build().perform();
			Thread.sleep(800);
	      	//ele1.click();
		  }
			catch(Throwable t){
				t.printStackTrace();
			}
		  try {
			  Scrollpagedown();
	          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Student Classroom Associations']"));
				jsClick(ele1);
				Thread.sleep(800);
		  }
		  catch(Throwable t) {
			  t.printStackTrace();
		  }
	}
	
	//Navigate to Student App Informations
		public void ClickStudentAppInfo() {
			 try {
				  Scrollpagedown();
				  //Scrollpagedown();
				  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Student App Informations']"));
		      	//Thread.sleep(800);
				Actions ac= new Actions(driver);
				ac.moveToElement(ele1).click().build().perform();
				Thread.sleep(800);
		      	//ele1.click();
			  }
				catch(Throwable t){
					t.printStackTrace();
				}
			  try {
				  Scrollpagedown();
		          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Student App Informations']"));
					jsClick(ele1);
					Thread.sleep(800);
			  }
			  catch(Throwable t) {
				  t.printStackTrace();
			  }
		}
		
		//Close the History tab
		public void ClickMinimizeHistory() throws InterruptedException {
			driver.findElement(By.xpath("//h2[@title='History']/following::button[@title='Minimize']")).click();
			Thread.sleep(1000);
		}
		
		//Navigate to Open Activities
				public void ClickOpenActivities() {
					 try {
						  Scrollpagedown();
						  //Scrollpagedown();
						  WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']"));
				      	//Thread.sleep(800);
						Actions ac= new Actions(driver);
						ac.moveToElement(ele1).click().build().perform();
						Thread.sleep(800);
				      	//ele1.click();
					  }
						catch(Throwable t){
							t.printStackTrace();
						}
					  try {
						  Scrollpagedown();
				          WebElement ele1 =  driver.findElement(By.xpath("//span[text()='Open Activities']"));
							jsClick(ele1);
							Thread.sleep(800);
					  }
					  catch(Throwable t) {
						  t.printStackTrace();
					  }
				}
				
				
	//Scroll down
	public void Scrolldown() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.DOWN);
		Thread.sleep(700);
	}
	
	//Close all sub tabs opened
	public void CloseSubTabs() throws InterruptedException {
		List<WebElement> ele=driver.findElements(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//div[contains(@class,'close')]/button/lightning-primitive-icon[@data-aura-rendered-by]"));
		for(int i=0;i<ele.size();i++) {
			jsClick(ele.get(i));
			Thread.sleep(300);
		}
	}
	
	//Close current sub tab open
	public void CloseCurrentSubTab() throws InterruptedException {
		//RefreshTab();
		driver.switchTo().defaultContent();
		List<WebElement> ele=driver.findElements(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//div[contains(@class,'close')]/button/lightning-primitive-icon[@data-aura-rendered-by]"));
		int m = ele.size()-1;
		jsClick(ele.get(m));
		Thread.sleep(300);		
	}
	
	//Close current sub tab open
	public void CloseCurrentSubTab_Prod() throws InterruptedException {
		//RefreshTab();
		try {
		driver.switchTo().defaultContent();
		List<WebElement> ele=driver.findElements(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//div[contains(@class,'close')]/button/lightning-primitive-icon[@data-aura-rendered-by]"));
		int m = ele.size()-1;
		jsClick(ele.get(m));
		Thread.sleep(300);		
		}
		catch(Throwable t) {
			driver.switchTo().defaultContent();
			List<WebElement> ele=driver.findElements(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//div[contains(@class,'close')]/button/lightning-primitive-icon[@data-aura-rendered-by]"));
			int m = ele.size()-1;
			jsClick(ele.get(m));
			Thread.sleep(300);	
		}
	}
	
	//Mark all notifications as Read
    public void AllNotificationRead() throws InterruptedException {
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-notification slds-button__icon slds-global-header__icon slds-icon_container forceIcon']")).click();
    	Thread.sleep(2000); 	
    	driver.findElement(By.xpath("//a[text()='Mark all as read']")).click();
    	Thread.sleep(1000);
    	}
    		
    //If No notification
    public void Notification() throws InterruptedException {
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-notification slds-button__icon slds-global-header__icon slds-icon_container forceIcon']")).click();
    	Thread.sleep(2000);
    	String Text=driver.findElement(By.xpath("//div[@class='titleContainer']/following::span")).getText();
    	if(Text.equalsIgnoreCase("You don't have any notifications right now.")) {
    		driver.findElement(By.xpath("//button[@title='Close Notifications']")).click();
    		Thread.sleep(1000);
    	}
    	else {
        	driver.findElement(By.xpath("//a[text()='Mark all as read']")).click();
        	Thread.sleep(1000);
    	}
    	}
    
    //Check for Quick Access Buttons and show more buttons
    public void CheckButtonsQAandSM(String Env,String SheetName) throws Exception {
    	List<WebElement> List= driver.findElements(By.xpath("//ul/li[@class='visible']//button"));
		System.out.println(List.size());
		for(int i=0;i<List.size();i++) {
			
			String OnPagebuttons=List.get(i).getText();
			System.out.println(OnPagebuttons);
			setData(Env, SheetName, i + 1, 1, OnPagebuttons);
			if(i==List.size()-1) {
				setData2(Env, SheetName, i + 2, 1, List.size());
				jsClick(driver.findElement(By.xpath("//span[text()='Show more actions']")));
				Thread.sleep(1500);
				List<WebElement> Showmore= driver.findElements(By.xpath("//span[@runtime_platform_actions-ribbonmenuitem_ribbonmenuitem]"));
				System.out.println(Showmore.size());
				
				for(int j=0;j<Showmore.size();j++) {
					
					String ShowMorebuttons=Showmore.get(j).getText();
					System.out.println(ShowMorebuttons);
					setData(Env, SheetName, j + 1, 3, ShowMorebuttons);
					if(j==Showmore.size()-1) {
					setData2(Env, SheetName, j + 2, 3, Showmore.size());
				}

					//Actions ac= new Actions(driver);
					//ac.sendKeys(Keys.ESCAPE);
			}
				jsClick(driver.findElement(By.xpath("//span[text()='Show more actions']")));
		}
		}
    }
    
    //Check for Related List
    public void CheckRelatedList(String Env,String SheetName) throws Exception {
		Scrollpagedown();
		Thread.sleep(2500);
		
		Scrollpagedown();
		Thread.sleep(2500);
		
		Scrollend();
		Thread.sleep(2500);
		
		List<WebElement> List1= driver.findElements(By.xpath("//span[@lst-listviewmanagerheader_listviewmanagerheader][@class='slds-truncate slds-m-right--xx-small']"));
		System.out.println(List1.size());
		for(int k=0;k<List1.size();k++) {
			String MobileCards=List1.get(k).getText();
			System.out.println(MobileCards);
			setData(Env, SheetName, k + 1, 5, MobileCards);
			if(k==List1.size()-1) {
				setData2(Env, SheetName, k + 2, 5, List1.size());
			}
			
			
		}
		
		
    }
    
    //Check for Case List view Buttons
    public void CheckCaseListViewButtons(String Env,String SheetName) throws Exception {		
    	List<WebElement> List= driver.findElements(By.xpath("//ul/li[@class='slds-button slds-button--neutral']//div"));
		System.out.println(List.size());
		for(int i=0;i<List.size();i++) {
			
			String OnPagebuttons=List.get(i).getText();
			System.out.println(OnPagebuttons);
			setData(Env, SheetName, i + 1, 1, OnPagebuttons);
			if(i==List.size()-1) {
				setData2(Env, SheetName, i + 2, 1, List.size());
				if(driver.findElement(By.xpath("//span[text()='Show more actions']")).isDisplayed()) {
				jsClick(driver.findElement(By.xpath("//span[text()='Show more actions']")));
				Thread.sleep(1500);
				}
				List<WebElement> Showmore= driver.findElements(By.xpath("//a/div[@class='forceActionLink']"));
				System.out.println(Showmore.size());
				
				for(int j=0;j<Showmore.size();j++) {
					
					String ShowMorebuttons=Showmore.get(j).getText();
					System.out.println(ShowMorebuttons);
					setData(Env, SheetName, j + 1, 3, ShowMorebuttons);
					if(j==Showmore.size()-1) {
					setData2(Env, SheetName, j + 2, 3, Showmore.size());
				}

					//Actions ac= new Actions(driver);
					//ac.sendKeys(Keys.ESCAPE);
			}
				if(driver.findElement(By.xpath("//span[text()='Show more actions']")).isDisplayed()) {
				jsClick(driver.findElement(By.xpath("//span[text()='Show more actions']")));
				}	
		}
		}
    }
    
    //Enable all the buttons
    public void EnableButtons() throws InterruptedException { 
    	Scrollhome();
    	Thread.sleep(3000);
    	List<WebElement> List= driver.findElements(By.xpath("//*[local-name()='svg'][@class='slds-icon slds-icon-text-default slds-icon_xx-small']"));
    	for(int i=0;i<List.size();i++) {
    		jsClick(List.get(i));
    		Thread.sleep(500);
    	}
    }
    
    //Check for Related List buttons values
    public void CheckRelatedListButtonVals(String Env,String SheetName) throws Exception {
		//Scrollpagedown();
		//Thread.sleep(4000);
		
		//Scrollend();
		//Thread.sleep(4000);
		
    	Scrollhome();
    	Thread.sleep(3000);
    	//span[@lst-listviewmanagerheader_listviewmanagerheader][@class='slds-truncate slds-m-right--xx-small']/following::div[@class='uiMenu']
    	//span[@lst-listviewmanagerheader_listviewmanagerheader][@class='slds-truncate slds-m-right--xx-small']/following::ul[@data-aura-class]/li//a/following::div[@role='button']
    	List<WebElement> List= driver.findElements(By.xpath("//span[@lst-listviewmanagerheader_listviewmanagerheader][@class='slds-truncate slds-m-right--xx-small']/following::ul[@data-aura-class]/li"));
		List<WebElement> List1= driver.findElements(By.xpath("//span[@lst-listviewmanagerheader_listviewmanagerheader][@class='slds-truncate slds-m-right--xx-small']/following::ul[@data-aura-class]/li//a"));
		List<WebElement> List2= driver.findElements(By.xpath("//span[@lst-listviewmanagerheader_listviewmanagerheader][@class='slds-truncate slds-m-right--xx-small']/following::ul[@class='slds-button-group-list']/li/lightning-button-menu/following::runtime_platform_actions-ribbon-menu-item/a/span"));
		//System.out.println(List1.size());
		for(int k=0;k<List.size();k++) {
			String MobileCards=List.get(k).getAttribute("data-target-reveals").replaceAll("sfdc:StandardButton.", "").replaceAll("__c.New", "").replaceAll("_", " ");
			System.out.println(MobileCards);
			setData(Env, SheetName, k + 1, 7, MobileCards);
			jsClick(List1.get(k));
			Thread.sleep(800);
			String ButtonVal=List2.get(k).getText();
			setData(Env, SheetName, k + 2, 7, ButtonVal);
			System.out.println(ButtonVal);
			if(k==List.size()-1) {
				setData2(Env, SheetName, k + 2, 7, List1.size());
			}
			
			
		}
    }
    
    //Current owner check
    public String AccOwnerCheck() {
    	jsClick(driver.findElement(By.xpath("//span[text()='Account Owner']")));
    	System.out.println("Acocunt Owner id: "+driver.findElement(By.xpath("//span[text()='Account Owner']/following::a")).getAttribute("href"));
    	String AccountOwner= driver.findElement(By.xpath("//span[text()='Account Owner']/following::a//span")).getText();
    	return AccountOwner;
    }
    
    //Assign Account to logged in profile user
    public void AssignAccount(String val) throws InterruptedException {
    	jsClick(driver.findElement(By.xpath("//span[text()='Change Owner']")));
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//h2[text()='Change Account Owner']/following::input[@placeholder]")).sendKeys(val);
    	Thread.sleep(1500);
    	jsClick(driver.findElement(By.xpath("//div[@title='"+val+"']")));
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[@name='change owner']")).click();
    	Thread.sleep(2500);    
    }
    
    //Assign Account to logged in profile user
    public void AssignAccount2() throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//span[@class='uiImage']/img[@title='User']")));
		Thread.sleep(2000);
		String val= driver.findElement(By.xpath("//a[@class='profile-link-label']")).getText();
		Thread.sleep(1000);
    	jsClick(driver.findElement(By.xpath("//span[text()='Change Owner']")));
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//h2[text()='Change Account Owner']/following::input[@placeholder]")).sendKeys(val);
    	Thread.sleep(1500);
    	jsClick(driver.findElement(By.xpath("//div[@title='"+val+"']")));
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[@name='change owner']")).click();
    	Thread.sleep(2500);    
    }
    
    //Go to logged in user
    public void GotoLoggedInUser() throws InterruptedException {
 		jsClick(driver.findElement(By.xpath("//span[@class='uiImage']/img[@title='User']")));
 		Thread.sleep(2000);
 		jsClick(driver.findElement(By.xpath("//a[@class='profile-link-label']")));
 		Thread.sleep(2500); 
     }
     
    
	public void Scrollpagedown() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(1500);
	}
	
	public void Scrollpageup() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.PAGE_UP);
		Thread.sleep(1500);
	}
	
	public void Scrollhome() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.HOME);
		Thread.sleep(1200);
	}
	
	public void Scrollend() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.END);
		Thread.sleep(1200);
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
    
    public void setData(String fileName, String sheetName, int rowIndex, int cellIndex, String data) throws Exception {
        File location = new File(fileName);
        FileInputStream fis = new FileInputStream(location);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheet(sheetName);
        if (sheet1.getRow(rowIndex) == null) {
        sheet1.createRow(rowIndex).createCell(cellIndex).setCellValue(data);
        } else {
        sheet1.getRow(rowIndex).createCell(cellIndex).setCellValue(data);
        }
        FileOutputStream fos = new FileOutputStream(location);
        wb.write(fos);
        wb.close();
    }
    
    public void setData2(String fileName, String sheetName, int rowIndex, int cellIndex, int data) throws Exception {
        File location = new File(fileName);
        FileInputStream fis = new FileInputStream(location);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheet(sheetName);
        if (sheet1.getRow(rowIndex) == null) {
        sheet1.createRow(rowIndex).createCell(cellIndex).setCellValue(data);
        } else {
        sheet1.getRow(rowIndex).createCell(cellIndex).setCellValue(data);
        }
        FileOutputStream fos = new FileOutputStream(location);
        wb.write(fos);
        wb.close();
    }
    
	public boolean isDisplayed(By element) {
		List<WebElement> list = driver.findElements(element);
		//System.out.println(list.toString());
		boolean value = false;
		if (!list.isEmpty()) {
			if (list.get(0).isDisplayed()) {
				value = true;
				return value;
			}
		} else {

			return value;
		}
		return value;
	}
    
}

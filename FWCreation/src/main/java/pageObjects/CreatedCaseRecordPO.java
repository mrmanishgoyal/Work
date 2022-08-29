package pageObjects;

import java.util.ArrayList;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ExcelData;
import resources.base;

public class CreatedCaseRecordPO extends base {
	WebDriver driver;

	
	
	private String lblCaseRecordNumxpath = "//div[text()='Case']/following::lightning-formatted-text";
	private String txtAssignedToxpath = "//label[text()='Assigned To']/following::input";
	
	private String btnSavexpath = "//button[text()='Save']";

	// Declaring Constructor
	public CreatedCaseRecordPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Capture the New Case Record Created
	public String CaptureNewCaseRecord() throws InterruptedException {
		String CaseRecord=driver.findElement(By.xpath(lblCaseRecordNumxpath)).getText();
		Thread.sleep(300);
		return CaseRecord;
	}

	//Click on Assigned To option to assign the case
	public void ClickAssingedTo() throws InterruptedException {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("scroll(0,600);");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//lightning-formatted-text[text()='"+CaptureNewCaseRecord()+"']/following::span[text()='Assigned To']/following::button/span")).click();
	Thread.sleep(2000);
	}
	
	//Enter name details for collection assistant
	public void EnterAssingedTo(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtAssignedToxpath)).sendKeys(val);
	Thread.sleep(2500);
	driver.findElement(By.xpath("//label[text()='Assigned To']/following::span[@class='slds-media__body']/span/lightning-base-combobox-formatted-text[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"+val+"')]")).click();
	//driver.findElement(By.xpath("//label[text()='Assigned To']/following::span[@class='slds-media__body']/span/lightning-base-combobox-formatted-text/Strong[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"+val+"')]"));
	//jsClick(ele);
	Thread.sleep(800);
	}
	
	//Enter name details for collection assistant
	public void EnterAssingedTo2(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtAssignedToxpath)).sendKeys(val);
	Thread.sleep(2500);
	driver.findElement(By.xpath("//label[text()='Assigned To']/following::span[@class='slds-media__body']/span/lightning-base-combobox-formatted-text[@title='"+val+"']")).click();
	//driver.findElement(By.xpath("//label[text()='Assigned To']/following::span[@class='slds-media__body']/span/lightning-base-combobox-formatted-text/Strong[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"+val+"')]"));
	//jsClick(ele);
	Thread.sleep(800);
	}
	
	//Click on Save button
	public void ClickSave() throws InterruptedException {
	driver.findElement(By.xpath(btnSavexpath)).click();
	Thread.sleep(5000);
	}
	
	//Click on Assigned user
	public void ClickAssignedUser(String val) throws InterruptedException {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("scroll(0,600);");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//label[text()='Assigned To']/following::span[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"+val+"')]")).click();
	Thread.sleep(3000);
	}
	
	//Capture the Case Status val
	public String CaseStatusval() throws InterruptedException {
	String CaseStatus=driver.findElement(By.xpath("//div[@records-highlightsdetailsitem_highlightsdetailsitem]/p[@title='Status']/following::lightning-formatted-text")).getText();
	Thread.sleep(200);
	return CaseStatus;
	}
	
	//Capture the Case Owner val
	public String CaseOwnerval() throws InterruptedException {
	String CaseOwner=driver.findElement(By.xpath("//div[@records-highlightsdetailsitem_highlightsdetailsitem]/p[@title='Owner Name']/following::lightning-formatted-text")).getText();
	Thread.sleep(200);
	return CaseOwner;
	}
	
	//Capture the val of New case record created
	public String NewCaseCreated() throws InterruptedException {
		String CaseCreated=driver.findElement(By.xpath("//span[@title='Related Cases']/following::a[@data-refid]")).getText();
		Thread.sleep(200);
		return CaseCreated;
	}

	//Select the ForeClosure Approval Record
	public void ClickFCApprovalRcd(String val) throws InterruptedException {
	driver.findElement(By.xpath("//a[@title='"+val+"']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[@title='Open Activities']/following::a[@data-refid][text()='Foreclosure Approval']")).click();
	Thread.sleep(2000);
	}
	
	//Select the First Connect Approval Record
	public void ClickFCRcd(String val) throws InterruptedException {
	driver.findElement(By.xpath("//a[@title='"+val+"']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[@title='Open Activities']/following::a[@data-refid][text()='First Connect']")).click();
	Thread.sleep(2000);
	}
	
	//Select the ForeClosure Approval Record
	public void ClickFollowPTPRcd(String val) throws InterruptedException {
	driver.findElement(By.xpath("//a[@title='"+val+"']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[@title='Open Activities']/following::a[@data-refid][text()='Follow Up Call - PTP']")).click();
	Thread.sleep(2000);
	}
	
	//Capture the Created Case number
	public String CaseNumberCreated() {
	visibleText(By.xpath("//p[@title='Case Number']/following-sibling::p"));
	String CaseNumber=driver.findElement(By.xpath("//p[@title='Case Number']/following-sibling::p")).getText();
	return CaseNumber;
	
	}
	
	//Logout code
	public void Logout() throws InterruptedException {
		
		driver.findElement(By.xpath("//span[@class='uiImage']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		Thread.sleep(1500);
		
	}
	
	//Changing the Owner from user to queue
	public void CaseOwnrUpdate() throws InterruptedException {
	driver.findElement(By.xpath("//span[text()='Case Owner']/following::button[@title='Change Owner']")).click();
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//img[@title='Users']")).click();
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//a[@title='Queues']")).click();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//h2[text()='Change Case Owner']/following::input[@role='combobox']")).sendKeys("NEO L2 Queue");
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//div[@title='Neo L2 Queue']")).click();
	Thread.sleep(800);
	
	driver.findElement(By.xpath("//button[text()='Change Owner']")).click();
	Thread.sleep(3000);
	
	}
	
	//Changing the Owner from user to queue
		public void CaseOwnrUpdate_Prod(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Case Owner']/following::button[@title='Change Owner']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//h2[text()='Change Case Owner']/following::input[@role='combobox']")).sendKeys(val);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@title='"+val+"']")).click();
		Thread.sleep(800);
		driver.findElement(By.xpath("//button[text()='Change Owner']")).click();
		Thread.sleep(3000);
		}
	
	// Update the Status of case to Open
	public void UpdateStatsOpen() throws InterruptedException {
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[text()='Status'][@class='test-id__field-label']/following::button")).click();
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//label[text()='Status']/following::button")).click();
	Thread.sleep(800);
	
	driver.findElement(By.xpath("//label[text()='Status']/following::span[text()='Open']")).click();
	Thread.sleep(800);
	
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	Thread.sleep(3000);
	}
	
	// Update the Status of case to Open
	public void UpdateStatsClosed() throws InterruptedException {
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[text()='Status'][@class='test-id__field-label']/following::button")).click();
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//label[text()='Status']/following::button")).click();
	Thread.sleep(800);
	
	driver.findElement(By.xpath("//label[text()='Status']/following::span[text()='Closed']")).click();
	Thread.sleep(800);
	
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	Thread.sleep(3000);
	}
	
	//Capture Related Case Record
	public String CaptureRelatedCase() {
		String RelatedCaseRecord=driver.findElement(By.xpath("//span[text()='Related Cases']/following::a[@records-hoverablelink_hoverablelink]")).getText();
		return RelatedCaseRecord;
	}
	
	//Delete the Related Case record
	public void DeleteRelatedCaseRecord(String val) throws InterruptedException {
		Actions ac= new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("//span[text()='Related Cases']/following::a[@records-hoverablelink_hoverablelink]"))).build().perform();
		Thread.sleep(1200);
		
		//jsClick(driver.findElement(By.xpath("//span[text()='Related Cases']/following::span[text()='"+val+"']/following::button[@aria-haspopup]/lightning-primitive-icon")));
		
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()='Delete']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		Thread.sleep(1500);
		//jsClick(driver.findElement(By.xpath("//span[text()='Close'][@class='assistiveText']")));
		//Thread.sleep(1000);
	}
	
	//Click on Capture Retention Details 
	public void ClickCaptureRetentnDetail() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Capture Retention Details']")).click();
		Thread.sleep(6000);
	}
	
	//Enter Please select Escalation Category
	public void EnterEscalationCategory(String val) throws Exception {
	retryForDetachedFrame(driver, "//iframe", 0);
	WebElement frame1=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
	Thread.sleep(800);
	driver.switchTo().frame(frame1);
	Thread.sleep(1200);
	Select sel = new Select(driver.findElement(By.xpath("//span[text()='Please select Escalation Category']/following::select[@required]")));
	sel.selectByVisibleText(val);
	Thread.sleep(500);
	}
	
	//Enter Reason For Refund
	public void EnterReasonfrRefund(String val) throws Exception {
	
    driver.findElement(By.xpath("//div[text()='Reason For Refund']/following::span[text()='"+val+"']")).click();
    Thread.sleep(800);
    driver.findElement(By.xpath("//div[text()='Reason For Refund']/following::lightning-primitive-icon")).click();
    Thread.sleep(800);
	}
	
	//Enter Reason For Refund
	public void EnterReasonfrRefund_UAT(String val) throws Exception {
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame1=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
		Thread.sleep(800);
		driver.switchTo().frame(frame1);
		Thread.sleep(1200);
    driver.findElement(By.xpath("//div[text()='Reason For Refund']/following::span[text()='"+val+"']")).click();
    Thread.sleep(800);
    driver.findElement(By.xpath("//div[text()='Reason For Refund']/following::lightning-primitive-icon")).click();
    Thread.sleep(800);
	}
	
	
	//Enter Sub Reason For Refund
	public void EnterSubReasonfrRefund(String val) throws Exception {
    driver.findElement(By.xpath("//div[text()='Sub Reason For Refund']/following::span[text()='"+val+"']")).click();
    Thread.sleep(500);
    driver.findElement(By.xpath("//div[text()='Sub Reason For Refund']/following::lightning-primitive-icon")).click();
    Thread.sleep(500);
	}
	
	//Enter Retention Reason Notes 
	public void EnterRRNotes(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Retention Reason Notes']/following::textarea")).sendKeys(val);
		Thread.sleep(500);	
	}
	
	//Enter Mode Of Retention 
	public void EnterModeORetentn(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Mode Of Retention']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Mode Of Retention']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
		
	}	
	
	//Enter Retention Team Sales Escalation?
	public void EnterRTSE(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Retention Team Sales Escalation?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Retention Team Sales Escalation?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Enter Retention Team Sales Referral?
	public void EnterRTSR(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Retention Team Sales Referral?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Retention Team Sales Referral?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(3500);
	}
	
	//Make Order Selection
	public void Selectorder() throws InterruptedException {
		jsClick(driver.findElement(By.xpath("//span[text()='Please Select The Order']/following::span[contains(@class,'checkbox')]/input")));
		Thread.sleep(500);
		Scrollend();
		driver.findElement(By.xpath("//span[text()='Please Select The Order']/following::button[text()='Next']")).click();
		Thread.sleep(2000);	
	}
	
	//Enter Classes To Be Deactivated
	public void EnterClsTBD(String val) throws InterruptedException {
	    driver.findElement(By.xpath("//div[text()='Classes To Be Deactivated']/following::span[text()='"+val+"']")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//div[text()='Classes To Be Deactivated']/following::lightning-primitive-icon")).click();
	    Thread.sleep(500);
		
	}	
	
	//Enter Tablet To Be Returned
	public void EnterTTBR(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Tablet To Be Returned']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Tablet To Be Returned']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Enter Loan To Be Cancelled
	public void EnterLTBC(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Loan To Be Cancelled']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Loan To Be Cancelled']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Enter DP To Be Refunded-Bank
	public void EnterDPTBRefunBnk(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='DP To Be Refunded-Bank']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='DP To Be Refunded-Bank']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Enter DP To Be Refunded -Wallet
	public void EnterDPTBRefunWall(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='DP To Be Refunded -Wallet']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='DP To Be Refunded -Wallet']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Enter DP - Refund to wallet Amount
	public void EnterDPRefunWallAmt(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='DP - Refund to wallet Amount']/following::input")).sendKeys(val);
		Thread.sleep(500);	
	}
	
	//Enter DP - Byjus Bonus Point
	public void EnterDPBBP(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='DP - Byjus Bonus Point']/following::input")).sendKeys(val);
		Thread.sleep(500);	
	}
	
	//Enter Other Amount To Be Refunded Bank
	public void EnterOATBRefunBnk(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Other Amount To Be Refunded Bank']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Other Amount To Be Refunded Bank']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Enter Other Amount To Be Refunded-Wallet
	public void EnterOATBRefunWall(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Other Amount To Be Refunded-Wallet']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Other Amount To Be Refunded-Wallet']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
	}
	
	//Enter Other Refund to wallet Amount
	public void EnterORefunWallAmt(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Other Refund to wallet Amount']/following::input")).sendKeys(val);
		Thread.sleep(500);	
	}
	
	//Enter Other Byjus Bonus Point
	public void EnterOBBP(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Other Byjus Bonus Point']/following::input")).sendKeys(val);
		Thread.sleep(500);	
	}
	
	//Enter Courier Charges To Be Refunded?
	public void EnterCCTBRefunded(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Courier Charges To Be Refunded?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Courier Charges To Be Refunded?']/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);	
		driver.findElement(By.xpath("//label[text()='Courier Charges To Be Refunded?']/following::button[text()='Next']")).click();
		Thread.sleep(2000);	
	}
	
	//Enter Amount to be refunded
	public void EnterATBRefunded(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Amount to be refunded']/following::input")).sendKeys(val);
		Thread.sleep(500);	
	}
	
	//Enter Amount retained
	public void EnterARetained(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Amount retained']/following::input")).sendKeys(val);
		Thread.sleep(500);	
		driver.findElement(By.xpath("//span[text()='Amount retained']/following::button[text()='Next']")).click();
		Thread.sleep(2000);	
	}
	
	//Select Customer Wants To Go For One Shot Payment?
	public void SelectCWTGFOSP(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Customer Wants To Go For One Shot Payment?']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(500);
	}
	
	//Select Order To Be Re-Punched
	public void SelectOTBReP(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Order To Be Re-Punched']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(500);
	}
	
	//Select Complementary Products To Be Repunched
	public void SelectCPTBReP(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Complementary Products To Be Repunched']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(500);
	}
	
	//Select Books To Be Returned?
	public void SelectBTBR(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Books To Be Returned?']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(500);
	}
	
	//Select Retention Team Product Status
	public void SelectRTPS(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Retention Team Product Status']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(500);
	}
	
	//Enter Retention Team Product Status Notes
	public void EnterRTPSNotes(String val) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Retention Team Product Status Notes']/following::textarea")).sendKeys(val);
		Thread.sleep(500);	
		driver.findElement(By.xpath("//span[text()='Retention Team Product Status Notes']/following::button[text()='Next']")).click();
		Thread.sleep(2000);	
	}
	
	//Select the owner
	public void SelectOwner(String val) throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//span[text()='Select The Student Required For Order Repunch']/following::span[text()='"+val+"']"));
		scrollIntoView(ele);
		jsClick(ele);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Select The Student Required For Order Repunch']/following::button[text()='Next']")).click();
		Thread.sleep(2000);
	}
	
	//Enter Specify Products For Each Siblings
		public void EnterSPFES(String val) throws InterruptedException {
			driver.findElement(By.xpath("//label[text()='Specify Products For Each Siblings']/following::textarea")).sendKeys(val);
			Thread.sleep(500);	
		}
		
	
	//Enter Grades To Be Re Punched
	public void EnterGTBP(String val) throws InterruptedException {
	    driver.findElement(By.xpath("//div[text()='Grades To Be Re Punched']/following::span[text()='"+val+"']")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//div[text()='Grades To Be Re Punched']/following::lightning-primitive-icon")).click();
	    Thread.sleep(500);
		
	}	
	
	//Enter Classes To Be Re - Punched
	public void EnterClsTBReP(String val) throws InterruptedException {
	    driver.findElement(By.xpath("//div[text()='Classes To Be Re - Punched']/following::span[text()='"+val+"']")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//div[text()='Classes To Be Re - Punched']/following::lightning-primitive-icon")).click();
	    Thread.sleep(500);
		
	}	
	
	//Enter Amount
	public void EnterAmount(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Amount']/following::input")).sendKeys(val);
		Thread.sleep(500);	
	}
	
	//Enter Other Payment Details
	public void EnterOtherPD(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Other Payment Details']/following::textarea")).sendKeys(val);
		Thread.sleep(500);	
	}
	
	//Enter Grades To Be Re Punched
	public void EnterCompliGTBP(String val) throws InterruptedException {
	    driver.findElement(By.xpath("//span[contains(text(),'Complimentary')]/following::div[text()='Grades To Be Re Punched']/following::span[text()='"+val+"']")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//span[contains(text(),'Complimentary')]/following::div[text()='Grades To Be Re Punched']/following::lightning-primitive-icon")).click();
	    Thread.sleep(500);
		
	}	
	
	//Enter Classes To Be Re - Punched
	public void EnterCompliClsTBReP(String val) throws InterruptedException {
	    driver.findElement(By.xpath("//span[contains(text(),'Complimentary')]/following::div[text()='Classes To Be Re - Punched']/following::span[text()='"+val+"']")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//span[contains(text(),'Complimentary')]/following::div[text()='Classes To Be Re - Punched']/following::lightning-primitive-icon")).click();
	    Thread.sleep(500);
		driver.findElement(By.xpath("//span[contains(text(),'Complimentary')]/following::button[text()='Next']")).click();
		Thread.sleep(2000);
	}	
	
	//Select Ask For Bank Details
	public void SelectAFBD(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Ask For Bank Details']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(500);
	}
	
	//Select Do You Want To Send The Address So That The Customer Can Send Product
	public void SelectDYWTSTASTTCProd(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Do You Want To Send The Address So That The Customer Can Send Product']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(500);
	}
	
	//Select Do You Want To Ask The Customer To Share The Copy Of Application Form?
	public void SelectCOAForm(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Do You Want To Ask The Customer To Share The Copy Of Application Form?']/following::select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[contains(text(),'Do You Want To Ask The Customer To Share The Copy Of Application Form?')]/following::button[text()='Next']")).click();
		Thread.sleep(2000);
	}
	
	//Check for Created Case Buttons
    public void CheckCreatedCaseButtonsQAandSM(String Env,String SheetName) throws Exception {
    	List<WebElement> List= driver.findElements(By.xpath("//div[text()='Case']/following::ul/li[@class='visible']//button"));
		System.out.println(List.size());
		for(int i=0;i<List.size();i++) {
			
			String OnPagebuttons=List.get(i).getText();
			System.out.println(OnPagebuttons);
			setData(Env, SheetName, i + 1, 5, OnPagebuttons);
			if(i==List.size()-1) {
				setData2(Env, SheetName, i + 2, 5, List.size());
				try {
					if(driver.findElement(By.xpath("//div[text()='Case']/following::button[@class='slds-button slds-button_icon-border-filled']/span[text()='Show more actions']")).isDisplayed()) {
				jsClick(driver.findElement(By.xpath("//div[text()='Case']/following::button[@class='slds-button slds-button_icon-border-filled']/span[text()='Show more actions']")));
				Thread.sleep(1500);

				List<WebElement> Showmore= driver.findElements(By.xpath("//div[text()='Case']/following::span[@runtime_platform_actions-ribbonmenuitem_ribbonmenuitem]"));
				System.out.println(Showmore.size());
				
				for(int j=0;j<Showmore.size();j++) {
					
					String ShowMorebuttons=Showmore.get(j).getText();
					System.out.println(ShowMorebuttons);
					setData(Env, SheetName, j + 1, 7, ShowMorebuttons);
					if(j==Showmore.size()-1) {
					setData2(Env, SheetName, j + 2, 7, Showmore.size());
				}
			}
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("No corner button present");
				}
		}
		}
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

}

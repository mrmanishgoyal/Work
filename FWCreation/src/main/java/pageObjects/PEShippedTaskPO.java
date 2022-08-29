package pageObjects;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.base;

public class PEShippedTaskPO extends base {
	WebDriver driver;

	private String btnNextxpath= "//button[text()='Next']";
	private String lblAmtPaidbyCusxpath= "//label[text()='Amount Paid By Customer']/following::input";
	private String btnListboxMovexpath = "//button[@title='Move selection to Selected']";
	private String txtPayRefxpath = "//label[text()='Payment Reference']/following::input";
	private String ddCollectionChnnelxpath = "//label[text()='Collection Channel']/following::button";
	private String lblErrorMessxpath = "//div[@class='slds-text-color_error']";
	private String ddNoEMICollxpath = "//select[@name='No_Of_EMI_Collected']";
	private String ddTOCollAPxpath = "//select[@name='Type_Of_Collection_alreadyPaid']";
	private String ddEligfrRefxpath = "//select[@name='Eligible_For_Refund']";


	// Declaring Constructor
	public PEShippedTaskPO(WebDriver driver) {
		this.driver = driver;
	}
	

	//Click on Capture detail button
	public void ClickCaptureDetail() throws InterruptedException {
  driver.findElement(By.xpath("//div[text()='Capture Call Details']")).click();
  Thread.sleep(3000);
	}
	
	//Select DNP as option
	public void SelectDNP() throws InterruptedException {
  driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='DNP']")).click();
  Thread.sleep(1200);
  driver.findElement(By.xpath("//button[text()='Next']")).click();
  Thread.sleep(2000);
	}
	
	//Select Proceed as option
	public void SelectProceed() throws InterruptedException {
	 // visibleText(By.xpath("//lightning-formatted-rich-text/span[text()='Proceed']"));
	  driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Proceed']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//button[text()='Next']")).click();
	  Thread.sleep(1000);
	}

	//Select Proceed as option
	public void SelectProceed_iframe() throws Exception {
		retryForDetachedFrame(driver, "//iframe", 0);
		WebElement frame1=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
		Thread.sleep(800);
		driver.switchTo().frame(frame1);
		visibleText(By.xpath("//lightning-formatted-rich-text/span[text()='Proceed']"));
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Proceed']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(1000);
	}
	
	//Select Finish button
	public void ClickFinish() throws InterruptedException {
		visibleText(By.xpath("//button[text()='Finish']"));
		driver.findElement(By.xpath("//button[text()='Finish']")).click();
	}
	
	//Select Finish button
	public void ClickNext() throws InterruptedException {
		visibleText(By.xpath("//button[text()='Next']"));
		driver.findElement(By.xpath("//button[text()='Next']")).click();
	}
	
	//Select value for Proceed - Information About Finance Option
	public void SelectProceedIAFO(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Information_about_Finance_Option']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - DOB Asked
	public void SelectProceedDOBA(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='DOB_Askeds']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Language Preference
	public void SelectProceedLP(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Language_Spoken']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Reason For Purchase?
	public void SelectProceedRFP(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Reason_For_Purchase']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Primary Caretaker Of Studies
	public void SelectProceedPCOS(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Primary_Caretaker_of_Studies']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Customer Expectation
	public void SelectProceedCE(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Customer_Expectation']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Spoke To
	public void SelectProceedST(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Spoke_To_PE']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - School Name
	public void SelectProceedSN(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='School Name']/following::input")).sendKeys(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - School Address
	public void SelectProceedSA(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='School Address']/following::textarea")).sendKeys(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Math Marks(Last Exam)
	public void SelectProceedMMLE(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Math Marks(Last Exam)']/following::input")).sendKeys(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Science Marks(Last Exam)
	public void SelectProceedSMLE(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Science Marks(Last Exam)']/following::input")).sendKeys(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Whatsapp Opt In Status?
	public void SelectProceedWOIS(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Whatsapp_Opt_In_Status']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Notes
	public void SelectProceedNotes(String val) throws InterruptedException {
		driver.findElement(By.xpath("//lightning-formatted-rich-text/span[text()='Notes']/following::textarea")).sendKeys(val);
		Thread.sleep(800);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(3000);
	}
	
	//Select value for Proceed - Did you Inform the customer about the orientation program ?
	public void SelectProceedInfrmCustOP(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Did_you_Inform_the_customer_about_the_orientation_program']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - Was the schedule of the orientation session shared with the customer ?
	public void SelectProceedSchdOS(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Was_the_schedule_of_the_orientation_session_shared_with_the_customer']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - How likely are the parents to attend the orientation class ?
	public void SelectProceedParentOS(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='How_likely_are_the_parents_to_attend_the_orientation_clas']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - On a scale of 1 - 5 how excited are the parents about BYJU’S Learning Centre
	public void SelectProceedRateScale(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='On_a_scale_of_1_5_how_excited_are_the_parents_about_BYJU_S_Learning_Centre']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(2000);
	}
	

	//Select value for Proceed - Order and Payment Information Confirmed ? Raise a flag if the order has been wrongly punched
	public void SelectProceedOrdernPay(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='Order_and_Payment_Information_Confirmed_Raise_a_flag_if_the_order_has_been_wrong']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
	}
	
	//Select value for Proceed - If the parents are not attending the orientation, capturing the reason for non attendance?
	public void SelectProceedNotAttend(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='If_the_parents_are_not_attending_the_orientation_capturing_the_reason_for_non_at']")));
		sel.selectByVisibleText(val);
		Thread.sleep(800);
		 driver.findElement(By.xpath("//button[text()='Next']")).click();
		  Thread.sleep(4000);
		
	}
	
	//Select value for Proceed - Is There Any Issue?
	public void SelectProceedIsThereIssue(String val) throws InterruptedException {
		try {
		Select sel = new Select(driver.findElement(By.xpath("//select[@required]")));
		sel.selectByVisibleText(val);
		Thread.sleep(1000);
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex) {
			Select sel = new Select(driver.findElement(By.xpath("//select[@required]")));
			sel.selectByVisibleText(val);
			Thread.sleep(800);
		}
		 //WebElement ele= driver.findElement(By.xpath("//button[text()='Next']"));
		 ClickOn(By.xpath("//button[text()='Next']"));
		  Thread.sleep(5000);
		
	}
	
	//Select value for Proceed - Is There Any Issue?
		public void SelectProceedVisIsThereIssue() throws InterruptedException {
			visibleText(By.xpath("//label[text()='Is There Any Issue?']"));
			
		}
	//Shipped Call - K4-9 : Spoke To
	public void SelectProceedSpokeTo(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Spoke To']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Spoke To']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Date & Time of Orientation
	public void SelectProceedDatenTimeOrientation() throws InterruptedException {
		Date date = DateUtils.addDays(new Date(), -10);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		driver.findElement(By.xpath("//label[text()='Date']/following::input")).sendKeys(sdf.format(date));
		Thread.sleep(500);	
	}
	
	//Shipped Call - K4-9 : Information about Orientation Given
	public void SelectProceedInfoOrientation(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Information about Orientation Given']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Information about Orientation Given']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Was the availability of customer is checked?
	public void SelectProceedAvailOfCustomer(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Was the availability of customer is checked?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Was the availability of customer is checked?']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Was it informed that PO for parents is mandatory?
	public void SelectProceedParentsIsMandatory(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Was it informed that PO for parents is mandatory?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Was it informed that PO for parents is mandatory?']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : PE explained how to attend PO?
	public void SelectProceedInfoAttendPO(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='PE explained how to attend PO?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='PE explained how to attend PO?']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Did the customer agree to attend the parent orientation?
	public void SelectProceedParentOrientation(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Did the customer agree to attend the parent orientation?']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Did the customer agree to attend the parent orientation?']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Did the PE emphasize on the importance on attending the PO
	public void SelectProceedAttendingPO(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Did the PE emphasize on the importance on attending the PO']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Did the PE emphasize on the importance on attending the PO']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Will the Customer attend the Orientation
	public void SelectProceedInfoWillCusAttend(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Will the Customer attend the Orientation']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Will the Customer attend the Orientation']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Informed about Finance
	public void SelectProceedInformedFinance(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Informed about Finance']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Informed about Finance']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : DOB
	public void SelectProceedDOB(String val) throws InterruptedException {
		//Date date = DateUtils.addDays(new Date(), -1000);
		 //SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy");
		driver.findElement(By.xpath("//label[text()='DOB']/following::input")).sendKeys(val);
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Reason for Purchase
	public void SelectProceedReasonPurchase(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Reason for Purchase']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Reason for Purchase']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Primary Caretaker of Studies
	public void SelectProceedPriCaretakerStudies(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Primary Caretaker of Studies']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Primary Caretaker of Studies']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Customer Expectation
	public void SelectCustmExpectation(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Customer Expectation']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Customer Expectation']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Math Marks
	public void SelectProceedMathMarks(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Math Marks']/following::input")).sendKeys(val);
		Thread.sleep(500);
	}
	
	//Shipped Call - K4-9 : Science Marks
	public void SelectProceedScienceMarks(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Science Marks']/following::input")).sendKeys(val);
		Thread.sleep(500);	
	}
	
	//Shipped Call - K4-9 : WhatsApp Opt-In
	public void SelectProceedWhatsApp(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='WhatsApp Opt-In']/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='WhatsApp Opt-In']/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//Shipped Call - K4-9 : Notes
	public void SelectProceedSCNotes(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Notes']/following::textarea")).sendKeys(val);
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(3000);
	}
	
	//Capture the Assigned To of PE Shipped Call
	public String CapturePEShippedAssigned() {
		String PEShippedOwner= driver.findElement(By.xpath("//span[text()='Assigned To']/following::a")).getText();
		return PEShippedOwner;
	}
	
	//BTC - Welcome Call : Whose number has been captured as Primary Contact ?
	public void SelectBTCNumberPrimaryContact(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'captured as Primary Contact')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'captured as Primary Contact')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	//BTC - Welcome Call : Who is going to be the primary point of contact beyond the student?
	public void SelectBTCPrimaryPointOfContact(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'primary point of contact beyond the student')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'primary point of contact beyond the student')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//BTC - Welcome Call : Is the number updated on SF ?
	public void SelectBTCNumberUpdatedOnSF(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'number updated on SF')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'number updated on SF')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//BTC - Welcome Call : What is the payment method of the customer?
	public void SelectBTCPaymentMethod(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'payment method of the customer')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'payment method of the customer')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//BTC - Welcome Call : Is the EMI Term and Value same as confirmed by customer?
	public void SelectBTCEMITermNValue(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'EMI Term and Value')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'EMI Term and Value')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//BTC - Welcome Call : Is the current grade stated by customer same as order punched ?
	public void SelectBTCCurrentGradeStated(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'current grade stated by customer same as order punched')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'current grade stated by customer same as order punched')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//BTC - Welcome Call : Is the centre punched is same as that stated by customer ?
	public void SelectBTCCentrePunched(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'centre punched is same as that stated')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'centre punched is same as that stated')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//BTC - Welcome Call : Is the board punched same as that stated by customer
	public void SelectBTCBoardPunched(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'board punched same as that stated')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'board punched same as that stated')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//BTC - Welcome Call : Is the tablet received by student same as order punched ?
	public void SelectBTCTabletReceived(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'tablet received by student same as order')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'tablet received by student same as order')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	
	//BTC - Welcome Call : Is the expiry of the program stated by student is the same as order punched ?
	public void SelectBTCExpiryProgramOrderPunched(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'expiry of the program stated by student')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'expiry of the program stated by student')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	//BTC - Welcome Call : Is the name of the school correct as populated?
	public void SelectBTCNameOfSchool(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'name of the school correct as populated')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'name of the school correct as populated')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	//BTC - Welcome Call : Was the in-class orientation information shared with customer ?
	public void SelectBTCInClassOrientation(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Was the in-class orientation information')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'Was the in-class orientation information')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	//BTC - Welcome Call : How likely are the parents to attend the orientation class ?
	public void SelectBTCLikelyParentsAttendOrientation(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'likely are the parents to attend')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'likely are the parents to attend')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	//BTC - Welcome Call : Reason for not attending Orientation or any other comments
	public void SelectBTCReasonNotAttendingOrientation(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Reason for not attending Orientation')]/following::textarea")).sendKeys(val);
		Thread.sleep(500);
	}
	
	//BTC - Welcome Call : Reason for not attending Orientation or any other comments
	public void SelectBTCReasonNotAttendingOrientation_UAT(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'Reason for not attending Orientation')]/following::input")).sendKeys(val);
		Thread.sleep(500);
	}
	
	//BTC - Welcome Call : Is the whatsapp opt in confirmed with the parent?
	public void SelectBTCWhatsappOptIn(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'whatsapp opt in confirmed')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'whatsapp opt in confirmed')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);		
	}
	//BTC - Welcome Call : On a scale of 1 - 5 how excited are the parents about BYJU'S Learning Centre ?
	public void SelectBTCScale(String val) throws InterruptedException {
		driver.findElement(By.xpath("//label[contains(text(),'scale of 1 - 5 how excited')]/following::button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[contains(text(),'scale of 1 - 5 how excited')]/following::button/following::span[text()='"+val+"']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(2000);
	}
	
	
  //Navigate back to Account screen
	public void NavBackAccount() throws InterruptedException {
		try
	    {
			  driver.findElement(By.xpath("//span[text()='Name']/following::a")).click();
			  Thread.sleep(1000);
	    }
	    catch(WebDriverException e)
	    {
	    	  driver.findElement(By.xpath("//span[text()='Name']/following::a")).click();
	    	  Thread.sleep(2500);
	    }
	    catch(Exception ee)
	    {
	        ee.printStackTrace();
	        throw ee;
	    }
	}
	  
	  
	public void ClickOn(By by) throws InterruptedException {

		WebElement element = driver.findElement(by);
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(element));
		element.click(); // Expected condition for the element to be clickable
		Thread.sleep(3000);
	}
	
	//To wait until element is visible
	public WebElement waitForElementToVisible(WebElement element) {
		WebElement find;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);
		find = wait.until(ExpectedConditions.visibilityOf(element));
		return find;
	}
	
	public boolean visibleText(By element)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		
		System.out.println("Element is visible");
		return false;
	}
}

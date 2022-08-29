package testCases;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import org.testng.Assert;

import pageObjects.CapturePaymentDetailsEMIPO;
import pageObjects.CapturePaymtDetailsFPPO;
import pageObjects.CapturePaymtDetailsForeClosrePO;
import pageObjects.CapturePaymtDetailsPTPPO;
import pageObjects.CasesPO;
import pageObjects.CollectionAssistantLoginPO;
import pageObjects.CreatedCaseRecordPO;
import pageObjects.FirstConnectPO;
import pageObjects.FollowUpPTPPO;
import pageObjects.ForeClosurePO;
import pageObjects.NPPaymentLoanPO;
import pageObjects.NewCaseDetailsPO;
import pageObjects.NewCaseRecordTypePO;
import pageObjects.NewPaymentPO;
import pageObjects.NewPaymentRecordPO;
import pageObjects.PaymentCaseQAPO;
import pageObjects.PaymentsPO;
import pageObjects.TasksPO;
import pageObjects.UserDetailPO;
import pageObjects.UserSetupPO;
import pageObjects.loginPO;
import resources.ExcelData;
import resources.base;

public class test_CollectionFlow2 extends base {

	public WebDriver driver;
	public static WebDriver driver1;
	public static WebDriver driver2;
	public static WebDriver driver3;
	//ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_CollectionFlow.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	ArrayList<String> al3 = new ArrayList<String>();
	ArrayList<String> al4 = new ArrayList<String>();
	static Faker faker = new Faker();
	static String firstName = faker.name().firstName();
	static String lastName = faker.name().lastName();
	static int randomNum = ThreadLocalRandom.current().nextInt(10000000, 100000000 + 1);

	
	/*
	 * @BeforeTest public void startDocker() throws IOException,
	 * InterruptedException {
	 * 
	 * File fi=new File("output.txt"); fi.delete(); DockerrunStart s=new
	 * DockerrunStart(); s.Startfile(); }
	 */
	 

	
	
	/*
	 * @BeforeMethod(alwaysRun = true) public void initialize() throws IOException,
	 * InterruptedException {
	 * 
	 * DesiredCapabilities cap=DesiredCapabilities.chrome(); URL u=new
	 * URL("http://localhost:4444/wd/hub"); RemoteWebDriver dr=new
	 * RemoteWebDriver(u,cap);
	 * 
	 * }
	 */
	 
	 

	@Test(priority = 1, groups = { "sanity", "Regression" },enabled = true)
	public void Collection_EMISuccessfull() throws Exception {
		
	    driver = initializeDriver();
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "CollectionFlow", "Tcid");
			al2 = excelData.getData("CollectionAssistant UATFC", "Login", "Type");
			al3 = excelData.getData("CollectionManager UATFC", "Login", "Type");
			al4 = excelData.getData("Adminuatfc", "Login", "Type");
			log.info("Logging in as Admin to UATFC");
			lo.LoginAsAdmin_UATFC();
			
			}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "CollectionFlow", "Tcid");
		al2 = excelData.getData("CollectionAssistant UAT", "Login", "Type");
		al3 = excelData.getData("CollectionManager UAT", "Login", "Type");
		al4 = excelData.getData("Admin", "Login", "Type");
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		//lo.SwitchUser(al2.get(1));
		
		}
		else {
			al = excelData.getData("TC1", "CollectionFlow", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
		}
		//Assert.assertTrue(false);
		closeTabWindows();
		log.info("Creating new Payment record");
		PaymentsPO p = new PaymentsPO(driver);
		p.NavMenuClick();
		p.PaymntNavMenuClick();
		p.NewPaymentClick();

		//Assert.assertTrue(false);

		log.info("Selection of Payment - Loan record");
		NewPaymentPO np = new NewPaymentPO(driver);
		np.SelectPaymentOptn(al.get(1));
		np.ClickNext();

		log.info("Enter New Payment - Loan details");
		NPPaymentLoanPO npp = new NPPaymentLoanPO(driver);
		//npp.EnterPayRefID(randomNum);
		npp.EnterParentFN(firstName);
		npp.EnterParentLN(lastName);
		npp.EnterLoanAmount(al.get(2));
		npp.EnterTenurity(al.get(4));
		npp.EnterProgramName(al.get(3));
		npp.EnterEPPartner(al.get(5));
		npp.EnterAmount(al.get(6));
		npp.EnterTotalAmountTBC(al.get(7));
		npp.EnterNetPayAmount(al.get(8));
		npp.EnterPaymentAmount(al.get(9));
		npp.EnterPaymentCategory(al.get(10));
		npp.EnterPaymentMS(al.get(11));
		npp.EnterPaymentDate(al.get(12));
		npp.EnterPaymentType(al.get(13));
		npp.ClickSave();

		NewPaymentRecordPO npr = new NewPaymentRecordPO(driver);
		String PaymentRecord = npr.CapturePaymentRcdID();
		log.info("New Payment Record " + PaymentRecord + " created successfully");
		npr.ClickCasesQA();

		log.info("Creating New Case Record for " + PaymentRecord);
		PaymentCaseQAPO pcqa = new PaymentCaseQAPO(driver);
		pcqa.ClickCaseNewButton(PaymentRecord);

		NewCaseRecordTypePO ncrt = new NewCaseRecordTypePO(driver);
		ncrt.SelectCaseRecordType(al.get(14));
		ncrt.ClickNext();

		NewCaseDetailsPO ncd = new NewCaseDetailsPO(driver);
		Thread.sleep(1200);
		
		ncd.EnterSubject(al.get(43));
		if(CurrURL.contains("--byjusuatfc")) {
		ncd.EnterOrders(randomNum);
		ncd.SelectReasonForRefund(al.get(44));
		}
		ncd.ClickSave();

		CreatedCaseRecordPO ccr = new CreatedCaseRecordPO(driver);
		String CaseRecord = ccr.CaptureNewCaseRecord();
		log.info("New Case Record " + CaseRecord + " created successfully");
		ccr.CaptureNewCaseRecord();
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ccr.ClickAssingedTo();
		ccr.EnterAssingedTo2(al2.get(1));
		ccr.ClickSave();
		}
		else {
			ccr.CaseOwnrUpdate_Prod(al2.get(1));
		}

		String Colluser = MailFullName(al2.get(1));

		/*
		 * log.info("Logging in as Collection Assistant " + FullName(al2.get(1)));
		 * UserDetailPO ud = new UserDetailPO(driver); ud.ClickUserDetailbutton();
		 * 
		 * UserSetupPO us = new UserSetupPO(driver); us.ClickLoginbutton();
		 */
		CollectionAssistantLoginPO cal = new CollectionAssistantLoginPO(driver);
		cal.ClickBellicon();
		al2 = excelData.getData("Admin", "Login", "Type");
		cal.SelectAssignedTask(taskName(al4.get(1)));

		FirstConnectPO fc = new FirstConnectPO(driver);
		String Firstconnect_caseid = fc.CaptureFCcaseid();
		// Compare the Case Record to First connect case Record
		Assert.assertEquals(CaseRecord, Firstconnect_caseid);
		log.info("Providing Capture payment detail for Case record " + Firstconnect_caseid);
		fc.ClickCapturePayment();

		CapturePaymentDetailsEMIPO cpd = new CapturePaymentDetailsEMIPO(driver);
		cpd.EnterRating(al.get(15));
		cpd.ClickNext();
		cpd.SelectPaymntOptn(al.get(16));
		log.info("Selected the payment option " + al.get(16));
		cpd.ClickNext();
		cpd.EnterAmtpaid(al.get(17));
		cpd.SelectPaymentMethod(al.get(18));
		cpd.EnterDupPaymentRef();
		cpd.SelectCollectionchannel(al.get(19));
		cpd.ClickNext();
		// Assert.assertTrue(cpd.PRerror1.isDisplayed());
		//String PRerror = cpd.CaptureErrorMess();
		// Verify the Error message is Matching.
		//Assert.assertEquals(PRerror,
			//	"Audit Cases are found with the same Payment Reference.Kindly provide the different Payment Reference Id");
		//cpd.EnterPaymntRefVal(randomNum);
		//cpd.ClickNext();
		cpd.SelectEMIcoll(al.get(20));
		cpd.SelectTypofCollPaid(al.get(21));
		cpd.ClickNext();
		cpd.SelectElgfrRef(al.get(22));
		cpd.ClickNext();
		Thread.sleep(4000);
		
		String Firstconnect_status = fc.CheckFCStatus();
		// Verify the Status for first connect is changed to Completed
		Assert.assertEquals(Firstconnect_status, "Completed");
		String Firstconnect_callstatus = fc.CheckFCCallStatus();
		// Verify the Call Status for first connect is changed to Payment Confirmed
		Assert.assertEquals(Firstconnect_callstatus, "Payment Confirmed");

		log.info("Logging out as Collection Assistant");
		//cal.CloseNotification();
		Thread.sleep(1000);
		//cal.Logout();
		lo.Logouthome();

		log.info("Verifying the Payment/Case Status are correct");
		p.NavCaseTab(CaseRecord);

		String CaseStatus = ccr.CaseStatusval();
		String CaseOwner = ccr.CaseOwnerval();
		log.info("The record number for newly created Case record is " + ccr.CaptureNewCaseRecord());
		// Verify the Case Status is changed to Audit Pending
		Assert.assertEquals(CaseStatus, "Audit Pending");
		// Verify the Case owner is same
		Assert.assertTrue(CaseOwner.equalsIgnoreCase(Colluser));
		
		String RelatedCaseRecord= ccr.CaptureRelatedCase();
		log.info("Deleting the Related Case Record " +RelatedCaseRecord);
		
		ccr.DeleteRelatedCaseRecord(RelatedCaseRecord);
		
		log.info("Deleting the created Case Record " +CaseRecord);
		p.NavCasesTab();
		CasesPO cases=new CasesPO(driver);
		cases.RefreshTab();
		cases.DeleteCCaseRecord(CaseRecord);
		
		log.info("Deleting the created Payment Record " + PaymentRecord);
		p.NavMenuClick();
		p.PaymntNavMenuClick();
		p.DeletePayRecord(PaymentRecord);
		//driver.quit();
	}

	@Test(priority = 2, groups = { "sanity", "Regression" },enabled=false)
	public void Collection_ForeClosure() throws Exception {
			//Thread.sleep(30000);
		 	driver1 = initializeDriver();
		  	//driver1.manage().window().maximize();
			//log.info("Driver is initialized");
			//driver1.get(prop.getProperty("urlqa"));
			//CurrURL = driver1.getCurrentUrl();
			//log.info("Navigated to Home page");
			loginPO lo=new loginPO(driver1);
			if(CurrURL.contains("--byjusuatfc")) {
				al = excelData.getData("TC2", "CollectionFlow", "Tcid");
				al2 = excelData.getData("Collection Assistant UAT", "Login", "Type");
				al3 = excelData.getData("Collection Manager UAT", "Login", "Type");
				log.info("Logging in as Admin to UATFC");
				lo.LoginAsAdmin_UATFC();
				
				}
			else if(CurrURL.contains("--byjusuat")) {
			al = excelData.getData("TC2", "CollectionFlow", "Tcid");
			al2 = excelData.getData("Collection Assistant UAT", "Login", "Type");
			al3 = excelData.getData("Collection Manager UAT", "Login", "Type");
			log.info("Logging in as Admin to UAT");
			lo.LoginAsAdmin_UAT();
			
			}
			else if(CurrURL.contains("--byjusqa")) {
				al = excelData.getData("TC2", "CollectionFlow", "Tcid");
				al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
				al3 = excelData.getData("Collection Manager QA", "Login", "Type");
				log.info("Logging in as Admin to QA Env");
				lo.LoginAsAdmin_QA();
			}
			else {
				al = excelData.getData("TC2", "CollectionFlow", "Tcid");
				al2 = excelData.getData("Collection Assistant Prod", "Login", "Type");
				log.info("Logging in as Admin to Prod");
				lo.LoginAsAdmin_Prod();
			}
			
			closeTabWindows();
			log.info("Creating new Payment record");
			PaymentsPO p = new PaymentsPO(driver1);
			p.NavMenuClick();
			p.PaymntNavMenuClick();
			p.NewPaymentClick();

			//Assert.assertTrue(false);

			log.info("Selection of Payment - Loan record");
			NewPaymentPO np = new NewPaymentPO(driver1);
			np.SelectPaymentOptn(al.get(1));
			np.ClickNext();

			log.info("Enter New Payment - Loan details");
			NPPaymentLoanPO npp = new NPPaymentLoanPO(driver1);
			// npp.EnterPayRefID(randomNum);
			npp.EnterParentFN(firstName);
			npp.EnterParentLN(lastName);
			npp.EnterLoanAmount(al.get(2));
			npp.EnterTenurity(al.get(4));
			npp.EnterProgramName(al.get(3));
			npp.EnterEPPartner(al.get(5));
			npp.EnterAmount(al.get(6));
			npp.EnterTotalAmountTBC(al.get(7));
			npp.EnterNetPayAmount(al.get(8));
			npp.EnterPaymentAmount(al.get(9));
			npp.EnterPaymentCategory(al.get(10));
			npp.EnterPaymentMS(al.get(11));
			npp.EnterPaymentDate(al.get(12));
			npp.EnterPaymentType(al.get(13));
			npp.ClickSave();

			NewPaymentRecordPO npr = new NewPaymentRecordPO(driver1);
			String PaymentRecord = npr.CapturePaymentRcdID();
			log.info("New Payment Record " + PaymentRecord + " created successfully");
			npr.ClickCasesQA();

			log.info("Creating New Case Record for " + PaymentRecord);
			PaymentCaseQAPO pcqa = new PaymentCaseQAPO(driver1);
			pcqa.ClickCaseNewButton(PaymentRecord);

			NewCaseRecordTypePO ncrt = new NewCaseRecordTypePO(driver1);
			ncrt.SelectCaseRecordType(al.get(14));
			ncrt.ClickNext();

			NewCaseDetailsPO ncd = new NewCaseDetailsPO(driver1);
			Thread.sleep(1200);
			ncd.ClickSave();

			CreatedCaseRecordPO ccr = new CreatedCaseRecordPO(driver1);
			String CaseRecord = ccr.CaptureNewCaseRecord();
			log.info("New Case Record " + CaseRecord + " created successfully");
			ccr.CaptureNewCaseRecord();
			ccr.ClickAssingedTo();
			ccr.EnterAssingedTo(MailFullName(al2.get(1)));
			ccr.ClickSave();
			ccr.Logout();
			//ccr.ClickAssignedUser(MailFullName(al2.get(1)));
			if(CurrURL.contains("--byjusuatfc")) {
				log.info("Logging in as Collection Associate");
				driver1.get(CurrURL);
				Thread.sleep(2000);
				lo.LoginAsColltnAssociate_UATFC();
				
			}
			else if(CurrURL.contains("--byjusuat")) {
				log.info("Logging in as Collection Associate");
				driver1.get(CurrURL);
				Thread.sleep(2000);
				lo.LoginAsColltnAssociate_UAT();
				
			}
			else if (CurrURL.contains("--byjusqa")){
				driver1.get(CurrURL);
				Thread.sleep(2000);
				lo.LoginAsColltnAssociate_QA();
			}
			else {
				driver1.get(CurrURL);
				Thread.sleep(2000);
				lo.LoginAsColltnAssociate_Prod();
			}
			//String Colluser = MailFullName(al2.get(1));
			lo.InternetConnectionIssue();
			lo.SessionLogin();
			/*
			 * log.info("Logging in as Collection Assistant " + FullName(al2.get(1)));
			 * UserDetailPO ud = new UserDetailPO(driver1); ud.ClickUserDetailbutton();
			 * 
			 * UserSetupPO us = new UserSetupPO(driver1); us.ClickLoginbutton();
			 */
			CollectionAssistantLoginPO cal = new CollectionAssistantLoginPO(driver1);
			cal.ClickBellicon();
			al2 = excelData.getData("Admin", "Login", "Type");
			cal.SelectAssignedTask(taskName(al2.get(1)));

			FirstConnectPO fc = new FirstConnectPO(driver1);
			String Firstconnect_caseid = fc.CaptureFCcaseid();
			// Compare the Case Record to First connect case Record
			Assert.assertEquals(CaseRecord, Firstconnect_caseid);
			log.info("Providing Capture payment detail for Case record " + Firstconnect_caseid);
			fc.ClickCapturePayment();


		CapturePaymtDetailsForeClosrePO cpfcd = new CapturePaymtDetailsForeClosrePO(driver1);
		cpfcd.EnterRating(al.get(15));
		cpfcd.ClickNext();
		cpfcd.SelectPaymntOptn(al.get(16));
		log.info("Selected the payment option " + al.get(16));
		cpfcd.ClickNext();
		cpfcd.EnterAmntColl(al.get(23));
		cpfcd.ClickNext();
		cpfcd.ClickNext();
		Thread.sleep(1000);
		String FCMessage = cpfcd.CaptureFCPayMess();
		// Verify the Foreclosure payment capture is completed
		Assert.assertEquals(FCMessage, "Foreclosure payment capture is completed.");
		cpfcd.ClickFinish();

		log.info("Attempting to Close without collection manager approval");
		CloseNotification();
		fc.RefreshFCScreen();
		// fc.ClickCompleteFC();

		cpfcd.SelectStatusFC(al.get(24));
		cpfcd.SelectCallStatusFC(al.get(25));
		cpfcd.ClickNext();
		String SubBefManApprError = cpfcd.SubmissionBeforeManAppr();
		// Verify the error message
		Assert.assertEquals("You Can't Complete this task without Manager's Approval", SubBefManApprError);
		cpfcd.ClickFinish();

		log.info("Logging out as Collection Assistant");
		//SwitchDefaultContent();
		cal.Logout();
		//ReturntoMain();
		if(CurrURL.contains("--byjusuatfc")) {
			log.info("Logging in as Admin to UATFC");
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(2500);
			lo.LoginAsAdmin_UATFC();
			
			}
		else if(CurrURL.contains("--byjusuat")) {
			log.info("Logging in as Admin to UAT");
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(2500);
			lo.LoginAsAdmin_UAT();
			
			}
			else if(CurrURL.contains("--byjusqa")) {
				log.info("Logging in as Admin to QA Env");
				driver1.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(2500);
				lo.LoginAsAdmin_QA();
			}
			else {
				log.info("Logging in as Admin to Prod");
				driver1.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(2500);
				lo.LoginAsAdmin_Prod();
			}
			
		log.info("Logging in as Collection Manager");
		ccr.ClickFCApprovalRcd(CaseRecord);

		ForeClosurePO fcl = new ForeClosurePO(driver1);
		fcl.ChangeAssignedTo(MailFullName(al3.get(1)));
		fcl.ClickAssignedToPerson();
		
		UserDetailPO ud = new UserDetailPO(driver1);
		ud.ClickUserDetailbutton();
		
		UserSetupPO us = new UserSetupPO(driver1);
		String Profile = us.CaptureProfileVal();
		// Verify the profile displayed is equal to Collection Manager
		Assert.assertEquals(Profile, "Collection Manager");
		//us.ClickMangerLoginbutton();
		us.Logout();
		if(CurrURL.contains("--byjusuatfc")) {
			log.info("Logging in as Collection Manager to UATFC");
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsColltnAssociateManger_UATFC();
			
			}
		else if(CurrURL.contains("--byjusuat")) {
			log.info("Logging in as Collection Manager to UAT");
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsColltnAssociateManger_UAT();
			
			}
			else if(CurrURL.contains("--byjusqa")) {
				log.info("Logging in as Collection Manager to QA Env");
				driver1.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(1500);
				lo.LoginAsColltnAssociateManger_QA();
			}
			else {
				log.info("Logging in as Collection Assistant Manager to Prod");
				driver1.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(1500);
				lo.LoginAsColltnAssociateManger_Prod();
			}
		
		lo.InternetConnectionIssue();
		lo.SessionLogin();
		lo.RefreshURL();
		Thread.sleep(2000);
		
		AllNotificationRead();
		closeTabWindows();

		cal.ClickBellicon();
		al2 = excelData.getData("Admin", "Login", "Type");
		cal.SelectAssignedTask(taskName(al2.get(1)));
		
		log.info("Collection Manager marking task as complete");
		//TasksPO t = new TasksPO(driver1);
		//t.NavBackToTask();
		//t.TaskScreen();
		//t.MyAssignedSelctn();
		//t.SelectCR(CaseRecord);

		//ccr.ClickFCApprovalRcd(CaseRecord);

		fcl.SelectMangerAppr(al.get(26));
		fcl.SelectStatus(al.get(27));
		fcl.ClickSave();

		cal.Logout();
		//		ReturntoMain();
		if(CurrURL.contains("--byjusuatfc")) {
			log.info("Logging in as Collection Associate");
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsColltnAssociate_UATFC();
			
		}
		else if(CurrURL.contains("--byjusuat")) {
			log.info("Logging in as Collection Associate");
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsColltnAssociate_UAT();
			
		}
		else if (CurrURL.contains("--byjusqa")){
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsColltnAssociate_QA();
		}
		else {
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsColltnAssociate_Prod();
		}
		log.info("Logging back as Collection Assistant to mark first connect task as complete");
		lo.InternetConnectionIssue();
		lo.SessionLogin();
		lo.RefreshURL();
		Thread.sleep(2000);
		//ccr.ClickAssignedUser(Colluser);
		//ud.ClickUserDetailbutton();
		//us.ClickLoginbutton();
		closeTabWindows();
		cal.ClickBelliconFC();
		cal.SelectAssignedFCTask(CaseRecord);

		ccr.ClickFCRcd(CaseRecord);

		fc.ClickCompleteFC();

		cpfcd.SelectStatusFC(al.get(24));
		cpfcd.SelectCallStatusFC(al.get(25));
		cpfcd.ClickNext();
		cpfcd.SelectPaymentMethod(al.get(28));
		cpfcd.EnterPaymentdate(al.get(29));
		cpfcd.EnterPaymentRef(randomNum);
		cpfcd.EnterCollBrnch(al.get(30));
		cpfcd.ClickNextSwitchDefault();
		Thread.sleep(3500);

		fc.RefreshPage();

		String Firstconnect_status = fc.CheckFCStatus();
		// Verify the status of First connect is updated to Completed
		Assert.assertEquals(Firstconnect_status, "Completed");
		String Firstconnect_callstatus = fc.CheckFCCallStatus();
		// Verify the Call status of First connect is updated to Payment Confirmed
		Assert.assertEquals(Firstconnect_callstatus, "Payment Confirmed");

		log.info("Logging out as Collection Assistant");
		Thread.sleep(1000);
		cal.Logout();
		if(CurrURL.contains("--byjusuatfc")) {
			log.info("Logging in as Admin to UATFC");
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsAdmin_UATFC();
			
			}
		if(CurrURL.contains("--byjusuat")) {
			log.info("Logging in as Admin to UAT");
			driver1.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsAdmin_UAT();
			
			}
			else if(CurrURL.contains("--byjusqa")) {
				log.info("Logging in as Admin to QA Env");
				driver1.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(1500);
				lo.LoginAsAdmin_QA();
			}
			else {
				log.info("Logging in as Admin to Prod");
				driver1.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(1500);
				lo.LoginAsAdmin_Prod();
			}
			
		//ReturntoMain();
		lo.InternetConnectionIssue();
		lo.SessionLogin();
		log.info("Verifying the Payment/Case Status are correct");
		p.NavCaseTab(CaseRecord);

		String CaseStatus = ccr.CaseStatusval();
		// Verify the case is updated to Audit Pending status
		Assert.assertEquals(CaseStatus, "Audit Pending");
		
		String RelatedCaseRecord= ccr.CaptureRelatedCase();
		log.info("Deleting the Related Case Record " +RelatedCaseRecord);
		
		ccr.DeleteRelatedCaseRecord(RelatedCaseRecord);
		
		log.info("Deleting the created Case Record " +CaseRecord);
		p.NavCasesTab();
		CasesPO cases=new CasesPO(driver1);
		cases.RefreshTab();
		cases.DeleteCCaseRecord(CaseRecord);
		log.info("Deleting the created Payment Record " + PaymentRecord);
		p.NavMenuClick();
		p.PaymntNavMenuClick();
		p.DeletePayRecord(PaymentRecord);
		//driver1.quit();

	}

	@Test(priority = 3, groups = { "sanity", "Regression" },enabled = false)
	public void Collection_PTP() throws Exception {
		//Thread.sleep(200000);
	    driver2 = initializeDriver();
		loginPO lo=new loginPO(driver2);
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC3", "CollectionFlow", "Tcid");
			al2 = excelData.getData("Collection Assistant UAT", "Login", "Type");
			
			log.info("Logging in as Admin to UATFC");
			lo.LoginAsAdmin_UATFC();
			
			}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC3", "CollectionFlow", "Tcid");
		al2 = excelData.getData("Collection Assistant UAT", "Login", "Type");
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC3", "CollectionFlow", "Tcid");
			al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			al3 = excelData.getData("Collection Manager QA", "Login", "Type");
			log.info("Logging in as Admin to UAT");
			lo.LoginAsAdmin_QA();
		}
		else {
			al = excelData.getData("TC3", "CollectionFlow", "Tcid");
			al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
		}
		closeTabWindows();
		log.info("Creating new Payment record");
		PaymentsPO p = new PaymentsPO(driver2);
		p.NavMenuClick();
		p.PaymntNavMenuClick();
		p.NewPaymentClick();

		//Assert.assertTrue(false);

		log.info("Selection of Payment - Loan record");
		NewPaymentPO np = new NewPaymentPO(driver2);
		Thread.sleep(1000);
		np.SelectPaymentOptn(al.get(1));
		np.ClickNext();

		log.info("Enter New Payment - Loan details");
		NPPaymentLoanPO npp = new NPPaymentLoanPO(driver2);
		// npp.EnterPayRefID(randomNum);
		npp.EnterParentFN(firstName);
		npp.EnterParentLN(lastName);
		npp.EnterLoanAmount(al.get(2));
		npp.EnterTenurity(al.get(4));
		npp.EnterProgramName(al.get(3));
		npp.EnterEPPartner(al.get(5));
		npp.EnterAmount(al.get(6));
		npp.EnterTotalAmountTBC(al.get(7));
		npp.EnterNetPayAmount(al.get(8));
		npp.EnterPaymentAmount(al.get(9));
		npp.EnterPaymentCategory(al.get(10));
		npp.EnterPaymentMS(al.get(11));
		npp.EnterPaymentDate(al.get(12));
		npp.EnterPaymentType(al.get(13));
		npp.ClickSave();

		NewPaymentRecordPO npr = new NewPaymentRecordPO(driver2);
		String PaymentRecord = npr.CapturePaymentRcdID();
		log.info("New Payment Record " + PaymentRecord + " created successfully");
		npr.ClickCasesQA();

		log.info("Creating New Case Record for " + PaymentRecord);
		PaymentCaseQAPO pcqa = new PaymentCaseQAPO(driver2);
		pcqa.ClickCaseNewButton(PaymentRecord);

		NewCaseRecordTypePO ncrt = new NewCaseRecordTypePO(driver2);
		ncrt.SelectCaseRecordType(al.get(14));
		ncrt.ClickNext();

		NewCaseDetailsPO ncd = new NewCaseDetailsPO(driver2);
		ncd.ClickSave();

		CreatedCaseRecordPO ccr = new CreatedCaseRecordPO(driver2);
		String CaseRecord = ccr.CaptureNewCaseRecord();
		log.info("New Case Record " + CaseRecord + " created successfully");
		ccr.CaptureNewCaseRecord();
		ccr.ClickAssingedTo();
		ccr.EnterAssingedTo(MailFullName(al2.get(1)));
		ccr.ClickSave();
		ccr.Logout();
		//ccr.ClickAssignedUser(MailFullName(al2.get(1)));
		if(CurrURL.contains("--byjusuatfc")) {
			log.info("Logging in as Collection Associate");
			driver2.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(2500);
			lo.LoginAsColltnAssociate_UATFC();
			
		}
		if(CurrURL.contains("--byjusuat")) {
			log.info("Logging in as Collection Associate");
			driver2.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(2500);
			lo.LoginAsColltnAssociate_UAT();
			
		}
		else if (CurrURL.contains("--byjusqa")){
			driver2.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(2500);
			lo.LoginAsColltnAssociate_QA();
		}
		else {
			driver2.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(2500);
			lo.LoginAsColltnAssociate_Prod();
		}
		String Colluser = MailFullName(al2.get(1));

		/*
		 * log.info("Logging in as Collection Assistant " + FullName(al2.get(1)));
		 * UserDetailPO ud = new UserDetailPO(driver2); ud.ClickUserDetailbutton();
		 * 
		 * UserSetupPO us = new UserSetupPO(driver2); us.ClickLoginbutton();
		 */
		CollectionAssistantLoginPO cal = new CollectionAssistantLoginPO(driver2);
		cal.ClickBellicon();
		Thread.sleep(2000);
		al2 = excelData.getData("Admin", "Login", "Type");
		cal.SelectAssignedTask(taskName(al2.get(1)));

		FirstConnectPO fc = new FirstConnectPO(driver2);
		String Firstconnect_caseid = fc.CaptureFCcaseid();
		// Compare the Case Record to First connect case Record
		Assert.assertEquals(CaseRecord, Firstconnect_caseid);
		log.info("Providing Capture payment detail for Case record " + Firstconnect_caseid);
		fc.ClickCapturePayment();

		CapturePaymtDetailsPTPPO cpptp = new CapturePaymtDetailsPTPPO(driver2);
		cpptp.EnterRating(al.get(15));
		cpptp.ClickNext();
		cpptp.SelectPaymntOptn(al.get(16));
		log.info("Selected the payment option " + al.get(16));
		cpptp.ClickNext();

		cpptp.SelectNoofEMI(al.get(31));
		cpptp.EnterPhoneNo(al.get(32));
		cpptp.EnterDateerrormess();
		cpptp.EnterComments(al.get(33));
		cpptp.SelectPTPGivenBy(al.get(34));
		cpptp.ClickNext();

		String DateErrorMess = cpptp.CapturDateError();
		// Verify the error message
		Assert.assertEquals(DateErrorMess, "Please fill 'Due Date/Time' within today only.");
		cpptp.EnterDateTimePTP();
		cpptp.ClickNext();
		Thread.sleep(500);
		cpptp.ClickNextSwitchDefault();
		

		String Firstconnect_status = fc.CheckFCStatus();
		// Verify the First connect status is updated to Completed
		Assert.assertEquals(Firstconnect_status, "Completed");
		String Firstconnect_callstatus = fc.CheckFCCallStatus();
		// Verify the First connect call status is updated to PTP
		Assert.assertEquals(Firstconnect_callstatus, "PTP");

		log.info("Verifying the Follow up Task is created");
		TasksPO t = new TasksPO(driver2);
		t.NavBackToTask();
		//t.TaskScreen();
		//t.MyAssignedSelctn();
		t.SelectCR(CaseRecord);

		ccr.ClickFollowPTPRcd(CaseRecord);

		FollowUpPTPPO fuptp = new FollowUpPTPPO(driver2);
		String AssignedToOwner = fuptp.CaptureAssignedTo();
		// Verify the Follow up PTP owner is same
		Assert.assertTrue(AssignedToOwner.equalsIgnoreCase(Colluser));

		log.info("Logging out as Collection Assistant");
		cal.Logout();
		//ReturntoMain();
		if(CurrURL.contains("--byjusuatfc")) {
			log.info("Logging in as Admin to UATFC");
			driver2.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsAdmin_UATFC();
			
			}
		else if(CurrURL.contains("--byjusuat")) {
			log.info("Logging in as Admin to UAT");
			driver2.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsAdmin_UAT();
			
			}
			else if(CurrURL.contains("--byjusqa")) {
				log.info("Logging in as Admin to QA Env");
				driver2.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(1500);
				lo.LoginAsAdmin_QA();
			}
			else {
				log.info("Logging in as Admin to Prod");
				driver2.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(1500);
				lo.LoginAsAdmin_Prod();
			}
		
		//String RelatedCaseRecord= ccr.CaptureRelatedCase();
		//log.info("Deleting the Related Case Record " +RelatedCaseRecord);
		
		//ccr.DeleteRelatedCaseRecord(RelatedCaseRecord);
		
		log.info("Deleting the created Case Record " +CaseRecord);
		p.NavCasesTab();
		CasesPO cases=new CasesPO(driver2);
		cases.RefreshTab();
		cases.DeleteCCaseRecord(CaseRecord);
		
		log.info("Deleting the created Payment Record " + PaymentRecord);
		p.NavMenuClick();
		p.PaymntNavMenuClick();
		p.DeletePayRecord(PaymentRecord);
		//driver2.quit();
	}

	@Test(priority = 4, groups = { "sanity", "Regression" },enabled = false)
	public void Collection_FieldPickup() throws Exception {
		//Thread.sleep(200000);
	    driver3 = initializeDriver();
		loginPO lo=new loginPO(driver3);
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC4", "CollectionFlow", "Tcid");
			al2 = excelData.getData("Collection Assistant UAT", "Login", "Type");
			
			log.info("Logging in as Admin to UATFC");
			lo.LoginAsAdmin_UATFC();
			
			}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC4", "CollectionFlow", "Tcid");
		al2 = excelData.getData("Collection Assistant UAT", "Login", "Type");
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC4", "CollectionFlow", "Tcid");
			al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			al3 = excelData.getData("Collection Manager QA", "Login", "Type");
			log.info("Logging in as Admin to QA");
			lo.LoginAsAdmin_QA();
		}
		else {
			al = excelData.getData("TC4", "CollectionFlow", "Tcid");
			al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
		}
		closeTabWindows();
		log.info("Creating new Payment record");
		PaymentsPO p = new PaymentsPO(driver3);
		p.NavMenuClick();
		p.PaymntNavMenuClick();
		p.NewPaymentClick();

		//Assert.assertTrue(false);

		log.info("Selection of Payment - Loan record");
		NewPaymentPO np = new NewPaymentPO(driver3);
		np.SelectPaymentOptn(al.get(1));
		np.ClickNext();

		log.info("Enter New Payment - Loan details");
		NPPaymentLoanPO npp = new NPPaymentLoanPO(driver3);
		// npp.EnterPayRefID(randomNum);
		npp.EnterParentFN(firstName);
		npp.EnterParentLN(lastName);
		npp.EnterLoanAmount(al.get(2));
		npp.EnterTenurity(al.get(4));
		npp.EnterProgramName(al.get(3));
		npp.EnterEPPartner(al.get(5));
		npp.EnterAmount(al.get(6));
		npp.EnterTotalAmountTBC(al.get(7));
		npp.EnterNetPayAmount(al.get(8));
		npp.EnterPaymentAmount(al.get(9));
		npp.EnterPaymentCategory(al.get(10));
		npp.EnterPaymentMS(al.get(11));
		npp.EnterPaymentDate(al.get(12));
		npp.EnterPaymentType(al.get(13));
		npp.ClickSave();

		NewPaymentRecordPO npr = new NewPaymentRecordPO(driver3);
		String PaymentRecord = npr.CapturePaymentRcdID();
		log.info("New Payment Record " + PaymentRecord + " created successfully");
		npr.ClickCasesQA();

		log.info("Creating New Case Record for " + PaymentRecord);
		PaymentCaseQAPO pcqa = new PaymentCaseQAPO(driver3);
		pcqa.ClickCaseNewButton(PaymentRecord);

		NewCaseRecordTypePO ncrt = new NewCaseRecordTypePO(driver3);
		ncrt.SelectCaseRecordType(al.get(14));
		ncrt.ClickNext();

		NewCaseDetailsPO ncd = new NewCaseDetailsPO(driver3);
		ncd.ClickSave();

		CreatedCaseRecordPO ccr = new CreatedCaseRecordPO(driver3);
		String CaseRecord = ccr.CaptureNewCaseRecord();
		log.info("New Case Record " + CaseRecord + " created successfully");
		ccr.CaptureNewCaseRecord();
		ccr.ClickAssingedTo();
		ccr.EnterAssingedTo(MailFullName(al2.get(1)));
		ccr.ClickSave();
		ccr.Logout();
		//ccr.ClickAssignedUser(MailFullName(al2.get(1)));
		if(CurrURL.contains("--byjusuatfc")) {
			log.info("Logging in as Collection Associate");
			driver3.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1000);
			lo.LoginAsColltnAssociate_UATFC();
			
		}
		else if(CurrURL.contains("--byjusuat")) {
			log.info("Logging in as Collection Associate");
			driver3.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1000);
			lo.LoginAsColltnAssociate_UAT();
			
		}
		else if (CurrURL.contains("--byjusqa")){
			driver3.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1000);
			lo.LoginAsColltnAssociate_QA();
		}
		else {
			driver3.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1000);
			lo.LoginAsColltnAssociate_Prod();
		}
		//String Colluser = MailFullName(al2.get(1));

		/*
		 * log.info("Logging in as Collection Assistant " + FullName(al2.get(1)));
		 * UserDetailPO ud = new UserDetailPO(driver3); ud.ClickUserDetailbutton();
		 * 
		 * UserSetupPO us = new UserSetupPO(driver3); us.ClickLoginbutton();
		 */
		CollectionAssistantLoginPO cal = new CollectionAssistantLoginPO(driver3);
		cal.ClickBellicon();
		al2 = excelData.getData("Admin", "Login", "Type");
		cal.SelectAssignedTask(taskName(al2.get(1)));

		FirstConnectPO fc = new FirstConnectPO(driver3);
		String Firstconnect_caseid = fc.CaptureFCcaseid();
		// Compare the Case Record to First connect case Record
		Assert.assertEquals(CaseRecord, Firstconnect_caseid);
		log.info("Providing Capture payment detail for Case record " + Firstconnect_caseid);
		fc.ClickCapturePayment();

		CapturePaymtDetailsFPPO cpfp = new CapturePaymtDetailsFPPO(driver3);
		cpfp.EnterRating(al.get(15));
		cpfp.ClickNext();
		cpfp.SelectPaymntOptn(al.get(16));
		log.info("Selected the payment option " + al.get(16));
		cpfp.ClickNext();
		cpfp.SelectNoofEMI(al.get(35));
		cpfp.EnterDateTimePPD();
		cpfp.ClickNext();
		cpfp.EnterStreetDetails(al.get(36));
		cpfp.EnterCityDetails(al.get(37));
		cpfp.EnterStateDetails(al.get(38));
		cpfp.EnterPinDetails(al.get(39));
		cpfp.EnterCountryDetails(al.get(40));
		cpfp.EnterAPhoneDetails(al.get(41));
		cpfp.EnterFPCommentDetails(al.get(42));
		cpfp.ClickNextSwitchDefault();

		fc.RefreshTab();

		String Firstconnect_status = fc.CheckFCStatus();
		// Verify the status First connect is updated to Completed
		Assert.assertEquals(Firstconnect_status, "Completed");
		String Firstconnect_callstatus = fc.CheckFCCallStatus();
		// Verify the Call status First connect is updated to SLA Violation
		Assert.assertEquals(Firstconnect_callstatus, "SLA Violation");

		log.info("Logging out as Collection Assistant");
		cal.Logout();
		//ReturntoMain();
		if(CurrURL.contains("--byjusuatfc")) {
			log.info("Logging in as Admin to UATFC");
			driver3.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsAdmin_UATFC();
			
			}
		else if(CurrURL.contains("--byjusuat")) {
			log.info("Logging in as Admin to UAT");
			driver3.get(CurrURL);
			lo.RefreshURL();
			Thread.sleep(1500);
			lo.LoginAsAdmin_UAT();
			
			}
			else if(CurrURL.contains("--byjusqa")) {
				log.info("Logging in as Admin to QA Env");
				driver3.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(1500);
				lo.LoginAsAdmin_QA();
			}
			else {
				log.info("Logging in as Admin to Prod");
				driver3.get(CurrURL);
				lo.RefreshURL();
				Thread.sleep(1500);
				lo.LoginAsAdmin_Prod();
			}
		
		lo.InternetConnectionIssue();
		lo.SessionLogin();
		lo.RefreshURL();
		Thread.sleep(2000);
		//String RelatedCaseRecord= ccr.CaptureRelatedCase();
		//log.info("Deleting the Related Case Record " +RelatedCaseRecord);
		
		//ccr.DeleteRelatedCaseRecord(RelatedCaseRecord);
		
		log.info("Deleting the created Case Record " +CaseRecord);
		p.NavCasesTab();
		CasesPO cases=new CasesPO(driver3);
		cases.RefreshTab();
		cases.DeleteCCaseRecord(CaseRecord);
		
		log.info("Deleting the created Payment Record " + PaymentRecord);
		p.NavMenuClick();
		p.PaymntNavMenuClick();
		p.DeletePayRecord(PaymentRecord);
		//driver3.quit();
	}

	
	  @AfterTest(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  //driver.quit();
	  
	  }
	 
	 

	
	/*
	 * @AfterTest public void stopDocker() throws IOException, InterruptedException
	 * { File fi=new File("output1.txt"); fi.delete(); DockerrunStop s1=new
	 * DockerrunStop(); s1.Stopfile(); }
	 */

}

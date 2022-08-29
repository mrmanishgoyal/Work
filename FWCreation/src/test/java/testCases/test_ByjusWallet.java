package testCases;

import java.io.IOException;

import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;


import com.github.javafaker.Faker;

import org.testng.Assert;


import pageObjects.CasesPO;

import pageObjects.CreatedAccountPO;
import pageObjects.CreatedCaseRecordPO;
import pageObjects.InboundTaskPO;

import pageObjects.NewCaseDetailsPO;
import pageObjects.NewCaseRecordTypePO;
import pageObjects.Retention1stCallPO;
import pageObjects.loginPO;
import payLoad.payLoad_BTLA;
import payLoad.payLoad_ByjusWallet;
import resources.ExcelData;
import resources.base;



public class test_ByjusWallet extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_ByjusWallet.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	static Faker faker = new Faker();
	static String firstName = faker.name().firstName();
	static String lastName = faker.name().lastName();
	static int randomNum = ThreadLocalRandom.current().nextInt(10000000, 100000000 + 1);
	
	@BeforeMethod(alwaysRun = true)
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

	}
	

	@Test(groups = {"sanity", "Regression" },enabled = true)
	public void TestByjusWallet() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "ByjuWallet", "Tcid");
			
			
			log.info("Logging in as Admin to UATFC");
			lo.LoginAsAdmin_UATFC();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_ByjusWallet.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
			
			}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "ByjuWallet", "Tcid");
		
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_ByjusWallet.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "ByjuWallet", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_ByjusWallet.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "ByjuWallet", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_ByjusWallet.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		CreatedAccountPO ca1= new CreatedAccountPO(driver);
		ca1.Notification();
		ca1.NavBackToAccount();
		String AccountURL=CurrURL+Accountid;
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		
		String AccountOwner= ca1.CaptureAccOwnrNam();
		//Verify Account is created with Student order,Student Payment in it
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			
			Assert.assertTrue(ca1.CheckVisSalesStudentOrderid());
			ca1.CaptureStudentSalesOrder();	
		}
		else {
		//Assert.assertTrue(ca1.CheckVisStudentOrderid());
		//ca1.CaptureStudentOrder();
		Assert.assertTrue(ca1.CheckVisSalesStudentOrderid());
		ca1.CaptureStudentSalesOrder();	
		//ca1.CaptureStudentOrderIndvid();
		}
		String OwnerName=ca1.CaptureAccOwnrNam();
		Assert.assertTrue(ca1.CheckVisStudentPayid());
		ca1.CaptureAllStudentPayid();
		ca1.CheckVisStudentPrgid();
		ca1.CaptureAllStudentProgDetails();		
		
		//ca1.Scrollpagedown();
		log.info("Creating Inbound Task");
		ca1.ClickOpenActivitiestoNewTask();
		
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver);
		
		ncrt.SelectCaseRecordTypeInbound();
		ncrt.ClickNext();
		Thread.sleep(2000);
		ncrt.ClickSave();
		Thread.sleep(2000);
		
		InboundTaskPO ibdt= new InboundTaskPO(driver);
		ibdt.ClickCaptureDetail();
		Thread.sleep(3000);		
		  
		ibdt.ClickProceedOptn();
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ibdt.SelectSpokeTo(al.get(43));
		}
		ibdt.SelectPSTCT(al.get(1));
		ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		//ncrt.ClickSave();
		//Thread.sleep(4000);
		
		ibdt.SelectDevice(al.get(3));
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ibdt.SelectCategory(al.get(4));
		}
		else {
			ibdt.SelectCategory(al.get(4));
			//ibdt.SelectIssueCategory(al.get(4));
		}
		if(!CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//ibdt.SelectPSCOYR(al.get(5));
		ibdt.SelectPSTRR(al.get(5));
		//ibdt.SelectPSTRR2(al.get(6));
		ibdt.SelectPSSTRR(al.get(6));
		ibdt.EnterComments(al.get(7));
		ibdt.SelectIsTICR(al.get(8));
		}
		else {
		ibdt.SelectPSTRR(al.get(5));
		//ibdt.SelectPSCOYR(al.get(5));
		ibdt.SelectPSSTRR(al.get(6));
		//ibdt.SelectPSTRR2(al.get(6));
		ibdt.EnterComments(al.get(7));
		ibdt.SelectIsTICR(al.get(8));
		}
		ibdt.SelectPSTO();
		Thread.sleep(1000);
		ibdt.ClickNext();
		Thread.sleep(3000);
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ibdt.NavBackAccount();
		}
		else if(CurrURL.contains("--byjusuatfc")){
			//ca1.CloseCurrentSubTab();
			ibdt.NavBackAccount();
		}
		else {
			ibdt.NavBackAccount();
		}
		ca1.CloseSubTabs();
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			ca1.ClickCasesMC2();
		}
		else {
		ca1.ClickCasesMC();
		}
		CasesPO cases= new CasesPO(driver);
		
		String CaseNumber= cases.CaseRN();
		log.info("The case number created is: "+CaseNumber);
		cases.CaseOptnSel();
		
		
		String Mainwin= driver.getWindowHandle();
		
		
		//if(CurrURL.contains("--byjusuat") || CurrURL.contains("--byjusuatfc")) {
		//	al2 = excelData.getData("Retention User UAT", "Login", "Type");
		//	lo.SwitchUser(al2.get(1));
		//	ca1.closeTabWindows();
		//	driver.close();
		//	Thread.sleep(1000);
		//	driver.switchTo().window(Mainwin);
		//	Thread.sleep(1000);
		//}
		//else {
		if(CurrURL.contains("--byjusuatfc")) {
				al2 = excelData.getData("Retention User UAT", "Login", "Type");
				lo.SwitchUser(al2.get(1));
				ca1.closeTabWindows();
				driver.close();
				Thread.sleep(1000);
				driver.switchTo().window(Mainwin);
				Thread.sleep(1000);
		}
		else if(CurrURL.contains("byjusprod.")) {
			lo.SwitchUsernProfile_Prod("Retention Manager");
			ca1.closeTabWindows();
			driver.close();
			Thread.sleep(1000);
			driver.switchTo().window(Mainwin);
			Thread.sleep(1000);
		}
		//}
		
		//NewCaseDetailsPO ncd= new NewCaseDetailsPO(driver);
		//Thread.sleep(1500);
		//if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//ncd.ClickOpenActiDD();
		
		//}
		//else {
		//	ncd.ClickOpenActiDD_Prod();
		//}
		//ncrt.SelectCaseRecdTypeRetenInitialCall();
		//ncrt.ClickNext();
		//Thread.sleep(2000);
		//ncrt.ClickRetentionSave();
		//Thread.sleep(2000);
		
		//ca1.RefreshTab();
		CreatedCaseRecordPO ccr= new CreatedCaseRecordPO(driver);
		lo.RefreshURL();
		Thread.sleep(2000);
		if(CurrURL.contains("byjusprod.")) {
			ccr.CaseOwnrUpdate_Prod("Testing User");
		}
		cases.ClickRetentionFirstCall();
		log.info("Marking the Initial call as Complete");
		Retention1stCallPO rfc=new Retention1stCallPO(driver);
		rfc.EnterCompletedcall();
		rfc.ClickSave();
		
		ibdt.NavToCase();
		//ca1.CloseSubTabs();
		//ca1.ClickCasesMC2();
		//cases.CaseOptnSel();
		
		log.info("Capturing Retention Details");
		
		ccr.ClickCaptureRetentnDetail();
		if(CurrURL.contains("byjusprod.") ||CurrURL.contains("--byjusuatfc")) {
		ccr.EnterEscalationCategory(al.get(44));
		ccr.EnterReasonfrRefund(al.get(9));
		}
		else {
		ccr.EnterReasonfrRefund_UAT(al.get(9));
		}
		ccr.EnterSubReasonfrRefund(al.get(10));
		ccr.EnterRRNotes(al.get(11));
		ccr.EnterModeORetentn(al.get(12));
		ccr.EnterRTSE(al.get(13));
		ccr.EnterRTSR(al.get(14));
		ccr.Selectorder();
		ccr.EnterClsTBD(al.get(15));
		ccr.EnterTTBR(al.get(16));
		ccr.EnterLTBC(al.get(17));
		ccr.EnterDPTBRefunBnk(al.get(18));
		ccr.EnterDPTBRefunWall(al.get(19));
		ccr.EnterDPRefunWallAmt(al.get(20));
		ccr.EnterDPBBP(al.get(21));
		ccr.EnterOATBRefunBnk(al.get(22));
		ccr.EnterOATBRefunWall(al.get(23));
		ccr.EnterORefunWallAmt(al.get(24));
		ccr.EnterOBBP(al.get(25));
		ccr.EnterCCTBRefunded(al.get(26));
		ccr.EnterATBRefunded(al.get(27));
		ccr.EnterARetained(al.get(28));
		ccr.SelectCWTGFOSP(al.get(29));
		ccr.SelectOTBReP(al.get(30));
		ccr.SelectCPTBReP(al.get(31));
		ccr.SelectBTBR(al.get(32));
		ccr.SelectRTPS(al.get(33));
		ccr.EnterRTPSNotes(al.get(34));
		ccr.SelectOwner(OwnerName);
		ccr.EnterSPFES(al.get(35));
		ccr.EnterGTBP(al.get(36));
		ccr.EnterClsTBReP(al.get(37));
		ccr.EnterAmount(al.get(38));
		ccr.EnterOtherPD(al.get(39));
		ccr.EnterCompliGTBP(al.get(36));
		ccr.EnterCompliClsTBReP(al.get(37));
		ccr.SelectAFBD(al.get(40));
		ccr.SelectDYWTSTASTTCProd(al.get(41));
		ccr.SelectCOAForm(al.get(42));
		
		if(CurrURL.contains("byjusprod.") ||CurrURL.contains("--byjusuatfc")) {
		lo.Logouthome();
		}
		if(CurrURL.contains("--byjusuat") &&!CurrURL.contains("--byjusuatfc")) {
		driver.get(AccountURL);
		Thread.sleep(5000);
		}
		else {
		ca1.ClickAccOwnrTab2(AccountOwner);
		}
		ca1.CloseSubTabs();
		ca1.ClickCasesMC2();
		//cases.NavCasesTab();
		//cases.RefreshCurrentTab();
		
		cases.CheckDPRefdWall();
		
		String CaseNumberfrDP= cases.CaptureCaseNoDP();
		log.info("The Case number created for DP to be Refunded to Wallet is: "+CaseNumberfrDP);
		
		String Owner= cases.CaptureCaseOwnerDP();
		Assert.assertTrue(Owner.contains("Process"));
		
		String ParentCase= cases.CaptureParentCaseOwnerDP();
		Assert.assertTrue(ParentCase.equalsIgnoreCase(CaseNumber));// Added as a part of SFTNL-6558
		
		cases.NavCasesTab();
		
		cases.CheckOARefdWall();
		
		String CaseNumberfrOA= cases.CaptureCaseNoOA();
		log.info("The Case number created for Other Amount To Be Refunded To Wallet is: "+CaseNumberfrOA);
		
		String Owner1= cases.CaptureCaseOwnerOA();
		Assert.assertTrue(Owner1.contains("Process"));
		
		cases.NavCasesTab();
		cases.RefreshCurrentTab();
		Thread.sleep(2000);
		cases.CloseAllCases();
		

		
		log.info("Deleting the Student Program details");
		ca1.ClickAccOwnrTab();
		ca1.DeleteCreatedStuProg();
		ca1.NavBackToAccount();
		log.info("Deleting the Account created details");
		ca1.DeleteAccountCreated(ca1.CaptureAccOwnrNam());

		if(CurrURL.contains("--byjusuatfc")||CurrURL.contains("byjusprod.")) {
		log.info("Deleting the Cases present as My Cases");
		Thread.sleep(1000);
		cases.NavMenuClick();
		cases.CaseNavMenuClick();
		cases.NavNEOL2QueueProd();
		cases.FilterbyMyCases();
		String FilterText = cases.CaptureFilterText();
		Assert.assertTrue(FilterText.contains("Filtered by My cases"));
		cases.DeleteAllMyCaseRecord();
		cases.SetBackDefault();
		}
	}
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
	
	
}

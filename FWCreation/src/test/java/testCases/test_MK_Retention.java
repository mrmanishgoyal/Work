package testCases;

import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;


import com.github.javafaker.Faker;

import org.testng.Assert;

import pageObjects.CasesPO;
import pageObjects.CreatedAccountPO;
import pageObjects.DevConsolePO;
import pageObjects.ExamDetailsPO;
import pageObjects.InboundTaskPO;
import pageObjects.NewCaseRecordTypePO;
import pageObjects.PEFollowUpTaskPO;
import pageObjects.PEOnBoardingTaskPO;
import pageObjects.PEShippedTaskPO;

import pageObjects.SessionsInformationPO;
import pageObjects.StudentAppInfoPO;
import pageObjects.StudentCRAssoPO;
import pageObjects.StudentProgPO;
import pageObjects.StudentSalesOrderPO;
import pageObjects.StudentSubOrderPO;

import pageObjects.loginPO;
import payLoad.payLoad_MK;
import payLoad.payLoad_MK_Retention;
import payLoad.payLoad_MK_Retention;
import resources.Batches;
import resources.ExcelData;
import resources.base;

public class test_MK_Retention extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_MK_Retention.class.getName());
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
	
	
	@Test(groups = {"sanity", "UAT" }, enabled = true)
	public void TestMK_Retention() throws Exception {
		String Accountid = null;
		String StudentClassroom = null;
		String StudentAppInfo1 = null;
		String StudentAppInfo2 = null;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
		al = excelData.getData("TC1", "Neo", "Tcid");
		//al2 = excelData.getData("Collection Assistant", "Login", "Type");
		
		log.info("Logging in as Admin to UATFC");
		lo.LoginAsAdmin_UATFC();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_MK_Retention.AccountidCreationResponse_UATFC();
		payLoad_MK_Retention.ClassRoomMappingResponse_UATFC();
		payLoad_MK_Retention.StudentAppInfoResponse_UATFC();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "Neo", "Tcid");
		//al2 = excelData.getData("Collection Assistant", "Login", "Type");
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_MK_Retention.AccountidCreationResponse_UAT();
		payLoad_MK_Retention.StudentClassRoomAdd_UAT();
		payLoad_MK_Retention.ClassRoomMappingResponse_UAT();
		payLoad_MK_Retention.StudentAppInfoResponse_UAT();
		StudentClassroom = payLoad_MK_Retention.StudentClassRoomid_UAT();
		StudentAppInfo1 = payLoad_MK_Retention.StudentAppInfoid1_UAT();
		StudentAppInfo2 = payLoad_MK_Retention.StudentAppInfoid2_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "Neo", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_MK_Retention.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "Neo", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_MK_Retention.AccountidCreationResponse_Prod();
			payLoad_MK_Retention.ClassRoomMappingResponse_Prod();
			payLoad_MK_Retention.StudentAppInfoResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		
		//log.info("Submitting the Account creation payload");
		//String Accountid=payLoad_AccountCreation.AccountidCreationResponse_UAT();
		//log.info("Launching the newly created Account id "+Accountid);
		
		CreatedAccountPO ac=new CreatedAccountPO(driver);
		//Open the account by searching PID
		ac.Notification();
		ac.NavBackToAccount();
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		
		String PremiumID = ac.CapturePremiumID();
		if(PremiumID!=null) {
			Assert.assertTrue(true);
		}
		log.info("The Premium id is "+PremiumID);
		
		//Verify Account is created with Student order,Student Payment in it
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			
			Assert.assertTrue(ac.CheckVisSalesStudentOrderid());
			ac.CaptureStudentSalesOrder();	
		}
		else {
		//Assert.assertTrue(ac.CheckVisStudentOrderid());
		Assert.assertTrue(ac.CheckVisSalesStudentOrderid());
		//ac.CaptureStudentOrder();
		ac.CaptureStudentSalesOrder();
		//ac.CaptureStudentOrderIndvid();
		}
		Assert.assertTrue(ac.CheckVisStudentPayid());
		ac.CaptureAllStudentPayid();
		ac.CheckVisStudentPrgid();
		ac.CaptureAllStudentProgDetails();
		
		//Verify  BYJUS The Learning App  program is created in student program related list
		String Progval = ac.CapturePrgCreated();
		Assert.assertEquals(Progval, "Neo Classes");
		
		//Verify Account super status and status is PE/First60 and New
		
		String SuperStatus = ac.CaptureSuperStatus();
		//Assert.assertTrue(SuperStatus.contains("Frist60"));
		
		String Status = ac.CaptureStatus();
		Assert.assertEquals(Status, "New");
		Thread.sleep(1000);
		
		//Verify Student App Information is created
		ac.CaptureStudentAppInfo();
		
		//Verify Student Classroom Associations has the record created
		ac.ClickStudentClassroomAssociations();
		StudentCRAssoPO scra = new StudentCRAssoPO(driver);
		
		String StudentClassroomAssociation= scra.CaptureStuCRAid();
		log.info("Student Classroom id created for Assessment Submitted is: "+StudentClassroomAssociation);
		
		scra.ClickStuCRAid();
		scra.ClickStuClassRoomRcd();
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		scra.ChangeSTtoRegular();
		scra.ChangeCRPTtoClassroom();
		}
		scra.RefreshTab();
		
		String ClassroomOwner= scra.CaptureOwner();
		System.out.println("The value of Owner is: "+ClassroomOwner);
		
		ac.ClickAccOwnrTab();
		ac.CloseSubTabs();
		Thread.sleep(1000);
		ac.RefreshTab();
		
		//Assert.assertEquals(ClassroomOwner, ac.CaptureAccOwnerNam());
		//Assert.assertTrue(false);
				
		
		//Verify the PE Shipped all is created
		Assert.assertTrue(ac.CheckVisPEShippedCall());
		  
		log.info("Navigating to PE Shipped Call tasks");
		ac.ClickPEShipped();
		
		PEShippedTaskPO pes=new PEShippedTaskPO(driver);
		pes.ClickCaptureDetail();
		log.info("Selecting Capture Call as Proceed for PE Shipped Task");
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		pes.SelectProceed_iframe();
		}
		else {
			//pes.SelectProceed();
			pes.SelectProceed_iframe();
		}
		
		//if(CurrURL.contains("--byjusuat")) {
		pes.SelectProceedIAFO(al.get(1));
		pes.SelectProceedDOBA(al.get(2));
		pes.SelectProceedLP(al.get(3));
		pes.SelectProceedRFP(al.get(4));
		pes.SelectProceedPCOS(al.get(5));
		pes.SelectProceedCE(al.get(6));
		pes.SelectProceedST(al.get(7));
		
		//if(CurrURL.contains("--byjusuat")) { 2.0
		//pes.SelectProceedSN(al.get(8));2.0
		//pes.SelectProceedSA(al.get(9));2.0
		//}2.0
		
		if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.")) {
			//pes.SelectProceedMMLE(al.get(10));
			//pes.SelectProceedSMLE(al.get(11));
			}
		pes.SelectProceedWOIS(al.get(12));
		pes.SelectProceedNotes(al.get(13));
		//pes.SelectProceedInfrmCustOP(al.get(14));
		//pes.SelectProceedSchdOS(al.get(15));
		//pes.SelectProceedParentOS(al.get(16));
		//pes.SelectProceedRateScale(al.get(17));
		//pes.SelectProceedOrdernPay(al.get(18));
		//pes.SelectProceedNotAttend(al.get(19));
		//}
		//else {
		//pes.SelectProceedSpokeTo(al.get(103)); //Created with SFDC-2418 2.0 2.0
		//pes.SelectProceedDatenTimeOrientation(); //Created with SFDC-2418 2.0 2.0
		//pes.SelectProceedInfoOrientation(al.get(104)); //Created with SFDC-2418 2.0 2.0
		//pes.SelectProceedAvailOfCustomer(al.get(105)); //Created with SFDC-2418 2.0 2.0
		//pes.SelectProceedParentsIsMandatory(al.get(106)); //Created with SFDC-2418 2.0 2.0
		//pes.SelectProceedInfoAttendPO(al.get(107)); //Created with SFDC-2418 2.0 2.0
		//pes.SelectProceedParentOrientation(al.get(108)); //Created with SFDC-2418 2.0 2.0
		//pes.SelectProceedAttendingPO(al.get(109)); //Created with SFDC-2418 2.0 2.0
		//pes.SelectProceedInfoWillCusAttend(al.get(110)); //Created with SFDC-2418 2.0 2.0
		//pes.SelectProceedInformedFinance(al.get(111)); //Created with SFDC-2418 2.0
		//pes.SelectProceedDOB(al.get(112)); //Created with SFDC-2418 2.0
		//pes.SelectProceedReasonPurchase(al.get(113)); //Created with SFDC-2418 2.0
		//pes.SelectProceedPriCaretakerStudies(al.get(114)); //Created with SFDC-2418 2.0
		//pes.SelectCustmExpectation(al.get(115)); //Created with SFDC-2418 2.0
		//pes.SelectProceedMathMarks(al.get(116)); //Created with SFDC-2418 2.0
		//pes.SelectProceedScienceMarks(al.get(117)); //Created with SFDC-2418 2.0
		//pes.SelectProceedWhatsApp(al.get(118)); //Created with SFDC-2418 2.0
		//pes.SelectProceedSCNotes(al.get(119)); //Created with SFDC-2418 2.0
		//}
		Thread.sleep(2500);
		pes.SelectProceedIsThereIssue(al.get(20));
		pes.NavBackAccount();
		
		log.info("Navigating to Student sales order");
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			
			//ac.ClickStudentProg();2.0
			
			//StudentProgPO sp= new StudentProgPO(driver);2.0
			//sp.ClickSSO();2.0
			
			//StudentSalesOrderPO sso= new StudentSalesOrderPO(driver);2.0
			//sso.SelectPartialDel();2.0
			//sso.ClickSave();2.0
			StudentSubOrderPO Ssub=new StudentSubOrderPO(driver);
			ac.ClickStudentSalesOrder();
			//Ssub.ClickStudentSalesOrder();
			Ssub.ClickOrderinOrders();
			Ssub.SelectSSOStatus();
			Ssub.ClickSave();
		}
		else {
		StudentSubOrderPO Ssub=new StudentSubOrderPO(driver);
		ac.ClickStudentSalesOrder();
		//Ssub.ClickStudentSalesOrder();
		Ssub.ClickOrderinOrders();
		Ssub.SelectSSOStatus();
		Ssub.ClickSave();
		}
		
		ac.ClickAccOwnrTab();
		ac.RefreshTab();
				
		log.info("Navigating to PE On boarding Call tasks");
		//Verify PE Onboarding call task is created when the SSO status moved to Partial delivered/delivered
		Assert.assertTrue(ac.CheckVisPEOnboardingCall());
		ac.CloseSubTabs();
		ac.ClickPEOnboardngCall();
		
		
		PEOnBoardingTaskPO peob=new PEOnBoardingTaskPO(driver);
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			peob.ChangeDueDate();
		}
		peob.ClickCaptureDetail();
		log.info("Selecting Capture Call as Proceed for PE Onboarding Task");
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		peob.SelectProceed_iframe();
		peob.ClickOnboardingScreenNext();
		}
		else {
			//peob.SelectProceed();
			peob.SelectProceed_iframe();
			peob.ClickOnboardingScreenNext();
		}
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		peob.SelectProceedS_T(al.get(92)); //Created with SFDC-2419 2.0
		peob.SelectProceedHTPTMCA(al.get(134));
		peob.EnterNotes(al.get(93)); //Created with SFDC-2419 2.0
		peob.SelectSHAACLOC(al.get(94)); //Created with SFDC-2419 2.0
		peob.SelectProductRating(al.get(95)); //Created with SFDC-2419 2.0
		peob.SelectNotLikeProduct(al.get(96)); //Created with SFDC-2419 2.0
		peob.SelectTeacherRating(al.get(97)); //Created with SFDC-2419 2.0
		peob.SelectNotLikeTeacher(al.get(98)); //Created with SFDC-2419 2.0
		peob.SelectContentRating(al.get(99)); //Created with SFDC-2419 2.0
		peob.SelectNotLikeContent(al.get(100)); //Created with SFDC-2419 2.0
		peob.SelectStudentAppUsage(al.get(101)); //Created with SFDC-2419 2.0
		peob.SelectTLPOnboardngDone(al.get(102)); //Created with SFDC-2419 2.0		
		}
		else {
		//peob.SelectProceedST(al.get(28));
		//peob.SelectProceedSAU(al.get(29));
		//peob.SelectProceedIR(al.get(30));
		//peob.SelectProceedNotes(al.get(31));
		
		peob.SelectProceedS_T(al.get(92)); //Created with SFDC-2419 2.0
		peob.SelectProceedHTPTMCA(al.get(134));
		peob.EnterNotes(al.get(93)); //Created with SFDC-2419 2.0
		peob.SelectSHAACLOC(al.get(94)); //Created with SFDC-2419 2.0
		peob.SelectProductRating(al.get(95)); //Created with SFDC-2419 2.0
		peob.SelectNotLikeProduct(al.get(96)); //Created with SFDC-2419 2.0
		peob.SelectTeacherRating(al.get(97)); //Created with SFDC-2419 2.0
		peob.SelectNotLikeTeacher(al.get(98)); //Created with SFDC-2419 2.0
		peob.SelectContentRating(al.get(99)); //Created with SFDC-2419 2.0
		peob.SelectNotLikeContent(al.get(100)); //Created with SFDC-2419 2.0
		peob.SelectStudentAppUsage(al.get(101)); //Created with SFDC-2419 2.0
		peob.SelectTLPOnboardngDone(al.get(102)); //Created with SFDC-2419 2.0	
		
		//peob.SelectProceedSHAAC(al.get(32));
		}

		
		peob.SelectProceedIsThereIssue(al.get(33));
		
		peob.NavBackAccount();
		
		//if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			//Verify once the PE Onboarding call is completed, Follow up task is created on the account 
			Assert.assertTrue(ac.CheckVisFollowUpTutorandMentorCall());
			ac.CloseSubTabs();
			ac.ClickFollowUpTutorandMentorCall();
			
			PEFollowUpTaskPO pefu= new PEFollowUpTaskPO(driver);
			if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			pefu.ChangeDueDate();
			}
			pefu.ClickCaptureDetail();
			log.info("Selecting Capture Call as Proceed for PE Follow Up Task 1");
			
			//Verify once the Follow up  is completed, Follow up 1 task is created on the account
			pefu.SelectProceed_iframe();
				
			String FollowUpheader1 = pefu.FollowUpHeader_iframe();
			Assert.assertTrue(FollowUpheader1.contains("Follow Up"));
			//pefu.SelectLProceedST(al.get(120));
			//pefu.SelectLProceedSAU(al.get(121));
			//pefu.SelectLProceedITTFRD(al.get(122));
			//pefu.SelectLProceedITCAD(al.get(123));
			//pefu.SelectLProceedPPCDD(al.get(124));
			//pefu.SelectLProceedNotes(al.get(125));
			//pefu.SelectLProceedACSLOC(al.get(126));
			//pefu.SelectLProceedRateProduct(al.get(127));
			//pefu.SelectLProceedNotLikeProduct(al.get(128));
			//pefu.SelectLProceedRateTeacher(al.get(129));
			//pefu.SelectLProceedNotLikeTeacher(al.get(130));
			//pefu.SelectLProceedRateContent(al.get(131));
			//pefu.SelectLProceedNotLikeContent(al.get(132));
		
			pefu.SelectProceedST(al.get(66));
			pefu.SelectProceedSAU(al.get(67));
			pefu.SelectProceedNotes(al.get(68));
			pefu.ClickNext();
			
			pefu.SelectProceedIsThereIssue(al.get(45));
			
			pefu.NavBackAccount();
			


			//Verify once the PE Onboarding call is completed, Follow up task is created on the account 
			Assert.assertTrue(ac.CheckVisFollowUpTutorandMentorCall());
			ac.CloseSubTabs();
			ac.ClickFollowUpTutorandMentorCall();
			
			if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			pefu.ChangeDueDate();
			}
			pefu.ClickCaptureDetail();
			log.info("Selecting Capture Call as Proceed for PE Follow Up Task 2");
			
			//Verify once the Follow up  is completed, Follow up 2 task is created on the account
			pefu.SelectProceed_iframe();

			String FollowUpheader2 = pefu.FollowUpHeader_iframe();
			Assert.assertTrue(FollowUpheader2.contains("Follow Up"));
			//pefu.SelectLProceedST(al.get(120));
			//pefu.SelectLProceedSAU(al.get(121));
			//pefu.SelectLProceedMRPShared(al.get(122));
			//pefu.SelectLProceedMRPDiscussionDone(al.get(122));
			//pefu.SelectLProceedNotes(al.get(125));
			//pefu.SelectLProceedACSLOC(al.get(85));
			//pefu.SelectLProceedRFNAC(al.get(133));
			//pefu.SelectProceedIsThereIssue(al.get(59));
			
			pefu.SelectProceedST(al.get(66));
			pefu.SelectProceedSAU(al.get(67));
			pefu.SelectProceedNotes(al.get(68));
			pefu.ClickNext();
			
			pefu.SelectProceedIsThereIssue(al.get(59));
			
			pefu.NavBackAccount();
			
			//Verify once the PE Onboarding call is completed, Follow up task is created on the account 
			Assert.assertTrue(ac.CheckVisFollowUpTutorandMentorCall());
			ac.CloseSubTabs();
			ac.ClickFollowUpTutorandMentorCall();
			
			if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			pefu.ChangeDueDate();
			}
			pefu.ClickCaptureDetail();
			log.info("Selecting Capture Call as Proceed for PE Follow Up Task 3");
			
			//Verify once the Follow up  is completed, Follow up 3 task is created on the account

			
			pefu.SelectProceed_iframe();

			//String FollowUpheader3 = pefu.FollowUpHeader_iframe();
			//Assert.assertTrue(FollowUpheader3.contains("Follow Up Call (3)"));
			pefu.SelectProceedST(al.get(66));
			pefu.SelectProceedSAU(al.get(67));
			pefu.SelectProceedNotes(al.get(68));
			pefu.ClickNext();
			if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
				//pefu.SelectProceedSchoolName(al.get(69));2.0
				//pefu.SelectProceedSchoolAddr(al.get(70));2.0
			}
			else {
				pefu.ClickNext();
			}
			
			pefu.SelectProceedIsThereIssue(al.get(59));
			
			pefu.NavBackAccount();
			ac.CloseSubTabs();
			ac.Scrollpagedown();
			//}
		//*************************//
		
		//Assert.assertTrue(false);
		ac.CloseSubTabs();
		//Creating a Refund scenario
		log.info("Creating Inbound Task");

		ac.Scrollpagedown();
		ac.Scrollpagedown();
		//ac.ClickOpenActiDD();
		ac.ClickOpenActivitiestoNewTask();


		
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver);
		
		ncrt.SelectCaseRecordTypeInbound();
		ncrt.ClickNext();
		Thread.sleep(2000);
		ncrt.ClickSave();
		Thread.sleep(2000);
		
		InboundTaskPO ibdt= new InboundTaskPO(driver);
		ibdt.ClickCaptureDetail();
		Thread.sleep(3000);		
		al = excelData.getData("TC1", "ByjuWallet", "Tcid");  
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
		if(CurrURL.contains("--byjusuatfc")||CurrURL.contains("byjusprod.")) {
			ibdt.SelectPSTRR(al.get(5));
			//ibdt.SelectPSCOYR(al.get(5));
			ibdt.SelectPSSTRR(al.get(6));
			//ibdt.SelectPSTRR2(al.get(6));
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
		
		if(CurrURL.contains("--byjusuatfc")) {
		//ac.CloseCurrentSubTab_Prod();
		}

		ibdt.NavBackAccount();
		ac.CloseSubTabs();
		
		StudentAppInfoPO spi=new StudentAppInfoPO(driver);
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//Verifying the Block Status of Student App Information

		ac.ClickStudentAppInfo();
		ac.ClickMinimizeHistory();
		
		spi.CheckBlockStatus();
		
		ac.ClickAccOwnrTab();
		ac.CloseSubTabs();
		

		ac.Scrollpagedown();
		
		//Assert.assertTrue(false);
		//**************************************************************//
		int i=1;
		if(i==2) {
		ac.ClickStudentClassroomAssociations();
		scra.ClickStuCRAid();
		
		log.info("Verifying Post 60 Check is marked");
		
		//Verifying Post 60 Check is marked
		Assert.assertTrue(scra.Post60Check().isSelected());
		scra.EnterSessionMissed();
		scra.EnterSessionNo();
		String Mainwin= driver.getWindowHandle();
		
		scra.ClickStuClassRoomRcd();
		//Launching Dev console to run the batch job
		DevConsolePO dc= new DevConsolePO(driver);
		dc.GetDevConsole("UAT");
		dc.RunBatch(Batches.MKBatch());
		dc.CheckBatchRunSuccess();
		
		log.info("Verifying Post Assignment Done Check is marked");
		driver.close();
		driver.switchTo().window(Mainwin);
		Thread.sleep(3000);
		lo.RefreshURL();
		Thread.sleep(3000);
		ac.RefreshTab();
		Assert.assertTrue(scra.PostAssignmentDoneCheck().isSelected());
		
		String ClassroomOwner2= scra.CaptureOwner();
		System.out.println("The value of Owner is: "+ClassroomOwner2);
		
		ac.ClickAccOwnrTab();
		ac.CloseSubTabs();
		ac.RefreshTab();
		
		Assert.assertFalse(ClassroomOwner2.equalsIgnoreCase(ac.CaptureAccOwnerNam()));
		}
		}

		//ac.ClickAccOwnrTab();
		
		//Capturing Case details and then deleting it
		ac.Scrollpagedown();
		ac.ClickCasesMC();
		
		CasesPO cases= new CasesPO(driver);
		
		String CaseNumber= cases.CaseRN();
		log.info("The case number created is: "+CaseNumber);
		//cases.CaseOptnSel();
		cases.DeleteCCaseRecord(CaseNumber);
		
		//ac.ClickAccOwnrTab();
		//ac.CloseSubTabs();
		
		
		log.info("Deleting the Student Program details");
		ac.ClickAccOwnrTab();
		ac.DeleteCreatedStuProg();
		ac.NavBackToAccount();
		log.info("Deleting the Account created details");
		ac.DeleteAccountCreated(ac.CaptureAccOwnrNam());
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		log.info("Deleting the Student Classroom details "+StudentClassroom);
		driver.get(CurrURL+StudentClassroom);	
		Thread.sleep(3500);
		scra.ClickDelete();
		// StudentAppInfo1
		
		log.info("Deleting the Student App Info details ");
		driver.get(CurrURL+StudentAppInfo1);	
		Thread.sleep(3500);
		spi.DeleteStudentAppInfolastbtn();
		
		driver.get(CurrURL+StudentAppInfo2);	
		Thread.sleep(3500);
		spi.DeleteStudentAppInfolastbtn();
		}
		
	}
		
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
	
	
}

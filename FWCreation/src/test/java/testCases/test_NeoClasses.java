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


import pageObjects.CreatedAccountPO;

import pageObjects.ExamDetailsPO;

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
import payLoad.payLoad_MK_Retention;
import payLoad.payLoad_NeoClasses;

import resources.ExcelData;
import resources.base;



public class test_NeoClasses extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_NeoClasses.class.getName());
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
	public void TestNeoClasses() throws Exception {
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
		Accountid=payLoad_NeoClasses.AccountidCreationResponse_UATFC();
		payLoad_NeoClasses.ClassRoomMappingResponse_UATFC();
		payLoad_NeoClasses.StudentAppInfoResponse_UATFC();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusuat")) {
			al = excelData.getData("TC1", "Neo", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			
			log.info("Logging in as Admin to UAT");
			lo.LoginAsAdmin_UAT();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_NeoClasses.AccountidCreationResponse_UAT();
			payLoad_NeoClasses.StudentClassRoomAdd_UAT();
			payLoad_NeoClasses.ClassRoomMappingResponse_UAT();
			payLoad_NeoClasses.StudentAppInfoResponse_UAT();
			StudentClassroom = payLoad_NeoClasses.StudentClassRoomid_UAT();
			StudentAppInfo1 = payLoad_NeoClasses.StudentAppInfoid1_UAT();
			StudentAppInfo2 = payLoad_NeoClasses.StudentAppInfoid2_UAT();
			log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "Neo", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_NeoClasses.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "Neo", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_NeoClasses.AccountidCreationResponse_Prod();
			payLoad_NeoClasses.ClassRoomMappingResponse_Prod();
			payLoad_NeoClasses.StudentAppInfoResponse_Prod();
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
		
		//Verify the PE Shipped all is created
		Assert.assertTrue(ac.CheckVisPEShippedCall());
		  
		log.info("Navigating to PE Shipped Call tasks");
		ac.ClickPEShipped();
		
		PEShippedTaskPO pes=new PEShippedTaskPO(driver);
		pes.ClickCaptureDetail();
		log.info("Selecting Capture Call as Proceed for PE Shipped Task");
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		pes.SelectProceed_iframe();
		//pes.ClickNext();
		}
		else {
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
			Assert.assertTrue(ac.CheckExistingProfile());//Added check for SFTNL-6458
			//Ssub.ClickStudentSalesOrder();
			Ssub.ClickOrderinOrders();
			Ssub.SelectSSOStatus();
			Ssub.ClickSave();
		}
		else {
		StudentSubOrderPO Ssub=new StudentSubOrderPO(driver);
		ac.ClickStudentSalesOrder();
		Assert.assertTrue(ac.CheckExistingProfile());//Added check for SFTNL-6458
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
		
		//****************************************************Zoom**********************
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ac.AssignAccount2();
		}
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
			//pefu.SelectProceedZoom(al.get(135));
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
			
		
			//Verify Session Attended is Created
			//if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			//	ac.ClickSessionInformation_UAT();
			//}
			//else {
			
			//ac.ClickSessionInformation();
			//}
			
			if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.")) {
			//	ac.ClickSessionInformation();
			}
			
		SessionsInformationPO si = new SessionsInformationPO(driver);
		//if(!CurrURL.contains("--byjusuat")) {
		log.info("Sending the Session Attended API");
		if(CurrURL.contains("--byjusuatfc")) {
			payLoad_NeoClasses.SessionAttendedResponse_UATFC();
		    Thread.sleep(2500);
			}
		else if(CurrURL.contains("--byjusuat")) {
		payLoad_NeoClasses.SessionAttendedResponse_UAT();
	    Thread.sleep(2500);
		}
		else if(CurrURL.contains("--byjusqa")) {
			payLoad_NeoClasses.SessionAttendedResponse_QA();
		    Thread.sleep(2500);
			}
		else {
			payLoad_NeoClasses.SessionAttendedResponse_Prod();
		    Thread.sleep(2500);
			}
	  
	    
	    Thread.sleep(5000);
	    si.RefreshTab();
		
	    if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			String SessionAttendedid= si.CaptureSessionid_UAT2();
			log.info("Session id created for Session Attended is: "+SessionAttendedid);
			
			si.ClickSessionid_UAT2();
		    }
		    else {
				String SessionAttendedid= si.CaptureSessionid_UAT2();;
				log.info("Session id created for Session Attended is: "+SessionAttendedid);
				
				si.ClickSessionid_UAT2();
		    }
		
		String SessionAttend= si.CaptureSessiontext();
		Thread.sleep(800);
		
		Assert.assertEquals(SessionAttend, "Session Attended");
		
		log.info("Deleting the created Session Information");
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//si.DeleteSessionInfoCreated();
			si.DeleteSessionInfolastbtn();
		}
		else {
		si.DeleteSessionInfolastbtn();
		//si.NavSessionInfo();
		}
		
		//}
		
		 //if(!CurrURL.contains("--byjusuat")) {
		
		//Verify Session Missed is Created
				
		log.info("Sending the Session Missed API");
	    if(CurrURL.contains("--byjusuatfc")) {
			payLoad_NeoClasses.SessionMissedResponse_UATFC();
		    Thread.sleep(2500);
			}
	    else if(CurrURL.contains("--byjusuat")) {
			payLoad_NeoClasses.SessionMissedResponse_UAT();
		    Thread.sleep(4500);
			}
			else if(CurrURL.contains("--byjusqa")) {
				payLoad_NeoClasses.SessionMissedResponse_QA();
			    Thread.sleep(2500);
				}
			else {
				payLoad_NeoClasses.SessionMissedResponse_Prod();
			    Thread.sleep(2500);
				}
		  
	  
	    si.RefreshTab();
		
	    if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		String SessionMissedid= si.CaptureSessionid_UAT2();
		log.info("Session id created for Session Attended is: "+SessionMissedid);
		
		si.ClickSessionid_UAT2();
	    }
	    else {
			String SessionMissedid= si.CaptureSessionid_UAT2();;
			log.info("Session id created for Session Missed is: "+SessionMissedid);
			
			si.ClickSessionid_UAT2();
	    }
		
		
		String SessionMissed= si.CaptureSessiontext();
		Thread.sleep(800);
		
		Assert.assertEquals(SessionMissed, "Session Missed");
		//Assert.assertFalse(true);
		log.info("Deleting the created Session Information");
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			//si.DeleteSessionInfoCreated();
			si.DeleteSessionInfolastbtn();
			}
			else {
			si.DeleteSessionInfolastbtn();
			//si.NavSessionInfo();
			}
		
		 //}
		//Verify Session Feedback is Created
		//***********************************************************//
		
		  log.info("Sending the Session Feedback API");
		  if(CurrURL.contains("--byjusuatfc")) {
		  payLoad_NeoClasses.SessionFeedbackResponse_UATFC(); 
		  Thread.sleep(2500); 
		  }
		  else if(CurrURL.contains("--byjusuat")) {
		  payLoad_NeoClasses.SessionFeedbackResponse_UAT(); 
		  Thread.sleep(4500); 
		  } else
		  if(CurrURL.contains("--byjusqa")) {
		  payLoad_NeoClasses.SessionFeedbackResponse_QA(); 
		  Thread.sleep(2500); } 
		  else {
		  payLoad_NeoClasses.SessionFeedbackResponse_Prod(); 
		  Thread.sleep(2500); }
		  
		  si.RefreshTab();
		  
		  if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
				String SessionFeedbackid= si.CaptureSessionid_UAT2();
				log.info("Session id created for Session Attended is: "+SessionFeedbackid);
				
				si.ClickSessionid_UAT2();
			    }
			    else {
					  String SessionFeedbackid= si.CaptureSessionid_UAT2();
					  log.info("Session id created for Session Feedback is: "+SessionFeedbackid);
					  
					  si.ClickSessionid_UAT2();
			    }
		  
		  //String SessionFeedback= si.CaptureTypeofSessiontext(); Thread.sleep(800);
		  
		  //Assert.assertEquals(SessionFeedback, "Session Feedback");
		  
		  log.info("Deleting the created Session Information");
		  if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) { 
			  //si.DeleteSessionInfoCreated(); 
			  si.DeleteSessionInfolastbtn();
			  } 
		  else {
		  si.DeleteSessionInfolastbtn(); }
		 
		  if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.") ) {
				//ac.ClickAccOwnrTab();
				//ac.CloseSubTabs();
				}
		
		//Verify Assessment Submitted Exam detail is Created
				
		ExamDetailsPO ed= new ExamDetailsPO(driver);
		
		log.info("Sending the Assessment Submitted API");
	    if(CurrURL.contains("--byjusuatfc")) {
	        payLoad_NeoClasses.AssessmentSubmittedResponse_UATFC();
		    Thread.sleep(2500);
			}
	    else if(CurrURL.contains("--byjusuat")) {
	        payLoad_NeoClasses.AssessmentSubmittedResponse_UAT();
		    Thread.sleep(2500);
		    ac.Scrollpagedown();
			}
			else if(CurrURL.contains("--byjusqa")) {
				payLoad_NeoClasses.AssessmentSubmittedResponse_QA();
			    Thread.sleep(2500);
				}
			else {
				payLoad_NeoClasses.AssessmentSubmittedResponse_Prod();
			    Thread.sleep(2500);
				}
	    ed.RefreshTab();
		ac.ClickExamDetails();
		
		String Examid= ed.CaptureExamid();
		log.info("Exam id created for Assessment Submitted is: "+Examid);
		
		String RecordType=ed.CaptureRecordtypetext();
		Assert.assertEquals(RecordType, "Monthly Test Scheduled");
		
		ed.ClickExamid();
			
		log.info("Deleting the created Exam Detail Information");
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			//ed.DeleteExamInfoCreated();
			ed.DeleteExamDetailslastbtn();
			}
			else {
			ed.DeleteExamDetailslastbtn();
			}
		
		ed.NavExamInfo();
	
		//Verify Monthly Submitted Exam detail is Created	
		log.info("Sending the Monthly Test API");
		 if(CurrURL.contains("--byjusuatfc")) {
		        payLoad_NeoClasses.MonthlyTestResponse_UATFC();
			    Thread.sleep(2500);
				}
		 else if(CurrURL.contains("--byjusuat")) {
	        payLoad_NeoClasses.MonthlyTestResponse_UAT();
		    Thread.sleep(2500);
			}
			else if(CurrURL.contains("--byjusqa")) {
				payLoad_NeoClasses.MonthlyTestResponse_QA();
			    Thread.sleep(2500);
				}
			else {
				payLoad_NeoClasses.MonthlyTestResponse_Prod();
			    Thread.sleep(2500);
				}
	    Thread.sleep(1200);
	    ed.RefreshTab();
		Thread.sleep(1000);
	    
		String Examid1= ed.CaptureExamid();
		log.info("Exam id created for Monthly Test Submitted is: "+Examid1);
		
		String RecordType1=ed.CaptureRecordtypetext();
		Assert.assertEquals(RecordType1, "Monthly Test Scheduled");
		
		ed.ClickExamid();
			
		log.info("Deleting the created Exam Detail Information");
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			//ed.DeleteExamInfoCreated();
			ed.DeleteExamDetailslastbtn();
			}
			else {
			ed.DeleteExamDetailslastbtn();
			}
		ac.ClickAccOwnrTab();
		
		
		//Verify Student School Informations is Created

		  log.info("Sending Student School Information API");
	    String SchoolInfo;
	    if(CurrURL.contains("--byjusuatfc")) {
		 SchoolInfo = payLoad_NeoClasses.StudentSchoolInformationsResponse_UATFC(); 
		  Thread.sleep(2500); 
		  }
		  else if(CurrURL.contains("--byjusuat")) {
		 SchoolInfo =  payLoad_NeoClasses.StudentSchoolInformationsResponse_UAT(); 
		  Thread.sleep(4500); 
		  }
		  else {
		 SchoolInfo =  payLoad_NeoClasses.StudentSchoolInformationsResponse_Prod(); 
		  Thread.sleep(2500); 
		  }
	  
		  ac.CloseSubTabs();
		  si.RefreshTab();
		  
		  
		  String StudentSchoolInformation= si.CaptureStuSchlInfo();
		  log.info("Student School Information created is: "+StudentSchoolInformation);
				
		  si.ClickStuSchlInfo();
		  
		  log.info("Deleting the created Student School Information");

	    si.DeleteStudentSchoolInfolastbtn(); 
			  
		String SchoolInfoURL=CurrURL+SchoolInfo;
		
		
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
		StudentCRAssoPO scra= new StudentCRAssoPO(driver);
		scra.ClickDelete();
		// StudentAppInfo1
		
		log.info("Deleting the Student App Info details ");
		driver.get(CurrURL+StudentAppInfo1);	
		Thread.sleep(3500);
		StudentAppInfoPO spi = new StudentAppInfoPO(driver);
		spi.DeleteStudentAppInfolastbtn();
		
		driver.get(CurrURL+StudentAppInfo2);	
		Thread.sleep(3500);
		spi.DeleteStudentAppInfolastbtn();
		}
		
		log.info("Deleting the School Information details "+SchoolInfo);
		driver.get(SchoolInfoURL);	
		Thread.sleep(3500);
		StudentAppInfoPO spi= new StudentAppInfoPO(driver);
		spi.DeleteSchoolInfolastbtn();		
		
	}
		
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
	
	
}

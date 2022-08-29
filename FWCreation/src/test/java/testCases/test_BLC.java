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
import pageObjects.StudentProgPO;
import pageObjects.StudentSalesOrderPO;
import pageObjects.StudentSubOrderPO;

import pageObjects.loginPO;
import payLoad.payLoad_BTLA;
import resources.ExcelData;
import resources.base;



public class test_BLC extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_BLC.class.getName());
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
	public void TestBLC() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "BLC", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to UATFC Env");
			lo.LoginAsAdmin_UATFC();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "BLC", "Tcid");
		//al2 = excelData.getData("Collection Assistant", "Login", "Type");
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_BTLA.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "BLC", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "BLC", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		//Assert.assertTrue(false);
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
		if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.")) {
			Assert.assertEquals(Progval, "Hybrid Tuitions at BYJU'S Learning Center");
		}
		else {
		Assert.assertEquals(Progval, "Hybrid Tuitions at BYJU'S Tuition Centre");
		}
		//Verify Account super status and status is PE/First60 and New
		
		String SuperStatus = ac.CaptureSuperStatus();
		//Assert.assertTrue(SuperStatus.contains("Frist60"));
		
		String Status = ac.CaptureStatus();
		Assert.assertEquals(Status, "New");
		PEShippedTaskPO pes=new PEShippedTaskPO(driver);
		//Verify BTC Welcome Call is created
		
			Assert.assertTrue(ac.CheckVisBTCWelcomeCall_UAT());
			log.info("Navigating to BTC Welcome Call tasks");
			ac.ClickBTCWelcomeCall();
			
			pes.ClickCaptureDetail();
			log.info("Selecting Capture Call as Proceed for BTC Welcome Call Task");
			pes.SelectProceed_iframe();
			pes.SelectBTCNumberPrimaryContact(al.get(92));
			pes.SelectBTCPrimaryPointOfContact(al.get(93));
			pes.SelectBTCNumberUpdatedOnSF(al.get(94));
			pes.SelectBTCPaymentMethod(al.get(95));
			pes.SelectBTCEMITermNValue(al.get(96));
			pes.SelectBTCCurrentGradeStated(al.get(97));
			pes.SelectBTCCentrePunched(al.get(98));
			pes.SelectBTCBoardPunched(al.get(99));
			pes.SelectBTCTabletReceived(al.get(100));
			pes.SelectBTCExpiryProgramOrderPunched(al.get(101));
			pes.SelectBTCNameOfSchool(al.get(102));
			pes.SelectBTCInClassOrientation(al.get(103));
			pes.SelectBTCLikelyParentsAttendOrientation(al.get(104));
			pes.SelectBTCReasonNotAttendingOrientation_UAT(al.get(105));
			pes.SelectBTCWhatsappOptIn(al.get(106));
			pes.SelectBTCScale(al.get(107));
			pes.SelectProceedVisIsThereIssue();
			pes.SelectProceedIsThereIssue(al.get(20));
		
		int l=0;
		if(l==1) { //****************Not to go in this code******************
		//Verify the PE Shipped all is created
		if(CurrURL.contains("--byjusuatfc")||CurrURL.contains("byjusprod.")) {
		Assert.assertTrue(ac.CheckVisProdPEShippedCall());
		
		
		log.info("Navigating to PE Shipped Call tasks");
		ac.ClickPEShipped();
		
		pes.ClickCaptureDetail();
		
		log.info("Selecting Capture Call as Proceed for PE Shipped Task");
		
			//pes.SelectProceed();
			pes.SelectProceed_iframe();
			pes.ClickFinish();
			int i=1;
			if(i==2) {
			pes.SelectProceedIAFO(al.get(1));
			pes.SelectProceedDOBA(al.get(2));
			pes.SelectProceedLP(al.get(3));
			pes.SelectProceedRFP(al.get(4));
			pes.SelectProceedPCOS(al.get(5));
			pes.SelectProceedCE(al.get(6));
			pes.SelectProceedST(al.get(7));
			//if(CurrURL.contains("--byjusuat")) {2.0
			//pes.SelectProceedSN(al.get(8));2.0
			//pes.SelectProceedSA(al.get(9));2.0
			//}2.0
			pes.SelectProceedMMLE(al.get(10));
			pes.SelectProceedSMLE(al.get(11));
			pes.SelectProceedWOIS(al.get(12));
			pes.SelectProceedNotes(al.get(13));
			pes.SelectProceedInfrmCustOP(al.get(14));
			pes.SelectProceedSchdOS(al.get(15));
			pes.SelectProceedParentOS(al.get(16));
			pes.SelectProceedRateScale(al.get(17));
			pes.SelectProceedOrdernPay(al.get(18));
			pes.SelectProceedNotAttend(al.get(19));
			}

		}
		else {
			/*
			 * pes.SelectProceed_iframe(); pes.SelectBTCNumberPrimaryContact(al.get(92));
			 * pes.SelectBTCPrimaryPointOfContact(al.get(93));
			 * pes.SelectBTCNumberUpdatedOnSF(al.get(94));
			 * pes.SelectBTCPaymentMethod(al.get(95));
			 * pes.SelectBTCEMITermNValue(al.get(96));
			 * pes.SelectBTCCurrentGradeStated(al.get(97));
			 * pes.SelectBTCCentrePunched(al.get(98));
			 * pes.SelectBTCBoardPunched(al.get(99));
			 * pes.SelectBTCTabletReceived(al.get(100));
			 * pes.SelectBTCExpiryProgramOrderPunched(al.get(101));
			 * pes.SelectBTCNameOfSchool(al.get(102));
			 * pes.SelectBTCInClassOrientation(al.get(103));
			 * pes.SelectBTCLikelyParentsAttendOrientation(al.get(104));
			 * pes.SelectBTCReasonNotAttendingOrientation(al.get(105));
			 * pes.SelectBTCWhatsappOptIn(al.get(106)); pes.SelectBTCScale(al.get(107));
			 */}
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			/*
			 * pes.SelectProceedVisIsThereIssue();
			 * pes.SelectProceedIsThereIssue(al.get(20));
			 */}

		Thread.sleep(2000);
		pes.NavBackAccount();
		
		
		log.info("Navigating to Student sales order");
		if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.")) {
			StudentSubOrderPO Ssub=new StudentSubOrderPO(driver);
			ac.ClickStudentSalesOrder();
			//Ssub.ClickStudentSalesOrder();
			Ssub.ClickOrderinOrders();
			Ssub.SelectSSOStatus();
			Ssub.ClickSave();
		

		
		ac.ClickAccOwnrTab();
		ac.RefreshTab();
		log.info("Navigating to PE On boarding Call tasks");
		//Verify PE Onboarding call task is created when the SSO status moved to Partial delivered/delivered
		Assert.assertTrue(ac.CheckVisPEOnboardingCall());
		ac.CloseSubTabs();
		ac.ClickPEOnboardngCall();
		
		
		PEOnBoardingTaskPO peob=new PEOnBoardingTaskPO(driver);
		peob.ClickCaptureDetail();
		log.info("Selecting Capture Call as Proceed for PE Onboarding Task");
		if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.")) {
			//peob.SelectProceed();
			peob.SelectProceed_iframe();
		}
		else {
			//peob.SelectProceed_iframe();
		}
		peob.SelectProceedST(al.get(28));
		peob.SelectProceedSAU(al.get(29));
		peob.SelectProceedIR(al.get(30));
		peob.SelectProceedNotes(al.get(31));
		peob.SelectProceedIsThereIssue(al.get(32));
		
		peob.NavBackAccount();
		
		//if(CurrURL.contains("--byjusuat")) {
		//Verify once the PE Onboarding call is completed, Follow up task is created on the account
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			//ac.Scrollpagedown();
			//ac.Scrollpagedown();
		}
		Assert.assertTrue(ac.CheckVisPEFollowUpCall());
		ac.CloseSubTabs();
		ac.ClickPEFollowUpCall();
		
		PEFollowUpTaskPO pefu= new PEFollowUpTaskPO(driver);
		pefu.ClickCaptureDetail();
		log.info("Selecting Capture Call as Proceed for PE Follow Up Task 1");
		
		//Verify once the Follow up  is completed, Follow up 1 task is created on the account
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//pefu.SelectProceed_iframe();
		//String FollowUpheader1 = pefu.FollowUpHeader_BTLP();
		//Assert.assertTrue(FollowUpheader1.contains("PE - Follow Up"));
		}
		else {
		//pefu.SelectProceed();
		//String FollowUpheader1 = pefu.FollowUpHeader();
		//Assert.assertTrue(FollowUpheader1.contains("PE - Follow Up 1"));
		
		pefu.SelectProceed_iframe();
		String FollowUpheader1 = pefu.FollowUpHeader_BTLP();
		Assert.assertTrue(FollowUpheader1.contains("PE - Follow Up"));
		}
		pefu.SelectProceedST(al.get(40));
		pefu.SelectProceedSAU(al.get(41));
		pefu.SelectProceedNotes(al.get(42));
		//if(CurrURL.contains("--byjusuat")) { 2.0
			//pefu.SelectProceedSchoolName(al.get(43)); 2.0
			//pefu.SelectProceedSchoolAddr(al.get(44)); 2.0
		//} 2.0
		//else { 2.0
			pefu.ClickNext();
		//} 2.0

		pefu.SelectProceedIsThereIssue(al.get(45));
		
		pefu.NavBackAccount();

		//Verify once the PE Onboarding call is completed, Follow up task is created on the account 
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			//ac.Scrollpagedown();
		}
		Assert.assertTrue(ac.CheckVisPEFollowUpCall());
		ac.CloseSubTabs();
		ac.ClickPEFollowUpCall();
		
		pefu.ClickCaptureDetail();
		log.info("Selecting Capture Call as Proceed for PE Follow Up Task 2");
		
		//Verify once the Follow up  is completed, Follow up 2 task is created on the account
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//pefu.SelectProceed_iframe();
		//String FollowUpheader2 = pefu.FollowUpHeader_BTLP();
		//Assert.assertTrue(FollowUpheader2.contains("PE - Follow Up"));
		}
		else {
		//pefu.SelectProceed();
		//String FollowUpheader2 = pefu.FollowUpHeader();
		//Assert.assertTrue(FollowUpheader2.contains("PE - Follow Up 2"));
		
		pefu.SelectProceed_iframe();
		String FollowUpheader2 = pefu.FollowUpHeader_BTLP();
		Assert.assertTrue(FollowUpheader2.contains("PE - Follow Up"));
		}
		pefu.SelectProceedST(al.get(53));
		pefu.SelectProceedSAU(al.get(54));
		pefu.SelectProceedNotes(al.get(55));
		//if(CurrURL.contains("--byjusuat")) { 2.0
			//pefu.SelectProceedSchoolName(al.get(56)); 2.0
			//pefu.SelectProceedSchoolAddr(al.get(57)); 2.0
		//} 2.0
		//else { 2.0
			pefu.ClickNext();
		//} 2.0
		pefu.SelectProceedIsThereIssue(al.get(58));
		
		pefu.NavBackAccount();
		
		//Verify once the PE Onboarding call is completed, Follow up task is created on the account 
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			//ac.Scrollpagedown();
		}
		Assert.assertTrue(ac.CheckVisPEFollowUpCall());
		ac.CloseSubTabs();
		ac.ClickPEFollowUpCall();
		
		pefu.ClickCaptureDetail();
		log.info("Selecting Capture Call as Proceed for PE Follow Up Task 3");
		
		//Verify once the Follow up  is completed, Follow up 3 task is created on the account
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//pefu.SelectProceed_iframe();
		//String FollowUpheader3 = pefu.FollowUpHeader_BTLP();
		//Assert.assertTrue(FollowUpheader3.contains("PE - Follow Up"));
		}
		else {
		//pefu.SelectProceed();
		//String FollowUpheader3 = pefu.FollowUpHeader();
		//Assert.assertTrue(FollowUpheader3.contains("PE - Follow Up 3"));
		
		pefu.SelectProceed_iframe();
		String FollowUpheader3 = pefu.FollowUpHeader_BTLP();
		Assert.assertTrue(FollowUpheader3.contains("PE - Follow Up"));
		}
		pefu.SelectProceedST(al.get(66));
		pefu.SelectProceedSAU(al.get(67));
		pefu.SelectProceedNotes(al.get(68));
		//if(CurrURL.contains("--byjusuat")) { 2.0
		//	pefu.SelectProceedSchoolName(al.get(69)); 2.0
		//	pefu.SelectProceedSchoolAddr(al.get(70)); 2.0
		//} 2.0
		//else { 2.0
			pefu.ClickNext();
		//} 2.0

		pefu.SelectProceedIsThereIssue(al.get(71));
		
		pefu.NavBackAccount();
		

		
		//Verify Session Attended is Created
		//if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//	ac.ClickSessionInformation_UAT();
		//}
		//else {
		
		//ac.ClickSessionInformation();
		//}
		
		if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.") ) {
		//	ac.ClickSessionInformation();
		}
		}
		}
		ac.CloseSubTabs();
		log.info("Sending the Session Attended API");
		if(CurrURL.contains("--byjusuatfc")) {
			payLoad_BTLA.SessionAttendedResponse_UATFC();
		    Thread.sleep(2500);
			}
		else if(CurrURL.contains("--byjusuat")) {
		payLoad_BTLA.SessionAttendedResponse_UAT();
	    Thread.sleep(2500);
		}
		else if(CurrURL.contains("--byjusqa")) {
			payLoad_BTLA.SessionAttendedResponse_QA();
		    Thread.sleep(2500);
			}
		else {
			payLoad_BTLA.SessionAttendedResponse_Prod();
		    Thread.sleep(2500);
			}
	  
	    SessionsInformationPO si = new SessionsInformationPO(driver);
	    si.RefreshTab();
		
	    if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		String SessionAttendedid= si.CaptureSessionid_UAT2();
		log.info("Session id created for Session Attended is: "+SessionAttendedid);
		
		si.ClickSessionid_UAT2();
	    }
	    else {
			String SessionAttendedid= si.CaptureSessionid_UAT2();
			log.info("Session id created for Session Attended is: "+SessionAttendedid);
			
			si.ClickSessionid_UAT2();
	    }
		
		String SessionAttend= si.CaptureSessiontext(); // SFTNL-6812
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
		
		
	//	if(CurrURL.contains("--byjusuat")) {
	
		//Verify Session Missed is Created
				
		log.info("Sending the Session Missed API");
		if(CurrURL.contains("--byjusuatfc")) {
			payLoad_BTLA.SessionMissedResponse_UATFC();
		    Thread.sleep(2500);
			}
		else if(CurrURL.contains("--byjusuat")) {
			payLoad_BTLA.SessionMissedResponse_UAT();
		    Thread.sleep(2500);
			}
			else if(CurrURL.contains("--byjusqa")) {
				payLoad_BTLA.SessionMissedResponse_QA();
			    Thread.sleep(2500);
				}
			else {
				payLoad_BTLA.SessionMissedResponse_Prod();
			    Thread.sleep(2500);
				}
		  
	    Thread.sleep(4000);
	    si.RefreshTab();
	    
	    if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		String SessionMissedid= si.CaptureSessionid_UAT2();
		log.info("Session id created for Session Attended is: "+SessionMissedid);
		
		si.ClickSessionid_UAT2();
	    }
	    else {
			String SessionMissedid= si.CaptureSessionid_UAT2();
			log.info("Session id created for Session Missed is: "+SessionMissedid);
			
			si.ClickSessionid_UAT2();
	    }
		

		
		String SessionMissed= si.CaptureSessiontext();
		Thread.sleep(800);
		
		Assert.assertEquals(SessionMissed, "Session Missed");
		
		log.info("Deleting the created Session Information");
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			//si.DeleteSessionInfoCreated();
			si.DeleteSessionInfolastbtn();
			}
			else {
			si.DeleteSessionInfolastbtn();
			//si.NavSessionInfo();
			}


		//Verify Session Feedback is Created
		//***********************************************************//
		
		  log.info("Sending the Session Feedback API");
		  if(CurrURL.contains("--byjusuatfc")) {
		  payLoad_BTLA.SessionFeedbackResponse_UATFC(); 
		  Thread.sleep(2500); } 
		  else if(CurrURL.contains("--byjusuat")) {
		  payLoad_BTLA.SessionFeedbackResponse_UAT(); 
		  Thread.sleep(2500); }
		  else
		  if(CurrURL.contains("--byjusqa")) {
		  payLoad_BTLA.SessionFeedbackResponse_QA(); 
		  Thread.sleep(2500); } 
		  else {
		  payLoad_BTLA.SessionFeedbackResponse_Prod(); 
		  Thread.sleep(2500); }
		   Thread.sleep(4000);
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
		  

		  
		  //String SessionFeedback= si.CaptureTypeofSessiontext(); 
		  Thread.sleep(800);
		  
		  //Assert.assertEquals(SessionFeedback, "Session Feedback");
		  
		  log.info("Deleting the created Session Information");
		  if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) { 
			  //si.DeleteSessionInfoCreated(); 
			  si.DeleteSessionInfolastbtn();
			  } 
		  else {
		  si.DeleteSessionInfolastbtn(); 
		  }
		
		if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.") ) {
		//ac.ClickAccOwnrTab();
		//ac.CloseSubTabs();
		}
		//Verify Assessment Submitted Exam detail is Created
				
		ExamDetailsPO ed= new ExamDetailsPO(driver);
		
		log.info("Sending the Assessment Submitted API");
		 if(CurrURL.contains("--byjusuatfc")) {
			payLoad_BTLA.AssessmentSubmittedResponse_UATFC();
		    Thread.sleep(2500);
			}
		 else if(CurrURL.contains("--byjusuat")) {
	        payLoad_BTLA.AssessmentSubmittedResponse_UAT();
		    Thread.sleep(2500);
		    ac.Scrollpagedown();
			}
			else if(CurrURL.contains("--byjusqa")) {
				payLoad_BTLA.AssessmentSubmittedResponse_QA();
			    Thread.sleep(2500);
				}
			else {
				payLoad_BTLA.AssessmentSubmittedResponse_Prod();
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
			payLoad_BTLA.MonthlyTestResponse_UATFC();
		    Thread.sleep(2500);
			}
		 else if(CurrURL.contains("--byjusuat")) {
	        payLoad_BTLA.MonthlyTestResponse_UAT();
		    Thread.sleep(2500);
			}
			else if(CurrURL.contains("--byjusqa")) {
				payLoad_BTLA.MonthlyTestResponse_QA();
			    Thread.sleep(2500);
				}
			else {
				payLoad_BTLA.MonthlyTestResponse_Prod();
			    Thread.sleep(2500);
				}
	    ed.RefreshTab();
		
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
		
		
		log.info("Deleting the Student Program details");
		ac.ClickAccOwnrTab();
		ac.DeleteCreatedStuProg();
		ac.NavBackToAccount();
		log.info("Deleting the Account created details");
		ac.DeleteAccountCreated(ac.CaptureAccOwnrNam());
			
				
		
	}
		
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
	
	
}

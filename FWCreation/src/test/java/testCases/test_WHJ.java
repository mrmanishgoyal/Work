package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.github.javafaker.Faker;

import org.testng.Assert;

import pageObjects.CapturePaymentDetailsEMIPO;
import pageObjects.CapturePaymtDetailsFPPO;
import pageObjects.CapturePaymtDetailsForeClosrePO;
import pageObjects.CapturePaymtDetailsPTPPO;
import pageObjects.CollectionAssistantLoginPO;
import pageObjects.CreatedAccountPO;
import pageObjects.CreatedCaseRecordPO;
import pageObjects.ExamDetailsPO;
import pageObjects.FirstConnectPO;
import pageObjects.FollowUpPTPPO;
import pageObjects.ForeClosurePO;
import pageObjects.NPPaymentLoanPO;
import pageObjects.NewCaseDetailsPO;
import pageObjects.NewCaseRecordTypePO;
import pageObjects.NewPaymentPO;
import pageObjects.NewPaymentRecordPO;
import pageObjects.PEFollowUpTaskPO;
import pageObjects.PEOnBoardingTaskPO;
import pageObjects.PEShippedTaskPO;
import pageObjects.PaymentCaseQAPO;
import pageObjects.PaymentsPO;
import pageObjects.SessionsInformationPO;
import pageObjects.StudentProgPO;
import pageObjects.StudentSalesOrderPO;
import pageObjects.StudentSubOrderPO;
import pageObjects.TasksPO;
import pageObjects.UserDetailPO;
import pageObjects.UserSetupPO;
import pageObjects.loginPO;
import payLoad.payLoad_Whitehatjr;
import resources.ExcelData;
import resources.base;



public class test_WHJ extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_WHJ.class.getName());
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
	public void TestWHJ() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "BLC", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			
			log.info("Logging in as Admin to UATFC");
			lo.LoginAsAdmin_UATFC();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_Whitehatjr.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
			
			}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "BLC", "Tcid");
		//al2 = excelData.getData("Collection Assistant", "Login", "Type");
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_Whitehatjr.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "BLC", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_Whitehatjr.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "BLC", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_Whitehatjr.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		
		//log.info("Submitting the Account creation payload");
		//String Accountid=payLoad_AccountCreation.AccountidCreationResponse_UAT();
		//log.info("Launching the newly created Account id "+Accountid);
		
		CreatedAccountPO ac=new CreatedAccountPO(driver);
		//Open the account by searching PID
		ac.NavBackToAccount();
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		
		String PremiumID = ac.CapturePremiumID();
		if(PremiumID!=null) {
			Assert.assertTrue(true);
		}
		log.info("The Premium id is "+PremiumID);
		
		//Verify Account is created with Student order,Student Payment in it
		
		//Assert.assertTrue(ac.CheckVisStudentOrderid());
		//ac.CaptureStudentOrder();
		//ac.CaptureStudentOrderIndvid();
		Assert.assertTrue(ac.CheckVisStudentPayid());
		ac.CaptureAllStudentPayid();
		ac.CheckVisStudentPrgid();
		ac.CaptureAllStudentProgDetails();
		
		//Verify  BYJUS The Learning App  program is created in student program related list
		String Progval = ac.CapturePrgCreated();
		Assert.assertEquals(Progval, "BYJUS Math");
		
		//Verify Account super status and status is PE/First60 and New
		
		String SuperStatus = ac.CaptureSuperStatus();
		//Assert.assertEquals(SuperStatus, "PE/Frist60");
		
		String Status = ac.CaptureStatus();
		Assert.assertEquals(Status, "New");
		
		//Verify Session Attended is Created
		//if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//	ac.ClickSessionInformation_UAT();
		//}
		//else {
		
		//ac.ClickSessionInformation();
		//}
		
		if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.")) {
			//ac.ClickSessionInformation();
		}
		
		log.info("Sending the Session Attended API");
		if(CurrURL.contains("--byjusuatfc")) {
			payLoad_Whitehatjr.SessionAttendedResponse_UATFC();
		    Thread.sleep(2500);
			}
		else if(CurrURL.contains("--byjusuat")) {
		payLoad_Whitehatjr.SessionAttendedResponse_UAT();
	    Thread.sleep(2500);
		}
		else if(CurrURL.contains("--byjusqa")) {
			payLoad_Whitehatjr.SessionAttendedResponse_QA();
		    Thread.sleep(2500);
			}
		else {
			payLoad_Whitehatjr.SessionAttendedResponse_Prod();
		    Thread.sleep(2500);
			}
		 Thread.sleep(3500);
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
		
		
		//Verify Session Missed is Created
				
		log.info("Sending the Session Missed API");
	    if(CurrURL.contains("--byjusuatfc")) {
			payLoad_Whitehatjr.SessionMissedResponse_UATFC();
		    Thread.sleep(2500);
			}
	    else if(CurrURL.contains("--byjusuat")) {
			payLoad_Whitehatjr.SessionMissedResponse_UAT();
		    Thread.sleep(2500);
			}
			else if(CurrURL.contains("--byjusqa")) {
				payLoad_Whitehatjr.SessionMissedResponse_QA();
			    Thread.sleep(2500);
				}
			else {
				payLoad_Whitehatjr.SessionMissedResponse_Prod();
			    Thread.sleep(2500);
				}
		  
	    Thread.sleep(3500);
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
			  payLoad_Whitehatjr.SessionFeedbackResponse_UATFC(); 
			  Thread.sleep(2500);
		  }
			  else if(CurrURL.contains("--byjusuat")) {
		  payLoad_Whitehatjr.SessionFeedbackResponse_UAT(); 
		  Thread.sleep(2500); } 
			  else  if(CurrURL.contains("--byjusqa")) {
		  payLoad_Whitehatjr.SessionFeedbackResponse_QA(); 
		  Thread.sleep(2500); 
		  } 
			  else {
		  payLoad_Whitehatjr.SessionFeedbackResponse_Prod(); 
		  Thread.sleep(2500); 
		  }
		  Thread.sleep(3000);
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
		  
		  
		 // Assert.assertEquals(SessionFeedback, "Session Feedback");
		  
		  log.info("Deleting the created Session Information");
		  if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) { 
			  //si.DeleteSessionInfoCreated(); 
			  si.DeleteSessionInfolastbtn();
			  } else 
			  {
		  si.DeleteSessionInfolastbtn(); 
		  }
		 
		  if(CurrURL.contains("--byjusuatfc") || CurrURL.contains("byjusprod.") ) {
				//ac.ClickAccOwnrTab();
				//ac.CloseSubTabs();
				}
		
		log.info("Deleting the Student Program details");
		//ac.ClickAccOwnrTab();
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

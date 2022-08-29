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

import pageObjects.ExamDetailsPO;
import pageObjects.InboundTaskPO;
import pageObjects.NewCaseRecordTypePO;
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



public class test_Retention_SFTNL_5947 extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_Retention_SFTNL_5947.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	ArrayList<String> al3 = new ArrayList<String>();
	static Faker faker = new Faker();
	static String firstName = faker.name().firstName();
	static String lastName = faker.name().lastName();
	static int randomNum = ThreadLocalRandom.current().nextInt(10000000, 100000000 + 1);
	
	@BeforeMethod(alwaysRun = true)
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

	}
	
	
	@Test(groups = {"sanity", "UAT" }, enabled = true)
	public void TestSFTNL_5497() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		al3 = excelData.getData("TC2", "RefundProcess", "Tcid");
		
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "BLC", "Tcid");
			al2 = excelData.getData("BLC User UATFC", "Login", "Type");
			log.info("Logging in as Admin to UATFC Env then switching user to BLC");
			lo.LoginAsAdmin_UATFC();
			//lo.SwitchUser(al2.get(1));
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "BLC", "Tcid");
		al2 = excelData.getData("BLC User UAT", "Login", "Type");
		
		log.info("Logging in as Admin to UAT then switching user to BLC");
		lo.LoginAsAdmin_UAT();
		//lo.SwitchUser(al2.get(1));
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_BTLA.AccountidCreationResponse_UAT();
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
		ac.NavBackToAccount();
		String AccountURL = CurrURL+Accountid;
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		
		if(CurrURL.contains("--byjusuat")||CurrURL.contains("--byjusuatfc")) {
			String AccountOwner= ac.AccOwnerCheck();
			if(!al2.get(1).equalsIgnoreCase(AccountOwner)) {
				ac.AssignAccount(al2.get(1));
			}
		}
		else {
			ac.AssignAccount("Testing User");
		}
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		lo.SwitchUser(al2.get(1));
		ac.closeTabWindows();
		ac.Notification();
		ac.NavBackToAccount();
		driver.get(AccountURL);
		Thread.sleep(5000);
		}
		else if(CurrURL.contains("--byjusuatfc")) {
			lo.SwitchUser(al2.get(1));
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
		}
		else {
			lo.SwitchUsernProfile_Prod("BLC Counsellor");
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
		}

		//Creating a Inbound task
		log.info("Creating Inbound Task");
		ac.Scrollpagedown();
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
		  
		ibdt.ClickProceedOptn();
		ibdt.SelectPSTCT(al.get(1));
		ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		//ncrt.ClickSave();
		//Thread.sleep(4000);
		
		ibdt.SelectDevice(al3.get(3));
		
		ibdt.SelectCategory(al3.get(4));
		
		
		ibdt.SelectPSTRR(al3.get(5));
		//ibdt.SelectPSCOYR(al.get(5));
		ibdt.VerifyAllValinPSSTRR(11);
		ibdt.SelectPSTRR(al3.get(10));
		ibdt.VerifyAllValinPSSTRR(12);
		ibdt.SelectPSTRR(al3.get(5));
		ibdt.SelectPSSTRR(al3.get(6));

		//ibdt.SelectPSTRR2(al.get(6));
		ibdt.EnterComments(al3.get(7));
		ibdt.SelectIsTICR(al3.get(8));

		ibdt.SelectPSTO();
		Thread.sleep(1000);
		ibdt.ClickNext();
		Thread.sleep(3000);
		
		

		ibdt.NavBackAccount();
		ac.CloseSubTabs();
		
		lo.Logouthome();
		
		ac.closeTabWindows();
		driver.get(AccountURL);
		Thread.sleep(3000);
		
		ac.ClickCasesMC2();

		CasesPO cases= new CasesPO(driver);
		
		String CaseNumber= cases.CaseRN();
		log.info("The case number created is: "+CaseNumber);
		cases.CloseAllCases();
		
		
		
		
		
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

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
import payLoad.payLoad_BTLA2;
import resources.ExcelData;
import resources.base;



public class test_ButtonCheckMentor extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_ButtonCheckMentor.class.getName());
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
	public void TestButtonMentor() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		String EnvUAT="src\\main\\java\\testData\\ButtonCheckMacro_UAT.xlsm";
		String EnvUATFC="src\\main\\java\\testData\\ButtonCheckMacro_UATFC.xlsm";
		String EnvProd="src\\main\\java\\testData\\ButtonCheckMacro_Prod.xlsm";
		
		al = excelData.getData("TC1", "RefundProcess", "Tcid");
		if(CurrURL.contains("--byjusuatfc")) {
			al2 = excelData.getData("Mentor User UATFC", "Login", "Type");
			log.info("Logging in as Admin to UATFC Env then switching user to PE");
			lo.LoginAsAdmin_UATFC();
			//lo.SwitchUser(al2.get(1));
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA2.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else if(CurrURL.contains("--byjusuat")) {
		al2 = excelData.getData("Mentor User UAT", "Login", "Type");
		
		log.info("Logging in as Admin to UAT then switching user to PE");
		lo.LoginAsAdmin_UAT();
		//lo.SwitchUser(al2.get(1));
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_BTLA2.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else {
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			//lo.SwitchUsernProfile_Prod("Mentor");
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA2.AccountidCreationResponse_Prod();
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
		ac.CheckButtonsQAandSM(EnvUAT,"Account_Mentor");
		ac.CheckRelatedList(EnvUAT,"Account_Mentor");
		}
		else if(CurrURL.contains("--byjusuatfc")) {
			lo.SwitchUser(al2.get(1));
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
			ac.CheckButtonsQAandSM(EnvUATFC,"Account_Mentor");
			ac.CheckRelatedList(EnvUATFC,"Account_Mentor");
		}
		else {
			lo.SwitchUsernProfile_Prod("Mentor");
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
			ac.CheckButtonsQAandSM(EnvProd,"Account_Mentor");
			ac.CheckRelatedList(EnvProd,"Account_Mentor");
		}
		
		ac.Scrollhome();
		
		log.info("Creating Inbound Task");
		ac.ClickOpenActivitiestoNewTask();
		
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver);
		
		ncrt.SelectCaseRecordTypeInbound();
		ncrt.ClickNext();
		Thread.sleep(2000);
		ncrt.ClickSave();
		Thread.sleep(2000);
		
		InboundTaskPO ibdt= new InboundTaskPO(driver);
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			ibdt.CheckTaskButtonsQAandSM(EnvUAT,"Task_Mentor");
			}
			else if(CurrURL.contains("--byjusuatfc")){
				ibdt.CheckTaskButtonsQAandSM(EnvUATFC,"Task_Mentor");
			}
			else {
				ibdt.CheckTaskButtonsQAandSM(EnvProd,"Task_Mentor");
			}
			
		ibdt.ClickCaptureDetail();
		Thread.sleep(3000);		
		  
		ibdt.ClickProceedOptn();
		ibdt.SelectPSTCT(al.get(1));
		ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		
		ibdt.SelectDevice(al.get(3));
		ibdt.SelectCategory(al.get(4));
		ibdt.SelectPSTRR(al.get(5));
		ibdt.SelectPSSTRR(al.get(6));
		ibdt.EnterComments(al.get(7));
		ibdt.SelectIsTICR(al.get(8));
		
		ibdt.SelectPSTO();
		Thread.sleep(1000);
		ibdt.ClickNext();
		Thread.sleep(3000);
		ibdt.NavBackAccount();
		ac.closeTabWindows();
		
		//Verifying all the Case list view buttons
		CasesPO cases=new CasesPO(driver);
		cases.NavMenuClick();
		cases.CaseNavMenuClick();
		cases.RecentlyViewedlist();
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ac.CheckCaseListViewButtons(EnvUAT,"Case_Mentor");
		}
		else if(CurrURL.contains("--byjusuatfc")){
			ac.CheckCaseListViewButtons(EnvUATFC,"Case_Mentor");
		}
		else {
			ac.CheckCaseListViewButtons(EnvProd,"Case_Mentor");
		}
		
		lo.Logouthome();
		
		ac.closeTabWindows();
		driver.get(AccountURL);
		Thread.sleep(3000);
		
		//Deleting the created case
		ac.ClickCasesMC2();
		String CaseNumber= cases.CaseRN();
		log.info("The case number created is: "+CaseNumber);
		cases.CloseAllCases();
		
		log.info("Deleting the Student Program details");
		ac.ClickAccOwnrTab();
		ac.DeleteCreatedStuProg();
		ac.Notification();
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

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
import pageObjects.ExamDetailsPO;
import pageObjects.NewCaseDetailsPO;
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



public class test_ButtonCheckRetentionManager extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_ButtonCheckRetentionManager.class.getName());
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
	public void TestButtonRetentionManager() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		String EnvUAT="src\\main\\java\\testData\\ButtonCheckMacro_UAT.xlsm";
		String EnvUATFC="src\\main\\java\\testData\\ButtonCheckMacro_UATFC.xlsm";
		String EnvProd="src\\main\\java\\testData\\ButtonCheckMacro_Prod.xlsm";
		al = excelData.getData("TC2", "LSPDCase", "Tcid");
		if(CurrURL.contains("--byjusuatfc")) {

			al2 = excelData.getData("RetentionManager User UATFC", "Login", "Type");
			log.info("Logging in as Admin to UATFC Env then switching user to Retention Manager");
			lo.LoginAsAdmin_UATFC();
			//lo.SwitchUser(al2.get(1));
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA2.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else if(CurrURL.contains("--byjusuat")) {

		al2 = excelData.getData("RetentionManager User UAT", "Login", "Type");
		
		log.info("Logging in as Admin to UAT then switching user to Retention Manager");
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
		ac.CheckButtonsQAandSM(EnvUAT,"Account_RetentionM");
		ac.CheckRelatedList(EnvUAT,"Account_RetentionM");
		}
		else if(CurrURL.contains("--byjusuatfc")) {
			lo.SwitchUser(al2.get(1));
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
			ac.CheckButtonsQAandSM(EnvUATFC,"Account_RetentionM");
			ac.CheckRelatedList(EnvUATFC,"Account_RetentionM");
		}
		else {
			lo.SwitchUsernProfile_Prod("Retention Manager");
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
			ac.CheckButtonsQAandSM(EnvProd,"Account_RetentionM");
			ac.CheckRelatedList(EnvProd,"Account_RetentionM");
		}
		
		ac.Scrollhome();
		
		//Creating Retention Refund Case
		CasesPO cases=new CasesPO(driver);
		ac.ClickCasesMC2();
		cases.NewCaseInAccountClick();
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver);
		ncrt.SelectCaseRecordType("Retention Refund Request");
		ncrt.ClickNext();
		
		NewCaseDetailsPO ncd= new NewCaseDetailsPO(driver);
		ncd.EnterSubject2(al.get(1));
		ncd.EnterStatus(al.get(2));
		ncd.SelectReasonForRefund(al.get(12));
		if(CurrURL.contains("--byjusuatfc")||CurrURL.contains("byjusprod.")) {
		ncd.EnterOrders(al.get(13));
		}
		ncd.ClickSave();
		
		CreatedCaseRecordPO ccr= new CreatedCaseRecordPO(driver);
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			ccr.CheckCreatedCaseButtonsQAandSM(EnvUAT,"Case_RetentionM");
		}
		else if(CurrURL.contains("--byjusuatfc")){
			ccr.CheckCreatedCaseButtonsQAandSM(EnvUATFC,"Case_RetentionM");
		}
		else {
			ccr.CheckCreatedCaseButtonsQAandSM(EnvProd,"Case_RetentionM");
		}
		
		ac.closeTabWindows();
		
		//Verifying all the Case list view buttons
		
		cases.NavMenuClick();
		cases.CaseNavMenuClick();
		cases.RecentlyViewedlist();
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ac.CheckCaseListViewButtons(EnvUAT,"Case_RetentionM");
		}
		else if(CurrURL.contains("--byjusuatfc")){
			ac.CheckCaseListViewButtons(EnvUATFC,"Case_RetentionM");
		}
		else {
			ac.CheckCaseListViewButtons(EnvProd,"Case_RetentionM");
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

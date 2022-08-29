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



public class test_ButtonCheckPS extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_ButtonCheckPS.class.getName());
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
	public void TestButtonPS() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		String EnvUAT="src\\main\\java\\testData\\ButtonCheckMacro_UAT.xlsm";
		String EnvUATFC="src\\main\\java\\testData\\ButtonCheckMacro_UATFC.xlsm";
		String EnvProd="src\\main\\java\\testData\\ButtonCheckMacro_Prod.xlsm";
		
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "BLC", "Tcid");
			al2 = excelData.getData("PS User UATFC", "Login", "Type");
			log.info("Logging in as Admin to UATFC Env then switching user to PS");
			lo.LoginAsAdmin_UATFC();
			//lo.SwitchUser(al2.get(1));
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA2.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "BLC", "Tcid");
		al2 = excelData.getData("PS User UAT", "Login", "Type");
		
		log.info("Logging in as Admin to UAT then switching user to PS");
		lo.LoginAsAdmin_UAT();
		//lo.SwitchUser(al2.get(1));
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_BTLA2.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else {
			al = excelData.getData("TC1", "BLC", "Tcid");
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
		ac.CheckButtonsQAandSM(EnvUAT,"Account_PS");
		ac.CheckRelatedList(EnvUAT,"Account_PS");
		}
		else if(CurrURL.contains("--byjusuatfc")) {
			lo.SwitchUser(al2.get(1));
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
			ac.CheckButtonsQAandSM(EnvUATFC,"Account_PS");
			ac.CheckRelatedList(EnvUATFC,"Account_PS");
		}
		else {
			lo.SwitchUsernProfile_Prod("Process Specialist");
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
			ac.CheckButtonsQAandSM(EnvProd,"Account_PS");
			ac.CheckRelatedList(EnvProd,"Account_PS");
		}


		//Verifying all the Case list view buttons
		CasesPO cases=new CasesPO(driver);
		cases.NavMenuClick();
		cases.CaseNavMenuClick();
		cases.RecentlyViewedlist();
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ac.CheckCaseListViewButtons(EnvUAT,"Case_PS");
		}
		else if(CurrURL.contains("--byjusuatfc")){
			ac.CheckCaseListViewButtons(EnvUATFC,"Case_PS");
		}
		else {
			ac.CheckCaseListViewButtons(EnvProd,"Case_PS");
		}
		
		lo.Logouthome();
		
		ac.closeTabWindows();
		driver.get(AccountURL);
		Thread.sleep(3000);
		
		
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

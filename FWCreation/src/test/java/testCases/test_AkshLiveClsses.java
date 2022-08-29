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
import pageObjects.NewCaseRecordTypePO;
import pageObjects.SDCTaskPO;
import pageObjects.loginPO;
import payLoad.payLoad_AakashLive;

import resources.ExcelData;
import resources.base;



public class test_AkshLiveClsses extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_AkshLiveClsses.class.getName());
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
	

	@Test(priority = 1,  groups = {"sanity", "Regression" },enabled = true)
	public void TestAkashLive() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "AakashLive", "Tcid");
			
			
			log.info("Logging in as Admin to UATFC");
			lo.LoginAsAdmin_UATFC();
			lo.SwitchUser("pujari");
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_AakashLive.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
			
			}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "AakashLive", "Tcid");
		
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		lo.SwitchUser("SF_Byjus Admin");
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_AakashLive.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "AakashLive", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_AakashLive.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "AakashLive", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_AakashLive.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		CreatedAccountPO ca1= new CreatedAccountPO(driver);
		ca1.Notification();
		ca1.NavBackToAccount();
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		//Click on Capture call details
		log.info("Creating SDC Task");
		
		ca1.ClickOpenActiDD_Prod();
		ca1.ClickNewTask();
		
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver);
		
		ncrt.SelectCaseRecordTypeSDC();
		ncrt.ClickNext();
		Thread.sleep(2000);
		ncrt.EnterSubject_SDC(al.get(1));
		//ncrt.ClickSave();
		Thread.sleep(1000);

		
		SDCTaskPO sdc=new SDCTaskPO(driver);
		sdc.ClickCaptureDetails();
		sdc.SelectATCDCasN();
		
		//Verify Correct details Captured flow
		log.info("Verify Correct details flow for customer details as No");
		
		sdc.SelectAddrandGrade();
		Assert.assertTrue(sdc.VisStreet().isDisplayed());
		Assert.assertTrue(sdc.VisCity().isDisplayed());
		Assert.assertTrue(sdc.VisState().isDisplayed());
		Assert.assertTrue(sdc.VisZip().isDisplayed());
		Assert.assertTrue(sdc.VisCountry().isDisplayed());
		//crollend();
		Assert.assertTrue(sdc.VisGrade().isDisplayed());
		
		//Verify SDC Pitched flow
		log.info("Verify Correct details flow for customer details as Yes");
		sdc.SelectATCDCasY();
		sdc.SelectvalDiscus(al.get(2));
		
		//Setting Did customer agree as No
		log.info("Setting did customer agree as No");
		sdc.SelectDCAasN();
		Assert.assertTrue(sdc.VisResnCD().isDisplayed());
		Assert.assertTrue(sdc.VisComments().isDisplayed());
		
		//Setting Did customer agree as Will decide and confirm
		log.info("Setting did customer agree as Will decide and confirm");
		sdc.SelectDCAasWillDcde();
		Assert.assertTrue(sdc.VisTentveDate().isDisplayed());
		
		//Setting Did customer agree as Yes
		log.info("Setting did customer agree as Yes");
		sdc.SelectDCAasY();
		sdc.SelectSubjects(al.get(3));
		Assert.assertTrue(sdc.VisPSPB().isDisplayed());
		Assert.assertTrue(sdc.VisBSD().isDisplayed());
		Assert.assertTrue(sdc.VisNOBA().isDisplayed());
		
		sdc.SelectPB();
		sdc.EnterSD();
		sdc.EnterBatchAss(al.get(4));
		//Assert.assertTrue(false);
		sdc.ClickFinish();
		//ca1.CloseCurrentSubTab();
		sdc.NavBackAccount();
		
		ca1.Scrollhome();
		
		log.info("Deleting the Student Program details");
		ca1.DeleteCreatedStuProg();
		
		ca1.NavBackToAccount();
		log.info("Deleting the Account details");
		ca1.DeleteAccountCreated(ca1.CaptureAccOwnrNam());
	}
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
	
	
}

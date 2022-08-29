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
import payLoad.payLoad_RefundProcess;
import resources.ExcelData;
import resources.base;



public class test_RefundProcessReq extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_RefundProcessReq.class.getName());
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
	public void TestRefundProcessReq() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "RefundProcess", "Tcid");
			
			
			log.info("Logging in as Admin to UATFC");
			lo.LoginAsAdmin_UATFC();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_RefundProcess.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
			
			}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "RefundProcess", "Tcid");
		
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_RefundProcess.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "RefundProcess", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_RefundProcess.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "RefundProcess", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_RefundProcess.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		CreatedAccountPO ca1= new CreatedAccountPO(driver);
		ca1.AllNotificationRead();
		ca1.NavBackToAccount();
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		String OwnerName=ca1.CaptureAccOwnrNam();
		ca1.Scrollpagedown();
		
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
		ibdt.SelectSpokeTo(al.get(9));
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
		
		if(CurrURL.contains("--byjusuatfc")||CurrURL.contains("byjusprod.")) {
		//ca1.CloseCurrentSubTab();
		}
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ibdt.NavBackAccount();
		}
		else {
			//ca1.CloseCurrentSubTab();
			ibdt.NavBackAccount();
		}
		ca1.CloseSubTabs();
		
		ca1.ClickCasesMC2();

		CasesPO cases= new CasesPO(driver);
		
		String CaseNumber= cases.CaseRN();
		log.info("The case number created is: "+CaseNumber);
		//cases.CaseOptnSel();
		//Assert.assertTrue(false);
		cases.CloseAllCases();
		
		log.info("Deleting the Student Program details");
		ca1.ClickAccOwnrTab();
		ca1.DeleteCreatedStuProg();
		ca1.NavBackToAccount();
		log.info("Deleting the Account created details");
		ca1.DeleteAccountCreated(ca1.CaptureAccOwnrNam());

		if(CurrURL.contains("--byjusuatfc")||CurrURL.contains("byjusprod.")) {
		log.info("Deleting the Cases present as My Cases");
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

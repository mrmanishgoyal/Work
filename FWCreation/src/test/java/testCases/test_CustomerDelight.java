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

import pageObjects.NewCaseDetailsPO;
import pageObjects.NewCaseRecordTypePO;

import pageObjects.loginPO;
import payLoad.payLoad_NEOL2Queue;
import resources.ExcelData;
import resources.base;



public class test_CustomerDelight extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_CustomerDelight.class.getName());
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
	public void CustomerDelight() throws Exception {
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "CollectionFlow", "Tcid");
			al2 = excelData.getData("Collection Assistant", "Login", "Type");
			
			log.info("Logging in as Admin to UATFC");
			lo.LoginAsAdmin_UATFC();
			
			}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "CollectionFlow", "Tcid");
		al2 = excelData.getData("Collection Assistant", "Login", "Type");
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		
		}
		else if (CurrURL.contains("--byjusqa")) {
						
			log.info("Logging in as Admin to QA");
			lo.LoginAsAdmin_QA();
		}
		
		else {
			al = excelData.getData("TC1", "CollectionFlow", "Tcid");
			al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
		}

		closeTabWindows();
		
		CreatedAccountPO ac=new CreatedAccountPO(driver);
		CasesPO cases=new CasesPO(driver);
		ac.Notification();
		cases.NavMenuClick();
		cases.CaseNavMenuClick();
		cases.NewCaseClick();
		
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver);
		ncrt.SelectCaseRecordType("Case From Email");
		ncrt.ClickNext();
		
		NewCaseDetailsPO ncd= new NewCaseDetailsPO(driver);
		ncrt.SelectCaseStatusClosed();
		ncd.ClickSave();
		
		CreatedCaseRecordPO ccr= new CreatedCaseRecordPO(driver);
		String CaseNumber = ccr.CaseNumberCreated();
		
		log.info("The Case created is: "+CaseNumber);
		
		ccr.CaseOwnrUpdate();
		//ccr.ClickSave();
		
		
		cases.NavMenuClick();
		cases.CaseNavMenuClick();
		
		
		cases.NavNEOL2QueueProd();
		
		cases.AddFilterStatus();
		cases.AddFilterOwnerName();
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			cases.AddFilterDateTimeOpen_UAT();
		}
		else {
		cases.AddFilterDateTimeOpen();
		}
		cases.AddFilterParentCaseNo();
		cases.ClickSave();
		String[] RecordCount= cases.RecrdCnt();
		Thread.sleep(1500);
		int i=0;
		try {
		i = Integer.parseInt(RecordCount[0]);
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
		}
		//Thread.sleep(1000);
		int CaseCountPayload=0;
		if(CurrURL.contains("--byjusuatfc")) {
		log.info("Submitting the NEO L2 Queue count extraction payload");
		CaseCountPayload=payLoad_NEOL2Queue.NEOL2CaseCount_UATFC();
		log.info("Case Count from Payload is: "+CaseCountPayload);
		Thread.sleep(2000);
		}
		else if(CurrURL.contains("--byjusuat")) {
		log.info("Submitting the NEO L2 Queue count extraction payload");
		CaseCountPayload=payLoad_NEOL2Queue.NEOL2CaseCount_UAT();
		log.info("Case Count from Payload is: "+CaseCountPayload);
		Thread.sleep(2000);
		}
		else if(CurrURL.contains("--byjusqa"))
		{
			log.info("Submitting the NEO L2 Queue count extraction payload");
			CaseCountPayload=payLoad_NEOL2Queue.NEOL2CaseCount_QA();
			log.info("Case Count from Payload is: "+CaseCountPayload);
			Thread.sleep(2000);
			
		}
		else {
			
				log.info("Submitting the NEO L2 Queue count extraction payload");
				CaseCountPayload=payLoad_NEOL2Queue.NEOL2CaseCount_Prod();
				log.info("Case Count from Payload is: "+CaseCountPayload);
				Thread.sleep(2000);
			
		}
		
		Thread.sleep(1200);
		//Verifying the Queue count is matching
		Assert.assertEquals(CaseCountPayload, i);
		
		log.info("Deleting the created Case record "+CaseNumber);
		cases.DeleteCaseRecord(CaseNumber);
		cases.RemveFlterAdded();
		
	}
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
	
	
}

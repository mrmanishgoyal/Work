package testCases;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import org.testng.Assert;
import pageObjects.loginPO;
import payLoad.payLoad_BTLA;
import resources.ExcelData;
import resources.base;



public class test_StudentOrderCreation extends base {

	
	public static Logger log = LogManager.getLogger(test_StudentOrderCreation.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	
	@BeforeMethod(alwaysRun = true)
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

	}
	

	@Test(priority = 0,  groups = {"sanity", "UAT" }, enabled = true)
	public void LoginToCreatedAccount_UAT() throws Exception {
		String Accountid;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuat")) {
	
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_BTLA.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if (CurrURL.contains("--byjusqa")) {

			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {

			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		closeTabWindows();
		
		//log.info("Submitting the Account creation payload");
		//String Accountid=payLoad_BTLA.AccountidCreationResponse_QA();
		//log.info("Launching the newly created Account id "+Accountid);
		
		if(Accountid!= null) {
		Assert.assertTrue(true);
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		}
		
		//payLoad_BTLA.SessionAttendedResponse_UAT();
		//payLoad_BTLA.SessionMissedResponse_UAT();
		//payLoad_BTLA.SessionFeedbackResponse_UAT();
		
		driver.get(driver.getCurrentUrl());
		
	}
		
		@Test(priority = 2,  groups = {"sanity", "Prod" }, enabled = false)
		public void LoginToCreatedAccount_Prod() throws Exception {
			//driver.get(prop.getProperty("urlprod"));
			log.info("Logging in as Admin");
			loginPO lo=new loginPO(driver);
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			String Accountid=payLoad_BTLA.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
			
			if(Accountid!= null) {
			Assert.assertTrue(true);
			driver.get(CurrURL+Accountid);
			Thread.sleep(5000);
			}
		}
		
	
	
	@AfterMethod(alwaysRun = true)
	public void teardown() throws InterruptedException {
		//Thread.sleep(2000);
		//driver.quit();
	}
	
	
}

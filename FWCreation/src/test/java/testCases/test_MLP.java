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
import pageObjects.WSOnboardingCallTaskPO;
import pageObjects.WSWelcomeCallTaskPO;
import pageObjects.loginPO;
import payLoad.payLoad_MLP;

import resources.ExcelData;
import resources.base;

public class test_MLP extends base {

	public WebDriver driver;
	// public String CurrURL;
	public static Logger log = LogManager.getLogger(test_MLP.class.getName());
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
	public void TestMLP() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
		al = excelData.getData("TC1", "MLP", "Tcid");
		//al2 = excelData.getData("Collection Assistant", "Login", "Type");
		
		log.info("Logging in as Admin to UATFC");
		lo.LoginAsAdmin_UATFC();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_MLP.AccountidCreationResponse_UATFC();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "MLP", "Tcid");
		//al2 = excelData.getData("Collection Assistant", "Login", "Type");
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_MLP.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "MLP", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_MLP.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "MLP", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_MLP.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		
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
		if(CurrURL.contains("--byjusuat")) {
			
			Assert.assertTrue(ac.CheckVisSalesStudentOrderid());
			ac.CaptureStudentSalesOrder();	
		}
		else {
		Assert.assertTrue(ac.CheckVisStudentOrderid());
		ac.CaptureStudentOrder();
		ac.CaptureStudentSalesOrder();
		//ac.CaptureStudentOrderIndvid();
		}
		Assert.assertTrue(ac.CheckVisStudentPayid());
		ac.CaptureAllStudentPayid();
		ac.CheckVisStudentPrgid();
		ac.CaptureAllStudentProgDetails();
		
		//Verify  MLP  program is created in student program related list
		String Progval = ac.CapturePrgCreated();
		Assert.assertEquals(Progval, "Neo Classes Mini Learning Program");
		
		//Verify Account super status and status is Product Trial and Trial New
		
		String SuperStatus = ac.CaptureSuperStatus();
		Assert.assertTrue(SuperStatus.equalsIgnoreCase("Product Trial"));
		
		String Status = ac.CaptureStatus();
		Assert.assertEquals(Status, "Trial New");
		
		//Verify the Workshop Welcome call is created
		Assert.assertTrue(ac.CheckVisWSWelcomeCall());
		ac.Scrolldown();
		
		log.info("Navigating to Workshop - Welcome Call tasks");
		ac.ClickWSWelcomeCall();
		
		WSWelcomeCallTaskPO wswc= new WSWelcomeCallTaskPO(driver);
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		wswc.ClickCaptureDetail();
		}
		else {
			wswc.ClickCaptureDetail_Prod();	
		}
		wswc.SelectProceed();
		wswc.SelectST(al.get(1));
		wswc.SelectCAATP(al.get(2));
		wswc.SelectIALWSD(al.get(3));
		wswc.SelectEAP(al.get(4));
		wswc.SelectITAI(al.get(5));
		wswc.SelectCS(al.get(6));
		wswc.SelectITIR(al.get(7));
		wswc.NavBackAccount();
		ac.CloseSubTabs();
		ac.RefreshTab();
		Thread.sleep(1500);
		
		//Verify Superstatus is not changed and Status is moved to Trial Onboarded
		Assert.assertTrue(SuperStatus.equalsIgnoreCase("Product Trial"));
		Assert.assertEquals(ac.CaptureStatus(), "Trial Onboarded");
		
		ac.Scrollpagedown();
		//Verify the Workshop Onboarding call is created
		Assert.assertTrue(ac.CheckVisWSOnboardingCall());
		
		log.info("Navigating to Workshop - Onboarding Cal tasks");
		ac.ClickWSOnboardngCall();
		
		WSOnboardingCallTaskPO weoc=new WSOnboardingCallTaskPO(driver);
		weoc.ClickCaptureDetail();
		weoc.SelectProceed();
		weoc.SelectITMSDID(al.get(8));
		weoc.SelectIAPPI(al.get(9));
		weoc.SelectCS(al.get(10));
		weoc.SelectHTTBD(al.get(11));
		weoc.SelectDYRTCPL(al.get(12));
		weoc.SelectWSAC(al.get(13));
		weoc.SelectITAI(al.get(14));
		weoc.NavBackAccount();
		ac.RefreshTab();
		
		
		//Verify Superstatus is not changed and Status is moved to Trial Onboarded
		Assert.assertTrue(SuperStatus.equalsIgnoreCase("Product Trial"));
		Assert.assertEquals(ac.CaptureStatus(), "Trial Onboarded");
		
		log.info("Deleting the Student Program details");
		ac.ClickAccOwnrTab();
		ac.DeleteCreatedStuProg();
		ac.NavBackToAccount();
		log.info("Deleting the Account created details");
		ac.DeleteAccountCreated(ac.CaptureAccOwnrNam());
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() throws InterruptedException {

		driver.quit();

		// Thread.sleep(2000);
	}

}

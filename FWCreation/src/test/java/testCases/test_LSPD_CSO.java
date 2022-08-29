package testCases;

import java.io.IOException;

import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;


import com.github.javafaker.Faker;

import org.testng.Assert;


import pageObjects.CasesPO;

import pageObjects.CreatedAccountPO;
import pageObjects.InboundTaskPO;

import pageObjects.NewCaseDetailsPO;
import pageObjects.NewCaseRecordTypePO;

import pageObjects.loginPO;
import payLoad.payLoad_LSPD_CSO;

import resources.ExcelData;
import resources.base;



public class test_LSPD_CSO extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_LSPD_CSO.class.getName());
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
	public void TestLSPD_CSO() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "LSPD-CSO", "Tcid");
			al2 = excelData.getData("Admin", "Login", "Type");
			
			log.info("Logging in as Admin to UATFC");
			lo.LoginAsAdmin_UATFC();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_LSPD_CSO.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
			
			}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "LSPD-CSO", "Tcid");
		al2 = excelData.getData("Admin", "Login", "Type");
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_LSPD_CSO.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "LSPD-CSO", "Tcid");
			al2 = excelData.getData("AdminQA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_LSPD_CSO.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "LSPD-CSO", "Tcid");
			al2 = excelData.getData("AdminProd", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_LSPD_CSO.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		CreatedAccountPO ca1= new CreatedAccountPO(driver);
		ca1.Notification();
		ca1.NavBackToAccount();
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		ca1.Scrollpagedown();
		
		//Assert.assertTrue(false);
		log.info("Creating Inbound Task");

		String PremiumID = ca1.CapturePremiumID();
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
		ibdt.SelectSpokeTo(al.get(13));
		}
		ibdt.SelectPSTCT(al.get(1));
		ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		ibdt.SelectDevice(al.get(3));
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ibdt.SelectCategory(al.get(4));
		ibdt.SelectSYLI(al.get(6));
		ibdt.SelectSubCategory(al.get(7));
		ibdt.SelectPSYIST(al.get(9));

		//ibdt.SelectITGCorV(al.get(10));// Removed in SFDC-2263
		//ibdt.SelectIRNorSA(al.get(11));// Removed in SFDC-2263
		
		ibdt.SelectITIRC(al.get(12));
		
		
		ibdt.ClickNext();
		Thread.sleep(2000);
		}
		else {
			ibdt.SelectCategory(al.get(4));
			//ibdt.SelectPSYICategory(al.get(5));
			ibdt.SelectSYLI(al.get(6));
			ibdt.SelectSubCategory(al.get(7));
			//ibdt.SelectPSYI(al.get(8));
			ibdt.SelectPSYIST("SD Card(FOC/Locked)");
			ibdt.SelectITGCorV(al.get(10));
			ibdt.SelectIRNorSA(al.get(11));
			ibdt.SelectITIRC(al.get(12));			
			ibdt.ClickNext();
			Thread.sleep(2000);
		}
		
		ibdt.NavBackAccount();
		ca1.CloseSubTabs();
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			ca1.ClickCasesMC2();
		}
		else {
		ca1.ClickCasesMC();
		}
		CasesPO cases= new CasesPO(driver);
		
		String CaseNumber= cases.CaseRN();
		log.info("The case number created is: "+CaseNumber);
		cases.CaseOptnSel();
		
		NewCaseDetailsPO ncd= new NewCaseDetailsPO(driver);
		//Assert.assertFalse(true);
		String MailName = MailName(al2.get(1));
		String Mainwin= driver.getWindowHandle();
		((JavascriptExecutor) driver).executeScript("window.open()");
		switchWindow();
		
		cases.OpenURL(MailName, CaseNumber, PremiumID);
	    String PageLaunch= cases.PageLaunch();
	    Assert.assertEquals(PageLaunch, "Create Customer Service Request");
		  
        driver.switchTo().window(Mainwin);
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		//ncd.ClickDeleteonCase();
		//ncd.ClickDeleteToComplete();
		cases.DeleteCCaseRecord(CaseNumber);
		}
		else {
			cases.DeleteCCaseRecord(CaseNumber);
		}
		CreatedAccountPO ac1=new CreatedAccountPO(driver);
		ac1.ClickAccOwnrTab();
		Thread.sleep(1000);
		log.info("Deleting the Student Program details");
		ac1.DeleteCreatedStuProg();
		
		ac1.NavBackToAccount();
		log.info("Deleting the Account details");
		ac1.DeleteAccountCreated(ac1.CaptureAccOwnrNam());
		
		if(CurrURL.contains("--byjusuatfc")||CurrURL.contains("byjusprod.")) {
		log.info("Deleting the Cases present as My Cases");
		cases.NavMenuClick();
		cases.CaseNavMenuClick();
		cases.NavNEOL2QueueProd();
		Thread.sleep(3000);
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

package testCases;

import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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



public class test_IssueTree_PE extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_IssueTree_PE.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	static Faker faker = new Faker();
	static String firstName = faker.name().firstName();
	static String lastName = faker.name().lastName();
	static int randomNum = ThreadLocalRandom.current().nextInt(10000000, 100000000 + 1);
	static int i=0;
	
	@BeforeMethod(alwaysRun = true)
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

	}
	
	
	@Test(dataProvider = "Datapro",groups = {"sanity", "UAT" }, enabled = true)
	public void Test_IT_Tech_LS_Tech(String PSTCT,String Device,String Category,String SCategory,String PSYIST,String ITIRC,String PSTIR) throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);

		al = excelData.getData("TC1", "RefundProcess", "Tcid");
		if(CurrURL.contains("--byjusuatfc")) {
			
			al2 = excelData.getData("PE User UATFC", "Login", "Type");
			log.info("Logging in as Admin to UATFC Env then switching user to PE");
			lo.LoginAsAdmin_UATFC();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA2.AccountidCreationResponse_UATFC();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else if(CurrURL.contains("--byjusuat")) {
		
		al2 = excelData.getData("PE User UAT", "Login", "Type");
		
		log.info("Logging in as Admin to UAT then switching user to PE");
		lo.LoginAsAdmin_UAT();
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
		}
		else if(CurrURL.contains("--byjusuatfc")){
			lo.SwitchUser(al2.get(1));
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
		}
		else {
			lo.SwitchUsernProfile_Prod("PE");
			ac.closeTabWindows();
			ac.Notification();
			ac.NavBackToAccount();
			driver.get(AccountURL);
			Thread.sleep(5000);
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
		ibdt.ClickCaptureDetail();
		Thread.sleep(3000);		
		  
		ibdt.ClickProceedOptn();
		ibdt.SelectPSTCT(PSTCT);
		//ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		
		ibdt.SelectDevice(Device);
		ibdt.SelectCategory(Category);
		ibdt.SelectSubCategory(SCategory);
		ibdt.SelectPSYIST(PSYIST);
		ibdt.SelectIsTICR(ITIRC);
		ibdt.SelectPSTIR(PSTIR);
		
		Thread.sleep(1000);
		ibdt.ClickNext();
		Thread.sleep(3000);
		
		//String PSTCT,String Device,String Category,String SCategory,String PSYIST,String ITIRC,String PSTIR
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_Tech_LS_Tech", i + 1, 0, Accountid);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_Tech_LS_Tech", i + 1, 1, PSTCT);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_Tech_LS_Tech", i + 1, 2, Device);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_Tech_LS_Tech", i + 1, 3, Category);		
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_Tech_LS_Tech", i + 1, 4, SCategory);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_Tech_LS_Tech", i + 1, 5, PSYIST);		
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_Tech_LS_Tech", i + 1, 6, ITIRC);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_Tech_LS_Tech", i + 1, 7, PSTIR);		
		i++;
		
	}
		
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  }
	 
		@DataProvider(name = "Datapro")
		public Object[][] testpro() throws Exception {
			Object[][] data = readData(System.getProperty("user.dir") + "\\src\\main\\java\\testData\\TestData.xlsx",
					"IT_Tech_LS_Tech");
			return data;
		}
	
	
}

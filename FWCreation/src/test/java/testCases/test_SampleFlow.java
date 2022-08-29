package testCases;


import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;


import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import org.testng.Assert;

import pageObjects.CapturePaymentDetailsEMIPO;
import pageObjects.CapturePaymtDetailsFPPO;
import pageObjects.CapturePaymtDetailsForeClosrePO;
import pageObjects.CapturePaymtDetailsPTPPO;
import pageObjects.CasesPO;
import pageObjects.CollectionAssistantLoginPO;
import pageObjects.CreatedAccountPO;
import pageObjects.CreatedCaseRecordPO;
import pageObjects.FirstConnectPO;
import pageObjects.FollowUpPTPPO;
import pageObjects.ForeClosurePO;
import pageObjects.InboundTaskPO;
import pageObjects.NPPaymentLoanPO;
import pageObjects.NewCaseDetailsPO;
import pageObjects.NewCaseRecordTypePO;
import pageObjects.NewPaymentPO;
import pageObjects.NewPaymentRecordPO;
import pageObjects.PaymentCaseQAPO;
import pageObjects.PaymentsPO;
import pageObjects.TasksPO;
import pageObjects.UserDetailPO;
import pageObjects.UserSetupPO;
import pageObjects.loginPO;
import payLoad.payLoad_BTLA;
import payLoad.payLoad_Sample;
import resources.ExcelData;
import resources.base;

public class test_SampleFlow extends base {

	public WebDriver driver;
	public static WebDriver driver1;
	public static WebDriver driver2;
	public static WebDriver driver3;
	//ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_SampleFlow.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	ArrayList<String> al3 = new ArrayList<String>();
	static Faker faker = new Faker();
	static String firstName = faker.name().firstName();
	static String lastName = faker.name().lastName();
	static int randomNum = ThreadLocalRandom.current().nextInt(10000000, 100000000 + 1);

	
	/*
	 * @BeforeTest public void startDocker() throws IOException,
	 * InterruptedException {
	 * 
	 * File fi=new File("output.txt"); fi.delete(); DockerrunStart s=new
	 * DockerrunStart(); s.Startfile(); }
	 */
	
	
	/*
	 * @BeforeMethod(alwaysRun = true) public void initialize() throws IOException,
	 * InterruptedException {
	 * 
	 * DesiredCapabilities cap=DesiredCapabilities.chrome(); URL u=new
	 * URL("http://localhost:4444/wd/hub"); RemoteWebDriver dr=new
	 * RemoteWebDriver(u,cap);
	 * 
	 * }
	 */
	 
	 

	@Test(priority = 1, enabled = true)
	public void test1() throws Exception {
		driver = initializeDriver();
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "RefundProcess", "Tcid");
		
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_Sample.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		
		closeTabWindows();
		CreatedAccountPO ca1= new CreatedAccountPO(driver);
		ca1.NavBackToAccount();
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		String OwnerName=ca1.CaptureAccOwnrNam();
	
		log.info("Creating Inbound Task");
		if(CurrURL.contains("--byjusuat")){
		ca1.ClickOpenActiDD();
		}
		else {
			ca1.ClickOpenActiDD_Prod();
		}
		Thread.sleep(800);
		ca1.ClickNewTask();
		
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
		ibdt.SelectSpokeTo(al.get(9));
		ibdt.SelectPSTCT(al.get(1));
		ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		//ncrt.ClickSave();
		//Thread.sleep(4000);
		
		ibdt.SelectDevice(al.get(3));
		if(CurrURL.contains("--byjusuat") || !CurrURL.contains("--byjusqa")) {
		ibdt.SelectCategory(al.get(4));
		}
		else {
			ibdt.SelectIssueCategory(al.get(4));
		}
		if(!CurrURL.contains("--byjusuat")) {
		ibdt.SelectPSCOYR(al.get(5));
		ibdt.SelectPSTRR2(al.get(6));
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
		
	}

	@Test(priority = 2, enabled=true)
	public void test2() throws Exception {
		Thread.sleep(5000);
		driver1 = initializeDriver();
		String Accountid = null;
		loginPO lo=new loginPO(driver1);
		if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "RefundProcess", "Tcid");
		
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_BTLA.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "RefundProcess", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "RefundProcess", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		CreatedAccountPO ca1= new CreatedAccountPO(driver1);
		ca1.NavBackToAccount();
		driver1.get(CurrURL+Accountid);
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		String OwnerName=ca1.CaptureAccOwnrNam();
	
		log.info("Creating Inbound Task");
		if(CurrURL.contains("--byjusuat")){
		ca1.ClickOpenActiDD();
		}
		else {
			ca1.ClickOpenActiDD_Prod();
		}
		Thread.sleep(800);
		ca1.ClickNewTask();
		
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver1);
		
		ncrt.SelectCaseRecordTypeInbound();
		ncrt.ClickNext();
		Thread.sleep(2000);
		ncrt.ClickSave();
		Thread.sleep(2000);
		
		InboundTaskPO ibdt= new InboundTaskPO(driver1);
		ibdt.ClickCaptureDetail();
		Thread.sleep(3000);		
		  
		ibdt.ClickProceedOptn();
		ibdt.SelectSpokeTo(al.get(9));
		ibdt.SelectPSTCT(al.get(1));
		ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		//ncrt.ClickSave();
		//Thread.sleep(4000);
		
		ibdt.SelectDevice(al.get(3));
		if(CurrURL.contains("--byjusuat") || !CurrURL.contains("--byjusqa")) {
		ibdt.SelectCategory(al.get(4));
		}
		else {
			ibdt.SelectIssueCategory(al.get(4));
		}
		if(!CurrURL.contains("--byjusuat")) {
		ibdt.SelectPSCOYR(al.get(5));
		ibdt.SelectPSTRR2(al.get(6));
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
		
	}

	@Test(priority = 3, enabled = true)
	public void test3() throws Exception {
		Thread.sleep(10000);
		driver2 = initializeDriver();
		String Accountid = null;
		loginPO lo=new loginPO(driver2);
		if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "RefundProcess", "Tcid");
		
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_BTLA.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "RefundProcess", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "RefundProcess", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		CreatedAccountPO ca1= new CreatedAccountPO(driver2);
		ca1.NavBackToAccount();
		driver2.get(CurrURL+Accountid);
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		String OwnerName=ca1.CaptureAccOwnrNam();
	
		log.info("Creating Inbound Task");
		if(CurrURL.contains("--byjusuat")){
		ca1.ClickOpenActiDD();
		}
		else {
			ca1.ClickOpenActiDD_Prod();
		}
		Thread.sleep(800);
		ca1.ClickNewTask();
		
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver2);
		
		ncrt.SelectCaseRecordTypeInbound();
		ncrt.ClickNext();
		Thread.sleep(2000);
		ncrt.ClickSave();
		Thread.sleep(2000);
		
		InboundTaskPO ibdt= new InboundTaskPO(driver2);
		ibdt.ClickCaptureDetail();
		Thread.sleep(3000);		
		  
		ibdt.ClickProceedOptn();
		ibdt.SelectSpokeTo(al.get(9));
		ibdt.SelectPSTCT(al.get(1));
		ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		//ncrt.ClickSave();
		//Thread.sleep(4000);
		
		ibdt.SelectDevice(al.get(3));
		if(CurrURL.contains("--byjusuat") || !CurrURL.contains("--byjusqa")) {
		ibdt.SelectCategory(al.get(4));
		}
		else {
			ibdt.SelectIssueCategory(al.get(4));
		}
		if(!CurrURL.contains("--byjusuat")) {
		ibdt.SelectPSCOYR(al.get(5));
		ibdt.SelectPSTRR2(al.get(6));
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
		
	}

	@Test(priority = 4,enabled = true)
	public void test4() throws Exception {
		Thread.sleep(15000);
		driver3 = initializeDriver();
		String Accountid = null;
		loginPO lo=new loginPO(driver3);
		if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "RefundProcess", "Tcid");
		
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_BTLA.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			al = excelData.getData("TC1", "RefundProcess", "Tcid");
			//al2 = excelData.getData("Collection Assistant QA", "Login", "Type");
			log.info("Logging in as Admin to QA Env");
			lo.LoginAsAdmin_QA();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_QA();
			log.info("Launching the newly created Account id "+Accountid);
		}
		else {
			al = excelData.getData("TC1", "RefundProcess", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();
			log.info("Submitting the Account creation payload");
			Accountid=payLoad_BTLA.AccountidCreationResponse_Prod();
			log.info("Launching the newly created Account id "+Accountid);
		}
		
		closeTabWindows();
		CreatedAccountPO ca1= new CreatedAccountPO(driver3);
		ca1.NavBackToAccount();
		driver3.get(CurrURL+Accountid);
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		String OwnerName=ca1.CaptureAccOwnrNam();
	
		log.info("Creating Inbound Task");
		if(CurrURL.contains("--byjusuat")){
		ca1.ClickOpenActiDD();
		}
		else {
			ca1.ClickOpenActiDD_Prod();
		}
		Thread.sleep(800);
		ca1.ClickNewTask();
		
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver3);
		
		ncrt.SelectCaseRecordTypeInbound();
		ncrt.ClickNext();
		Thread.sleep(2000);
		ncrt.ClickSave();
		Thread.sleep(2000);
		
		InboundTaskPO ibdt= new InboundTaskPO(driver3);
		ibdt.ClickCaptureDetail();
		Thread.sleep(3000);		
		  
		ibdt.ClickProceedOptn();
		ibdt.SelectSpokeTo(al.get(9));
		ibdt.SelectPSTCT(al.get(1));
		ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		//ncrt.ClickSave();
		//Thread.sleep(4000);
		
		ibdt.SelectDevice(al.get(3));
		if(CurrURL.contains("--byjusuat") || !CurrURL.contains("--byjusqa")) {
		ibdt.SelectCategory(al.get(4));
		}
		else {
			ibdt.SelectIssueCategory(al.get(4));
		}
		if(!CurrURL.contains("--byjusuat")) {
		ibdt.SelectPSCOYR(al.get(5));
		ibdt.SelectPSTRR2(al.get(6));
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
		
	}

	
	  @AfterTest(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  //driver.quit();
	  
	  }
	 
	 

	
	/*
	 * @AfterTest public void stopDocker() throws IOException, InterruptedException
	 * { File fi=new File("output1.txt"); fi.delete(); DockerrunStop s1=new
	 * DockerrunStop(); s1.Stopfile(); }
	 */

}

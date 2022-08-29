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
import pageObjects.KnowledgePO;
import pageObjects.PEFollowUpTaskPO;
import pageObjects.PEOnBoardingTaskPO;
import pageObjects.PEShippedTaskPO;

import pageObjects.SessionsInformationPO;
import pageObjects.StudentProgPO;
import pageObjects.StudentSalesOrderPO;
import pageObjects.StudentSubOrderPO;
import pageObjects.UserDetailPO;
import pageObjects.UserSetupPO;
import pageObjects.loginPO;
import payLoad.payLoad_BTLA2;
import resources.ExcelData;
import resources.base;



public class test_Knowledge extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_Knowledge.class.getName());
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
	public void TestButtonBLC() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);

		
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "Knowledge", "Tcid");
			log.info("Logging in as Admin to UATFC Env then switching user to BLC");
			lo.LoginAsAdmin_UATFC();

		}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "Knowledge", "Tcid");
		
		log.info("Logging in as Admin to UAT then switching user to BLC");
		lo.LoginAsAdmin_UAT();
		}
		else {
			al = excelData.getData("TC1", "Knowledge", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();

		}
		
		Thread.sleep(2000);
		closeTabWindows();
		

		
		CreatedAccountPO ac=new CreatedAccountPO(driver);
		UserDetailPO ud= new UserDetailPO(driver);
		UserSetupPO us= new UserSetupPO(driver);
		KnowledgePO kp=new KnowledgePO(driver);
		//Open the account by searching PID
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		ac.GotoLoggedInUser();
		ud.ClickUserDetailbutton();
		us.KnowledgeUserCheck();
		}
		else if(CurrURL.contains("--byjusuatfc")) {

		}
		else {

		}
		
		//Creating New Case
		kp.ClickButton("New");
		kp.SelectRecordType("Byjus Process");
		
		//Entering values
		kp.EnterTitle(al.get(1));
		kp.SelectIssueCategory(al.get(2));
		kp.EnterURLName(al.get(3));
		kp.SelectSubCategory(al.get(4));
		kp.EnterSummary(al.get(5));
		kp.SelectIssueType(al.get(6));
		kp.SelectIssueSubType(al.get(7));
		kp.SelectArticleType(al.get(8));
		kp.EnterYourCustomerDetail(al.get(9));
		kp.EnterArticleBody(al.get(10));
		
	}		
		
	
		
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  //driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
	
	
}

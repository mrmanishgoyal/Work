package testCases;

import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;


import com.github.javafaker.Faker;

import org.testng.Assert;

import pageObjects.AmeyoPO;
import pageObjects.CreatedAccountPO;
import pageObjects.DevConsolePO;
import pageObjects.ExamDetailsPO;

import pageObjects.PEFollowUpTaskPO;
import pageObjects.PEOnBoardingTaskPO;
import pageObjects.PEShippedTaskPO;

import pageObjects.SessionsInformationPO;
import pageObjects.StudentProgPO;
import pageObjects.StudentSalesOrderPO;
import pageObjects.StudentSubOrderPO;

import pageObjects.loginPO;
import payLoad.payLoad_AmeyoRegistered;
import payLoad.payLoad_BTLA;
import resources.ExcelData;
import resources.base;



public class test_CSInbound extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_CSInbound.class.getName());
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
	public void TestCSInbound() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		if(CurrURL.contains("--byjusuatfc")) {
			
		log.info("Logging in as IRT User to UATFC");
		lo.LoginAsAdmin_UATFC();

		
		}
		else if(CurrURL.contains("--byjusuat")) {
	
		log.info("Logging in as IRT User to UAT");
		lo.LoginAsAdmin_UAT();
		log.info("Submitting the Account creation payload");
		Accountid=payLoad_AmeyoRegistered.AccountidCreationResponse_UAT();
		log.info("Launching the newly created Account id "+Accountid);
		
		}
		else if(CurrURL.contains("--byjusqa")) {
			log.info("Logging in as Admin to QA");
			lo.LoginAsAdmin_QA();

		}
		else {

			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();

		}
		
		closeTabWindows();
		CreatedAccountPO ac=new CreatedAccountPO(driver);
		//Open the account by searching PID
		ac.NavBackToAccount();
		driver.get(CurrURL+Accountid);
		Thread.sleep(4000);
		
		AmeyoPO ameyo=new AmeyoPO(driver);
		ameyo.ClickPhoneIcon();
		ameyo.LoginAmeyo();
		ameyo.SelectCampaing("inbound");
		ameyo.ClickNext_Campaing();
		ameyo.Select_Extension("tata8067903939","8801303155");
		ameyo.SetStatusAvailable();
		
		String Status= ameyo.CaptureStatus();
		Assert.assertEquals(Status, "Available");
		String Mainwin= driver.getWindowHandle();
		
		ameyo.SelectionFlowPage();
		driver.switchTo().window(Mainwin);
		
		
		
	}
		
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  //driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
	
	
}

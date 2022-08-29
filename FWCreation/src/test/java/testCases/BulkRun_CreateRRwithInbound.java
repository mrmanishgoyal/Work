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



public class BulkRun_CreateRRwithInbound extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(BulkRun_CreateRRwithInbound.class.getName());
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
	

	@Test(dataProvider = "Datapro",enabled = true)
	public void TestRRCaseCreationwithInbound(String Progid, String ShippedRegion, String Accountid, String Contactid, String Premiumid) throws Exception {
		
		loginPO lo=new loginPO(driver);
		al = excelData.getData("TC1", "RefundProcess", "Tcid");
		
		log.info("Logging in as Admin to UAT");
		lo.LoginAsAdmin_UAT();
		Thread.sleep(3000);
		
		closeTabWindows();
		CreatedAccountPO ca1= new CreatedAccountPO(driver);
		Thread.sleep(1000);
		//ca1.AllNotificationRead();
		ca1.NavBackToAccount();
		driver.get(CurrURL+Accountid);
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		String OwnerName=ca1.CaptureAccOwnrNam();
		ca1.Scrollpagedown();
		
		log.info("Creating Inbound Task");
		ca1.Scrollpagedown();
		ca1.ClickOpenActiDD();

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
		if(CurrURL.contains("--byjusuat")) {
		//ibdt.SelectSpokeTo(al.get(9));
		}
		ibdt.SelectPSTCT(al.get(1));
		ibdt.EnterNotesVal(al.get(2));
		ibdt.ClickNext();
		Thread.sleep(2000);
		
		ibdt.SelectDevice(al.get(3));
		ibdt.SelectCategory(al.get(4));
		ibdt.SelectPSTRR(al.get(5));
		ibdt.SelectPSSTRR(al.get(6));
		ibdt.EnterComments(al.get(7));
		ibdt.SelectIsTICR(al.get(8));
		ibdt.SelectPSTO();
		Thread.sleep(1000);
		ibdt.ClickNext();
		Thread.sleep(3000);
		

		ibdt.NavBackAccount();

		
	}
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
		@DataProvider(name = "Datapro")
		public Object[][] testpro() throws Exception {
			Object[][] data = readData(System.getProperty("user.dir") + "\\src\\main\\java\\testData\\DataCapture.xlsx",
					"BTLA+AllPrgmswithShipped");
			return data;
		}
	
}

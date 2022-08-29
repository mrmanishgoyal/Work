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
import pageObjects.ExamDetailsPO;
import pageObjects.NewCaseDetailsPO;
import pageObjects.NewCaseRecordTypePO;
import pageObjects.PEFollowUpTaskPO;
import pageObjects.PEOnBoardingTaskPO;
import pageObjects.PEShippedTaskPO;

import pageObjects.SessionsInformationPO;
import pageObjects.StudentProgPO;
import pageObjects.StudentSalesOrderPO;
import pageObjects.StudentSubOrderPO;

import pageObjects.loginPO;
import payLoad.payLoad_BTLA;
import resources.ExcelData;
import resources.base;



public class test_ButtonCheckOrderPunchingTeam extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_ButtonCheckOrderPunchingTeam.class.getName());
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
	public void TestButtonOrderPunchingTeam() throws Exception {
		String Accountid = null;
		loginPO lo=new loginPO(driver);
		String EnvUAT="src\\main\\java\\testData\\ButtonCheckMacro_UAT.xlsm";
		String EnvUATFC="src\\main\\java\\testData\\ButtonCheckMacro_UATFC.xlsm";
		String EnvProd="src\\main\\java\\testData\\ButtonCheckMacro_Prod.xlsm";
		
		if(CurrURL.contains("--byjusuatfc")) {
			al = excelData.getData("TC1", "LSPDCase", "Tcid");
			al2 = excelData.getData("OrderPunching Team User UATFC", "Login", "Type");
			log.info("Logging in as Admin to UATFC Env then switching user to OPT");
			lo.LoginAsAdmin_UATFC();
			//lo.SwitchUser(al2.get(1));
			//log.info("Submitting the Account creation payload");
			//Accountid=payLoad_BTLA.AccountidCreationResponse_UATFC();
			//log.info("Launching the newly created Account id "+Accountid);
		}
		else if(CurrURL.contains("--byjusuat")) {
		al = excelData.getData("TC1", "LSPDCase", "Tcid");
		al2 = excelData.getData("OrderPunchingTeam User UAT", "Login", "Type");
		
		log.info("Logging in as Admin to UAT then switching user to OPT");
		lo.LoginAsAdmin_UAT();
		//lo.SwitchUser(al2.get(1));
		//log.info("Submitting the Account creation payload");
		//Accountid=payLoad_BTLA.AccountidCreationResponse_UAT();
		//log.info("Launching the newly created Account id "+Accountid);
		
		}
		else {
			al = excelData.getData("TC1", "LSPDCase", "Tcid");
			//al2 = excelData.getData("Collection Assistant", "Login", "Type");
			log.info("Logging in as Admin to Prod");
			lo.LoginAsAdmin_Prod();

			//log.info("Submitting the Account creation payload");
			//Accountid=payLoad_BTLA.AccountidCreationResponse_Prod();
			//log.info("Launching the newly created Account id "+Accountid);
		}
		//Assert.assertTrue(false);
		closeTabWindows();
		
		//log.info("Submitting the Account creation payload");
		//String Accountid=payLoad_AccountCreation.AccountidCreationResponse_UAT();
		//log.info("Launching the newly created Account id "+Accountid);
		
		CreatedAccountPO ac=new CreatedAccountPO(driver);
		CasesPO cases=new CasesPO(driver);
		//Open the account by searching PID
		//ac.NavBackToAccount();
		//String AccountURL = CurrURL+Accountid;
		//driver.get(CurrURL+Accountid);
		//Thread.sleep(5000);
		
		
		//Logging in as the User
		//Verifying all the Case list view buttons
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
		lo.SwitchUser(al2.get(1));
		ac.closeTabWindows();
		ac.Notification();
		cases.NavMenuClick();
		cases.CaseNavMenuClick();
		cases.RecentlyViewedlist();
		ac.CheckCaseListViewButtons(EnvUAT,"Case_OPT");
		}
		else if(CurrURL.contains("--byjusuatfc")) {
			lo.SwitchUser(al2.get(1));
			ac.closeTabWindows();
			ac.Notification();
			cases.NavMenuClick();
			cases.CaseNavMenuClick();
			cases.RecentlyViewedlist();
			ac.CheckCaseListViewButtons(EnvUATFC,"Case_OPT");
		}
		else {
			lo.SwitchUsernProfile_Prod("Order Punching Team");
			ac.closeTabWindows();
			ac.Notification();
			cases.NavMenuClick();
			cases.CaseNavMenuClick();
			cases.RecentlyViewedlist();
			ac.CheckCaseListViewButtons(EnvProd,"Case_OPT");
		}

		//Creating New LSPD Case
		cases.NewCaseClick();
		NewCaseRecordTypePO ncrt= new NewCaseRecordTypePO(driver);
		ncrt.SelectCaseRecordType("LSPD Case");
		ncrt.ClickNext();
		
		NewCaseDetailsPO ncd= new NewCaseDetailsPO(driver);
		ncd.EnterSubject2(al.get(1));
		ncd.EnterStatus(al.get(2));
		ncd.EnterCaseOrigin(al.get(3));
		ncd.EnterTypeOfOrderPunched(al.get(4));
		ncd.EnterTypeOfOrder(al.get(5));
		ncd.EnterCaseCategory(al.get(6));
		ncd.EnterOrderLandingSelected(al.get(7));
		ncd.EnterProductCategory(al.get(8));
		ncd.EnterReasonForDispatch(al.get(9));
		//ncd.EnterValidityOHorOMS(al.get(10));
		//ncd.EnterPincodeServicable(al.get(11));
		ncd.ClickSave();
		
		CreatedCaseRecordPO ccr= new CreatedCaseRecordPO(driver);
		String CaseNumber = ccr.CaseNumberCreated();
		
		log.info("The Case created is: "+CaseNumber);
		String CaseCreatedURL= driver.getCurrentUrl();
		
		if(CurrURL.contains("--byjusuat")&&!CurrURL.contains("--byjusuatfc")) {
			ccr.CheckCreatedCaseButtonsQAandSM(EnvUAT,"Case_OPT");
		}
		else if(CurrURL.contains("--byjusuatfc")){
			ccr.CheckCreatedCaseButtonsQAandSM(EnvUATFC,"Case_OPT");
		}
		else {
			ccr.CheckCreatedCaseButtonsQAandSM(EnvProd,"Case_OPT");
		}
		
		
		lo.Logouthome();
		
		//Deleting the created Case record
		ac.closeTabWindows();
		driver.get(CaseCreatedURL);
		Thread.sleep(3000);
		
		cases.NavMenuClick();
		cases.CaseNavMenuClick();
		log.info("Deleting the created Case record "+CaseNumber);
		cases.DeleteCaseRecord(CaseNumber);
		
	}		
		
	
		
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
	
	
}

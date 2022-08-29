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
import pageObjects.IssueTreePO;
import pageObjects.MentorFollowUpTaskPO;
import pageObjects.NewCaseRecordTypePO;
import pageObjects.OutboundTaskPO;
import pageObjects.PEFollowUpTaskPO;
import pageObjects.PEOnBoardingTaskPO;
import pageObjects.PEShippedTaskPO;

import pageObjects.SessionsInformationPO;
import pageObjects.StudentProgPO;
import pageObjects.StudentSalesOrderPO;
import pageObjects.StudentSubOrderPO;
import pageObjects.TasksPO;
import pageObjects.loginPO;
import payLoad.payLoad_BTLA2;
import resources.ExcelData;
import resources.base;



public class test_IT_MentorIssues extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(test_IT_MentorIssues.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	ArrayList<String> al3 = new ArrayList<String>();
	ArrayList<String> al4 = new ArrayList<String>();
	
	static int i=0;
	
	@BeforeMethod(alwaysRun = true)
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

	}
	
	
	@Test(dataProvider = "Datapro",groups = {"sanity", "UAT" }, enabled = true)
	public void Test_IT_MentorIssues(String Prgm1,String Prgm2,String SR,String CC,String Accid,String Cntctid,String TT,String Tid,String Category,String SCategory,String IssueT,String IssueST,String IssueN,String ITIR) throws Exception {
		//String Accountid = null;
		loginPO lo=new loginPO(driver);
		// Users to setup PE,Mentor,IRT,BLC
		String loginas="PE";
		
		al = excelData.getData("Outbound", "Outbound", "Tcid");
		al2 = excelData.getData("PE-Follow", "Outbound", "Tcid");
		al3 = excelData.getData("Inbound", "Inbound", "Tcid");
		al4 = excelData.getData("Mentor-Follow", "Outbound", "Tcid");
		
		if(CurrURL.contains("--byjusdev02")) {
			lo.LoginAsAdmin_Dev();
		}
		else {
		log.info("Logging in as: " +loginas);
		lo.LoginUser(loginas);
		}
		

		
		CreatedAccountPO ac=new CreatedAccountPO(driver);
		IssueTreePO it=new IssueTreePO(driver);
		TasksPO t=new TasksPO(driver);
		//Open the Task 
		ac.closeTabWindows();
		driver.get(CurrURL+Tid);
		Thread.sleep(3500);
		
		String TaskType = t.TaskType();
		
		if(TaskType.contains("Inbound")) {
			InboundTaskPO ibdt= new InboundTaskPO(driver);
			ibdt.ClickCaptureDetail();
			ibdt.ClickProceedOptn();
			//ibdt.SelectSpokeTo2(al3.get(1));
			//it.ProgramSelector();
			//it.PremiumidSelector();
			//it.ClickNext();
			ibdt.SelectPSTCT(al3.get(2));
			ibdt.EnterNotesVal(al3.get(3));
			it.ClickNext();
			it.IssueCategory(Category);
			//it.IssueSubCategory(SCategory);
			it.IssueType(IssueT);
			////it.IssueSubType(IssueST);
			it.IssueNotes(IssueN);
			it.IstheIssueResolved(ITIR);
			it.ClickNext2();
		}
		else if (TaskType.contains("Outbound")) {
			OutboundTaskPO ob= new OutboundTaskPO(driver);
			ob.ClickCaptureDetail();
			ob.SelectProceed_iframe();
			ob.SelectProceedST(al.get(1));
			ob.SelectOnboardingSurvey(al.get(2));
			ob.SelectStudyPlanStatus(al.get(3));
			ob.SelectPriCaretakerStudies(al.get(4));
			ob.ClickNext();
			ob.SelectProceedIsThereIssue2(al.get(5));
			it.ProgramSelector();
			//it.PremiumidSelector();
			it.ClickNext();
			it.IssueCategory(Category);
			//it.IssueSubCategory(SCategory);
			it.IssueType(IssueT);
			//it.IssueSubType(IssueST);
			it.IssueNotes(IssueN);
			it.IstheIssueResolved(ITIR);
			it.ClickNext2();
			
		}
		else if (TaskType.contains("PE")){
			PEFollowUpTaskPO pefu= new PEFollowUpTaskPO(driver);
			pefu.ClickCaptureDetail();
			pefu.SelectProceed_iframe();
			pefu.SelectProceedST(al2.get(1));
			pefu.SelectProceedSAU(al2.get(6));
			pefu.SelectProceedNotes(al2.get(3));
			pefu.ClickNext();
			pefu.SelectProceedIsThereIssue2(al2.get(5));
			it.ProgramSelector();
			//it.PremiumidSelector();
			it.ClickNext();
			it.IssueCategory(Category);
			//it.IssueSubCategory(SCategory);
			it.IssueType(IssueT);
			//it.IssueSubType(IssueST);
			it.IssueNotes(IssueN);
			it.IstheIssueResolved(ITIR);
			it.ClickNext2();
		}
		else {
			MentorFollowUpTaskPO mfu=new MentorFollowUpTaskPO(driver);
			mfu.ClickCaptureDetail();
			mfu.SelectProceed_iframe();
			mfu.SelectProceedST(al4.get(1));
			mfu.SelectProceedSPS(al4.get(7));
			mfu.ClickNext();
			mfu.SelectProceedIsThereIssue2(al4.get(5));
			it.ProgramSelector();
			//it.PremiumidSelector();
			it.ClickNext();
			it.IssueCategory(Category);
			//it.IssueSubCategory(SCategory);
			it.IssueType(IssueT);
			//it.IssueSubType(IssueST);
			it.IssueNotes(IssueN);
			it.IstheIssueResolved(ITIR);
			it.ClickNext2();	
		}
		
		//String Prgm1,String Prgm2,String SR,String CC,String Accid,String Cntctid,String TT,String Tid,String Category,String SCategory,
		//String IssueT,String IssueST,String IssueN,String ITIR
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 0, Prgm1);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 1, Prgm2);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 2, SR);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 3, CC);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 4, Accid);		
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 5, Cntctid);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 6, TT);		
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 7, Tid);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 8, Category);		
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 9, SCategory);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 10, IssueT);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 11, IssueST);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 12, IssueN);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "IT_MentorIssues", i + 1, 13, ITIR);
		i++;
		
	}
		
	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  //driver.quit();
	  
	  }
	 
		@DataProvider(name = "Datapro")
		public Object[][] testpro() throws Exception {
			Object[][] data = readData(System.getProperty("user.dir") + "\\src\\main\\java\\testData\\TestData.xlsx",
					"IT_MentorIssues");
			return data;
		}
	
	
}

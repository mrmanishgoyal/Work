package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pageObjects.CasesPO;
import pageObjects.CollectionAssistantLoginPO;
import pageObjects.CreatedAccountPO;
import pageObjects.DevConsolePO;
import pageObjects.ExamDetailsPO;
import pageObjects.FirstConnectPO;
import pageObjects.InboundTaskPO;
import pageObjects.NewCaseRecordTypePO;
import pageObjects.OpenActivitiesPO;
import pageObjects.PEOnBoardingTaskPO;
import pageObjects.PEShippedTaskPO;
import pageObjects.SessionsInformationPO;
import pageObjects.StudentAppInfoPO;
import pageObjects.StudentCRAssoPO;
import pageObjects.UserDetailPO;
import pageObjects.UserSetupPO;
import pageObjects.loginPO;
import payLoad.payLoad_BTLA;
import payLoad.payLoad_MK;
import payLoad.payLoad_NEOL2Queue;
import resources.Batches;
import resources.ExcelData;
import resources.Queries;
import resources.base;

public class Rough extends base {

	public static Properties prop;
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Rough.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();

	@BeforeMethod(alwaysRun = true)
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

	}
	

	@Test()
	public void setAsset() throws Exception {

		// TODO Auto-generated method stub
		
		loginPO lo=new loginPO(driver);
		lo.FromMiddleUAT("https://byjusprod--byjusuat.lightning.force.com/lightning/setup/Roles/page?address=%2F_ui%2Fcommon%2Fownership%2Fgroup%2FGroupBlowoutPage%2Fd%3FgroupId%3D00G2w000002vmKS%26appLayout%3Dsetup%26tour%3D%26sfdcIFrameOrigin%3Dhttps%253A%252F%252Fbyjusprod--byjusuat.lightning.force.com%26sfdcIFrameHost%3Dweb%26nonce%3D605402ce613af595843bd42394769406bd7f1a48f1decc65b4904fe6afac1692%26ltn_app_id%3D06m2w000002OO4CAAW%26clc%3D1");
		
		//Thread.sleep(3000);
		al = excelData.getData("TC1", "Neo", "Tcid");
		CreatedAccountPO ac=new CreatedAccountPO(driver);
		StudentCRAssoPO scra = new StudentCRAssoPO(driver);
		UserSetupPO us = new UserSetupPO(driver);
		CasesPO cases=new CasesPO(driver);
		
		ac.CloseCurrentSubTab();
		
		String a;
		
		//tr/th/span/a

}
	}

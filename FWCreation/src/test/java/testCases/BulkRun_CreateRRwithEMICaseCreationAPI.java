package testCases;

import static io.restassured.RestAssured.given;

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

import io.restassured.path.json.JsonPath;

import org.testng.Assert;


import pageObjects.CasesPO;

import pageObjects.CreatedAccountPO;
import pageObjects.CreatedCaseRecordPO;
import pageObjects.InboundTaskPO;

import pageObjects.NewCaseDetailsPO;
import pageObjects.NewCaseRecordTypePO;
import pageObjects.Retention1stCallPO;
import pageObjects.loginPO;
import payLoad.payLoad_AllProgram;
import payLoad.payLoad_BTLA;
import payLoad.payLoad_CaseCreation;
import payLoad.payLoad_EMICaseCreation;
import payLoad.payLoad_RefundProcess;
import resources.ExcelData;
import resources.Utils;
import resources.base;



public class BulkRun_CreateRRwithEMICaseCreationAPI extends base {

	public WebDriver driver;
	//public String CurrURL;
	public static Logger log = LogManager.getLogger(BulkRun_CreateRRwithEMICaseCreationAPI.class.getName());
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
	

	@Test(dataProvider = "Datapro",enabled = true)
	public void TestRRCaseCreationwithAPI(String Accountid, String Contactid,String Paymentid,String Appid) throws Exception {
		
		//loginPO lo=new loginPO(driver);
		String val = "OAuth ";
		String StudentCreation = given().header("Authorization", val + Utils.Access_Token())
				.header("Content-Type", "application/json").body(payLoad_EMICaseCreation.EMICaseCreationRR(Contactid,Paymentid,Appid)).when()
				.post("https://byjusprod--byjusuat.my.salesforce.com/services/data/v41.0/sobjects/Case").then().log().all().extract().response().asString();
		
		//String val = "OAuth ";
		//String StudentCreation = given().header("Authorization", val + Utils.Access_TokenUATFC())
				//.header("Content-Type", "application/json").body(payLoad_EMICaseCreation.EMICaseCreationRR(Contactid,Paymentid,Appid)).when()
				//.post("https://byjusprod--byjusuatfc.my.salesforce.com/services/data/v41.0/sobjects/Case").then().log().all().extract().response().asString();
		
        JsonPath js1 = new JsonPath(StudentCreation);
        String id = js1.get("id");
		System.out.println("The value of id is: " + id+" for account "+Accountid);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "EMICaseCreation4", i + 1, 0, Accountid);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "EMICaseCreation4", i + 1, 1, Contactid);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "EMICaseCreation4", i + 1, 2, Paymentid);
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "EMICaseCreation4", i + 1, 3, Appid);		
		setData("src\\main\\java\\testData\\DataCapture.xlsx", "EMICaseCreation4", i + 1, 4, id);
		i++;
	}
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
	 
		@DataProvider(name = "Datapro")
		public Object[][] testpro() throws Exception {
			Object[][] data = readData(System.getProperty("user.dir") + "\\src\\main\\java\\testData\\TestData.xlsx",
					"EMICaseCreation4");
			return data;
		}
	
}

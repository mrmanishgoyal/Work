package testCases;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import pageObjects.loginPO;
import payLoad.payLoad_AllProgram;
import payLoad.payLoad_CaseCreation_Email;
import resources.ExcelData;
import resources.Utils;
import resources.base;

public class BulkRunEmailCase extends base {

	public static Properties prop;
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(BulkRunEmailCase.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	static int j=0;
	
	@BeforeMethod(alwaysRun = true)
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

	}
	

	@Test()
	public void setAsset() throws Exception {

		// TODO Auto-generated method stub
		
		loginPO lo=new loginPO(driver);
		//lo.FromMiddleUAT("https://byjusprod--byjusuat.lightning.force.com/lightning/r/Account/0010k00001AbVZ9AAN/view");
		
		int i=0;
		while(i<40) {
			String val = "OAuth ";
			String StudentCreation="";
			StudentCreation = given().header("Authorization", val + Utils.Access_Token())
					.header("Content-Type", "application/json").body(payLoad_CaseCreation_Email.EmailCaseCreation()).when()
					.post("https://byjusprod--byjusuat.my.salesforce.com/services/data/v54.0/composite").then().log().all()
					.extract().response().asString();
			//JsonPath js1 = new JsonPath(StudentCreation);
	        //String id = js1.get("compositeResponse[1].httpStatusCode");
			//System.out.println("The value of id is: " + id);
			setData("src\\main\\java\\testData\\DataCapture.xlsx", "EmailCasesid", j + 1, 1, StudentCreation);
			j++;
			i++;
			
		}
		
		//Thread.sleep(3000);
		//CreatedAccountPO ac=new CreatedAccountPO(driver);
		//StudentCRAssoPO scra = new StudentCRAssoPO(driver);
		
		//Actions ac1 = new Actions(driver);
		//ac1.keyDown(Keys.)
		//Runtime.getRuntime().exec("C:\\Users\\Tnluser\\Desktop\\FirstPrgm.exe");
		//ac.CaptureAccOwnerNam();

		//for(int i=0;i==100;i++) {
			
		//	payLoad_BTLA.AccountCreationResponseTest_UAT();
			
		//}
		
		
		
	}

}

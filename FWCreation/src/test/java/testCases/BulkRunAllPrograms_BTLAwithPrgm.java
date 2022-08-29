package testCases;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import pageObjects.loginPO;
import payLoad.payLoad_AllProgram;
import resources.ExcelData;
import resources.Utils;
import resources.base;

public class BulkRunAllPrograms_BTLAwithPrgm extends base {

	public static Properties prop;
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(BulkRunAllPrograms_BTLAwithPrgm.class.getName());
	static Faker faker = new Faker();
	static int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
	static String firstName = faker.name().firstName().replaceAll("'","");
	static String lastName = faker.name().lastName().replaceAll("'","");
	static String parentName = faker.name().fullName().replaceAll("'","");
	static String Auto="Dummy ";
	static long Premiumid= 52512495345L+randomNum;
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	static int counter=1;
	static int i=0;

	@BeforeMethod(alwaysRun = true)
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

	}
	

	@Test(dataProvider = "Datapro")
	public void TestBulkRunAllPrograms_BTLAwithPrgm(String val1) throws Exception {

		// TODO Auto-generated method stub
		loginPO lo=new loginPO(driver);
		//lo.FromMiddleUAT("https://byjusprod--byjusuat.lightning.force.com/lightning/r/Account/0010k00001AbVZ9AAN/view");
			String val = "OAuth ";
			String StudentCreation="";
			StudentCreation = given().header("Authorization", val + Utils.Access_Token())
					.header("Content-Type", "application/json").body(payLoad_AllProgram.BTLAwithProg(val1)).when()
					.post("https://byjusprod--byjusuat.my.salesforce.com/services/data/v48.0/composite").then().log().all()
					.extract().response().asString();
	        log.info("The response is: "+StudentCreation);
	        
	        JsonPath js1 = new JsonPath(StudentCreation);
	        String id = js1.get("compositeResponse[0].body.id");
			System.out.println("The value of id is: " + id);
			setData("src\\main\\java\\testData\\DataCapture.xlsx", "BTLA+Prog", i + 1, 0, val1);
			setData("src\\main\\java\\testData\\DataCapture.xlsx", "BTLA+Prog", i + 1, 1, id);
			i++;
		
	}
	
	@DataProvider(name = "Datapro")
	public Object[][] testpro() throws Exception {
		Object[][] data = readData(System.getProperty("user.dir") + "\\src\\main\\java\\testData\\TestData.xlsx",
				"BTLA+AllPrgms");
		return data;
	}


	
	  @AfterMethod(alwaysRun = true) public void teardown() throws
	  InterruptedException {
	  
	  driver.quit();
	  
	  //Thread.sleep(2000); 
	  }
}

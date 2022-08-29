package testCases;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import pageObjects.loginPO;
import resources.ExcelData;
import resources.base;

public class BulkRun extends base {

	public static Properties prop;
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(BulkRun.class.getName());
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
		//lo.FromMiddleUAT("https://byjusprod--byjusuat.lightning.force.com/lightning/r/Account/0010k00001AbVZ9AAN/view");
		int i=0;
		while(i<1) {
			TestPayloadBTLA.AccountidCreationResponseTestBTC_UAT();
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

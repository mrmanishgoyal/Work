package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.github.javafaker.Faker;

import pageObjects.CasesPO;
import pageObjects.CreatedAccountPO;
import pageObjects.CreatedCaseRecordPO;
import pageObjects.ExamDetailsPO;
import pageObjects.PEFollowUpTaskPO;
import pageObjects.PEOnBoardingTaskPO;
import pageObjects.PaymentsPO;
import pageObjects.SessionsInformationPO;
import pageObjects.loginPO;
import payLoad.payLoad_BTLA;
import payLoad.payLoad_NEOL2Queue;
import payLoad.payLoad_NeoClasses;
import resources.ExcelData;
import resources.base;

public class Rough2 extends base {

	public static Properties prop;
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Rough2.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	static Faker faker = new Faker();
	static String firstName = faker.name().firstName();
	static String lastName = faker.name().lastName();
	static int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);

	@BeforeMethod(alwaysRun = true)
	public void initialize() throws IOException, InterruptedException {

		driver = initializeDriver();

	}


	@Test()
	public void setAsset() throws Exception {

		// TODO Auto-generated method stub
		loginPO lo=new loginPO(driver);
		lo.FromMiddleUATFC("https://byjusprod--byjusuatfc.lightning.force.com/lightning/r/Account/001HF000000WNKrYAO/view");
		Thread.sleep(4000);
		
		List<WebElement> List= driver.findElements(By.xpath("//ul/li[@class='visible']//button"));
		System.out.println(List.size());
		for(int i=0;i<List.size();i++) {
			
			String OnPagebuttons=List.get(i).getText();
			System.out.println(OnPagebuttons);
			setData("src\\main\\java\\testData\\ButtonCheckMacro.xlsm", "Account", i + 1, 1, OnPagebuttons);
			if(i==List.size()-1) {
				setData2("src\\main\\java\\testData\\ButtonCheckMacro.xlsm", "Account", i + 2, 1, List.size());
				jsClick(driver.findElement(By.xpath("//span[text()='Show more actions']")));
				Thread.sleep(1500);
				List<WebElement> Showmore= driver.findElements(By.xpath("//span[@runtime_platform_actions-ribbonmenuitem_ribbonmenuitem]"));
				System.out.println(Showmore.size());
				
				for(int j=0;j<Showmore.size();j++) {
					
					String ShowMorebuttons=Showmore.get(j).getText();
					System.out.println(ShowMorebuttons);
					setData("src\\main\\java\\testData\\ButtonCheckMacro.xlsm", "Account", j + 1, 3, ShowMorebuttons);
					if(j==Showmore.size()-1) {
					setData2("src\\main\\java\\testData\\ButtonCheckMacro.xlsm", "Account", j + 2, 3, Showmore.size());
				}

					//Actions ac= new Actions(driver);
					//ac.sendKeys(Keys.ESCAPE);
			}
				jsClick(driver.findElement(By.xpath("//span[text()='Show more actions']")));
		}
		}
		
		Scrollpagedown();
		Thread.sleep(4000);
		
		Scrollend();
		Thread.sleep(4000);
		
		List<WebElement> List1= driver.findElements(By.xpath("//span[@lst-listviewmanagerheader_listviewmanagerheader][@class='slds-truncate slds-m-right--xx-small']"));
		System.out.println(List1.size());
		for(int k=0;k<List1.size();k++) {
			String MobileCards=List1.get(k).getText();
			System.out.println(MobileCards);
			setData("src\\main\\java\\testData\\ButtonCheckMacro.xlsm", "Account", k + 1, 5, MobileCards);
			if(k==List1.size()-1) {
				setData2("src\\main\\java\\testData\\ButtonCheckMacro.xlsm", "Account", k + 2, 5, List1.size());
			}
			
			
		}
		
	}

}

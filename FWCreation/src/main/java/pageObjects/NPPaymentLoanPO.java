package pageObjects;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.github.javafaker.Faker;

import resources.ExcelData;
import resources.base;

public class NPPaymentLoanPO extends base {
	WebDriver driver;
	
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	static Faker faker = new Faker();
	static String firstName = faker.name().firstName();
	static String lastName = faker.name().lastName();
	static int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
	
	private String lblInformationxpath = "//span[text()='Information']";
	private String txtPayRefIDxpath = "//label[text()='Payment Reference ID']/following::input";
	private String lblPayRefIDxpath = "//label[text()='Payment Reference ID']";
	private String txtParentFNxpath = "//label[text()='Parent First Name']/following::input";
	private String lblParentFNxpath= "//label[text()='Parent First Name']";
	private String txtParentLNxpath= "//label[text()='Parent Last Name']/following::input";
	private String lblParentLNxpath= "//label[text()='Parent Last Name']";
	private String txtLoanAmtxpath = "//label[text()='Loan Amount']/following::input";
	private String lblLoanAmtxpath = "//label[text()='Loan Amount']";
	private String ddPrgNamexpath ="//label[text()='Program Name']/following::button";
	private String txtTenurityxpath = "//label[text()='Tenurity']/following::input";
	private String lblTenurityxpath = "//label[text()='Tenurity']";
	private String ddEPPartnerxpath = "//label[text()='EP Partner']/following::button";
	private String txtAmountxpath = "//label[text()='Amount']/following::input";
	private String lblAmountxpath = "//label[text()='Amount']";
	private String txtTotalAmtTBCxpath = "//label[text()='Total Amount To Be Collected']/following::input";
	private String lblTotalAmtTBCxpath = "//label[text()='Total Amount To Be Collected']";
	private String txtNetPayAmtxpath = "//label[text()='Net Payable Amount']/following::input";
	private String lblNetPayAmtxpath = "//label[text()='Net Payable Amount']";
	private String txtPAmountxpath = "//label[text()='Payment Amount']/following::input";
	private String lblPAmountxpath = "//label[text()='Payment Amount']";
	private String ddPaymtCxpath = "//label[text()='Payment Category']/following::button";
	private String ddPaymtMSxpath = "//label[text()='Payment Method/Subscription']/following::button";
	private String dpPaymtDatexpath = "//label[text()='Payment Date']/following::input";
	private String lblPaymtDatexpath = "//label[text()='Payment Date']";
	private String ddPaymtTypexpath = "//label[text()='Payment Type']/following::button";
	private String btnSavexpath = "//button[text()='Save']";

	// Declaring Constructor
	public NPPaymentLoanPO(WebDriver driver) {
		this.driver = driver;
	}
	
	//Enter Payment Ref ID
	public void EnterPayRefID(int val) throws InterruptedException {
		//driver.findElement(By.xpath(lblInformationxpath)).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(txtPayRefIDxpath)).sendKeys(val+"");
		Thread.sleep(200); 
		driver.findElement(By.xpath(lblPayRefIDxpath)).click();
		Thread.sleep(300);
	}
	
	//Enter Parent First Name
	public void EnterParentFN(String val) throws InterruptedException {
		driver.findElement(By.xpath(txtParentFNxpath)).sendKeys("Dummy "+val);
		Thread.sleep(200);
		driver.findElement(By.xpath(lblParentFNxpath)).click();
		Thread.sleep(300);
	}

	//Enter Parent Last Name
	public void EnterParentLN(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtParentLNxpath)).sendKeys(val);
	Thread.sleep(200);
	driver.findElement(By.xpath(lblParentLNxpath)).click();
	Thread.sleep(300);
	}
	
	//Enter Loan Amount
	public void EnterLoanAmount(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtLoanAmtxpath)).sendKeys(val);
	Thread.sleep(200);
	driver.findElement(By.xpath(lblLoanAmtxpath)).click();
	Thread.sleep(300);
	}

	//Enter Program Name
	public void EnterProgramName(String val) throws InterruptedException {
	WebElement ele = driver.findElement(By.xpath(ddPrgNamexpath));
	scrollIntoView(ele);
	ele.click();
	Thread.sleep(1000);
	WebElement a4=driver.findElement(By.xpath("//label[text()='Program Name']/following::span[text()='"+val+"']"));
	scrollIntoView(a4);
	a4.click();
	Thread.sleep(200);
	}
	
	//Enter Tenurity
	public void EnterTenurity(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtTenurityxpath)).sendKeys(val);
	Thread.sleep(200);
	driver.findElement(By.xpath(lblTenurityxpath)).click();
	Thread.sleep(300);
	}
	
	//Enter EP Partner
	public void EnterEPPartner(String val) throws InterruptedException {
	WebElement a5=driver.findElement(By.xpath(ddEPPartnerxpath));
	scrollIntoView(a5);
	a5.click();
	Thread.sleep(1000);
	WebElement a3=driver.findElement(By.xpath("//label[text()='EP Partner']/following::span[text()='"+val+"']"));
	scrollIntoView(a3);
	a3.click();
	Thread.sleep(200);
	}
	
	//Enter Amount
	public void EnterAmount(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtAmountxpath)).sendKeys(val);
	Thread.sleep(200);
	driver.findElement(By.xpath(lblAmountxpath)).click();
	Thread.sleep(300);
	}
	
	//Enter Total Amount to be collected
	public void EnterTotalAmountTBC(String val) throws InterruptedException {
	driver.findElement(By.xpath(txtTotalAmtTBCxpath)).sendKeys(val);
	Thread.sleep(200);
	driver.findElement(By.xpath(lblTotalAmtTBCxpath)).click();
	Thread.sleep(300);
	}
	
	//Enter Net Payable Amount
	public void EnterNetPayAmount(String val) throws InterruptedException {
		WebElement a1 = driver.findElement(By.xpath(txtNetPayAmtxpath));
		scrollIntoView(a1);
		a1.sendKeys(val);
		Thread.sleep(200);
		driver.findElement(By.xpath(lblNetPayAmtxpath)).click();
		Thread.sleep(300);
	}
		
	
	//Enter Payment Amount
	public void EnterPaymentAmount(String val) throws InterruptedException {
	WebElement a1=driver.findElement(By.xpath(txtPAmountxpath));
	scrollIntoView(a1);
	a1.sendKeys(val);
	Thread.sleep(200);
	driver.findElement(By.xpath(lblPAmountxpath)).click();
	Thread.sleep(300);
	}
	
	//Enter Payment Category
	public void EnterPaymentCategory(String val) throws InterruptedException {
	driver.findElement(By.xpath(ddPaymtCxpath)).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//label[text()='Payment Category']/following::span[text()='"+val+"']")).click();
	Thread.sleep(200);
	}
	
	//Enter Payment Method/Subscription 
	public void EnterPaymentMS(String val) throws InterruptedException {
	driver.findElement(By.xpath(ddPaymtMSxpath)).click();
	Thread.sleep(200);
	WebElement a=driver.findElement(By.xpath("//label[text()='Payment Method/Subscription']/following::span[text()='"+val+"']"));
	scrollIntoView(a);
	a.click();
	Thread.sleep(200);
	}
	
	//Enter Payment Date 
	public void EnterPaymentDate(String val) throws InterruptedException {
	driver.findElement(By.xpath(dpPaymtDatexpath)).sendKeys(val);
	Thread.sleep(200);
	driver.findElement(By.xpath(lblPaymtDatexpath)).click();
	Thread.sleep(300);
	}
	
	//Enter Payment Type
	public void EnterPaymentType(String val) throws InterruptedException {
	WebElement a2= driver.findElement(By.xpath(ddPaymtTypexpath));
	scrollIntoView(a2);
	a2.click();
	Thread.sleep(200);
	driver.findElement(By.xpath("//label[text()='Payment Type']/following::span[text()='"+val+"']")).click();
	Thread.sleep(200);
	}
	
	//Click Save
	public void ClickSave() throws InterruptedException {
	driver.findElement(By.xpath(btnSavexpath)).click();
	Thread.sleep(4000);
	}

	// Scroll element to view
	public void scrollIntoView(WebElement element) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			System.out.println("Page scrolled down");
		} catch (Exception e){
			System.out.println("=============================================================");
			System.out.println("Exception-scrollIntoView(): "+e.getMessage());
			takeScreenShot();
			e.printStackTrace();
			System.out.println("=============================================================");
		}    
	}

}

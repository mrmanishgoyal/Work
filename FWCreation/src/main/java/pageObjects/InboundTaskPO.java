package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ExcelData;
import resources.base;

public class InboundTaskPO extends base {
	WebDriver driver;

	private String btnNextxpath= "//button[text()='Next']";
	private String lblAmtPaidbyCusxpath= "//label[text()='Amount Paid By Customer']/following::input";
	private String btnListboxMovexpath = "//button[@title='Move selection to Selected']";
	private String txtPayRefxpath = "//label[text()='Payment Reference']/following::input";
	private String ddCollectionChnnelxpath = "//label[text()='Collection Channel']/following::button";
	private String lblErrorMessxpath = "//div[@class='slds-text-color_error']";
	private String ddNoEMICollxpath = "//select[@name='No_Of_EMI_Collected']";
	private String ddTOCollAPxpath = "//select[@name='Type_Of_Collection_alreadyPaid']";
	private String ddEligfrRefxpath = "//select[@name='Eligible_For_Refund']";
	ExcelData excelData = new ExcelData();
	ArrayList<String> al3 = new ArrayList<String>();
	
	// Declaring Constructor
	public InboundTaskPO(WebDriver driver) {
		this.driver = driver;
	}
	

	//Click on Capture detail button
	public void ClickCaptureDetail() throws InterruptedException {
  jsClick(driver.findElement(By.xpath("//div[text()='Capture Call Details']")));
  Thread.sleep(3000);
	}
	
	//Select on Spoke To
	public void SelectSpokeTo(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Spoke To']/following::select")));
		sel.selectByVisibleText(val);
		Thread.sleep(400);
	    driver.findElement(By.xpath("//button[text()='Next']")).click();
	    Thread.sleep(2000);
	}
	
	//Select on Spoke To
	public void SelectSpokeTo2(String val) throws InterruptedException {
		Select sel = new Select(driver.findElement(By.xpath("//span[text()='Spoke To']/following::select")));
		sel.selectByVisibleText(val);
		Thread.sleep(400);
	}
	//Click Proceed option to move forward
	public void ClickProceedOptn() throws Exception {
	retryForDetachedFrame(driver, "//iframe", 0);
	WebElement frame1=driver.findElement(By.xpath("//iframe[@title='accessibility title']"));
	Thread.sleep(400);
	driver.switchTo().frame(frame1);
	Thread.sleep(400);
    driver.findElement(By.xpath("//span[text()='Proceed']")).click();
    Thread.sleep(800);
    driver.findElement(By.xpath("//button[text()='Next']")).click();
    Thread.sleep(3000);
	}
	
    //Select the value for Please Select The Call Type
	public void SelectPSTCT(String val) throws InterruptedException {
	visibleText(By.xpath("//label[text()='Please Select The Call Type']"));
	Select sel = new Select(driver.findElement(By.xpath("//label[text()='Please Select The Call Type']/following::select[@required]")));
	sel.selectByVisibleText(val);
	Thread.sleep(200);
	}
	
	//Enter the value for Notes
	public void EnterNotesVal(String val) throws InterruptedException {
	driver.findElement(By.xpath("//label[text()='Notes']/following::textarea")).sendKeys(val);
	Thread.sleep(200);
	}
	
	//Click Next button
	public void ClickNext() throws InterruptedException {
	driver.findElement(By.xpath("//button[text()='Next']")).click();
	Thread.sleep(2000);
	}
	
	//Select the device
	public void SelectDevice(String val) throws InterruptedException {
	driver.findElement(By.xpath("//label[text()='Device']/following::span[@lightning-basecombobox_basecombobox]")).click();
	Thread.sleep(400);
	driver.findElement(By.xpath("//label[text()='Device']/following::span[text()='"+val+"']")).click();
	Thread.sleep(1000);
	}
	
	//Select the Please Select Your Issue Category
	public void SelectIssueCategory(String val) throws InterruptedException {
	driver.findElement(By.xpath("//label[text()='Please Select Your Issue Category']/following::span[@lightning-basecombobox_basecombobox]")).click();
	Thread.sleep(400);
	driver.findElement(By.xpath("//label[text()='Please Select Your Issue Category']/following::span[text()='"+val+"']")).click();
	Thread.sleep(400);
	}
	
    //Select the value for Please Specify Your Issue
	public void SelectPSYI(String val) throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath("//label[text()='Please Specify Your Issue']/following::select[@required]")));
	sel.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
    //Select the value for Please Specify Your Issue Sub Type
	public void SelectPSYIST(String val) throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath("//label[text()='Please Specify Your Issue Sub Type']/following::select[@required]")));
	sel.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
    //Select the value for Is The Issue Resolved Currently?
	public void SelectIsTICR(String val) throws InterruptedException {
	Select sel = new Select(driver.findElement(By.xpath("//label[text()='Is The Issue Resolved Currently?']/following::select[@required]")));
	sel.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Select the Please Select Your Issue Category
	public void SelectPSYICategory(String val) throws InterruptedException {
	driver.findElement(By.xpath("//label[text()='Please Select Your Issue Category']/following::span[@lightning-basecombobox_basecombobox]")).click();
	Thread.sleep(400);
	driver.findElement(By.xpath("//label[text()='Please Select Your Issue Category']/following::span[text()='"+val+"']")).click();
	Thread.sleep(400);
	}
	
	//Select the Category
	public void SelectCategory(String val) throws InterruptedException {
	driver.findElement(By.xpath("//label[text()='Category']/following::span[@lightning-basecombobox_basecombobox]")).click();
	Thread.sleep(400);
	driver.findElement(By.xpath("//label[text()='Category']/following::span[text()='"+val+"']")).click();
	Thread.sleep(400);
	}
	
	//Select the Sub Category
	public void SelectSubCategory(String val) throws InterruptedException {
	Select sel1 = new Select(driver.findElement(By.xpath("//label[text()='Sub Category']/following::select[@required]")));
	sel1.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Select the Please Specify Your Issue Sub Type
	public void SelectIssueSubType(String val) throws InterruptedException {
	Select sel2 = new Select(driver.findElement(By.xpath("//label[text()='Please Specify Your Issue Sub Type']/following::select[@required]")));
	sel2.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Select Is The Issue Resolved Currently?
	public void SelectIssueResolved(String val) throws InterruptedException {
	Select sel3 = new Select(driver.findElement(By.xpath("//label[text()='Is The Issue Resolved Currently?']/following::select[@required]")));
	sel3.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Select Please Specify Your Logistics Issue
	public void SelectSYLI(String val) throws InterruptedException {
	Select sel3 = new Select(driver.findElement(By.xpath("//label[text()='Please Specify Your Logistics Issue']/following::select[@required]")));
	sel3.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Select the Is there is any change in grade, course, or validity
	public void SelectITGCorV(String val) throws InterruptedException {
	driver.findElement(By.xpath("//label[text()='Is there is any change in grade, course, or validity']/following::span[@lightning-basecombobox_basecombobox]")).click();
	Thread.sleep(400);
	driver.findElement(By.xpath("//label[text()='Is there is any change in grade, course, or validity']/following::span[text()='"+val+"']")).click();
	Thread.sleep(400);
	}
	
	//Select Is there is any change in registered no: or shipping address
	public void SelectIRNorSA(String val) throws InterruptedException {
	driver.findElement(By.xpath("//label[text()='Is there is any change in registered no: or shipping address']/following::span[@lightning-basecombobox_basecombobox]")).click();
	Thread.sleep(400);
	driver.findElement(By.xpath("//label[text()='Is there is any change in registered no: or shipping address']/following::span[text()='"+val+"']")).click();
	Thread.sleep(400);
	}
	
	//Select Is The Issue Resolved Currently?
	public void SelectITIRC(String val) throws InterruptedException {
	Select sel3 = new Select(driver.findElement(By.xpath("//label[text()='Is The Issue Resolved Currently?']/following::select[@required]")));
	sel3.selectByVisibleText(val);
	Thread.sleep(400);
	}	
	
	//Select Please Select The Catagory Of Your Refund
	public void SelectPSCOYR(String val) throws InterruptedException {
	Select sel3 = new Select(driver.findElement(By.xpath("//label[text()='Please Select The Catagory Of Your Refund']/following::select[@required]")));
	sel3.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Select Please Select Reason for Refund
	public void SelectPSTRR(String val) throws InterruptedException {
	Select sel3 = new Select(driver.findElement(By.xpath("//label[text()='Please Select Reason for Refund']/following::select[@required]")));
	sel3.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Select Please Select Reason for Refund
	public void SelectPSTRR2(String val) throws InterruptedException {
	Select sel3 = new Select(driver.findElement(By.xpath("//label[text()='Please Select The Refund Reason']/following::select[@required]")));
	sel3.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Pull values for Product
	public void PullProductVal() throws InterruptedException, IOException {

		
		
	}
	
	//Select all values present in Please Select Sub Reason for Refund - Product
	public void VerifyAllValinPSSTRR(int m) throws InterruptedException, IOException {
		al3 = excelData.getData("TC2", "RefundProcess", "Tcid");
		String[] Val = al3.get(m).trim().split(";");
		List<WebElement> List = driver.findElements(By.xpath("//label[text()='Please Select Sub Reason for Refund']/following-sibling::select/option"));
		for(int i=0;i<List.size();i++) {
			String Val2=List.get(i).getText();
			Thread.sleep(300);
			System.out.println(Val[i]+" | "+Val2);
			Assert.assertTrue(Val[i].equalsIgnoreCase(Val2));
		}
		
		}
	
	//Select Please Select Sub Reason for Refund
	public void SelectPSSTRR(String val) throws InterruptedException {
	Select sel3 = new Select(driver.findElement(By.xpath("//label[text()='Please Select Sub Reason for Refund']/following::select[@required]")));
	sel3.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Enter Comments
	public void EnterComments(String val) throws InterruptedException {
	driver.findElement(By.xpath("//label[text()='Comments']/following::textarea")).sendKeys(val);
	Thread.sleep(400);
	}
	
	//Please Select The Order
	public void SelectPSTO() throws InterruptedException {
	driver.findElement(By.xpath("//label[text()='Please Select The Order']/following::span[@lightning-basecombobox_basecombobox]")).click();
	Thread.sleep(400);
	driver.findElement(By.xpath("//label[text()='Please Select The Order']/following::span[contains(text(),'ORD')]")).click();
	Thread.sleep(1200);
	}
	
	//Please Select The Issue responsibility
	public void SelectPSTIR(String val) throws InterruptedException {
	Select sel3 = new Select(driver.findElement(By.xpath("//label[text()='Please Select The Issue responsibility']/following::select[@required]")));
	sel3.selectByVisibleText(val);
	Thread.sleep(400);
	}
	
	//Verify Error message
	public boolean CheckErrorMess() {
		if(driver.findElement(By.xpath("//p[contains(text(),'Existing RR case')]")).isDisplayed()) {
			return true;
		}
		else {
		return false;
		}
	}
	
  //Navigate back to Account screen
	public void NavBackAccount() throws InterruptedException {
		try {
		jsClick(driver.findElement(By.xpath("//span[text()='Name']/following::a")));
		Thread.sleep(3000);
		}
		catch(WebDriverException e){
			Thread.sleep(500);
			jsClick(driver.findElement(By.xpath("//span[text()='Name']/following::a")));	
			Thread.sleep(3000);
		}
		 catch(Exception ee)
	    {
	        ee.printStackTrace();
	        throw ee;
	    }
	}
	  
	//Navigate back to Account screen
		public void NavToCase() throws InterruptedException {
			try {
			driver.findElement(By.xpath("//span[text()='Related To']/following::a")).click();
			Thread.sleep(3000);
			}
			catch(WebDriverException e){
				Thread.sleep(500);
				driver.findElement(By.xpath("//span[text()='Related To']/following::a")).click();	
				Thread.sleep(3000);
			}
			 catch(Exception ee)
		    {
		        ee.printStackTrace();
		        throw ee;
		    }
		}
		
		//Get the Case created value under Related To
		public String CaptureRelatedTo() throws InterruptedException {
			String RelatedTo=driver.findElement(By.xpath("//div[text()='Task']/following::span[text()='Related To']/following::a[@data-refid]")).getText();
			Thread.sleep(300);
			return RelatedTo;
		}
		
		//Check for Created Case Buttons
	    public void CheckTaskButtonsQAandSM(String Env,String SheetName) throws Exception {
	    	List<WebElement> List= driver.findElements(By.xpath("//div[text()='Task']/following::ul[contains(@class,'branding-actions')]/li/a/div"));
			System.out.println(List.size());
			for(int i=0;i<List.size();i++) {
				
				String OnPagebuttons=List.get(i).getText();
				System.out.println(OnPagebuttons);
				setData(Env, SheetName, i + 1, 1, OnPagebuttons);
				if(i==List.size()-1) {
					setData2(Env, SheetName, i + 2, 1, List.size());
					try {
						if(driver.findElement(By.xpath("//div[text()='Task']/following::a[@title='Show one more action']")).isDisplayed()) {
					Thread.sleep(1000);
					WebElement ele= driver.findElement(By.xpath("//div[text()='Task']/following::a[@title='Show one more action']"));
					Actions ac=new Actions(driver);
					ac.moveToElement(ele);
					jsClick(ele);
					Thread.sleep(1200);

					List<WebElement> Showmore= driver.findElements(By.xpath("//div[text()='Task']/following::div[contains(@class,'uiPopupTarget')]/div/ul/li/a/div"));
					System.out.println(Showmore.size());
					
					for(int j=0;j<Showmore.size();j++) {
						
						String ShowMorebuttons=Showmore.get(j).getText();
						System.out.println(ShowMorebuttons);
						setData(Env, SheetName, j + 1, 3, ShowMorebuttons);
						if(j==Showmore.size()-1) {
						setData2(Env, SheetName, j + 2, 3, Showmore.size());
					}
				}
						}
					}
					catch(Exception e) {
						e.printStackTrace();
						System.out.println("No corner button present");
					}
			}
			}
	    }
		
		
    public void jsClick(WebElement el) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click();", el);
			System.out.println("Element clicked");
		} catch (Exception e){
			System.out.println("=============================================================");
			System.out.println("Exception-jsClick(): "+e.getMessage());
			takeScreenShot();
			e.printStackTrace();
			System.out.println("=============================================================");
		}
	}
    
	public boolean visibleText(By element)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		
		System.out.println("Element is visible");
		return false;
	}

}

package resources;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.loginPO;
import io.github.bonigarcia.wdm.WebDriverManager;
//import pageObjects.LoginScreenPO;

public class base {

	public  WebDriver driver;
	public Properties prop;
	public static By tag = By.tagName("html");
	public static Logger log = LogManager.getLogger(base.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al1 = new ArrayList<String>();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	public String CurrURL;
	public static String extentreporturl;
	public String browserName;


	public WebDriver initializeDriver() throws IOException, InterruptedException {

		String DPWindows= "\\src\\main\\java\\resources\\data.properties";
		String DPMAC="//src//main//java//resources//data.properties";
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + DPWindows);

		prop.load(fis);
		//String browserName = System.getProperty("browser");
		//String url= System.getProperty("url");
		String browserName = prop.getProperty("browser");
		String url = "UAT";
		
		System.out.println("Browser Name :" + browserName);
		String Windows= "\\src\\main\\java\\resources\\chromedriver.exe";
		String MAC="//src//main//java//resources//chromedriver";
		
		if (browserName.equals("chrome")) {
			ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + Windows);
			//WebDriverManager.chromedriver().setup();
          // Thread.sleep(5000);
			driver = new ChromeDriver(ops);
			//LoginLogic();
			//driver = new ChromeDriver();
			// execute in chrome driver 
			driver.manage().window().maximize();
			log.info("Driver is initialized");
			if(url.equalsIgnoreCase("UAT")) 
			{
			extentreporturl="UAT";	
			driver.get(prop.getProperty("UAT"));
			CurrURL = driver.getCurrentUrl();
			log.info("Navigated to Home page");
			}
			else if(url.equalsIgnoreCase("Production")) 
			{
				extentreporturl="Production";
				driver.get(prop.getProperty("Production"));
				CurrURL = driver.getCurrentUrl();
				log.info("Navigated to Home page");
				}
			else if(url.equalsIgnoreCase("UATFC")) 
			{
				extentreporturl="UATFC";
				driver.get(prop.getProperty("UATFC"));
				CurrURL = driver.getCurrentUrl();
				log.info("Navigated to Home page");
				}
			else if(url.equalsIgnoreCase("Dev")) 
			{
				extentreporturl="Dev";
				driver.get(prop.getProperty("Dev"));
				CurrURL = driver.getCurrentUrl();
				log.info("Navigated to Home page");
				}
			else {
				extentreporturl="QA";
				driver.get(prop.getProperty("QA"));
				CurrURL = driver.getCurrentUrl();
				log.info("Navigated to Home page");
				}
			

		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
			// firefox code
		} else if (browserName.equals("IE")) {
//	IE code
		}

		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		return driver;

	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException, InterruptedException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}

	public void ScrollpagedownAxis(int Yaxis) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,200);");
	}

	public void Scrollpagedown() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(1200);
	}

	public void Scrollpageup() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.PAGE_UP);
		Thread.sleep(1200);
	}

	public void Scrolldown() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.DOWN);
		Thread.sleep(1200);
	}

	public void Scrollup() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.UP);
		Thread.sleep(1200);
	}

	public void Scrollhome() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.HOME);
		Thread.sleep(1200);
	}

	public void Scrollend() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.END);
		Thread.sleep(1200);
	}
	
	public void RefreshWindow() throws Exception {
		driver.navigate().refresh();
		wait(4);
	}

	public void SwitchDefaultContent() throws Exception {
		retryForDetachedFrame(driver, "//iframe", 0);
		driver.switchTo().defaultContent();
		Thread.sleep(1500);
	}
	public void Scrollpopup(String xpath) throws InterruptedException {

		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(xpath));
		// now execute query which actually will scroll until that element is not
		// appeared on page.
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void PressEnter() throws InterruptedException {

		driver.findElement(tag).sendKeys(Keys.ENTER);
		Thread.sleep(1200);
	}

	public void openNewTab(String url) throws InterruptedException, AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
		Thread.sleep(1200);

		// Switch focus to new tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(url);
		Thread.sleep(1200);

	}

	public void switchTab() throws IOException, InterruptedException {

		// Get handles of the windows
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();

		// Here we will check if child window has other child windows and will fetch the
		// heading of the child window
		while (iterator.hasNext()) {
			String ChildWindow = iterator.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				Thread.sleep(1200);
			}
		}
	}

	public int generateRandomNumber() throws IOException, InterruptedException {

		Random random = new Random();

		int n = random.nextInt(9999);
		System.out.println(n);
		return n;
	}

	public boolean isDisplayed(By element) {
		List<WebElement> list = driver.findElements(element);
		//System.out.println(list.toString());
		boolean value = false;
		if (!list.isEmpty()) {
			if (list.get(0).isDisplayed()) {
				value = true;
				return value;
			}
		} else {

			return value;
		}
		return value;
	}
	

	public void clickButton(WebElement element)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
		
		System.out.println("Element is clickable");
		element.click();
	}
	
	public boolean visibleText(By element)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		
		System.out.println("Element is visible");
		return false;
	}


	public void ClickOn(By by) throws InterruptedException {

		WebElement element = driver.findElement(by);
		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(element));
		element.click(); // Expected condition for the element to be clickable
		Thread.sleep(3000);
	}
	
	
	// Code for Static wait
	public void wait(int timeToWaitInSec){
		try {
			Thread.sleep(timeToWaitInSec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Read data from excel -> Start row from 1
	public String readData(String fileName, String sheetName, int rowIndex, int cellIndex) throws Exception {
		File location = new File(fileName);
		FileInputStream fis = new FileInputStream(location);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheet(sheetName);

		String data;
		try {
			data = sheet1.getRow(rowIndex).getCell(cellIndex).getStringCellValue();
		} catch (NullPointerException e) {
			data = "";
		}

		wb.close();

		return data;
	}
	
	// Get count of rows from excel
		public int getRowCount(String fileName, String sheetName) throws IOException {
			File location = new File(fileName);
			FileInputStream fis = new FileInputStream(location);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheet(sheetName);
			wb.close();
			return sheet1.getLastRowNum()+1;
		}
		
		//To switch the top most window
		public void switchWindow() throws InterruptedException{
			Set<String> winid = driver.getWindowHandles();
	        Iterator<String> iter = winid.iterator();
	        iter.next();
	        String tab = iter.next();
	        Thread.sleep(3000);
	        driver.switchTo().window(tab);
		}
		
		//To wait until element is visible
		public WebElement waitForElementToVisible(WebElement element) {
			WebElement find;
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
					.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);
			find = wait.until(ExpectedConditions.visibilityOf(element));
			return find;
		}
		
		//To wait until element is clickable
		public WebElement waitForElementToClickable(WebElement element) {
			WebElement find;
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
					.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);
			find = wait.until(ExpectedConditions.elementToBeClickable(element));
			return find;
		}
		
		//To check the Page is loaded 
		public void checkPageReady() throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Thread.sleep(5000);
			for (int i = 0; i <= 40; i++) {
				Thread.sleep(1000);
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					break;
				}
			}
		}
		
		/* 
	     * Read data from excel using Data Provider
	    */
	 public Object[][] readData(String fileName, String sheetName) throws Exception {
	     String[][] dataTable = null;
	     
	     File location = new File(fileName);
	     FileInputStream fis = new FileInputStream(location);
	     XSSFWorkbook wb = new XSSFWorkbook(fis);
	     XSSFSheet sheet1 = wb.getSheet(sheetName);

	     int totalRows = sheet1.getLastRowNum();
	     int totalCols = sheet1.getRow(0).getLastCellNum();
	     System.out.println("Total Rows: " + totalRows);
	     System.out.println("Total Cols: " + totalCols);
	     dataTable = new String[totalRows][totalCols];
	     for (int rowIndex=1; rowIndex<=totalRows; rowIndex++) {
	         for (int cellIndex=0; cellIndex<totalCols; cellIndex++) {
	             dataTable[rowIndex-1][cellIndex] = sheet1.getRow(rowIndex).getCell(cellIndex).getStringCellValue();
	         }
	     }
	     wb.close();

	     for (int i=0; i<dataTable.length; i++) {
	         for (int j=0; j<dataTable[0].length; j++) {
	             System.out.print(dataTable[i][j] + " ");
	         }
	         System.out.println("");
	     }
	     return dataTable;
	 }
	 
		// Take screenshot of page and save in screenshots folder
		public void takeScreenShot() {
			Date date=new Date();
			String screenshotFile=date.toString().replace(":", "_").replace(" ", "_")+".png";
			String filePath=System.getProperty("user.dir")+"screenshots\\"+screenshotFile;

			try {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
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

	public void setPageLoadTimeout(long timeout, TimeUnit unit) {
		System.out.println(timeout);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	
	
    public void setData(String fileName, String sheetName, int rowIndex, int cellIndex, String data) throws Exception {
        File location = new File(fileName);
        FileInputStream fis = new FileInputStream(location);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheet(sheetName);
        if (sheet1.getRow(rowIndex) == null) {
        sheet1.createRow(rowIndex).createCell(cellIndex).setCellValue(data);
        } else {
        sheet1.getRow(rowIndex).createCell(cellIndex).setCellValue(data);
        }
        FileOutputStream fos = new FileOutputStream(location);
        wb.write(fos);
        wb.close();
    }
    
    public void setData2(String fileName, String sheetName, int rowIndex, int cellIndex, int data) throws Exception {
        File location = new File(fileName);
        FileInputStream fis = new FileInputStream(location);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheet(sheetName);
        if (sheet1.getRow(rowIndex) == null) {
        sheet1.createRow(rowIndex).createCell(cellIndex).setCellValue(data);
        } else {
        sheet1.getRow(rowIndex).createCell(cellIndex).setCellValue(data);
        }
        FileOutputStream fos = new FileOutputStream(location);
        wb.write(fos);
        wb.close();
    }
    
    public void PLvalidate(WebElement pl) {
    	
    	Select s= new Select(pl);
    	s.selectByIndex(1);
    }
    
    public void THPLvalidate(WebElement thpl) throws InterruptedException {
    	
    	thpl.clear();
		thpl.click();
		Thread.sleep(700);
		driver.findElement(By.xpath("//c-ssp-type-ahead-picklist/div/ul/li/div")).click();
    }
    
    public void retryForDetachedFrame(final WebDriver driver, final String elementXpath, final int frameId) throws Exception {
        int webDriverExceptionCounter = 0;
        while (webDriverExceptionCounter < 5) {
            try {
                driver.findElement(By.xpath(elementXpath));
                break;
            } catch (final WebDriverException ex) {
                webDriverExceptionCounter++;
                if (webDriverExceptionCounter == 5) {
                   // log.error("WebDriverException, not trying again: {}", webDriverExceptionCounter);
                    throw ex;
                } else {
                   // log.info("WebDriverException, retrying: {}", webDriverExceptionCounter);
                    Thread.sleep(500);
                    final WebDriverWait wait = new WebDriverWait(driver, 15);
                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
                }
            } catch (final Exception e) {
               // log.error("Exception: {}", e.getClass());
                throw e;
            }
        }
    }
    
    public void closeTabWindows() throws InterruptedException {
		List<WebElement> list= driver.findElements(By.xpath("//ul[@class='tabBarItems slds-grid']//div[@class='close slds-col--bump-left slds-p-left--none slds-context-bar__icon-action ']"));
		if (list.size() == 0) {
			System.out.println("There are no open tabs");
		} else {
			for (int i = 0; i < list.size(); i++) {
				try {
				list.get(i).click();
				Thread.sleep(500);
				}
				catch(Throwable t) {
					System.out.println("More tabs on page loaded later");
					t.printStackTrace();
				}
			}
		}
    }
    
    public String taskName(String emailid) {
		String id=emailid;
		String splitspace= id.replace(".", " ");
		//System.out.println(splitspace);
		String[] split=splitspace.trim().split("@");
		String[] fname=split[0].split(" ");
		String lname=fname[1];
		//System.out.println(fname[0]);
		//System.out.println(lname);
		String Finaltaskname= fname[0]+" "+lname+" ";
		//System.out.println(Finaltaskname);
		return Finaltaskname;
    }
    
    public String FirstName(String emailid) {
		String id=emailid;
		String splitspace= id.replace(".", " ");
		//System.out.println(splitspace);
		String[] split=splitspace.trim().split("@");
		String[] fname=split[0].split(" ");
		String lname=fname[1];
		//System.out.println(fname[0]);
		//System.out.println(lname);
		String Finaltaskname= fname[0];
		//System.out.println(Finaltaskname);
		return Finaltaskname;
    }
    
    public String FullName(String emailid) {
		String id=emailid;
		String splitspace= id.replace(".", " ");
		String[] split=splitspace.trim().split("@");
		String[] fname=split[0].split(" ");
		String lname=fname[1];
		String Finaltaskname= fname[0]+" "+lname;
		return Finaltaskname;
    }
    
    public String MailFullName(String emailid) {
		String id=emailid;
		String splitspace= id.replace(".", " ");
		String[] split=splitspace.split("@");
		String Finaltaskname= split[0];
		return Finaltaskname;
    }
    
    public String MailName(String emailid) {
		String id=emailid;
		String[] split=emailid.trim().split("@");
		String Finaltaskname=split[0];
		return Finaltaskname;
    }
    
    public void ReturntoMain() throws InterruptedException {
    	Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//span/p[contains(text(),'Service Console_')]")).click();
		Thread.sleep(6000);
    }
    
    public void AllNotificationRead() throws InterruptedException {
    	driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-notification slds-button__icon slds-global-header__icon slds-icon_container forceIcon']")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//a[text()='Mark all as read']")).click();
    	Thread.sleep(800);
    	
    }
    
    public void CloseNotification() throws InterruptedException {
    	
    	List<WebElement> a= driver.findElements(By.xpath("//a[@class='slds-notification__target slds-media']/following::button[@title='Dismiss notification']"));
    	for(int i=0;i<a.size();i++) {
    		a.get(i).click();
    		Thread.sleep(300);
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
  
    
    public void LoginLogic() throws IOException, InterruptedException {
    	try{			
    	     
            File file = new File("Cookies.data");							
            FileReader fileReader = new FileReader(file);							
            BufferedReader Buffreader = new BufferedReader(fileReader);							
            String strline;			
            while((strline=Buffreader.readLine())!=null){									
            StringTokenizer token = new StringTokenizer(strline,";");									
            while(token.hasMoreTokens()){					
            String name = token.nextToken();					
            String value = token.nextToken();					
            String domain = token.nextToken();					
            String path = token.nextToken();					
            Date expiry = null;					
            		
            String val;			
            if(!(val=token.nextToken()).equals("null"))
    		{		
            	expiry = new Date(val);					
            }		
            Boolean isSecure = new Boolean(token.nextToken()).								
            booleanValue();		
            Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);			
            System.out.println(ck);
            driver.manage().addCookie(ck); // This will add the stored cookie to your current session					
            }		
            }		
            }catch(Exception ex){					
            ex.printStackTrace();			
            }		
    	
    }
}

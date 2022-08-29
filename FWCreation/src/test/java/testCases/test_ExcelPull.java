package testCases;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.ExcelData;
import resources.base;



public class test_ExcelPull extends base {

    //@BeforeTest
    
    
    //public static Logger log = LogManager.getLogger(test_StudentOrderCreation.class.getName());
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
    // 
	@Test(priority = 1,dataProvider="Datapro",groups = {"sanity", "Regression" })
	public void clickStartHM(String TCid,String I2,String I3,String I4) throws Exception {

		al = excelData.getData(TCid, "Sheet1", "JSONobj");
		//System.out.println(al.toString());
		//System.out.println(al.get(1).toString());
		//System.out.println("The Values are: "+al.get(2).toString());
		//System.out.println(al.get(3).toString());
		//System.out.println("The Values are: "+I3.get(1));
		System.out.println("The Values are: "+al.get(2));
		
	}
	
	@DataProvider(name = "Datapro")
	public Object[][] testpro() throws Exception {
		Object[][] data = readData(System.getProperty("user.dir") + "\\src\\main\\java\\testData\\TestData.xlsx",
				"Sheet1");
		return data;
	}
}

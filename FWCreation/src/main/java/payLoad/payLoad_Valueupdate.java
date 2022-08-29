package payLoad;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import resources.ExcelData;
import resources.Utils;


public class payLoad_Valueupdate{
	
	public static Logger log = LogManager.getLogger(payLoad_Valueupdate.class.getName());
	static Faker faker = new Faker();
	static int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
	static String firstName = faker.name().firstName().replaceAll("'","");
	static String lastName = faker.name().lastName().replaceAll("'","");
	static String parentName = faker.name().fullName().replaceAll("'","");
	static String Auto="Auto ";
	static long Premiumid= 52512495345L+randomNum;
	static int counter=1;
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	
	public static String ValueUpdate(String Premiumid) {
		
		String bodycontent="{\r\n"
				+ "\"allOrNone\": true,\r\n"
				+ "\"compositeRequest\": [\r\n"
				+ "{\r\n"
				+ "\"method\": \"PATCH\",\r\n"
				+ "\"url\": \"/services/data/v48.0/sobjects/Account/Premium_ID__c/"+Premiumid+"\",\r\n"
				+ "\"referenceId\": \"RefStudent9837"+randomNum+"\",\r\n"
				+ "\"body\": {\r\n"
				+ "\"Super_Status__c\": \"PE/Frist60\",\r\n"
				+ "\"Status__c\": \"New\",\r\n"
				+ "\"OwnerId\": \"0059G000000Hj5JQAS\"\r\n"
				+ "}\r\n"
				+ "}\r\n"
				+ "]\r\n"
				+ "}";
		return bodycontent;
	}
	
}

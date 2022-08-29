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


public class payLoad_EMICaseCreation{
	
	public static Logger log = LogManager.getLogger(payLoad_EMICaseCreation.class.getName());
	static Faker faker = new Faker();
	static int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
	static String firstName = faker.name().firstName().replaceAll("'","");
	static String lastName = faker.name().lastName().replaceAll("'","");
	static String parentName = faker.name().fullName().replaceAll("'","");
	static String Auto="Auto ";
	static long Premiumid= 52512495345L+randomNum;
	static int counter=10;
	static int counter1=1;
	ExcelData excelData = new ExcelData();
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	
	public static String EMICaseCreationRR(String Contactid,String Paymentid,String Appid) {
		
		String bodycontent="{\r\n"
				+ "  \"RecordTypeId\":\"0122w000001M5IcAAK\",\r\n"
				+ "  \"Super_Status__c\":\"Collection Due\",\r\n"
				+ "  \"ContactId\": \""+Contactid+"\",\r\n"
				+ "  \"Payment__c\":\""+Paymentid+"\",\r\n"
				+ "  \"Application_ID__c\":\""+Appid+"\"\r\n"
				+ "}";
		
		return bodycontent;
	}
	
}

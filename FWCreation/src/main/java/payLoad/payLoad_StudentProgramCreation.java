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


public class payLoad_StudentProgramCreation{
	
	public static Logger log = LogManager.getLogger(payLoad_StudentProgramCreation.class.getName());
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
	
	public static String StudentProgCreation(String Accountid, String Branchid) {
		
		String bodycontent="{\r\n"
				+ "    \"allOrNone\": true,\r\n"
				+ "    \"compositeRequest\": [\r\n"
				+ "           {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Program__c/Student_Enrolment_ID__c/STUPRGM006"+counter+"\",\r\n"
				+ "      \"referenceId\": \"STUPRGM000546"+counter+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Student__c\": \""+Accountid+"\",\r\n"
				+ "        \"Program__r\": {\r\n"
				+ "          \"Program_ID__c\": \"BCRPUCSY0001\"\r\n"
				+ "        },\r\n"
				+ "        \"Branch_Account__c\" : \""+Branchid+"\",\r\n"
				+ "        \"Status__c\": \"Active\",\r\n"
				+ "        \"Start_Date__c\": \"2022-04-27\",\r\n"
				+ "        \"Product_Type__c\": \"Trial\",\r\n"
				+ "        \"Class_Program_Type__c\":\"Complimentary BTC Upgrade\",\r\n"
				+ "        \"End_Date__c\": \"2022-12-11\"\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		counter++;
		counter1++;
		return bodycontent;
	}
	
}

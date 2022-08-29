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


public class payLoad_BranchAccCreation{
	
	public static Logger log = LogManager.getLogger(payLoad_BranchAccCreation.class.getName());
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
	
	public static String BranchAccountCreation(String QueueName,String ExternalId,String City) {
		
		String bodycontent="{\r\n"
				+ "    \"allOrNone\": true,\r\n"
				+ "    \"compositeRequest\": [\r\n"
				+ "          {\r\n"
				+ "      \"method\": \"POST\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Account\",\r\n"
				+ "      \"referenceId\": \"RefStudent93221"+counter1+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Name\": \""+QueueName+"\",\r\n"
				+ "        \"Branch_External_Id__c\" : \""+ExternalId+"\",\r\n"
				+ "        \"BillingCity\" : \""+City+"\",\r\n"				
				+ "        \"RecordTypeId\" : \"0125i000000Gqj9AAC\"\r\n"
				+ "      }\r\n"
				+ "          }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		counter1++;
		return bodycontent;
	}
	
}

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


public class payLoad_CaseCreation_Email{
	
	public static Logger log = LogManager.getLogger(payLoad_CaseCreation_Email.class.getName());
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
	
	public static String EmailCaseCreation() {
		
		String bodycontent="{\r\n"
				+ "  \"allOrNone\": true,\r\n"
				+ "  \"compositeRequest\": [\r\n"
				+ "        {\r\n"
				+ "            \"method\": \"POST\",\r\n"
				+ "            \"url\": \"/services/data/v53.0/composite/sobjects\",\r\n"
				+ "            \"referenceId\": \"caseList"+randomNum+""+counter1+"\",\r\n"
				+ "            \"body\": {\r\n"
				+ "                \"allOrNone\": true,\r\n"
				+ "                \"records\": [\r\n"
				+ "                    {\r\n"
				+ "                        \"attributes\": {\r\n"
				+ "                            \"type\": \"Case\",\r\n"
				+ "                            \"referenceId\": \"case"+randomNum+""+counter+"\"\r\n"
				+ "                        },\r\n"
				+ "                        \"Subject\": \"Cancellation Email "+counter1+"\",\r\n"
				+ "                        \"Status\": \"New\",\r\n"
				+ "                        \"Priority\": \"Medium\",\r\n"
				+ "                        \"RecordTypeId\": \"0122w000001LzZCAA0\"\r\n"			
				+ "                    }\r\n"
				+ "                ]\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "    \r\n"
				+ "}";
		
		counter++;
		counter1++;
		return bodycontent;
	}
	
	public static String EmailCaseCreation2() {
		
		String bodycontent="{\r\n"
				+ "  \"allOrNone\": true,\r\n"
				+ "  \"compositeRequest\": [\r\n"
				+ "        {\r\n"
				+ "            \"method\": \"POST\",\r\n"
				+ "            \"url\": \"/services/data/v53.0/composite/sobjects\",\r\n"
				+ "            \"referenceId\": \"caseList"+randomNum+""+counter1+"\",\r\n"
				+ "            \"body\": {\r\n"
				+ "                \"allOrNone\": true,\r\n"
				+ "                \"records\": [\r\n"
				+ "                    {\r\n"
				+ "                        \"attributes\": {\r\n"
				+ "                            \"type\": \"Case\",\r\n"
				+ "                            \"referenceId\": \"case"+randomNum+""+counter+"\"\r\n"
				+ "                        },\r\n"
				+ "                        \"Subject\": \"Cancellation Email "+counter1+"\",\r\n"
				+ "                        \"Status\": \"New\",\r\n"
				+ "                        \"Priority\": \"Medium\",\r\n"
				+ "                        \"RecordTypeId\": \"0122w000001LzZCAA0\"\r\n"			
				+ "                    }\r\n"
				+ "                ]\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "    \r\n"
				+ "}";
		
		counter++;
		counter1++;
		return bodycontent;
	}
	
	
}

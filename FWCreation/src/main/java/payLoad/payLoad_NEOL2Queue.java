package payLoad;
import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import resources.Utils;


public class payLoad_NEOL2Queue{
	
	public static Logger log = LogManager.getLogger(payLoad_NEOL2Queue.class.getName());
	static Faker faker = new Faker();
	static int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
	static String firstName = faker.name().firstName();
	static String lastName = faker.name().lastName();
	static String parentName = faker.name().fullName();
	static String Auto="Dummy ";
	
	public static String bodycontent() {
		//Adding the case via UI to verify the count on payload
		
		String bodycontent="{\r\n"
				+ "    \"queueName\" : \"NeoL2Queue\",\r\n"
				+ "    \"lastNdays\" : \"1\",\r\n"
				+ "    \"status\": \"Open,Closed\"\r\n"
				+ "}";
		
		
		return bodycontent;
	}
	
	public static String NEOL2QueueResponse_UAT() {
		String val = "OAuth ";
		String NEOL2Cases="";
		NEOL2Cases = given().header("Authorization", val + Utils.Access_Token())
				.header("Content-Type", "application/json").body(payLoad_NEOL2Queue.bodycontent()).when()
				.post("https://byjusprod--byjusuat.my.salesforce.com/services/apexrest/action/L2QueueData").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+NEOL2Cases);
		return NEOL2Cases;

	}
	
	public static String NEOL2QueueResponse_UATFC() {
		String val = "OAuth ";
		String NEOL2Cases="";
		NEOL2Cases = given().header("Authorization", val + Utils.Access_TokenUATFC())
				.header("Content-Type", "application/json").body(payLoad_NEOL2Queue.bodycontent()).when()
				.post("https://byjusprod--byjusuatfc.my.salesforce.com/services/apexrest/action/L2QueueData").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+NEOL2Cases);
		return NEOL2Cases;

	}
	
	public static String NEOL2QueueResponse_QA() {
		String val = "OAuth ";
		String NEOL2Cases="";
		NEOL2Cases = given().header("Authorization", val + Utils.Access_TokenQA())
				.header("Content-Type", "application/json").body(payLoad_NEOL2Queue.bodycontent()).when()
				.post("https://byjusprod--byjusqa.my.salesforce.com/services/apexrest/action/L2QueueData").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+NEOL2Cases);
		return NEOL2Cases;

	}
	
	public static String NEOL2QueueResponse_Prod() {
		String val = "OAuth ";
		String NEOL2Cases="";
		NEOL2Cases = given().header("Authorization", val + Utils.Access_TokenProd())
				.header("Content-Type", "application/json").body(payLoad_NEOL2Queue.bodycontent()).when()
				.post("https://byjusprod.my.salesforce.com/services/apexrest/action/L2QueueData").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+NEOL2Cases);
		return NEOL2Cases;

	}
	
	public static int NEOL2CaseCount_UAT() {

		
		JsonPath js1 = new JsonPath(NEOL2QueueResponse_UAT());
		int id = js1.getList("caseData").size();
		System.out.println("The count of object is: "+id);
		return id;

	}
	
	public static int NEOL2CaseCount_UATFC() {

		
		JsonPath js1 = new JsonPath(NEOL2QueueResponse_UATFC());
		int id = js1.getList("caseData").size();
		System.out.println("The count of object is: "+id);
		return id;

	}
	
	public static int NEOL2CaseCount_QA() {

		
		JsonPath js1 = new JsonPath(NEOL2QueueResponse_QA());
		int id = js1.getList("caseData").size();
		System.out.println("The count of object is: "+id);
		return id;

	}
	
	public static int NEOL2CaseCount_Prod() {


		JsonPath js1 = new JsonPath(NEOL2QueueResponse_Prod());
		int id = js1.getList("caseData").size();
		System.out.println("The count of object is: "+id);
		return id;

	}

}

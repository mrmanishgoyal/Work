package resources;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.sql.Timestamp;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	
	public static String Access_Token() {

		String ResponseExtracted = given().queryParams("username", "poms.prod.api@byjus.com.byjusuat")
				.queryParams("password", "byjusuatpoms1").queryParams("grant_type", "password")
				.queryParams("client_id",
						"3MVG9jGfKGWHGQBWiJLDv5IsXjTYYkEx3l_mlnzDj5zpmfenedzZEvXnzgRovhfmiNgMU.kv1Go9.xtYcbRdc")
				.queryParams("client_secret", "0C9C551A164C1E847CD4CB5C61D2F0EACC7832E448AE2B181135ACDC8202ACD1").when()
				.post("https://test.salesforce.com/services/oauth2/token").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println(ResponseExtracted);

		JsonPath js = new JsonPath(ResponseExtracted);
		String accessToken = js.get("access_token");
		System.out.println("The value of access Token is: " + accessToken);
		return accessToken;

		}
	
	public static String Access_TokenQA() {

		String ResponseExtracted = given().queryParams("username", "poms.prod.api@byjus.com.byjusqa")
				.queryParams("password", "Bangalore@1").queryParams("grant_type", "password")
				.queryParams("client_id",
						"3MVG9ct5lb5FGJTMnF1Hk7qHOR7pqJgvaxVhX1z6Y5B7L783a1GC0_kaqaAat1Fm5sKnCIY2G7rWU.eKlDGKA")
				.queryParams("client_secret", "6FDA938D65BD532E2B180553C991CA944F0A69F93BBB9D71216954104131E2DD").when()
				.post("https://test.salesforce.com/services/oauth2/token").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println(ResponseExtracted);

		JsonPath js = new JsonPath(ResponseExtracted);
		String accessToken = js.get("access_token");
		System.out.println("The value of access Token is: " + accessToken);
		return accessToken;

		}
	
	public static String Access_TokenUATFC() {

		String ResponseExtracted = given().queryParams("username", "poms.prod.api@byjus.com.byjusuatfc")
				.queryParams("password", "pomsapifc123").queryParams("grant_type", "password")
				.queryParams("client_id",
						"3MVG9CP2Kv.52YFurGOwC0BCkzzt7i_cVwX2AoDTS3_HqKZXHkGPCiP1nlwVE66vGvjlEQJ1yJqhLt_idW98L")
				.queryParams("client_secret", "87939A39575C9F91F1B92480C510B9D5DD19C137E7BEA2F43C4B9D47865D4CC6").when()
				.post("https://test.salesforce.com/services/oauth2/token").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println(ResponseExtracted);

		JsonPath js = new JsonPath(ResponseExtracted);
		String accessToken = js.get("access_token");
		System.out.println("The value of access Token is: " + accessToken);
		return accessToken;

		}
	
	public static String Access_TokenProd() {

		String ResponseExtracted = given().queryParams("username", "anil.kushwaha@byjus.com.byjusprodqa")
				.queryParams("password", "Welcome@123").queryParams("grant_type", "password")
				.queryParams("client_id",
						"3MVG9n_HvETGhr3BdcUS9axlz6Lgmj922Sf4.5Q5qq99rOM0PiInxsyT5ZEqn6IQe71JmFkskk6qiVj0Zn0zR")
				.queryParams("client_secret", "F5022DA988DD2EAC6418E4C394FEAC350A87850B507E89C58FCCF8766825CE36").when()
				.post("https://login.salesforce.com/services/oauth2/token").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println(ResponseExtracted);

		JsonPath js = new JsonPath(ResponseExtracted);
		String accessToken = js.get("access_token");
		System.out.println("The value of access Token is: " + accessToken);
		return accessToken;

		}
	
	
	public static String Access_TokenDev02() {

		String ResponseExtracted = given().queryParams("username", "rupali.janbandhu@byjus.com.byjusdev02")
				.queryParams("password", "Byjusrupalidev01").queryParams("grant_type", "password")
				.queryParams("client_id",
						"3MVG9ZUGg10Hh224dLrl9wXsHAMYmpJmh3O8tqdwWfy0B_G4r3GsYBHrDOVBtXHUqajjdfxD0rH6f3JaTb7bl")
				.queryParams("client_secret", "784DF99ECFD0C4B5CD3FF465CE7C95F0FB1348D75DD89816016C7859F94BE841").when()
				.post("https://test.salesforce.com/services/oauth2/token").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println(ResponseExtracted);

		JsonPath js = new JsonPath(ResponseExtracted);
		String accessToken = js.get("access_token");
		System.out.println("The value of access Token is: " + accessToken);
		return accessToken;

		}
	
		public static String getCurrentDateTime() {
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
			return formatter.format(currentDate.getTime());
		}
	
		public static Timestamp getCurrentTimeStamp() {
			  java.util.Date date= new java.util.Date();
		        Timestamp CurrTS=new Timestamp(date.getTime());
				return CurrTS;
		}
	
	    public static String getFileSeparator() {
	        return File.separator;
	    }
	
	
	

	
	
	public String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
}

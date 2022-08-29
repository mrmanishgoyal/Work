package testCases;

import static io.restassured.RestAssured.given;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import payLoad.payLoad_BTLA;
import resources.Utils;

public class TestPayloadBTLA {

	public static Logger log = LogManager.getLogger(payLoad_BTLA.class.getName());
	static Faker faker = new Faker();
	static int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
	static String firstName = faker.name().firstName().replaceAll("'","");
	static String lastName = faker.name().lastName().replaceAll("'","");
	static String parentName = faker.name().fullName().replaceAll("'","");
	static String Auto="Auto ";
	static long Premiumid= 52512495345L+randomNum;
	static int counter=1;
	
	public static String bodycontent() {
		//Add "+"+Premiumid+""+counter+"+" to Premium Id and "+randomNum+" to Student Enrollment Id
		//Add "+Auto+ ""+firstName+" to FirstName
		//Add "+lastName+" to LastName
		//Add "+parentName+" to ParentName
		
		String bodycontent="{\r\n"
				+ "  \"allOrNone\": true,\r\n"
				+ "  \"compositeRequest\": [\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Account/Premium_ID__c/"+Premiumid+""+counter+"\",\r\n"
				+ "      \"referenceId\": \"RefStudent"+Premiumid+""+counter+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"FirstName\": \"Test-"+Premiumid+""+counter+"\",\r\n"
				+ "        \"LastName\": \"User"+Premiumid+""+counter+"\",\r\n"
				+ "        \"Super_Status__c\": \"Mentoring\",\r\n"
				+ "        \"Status__c\": \"Mentoring In Progress\",\r\n"
				+ "        \"Sub_Status__c\": \"\",\r\n"
				+ "        \"Current_Class__c\": \"11\",\r\n"
				+ "        \"BillingState\": \"ANDHRA PRADESH\",\r\n"
				+ "        \"Board__c\":\"ICSE\",\r\n"
				+ "        \"Expiry_date__c\": \"2022-08-09\",\r\n"
				+ "        \"Manager__c\": \"\",\r\n"
				+ "        \"Lead_Category__c\": \"Non-EMI\",\r\n"
				+ "        \"Type_Of_Student__c\": \"\",\r\n"
				+ "        \"Student_Type__c\": \"\",\r\n"
				+ "        \"Parent_Name__c\": \"Parent Automation User "+Premiumid+""+counter+"\",\r\n"
				+ "        \"PersonEmail\": \"automation"+Premiumid+""+counter+"@user.com\",\r\n"
				+ "        \"Phone\": \"9876543210\",\r\n"
				+ "        \"Student_Email_ID__c\": \"automation"+Premiumid+""+counter+"@user.com\",\r\n"
				+ "        \"Student_Phone_Number__c\": \"9876543210\",\r\n"
				+ "        \"Alternate_Number_1__c\": \"9876543210\",\r\n"
				+ "        \"Alternate_Number_2__c\": \"9876543210\",\r\n"
				+ "        \"Alternate_Number_3__c\": \"9876543210\",\r\n"
				+ "        \"Created_On__c\": \"2020-07-03\",\r\n"
				+ "        \"BillingStreet\": \"133\",\r\n"
				+ "        \"BillingCountry\": \"India\",\r\n"
				+ "        \"BillingCity\": \"Bangalore\",\r\n"
				+ "        \"BillingPostalCode\": \"560001\",\r\n"
				+ "        \"ShippingStreet\": \"2212 18th main road\",\r\n"
				+ "        \"ShippingState\": \"ANDHRA PRADESH\",\r\n"
				+ "        \"ShippingCountry\": \"India\",\r\n"
				+ "        \"ShippingCity\": \"Bangalore\",\r\n"
				+ "        \"ShippingPostalCode\": \"560008\",\r\n"
				+ "        \"Parent_App_Code__c\": \"BS-1234\",\r\n"
				+ "        \"Session_1_Date__c\": \"2020-06-21\",\r\n"
				+ "        \"Shipped_Activity_Date__c\": \"2020-05-21\",\r\n"
				+ "        \"Order_Punched_By__c\": \"Byju Admin\",\r\n"
				+ "        \"E_Score__c\": \"\",\r\n"
				+ "        \"Version__c\": \"V1.2.1\",\r\n"
				+ "        \"High_Priority_PE_Date__c\": \"2020-05-14\",\r\n"
				+ "        \"Date_of_Birth__c\": \"1995-09-07\",\r\n"
				+ "        \"Last_online_time__c\": \"12:20 am\",\r\n"
				+ "        \"Back_End_Dashboard__c\": \"https://www.byju.com/resources/dashboard-examples\",\r\n"
				+ "        \"WhatsApp_OPTOUT__c\": true,\r\n"
				+ "        \"Creation_Agent__c\": \"Test Agent 11\",\r\n"
				+ "        \"Transfer_type__c\": \"\",\r\n"
				+ "        \"Notes__c\": \"24 test note\",\r\n"
				+ "        \"Trial_Type__c\": \"BTC Corporate Trial\",\r\n"
				+ "        \"BHLP_BOCP__c\": \"BHLP\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Sales_Order__c/Sales_Order_No__c/STUSALORD"+Premiumid+""+counter+"\",\r\n"
				+ "      \"referenceId\": \"RefSTUSALORD"+Premiumid+""+counter+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Name\": \"Order : K12 course-1st Order\",\r\n"
				+ "        \"Course_Name__c\": \"9th Physics\",\r\n"
				+ "        \"Order_Start_Date__c\": \"2020-05-11\",\r\n"
				+ "        \"Board__c\": \"ICSE\",\r\n"
				+ "        \"Order_Product_name__c\": \"9th CBSE (SD Card-Byjus) May-2022;10th CBSE (SD Card-Byjus) May-2023\",\r\n"
				+ "        \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "        \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "        \"Active_Order_Delivery_Date__c\": \"2020-05-17\",\r\n"
				+ "        \"K3_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "        \"Active_Order_Value__c\": \"45000\",\r\n"
				+ "        \"Status__c\": \"Confirmed\",\r\n"
				+ "        \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "        \"Active_Order_Shipped_Date__c\": \"2020-06-19\",\r\n"
				+ "        \"K12_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "        \"Order_Type__c\": \"Fresh order\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects/Order__c/Order_No__c\",\r\n"
				+ "      \"referenceId\": \"orderList\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Order__c\",\r\n"
				+ "              \"referenceId\": \"order1\"\r\n"
				+ "            },\r\n"
				+ "            \"Order_No__c\": \"STUSUBORD1"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Name\": \"Order : K12 course-1st Order\",\r\n"
				+ "            \"Course_Name__c\": \"9th Physics\",\r\n"
				+ "            \"Order_Start_Date__c\": \"2020-05-11\",\r\n"
				+ "            \"Board__c\": \"ICSE\",\r\n"
				+ "            \"Order_Product_name__c\": \"9th CBSE (SD Card-Byjus) May-2022\",\r\n"
				+ "            \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "            \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "            \"Active_Order_Delivery_Date__c\": \"2020-05-17\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"Active_Order_Value__c\": \"45000\",\r\n"
				+ "            \"Status__c\": \"Confirmed\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"K12_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"STUSALORD"+Premiumid+""+counter+"\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Order__c\",\r\n"
				+ "              \"referenceId\": \"order2\"\r\n"
				+ "            },\r\n"
				+ "            \"Order_No__c\": \"STUSUBORD2"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Name\": \"Order : K12 course-2nd Order\",\r\n"
				+ "            \"Course_Name__c\": \"9th Math\",\r\n"
				+ "            \"Order_Start_Date__c\": \"2020-05-11\",\r\n"
				+ "            \"Board__c\": \"ICSE\",\r\n"
				+ "            \"Order_Product_name__c\": \"10th CBSE (SD Card-Byjus) May-2023\",\r\n"
				+ "            \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "            \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "            \"Active_Order_Delivery_Date__c\": \"2020-05-17\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"Active_Order_Value__c\": \"20000\",\r\n"
				+ "            \"Status__c\": \"Confirmed\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"K12_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"STUSALORD"+Premiumid+""+counter+"\"\r\n"
				+ "            }\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects/Payment__c/Payment_Reference_ID__c\",\r\n"
				+ "      \"referenceId\": \"paymentList\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Payment__c\",\r\n"
				+ "              \"referenceId\": \"payment1\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment_Reference_ID__c\": \"PAY1"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Payment_Amount__c\": \"4500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Down payment\",\r\n"
				+ "            \"Payment_Method__c\": \"PayU\",\r\n"
				+ "            \"Payment_Date__c\": \"2020-05-21\",\r\n"
				+ "            \"Tenurity__c\": \"\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"LoanPay1"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"STUSUBORD1"+Premiumid+""+counter+"\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"STUSALORD"+Premiumid+""+counter+"\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Payment__c\",\r\n"
				+ "              \"referenceId\": \"payment2\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment_Reference_ID__c\": \"PAY2"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Payment_Amount__c\": \"40500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Loan\",\r\n"
				+ "            \"Payment_Method__c\": \"IIFL\",\r\n"
				+ "            \"Payment_Date__c\": \"2020-05-21\",\r\n"
				+ "            \"Tenurity__c\": \"12\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"3375\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"LoanPay2"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"STUSUBORD1"+Premiumid+""+counter+"\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"STUSALORD"+Premiumid+""+counter+"\"\r\n"
				+ "            }\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Program__c/Student_Enrolment_ID__c/STUPRGM"+Premiumid+""+counter+"\",\r\n"
				+ "      \"referenceId\": \"STUPRGM100106\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Student_Sales_Order__c\": \"@{RefSTUSALORD"+Premiumid+""+counter+".id}\",\r\n"
				+ "        \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "        \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\",\r\n"
				+ "        \"Program__r\": {\r\n"
				+ "          \"Program_ID__c\": \"BCRPUCSY0001\"\r\n"
				+ "        },\r\n"
				+ "        \"Status__c\": \"Active\",\r\n"
				+ "        \"Start_Date__c\": \"2022-04-27\",\r\n"
				+ "        \"Product_Type__c\": \"Trial\",\r\n"
				+ "        \"Class_Program_Type__c\":\"BTC Corporate Trial\",\r\n"
				+ "        \"End_Date__c\": \"2022-12-11\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"POST\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects\",\r\n"
				+ "      \"referenceId\": \"studentOrderList\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Orders__c\",\r\n"
				+ "              \"referenceId\": \"studentOrder1\"\r\n"
				+ "            },\r\n"
				+ "            \"Name\": \"Student Order : "+Premiumid+""+counter+" 1\",\r\n"
				+ "            \"Order__c\": \"@{orderList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\"\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Orders__c\",\r\n"
				+ "              \"referenceId\": \"studentOrder2\"\r\n"
				+ "            },\r\n"
				+ "            \"Name\": \"Student Order : "+Premiumid+""+counter+" 2\",\r\n"
				+ "            \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"POST\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects\",\r\n"
				+ "      \"referenceId\": \"RefPaymentPD1"+Premiumid+""+counter+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\"\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		counter++;
		return bodycontent;
	}
	
	public static String bodycontent_BTC() {
		//Add "+"+Premiumid+""+counter+"+" to Premium Id and "+randomNum+" to Student Enrollment Id
		//Add "+Auto+ ""+firstName+" to FirstName
		//Add "+lastName+" to LastName
		//Add "+parentName+" to ParentName
		
		String bodycontent="{\r\n"
				+ "  \"allOrNone\": true,\r\n"
				+ "  \"compositeRequest\": [\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Account/Premium_ID__c/"+Premiumid+""+counter+"\",\r\n"
				+ "      \"referenceId\": \"RefStudent"+Premiumid+""+counter+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"FirstName\": \"Test-"+Premiumid+""+counter+"\",\r\n"
				+ "        \"LastName\": \"User"+Premiumid+""+counter+"\",\r\n"
				+ "        \"Super_Status__c\": \"Mentoring\",\r\n"
				+ "        \"Status__c\": \"Mentoring In Progress\",\r\n"
				+ "        \"Sub_Status__c\": \"\",\r\n"
				+ "        \"Current_Class__c\": \"11\",\r\n"
				+ "        \"BillingState\": \"ANDHRA PRADESH\",\r\n"
				+ "        \"Board__c\":\"ICSE\",\r\n"
				+ "        \"Expiry_date__c\": \"2022-08-09\",\r\n"
				+ "        \"Manager__c\": \"\",\r\n"
				+ "        \"Lead_Category__c\": \"Non-EMI\",\r\n"
				+ "        \"Type_Of_Student__c\": \"\",\r\n"
				+ "        \"Student_Type__c\": \"\",\r\n"
				+ "        \"Parent_Name__c\": \"Parent Automation User "+Premiumid+""+counter+"\",\r\n"
				+ "        \"PersonEmail\": \"automation"+Premiumid+""+counter+"@user.com\",\r\n"
				+ "        \"Phone\": \"9876543210\",\r\n"
				+ "        \"Student_Email_ID__c\": \"automation"+Premiumid+""+counter+"@user.com\",\r\n"
				+ "        \"Student_Phone_Number__c\": \"9876543210\",\r\n"
				+ "        \"Alternate_Number_1__c\": \"9876543210\",\r\n"
				+ "        \"Alternate_Number_2__c\": \"9876543210\",\r\n"
				+ "        \"Alternate_Number_3__c\": \"9876543210\",\r\n"
				+ "        \"Created_On__c\": \"2020-07-03\",\r\n"
				+ "        \"BillingStreet\": \"133\",\r\n"
				+ "        \"BillingCountry\": \"India\",\r\n"
				+ "        \"BillingCity\": \"Bangalore\",\r\n"
				+ "        \"BillingPostalCode\": \"560001\",\r\n"
				+ "        \"ShippingStreet\": \"2212 18th main road\",\r\n"
				+ "        \"ShippingState\": \"ANDHRA PRADESH\",\r\n"
				+ "        \"ShippingCountry\": \"India\",\r\n"
				+ "        \"ShippingCity\": \"Bangalore\",\r\n"
				+ "        \"ShippingPostalCode\": \"560008\",\r\n"
				+ "        \"Parent_App_Code__c\": \"BS-1234\",\r\n"
				+ "        \"Session_1_Date__c\": \"2020-06-21\",\r\n"
				+ "        \"Shipped_Activity_Date__c\": \"2020-05-21\",\r\n"
				+ "        \"Order_Punched_By__c\": \"Byju Admin\",\r\n"
				+ "        \"E_Score__c\": \"\",\r\n"
				+ "        \"Version__c\": \"V1.2.1\",\r\n"
				+ "        \"High_Priority_PE_Date__c\": \"2020-05-14\",\r\n"
				+ "        \"Date_of_Birth__c\": \"1995-09-07\",\r\n"
				+ "        \"Last_online_time__c\": \"12:20 am\",\r\n"
				+ "        \"Back_End_Dashboard__c\": \"https://www.byju.com/resources/dashboard-examples\",\r\n"
				+ "        \"WhatsApp_OPTOUT__c\": true,\r\n"
				+ "        \"Creation_Agent__c\": \"Test Agent 11\",\r\n"
				+ "        \"Transfer_type__c\": \"\",\r\n"
				+ "        \"Notes__c\": \"24 test note\",\r\n"
				+ "        \"Trial_Type__c\": \"BTC Corporate Trial\",\r\n"
				+ "        \"BHLP_BOCP__c\": \"BHLP\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Sales_Order__c/Sales_Order_No__c/STUSALORD"+Premiumid+""+counter+"\",\r\n"
				+ "      \"referenceId\": \"RefSTUSALORD"+Premiumid+""+counter+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Name\": \"Order : K12 course-1st Order\",\r\n"
				+ "        \"Course_Name__c\": \"9th Physics\",\r\n"
				+ "        \"Order_Start_Date__c\": \"2020-05-11\",\r\n"
				+ "        \"Board__c\": \"ICSE\",\r\n"
				+ "        \"Order_Product_name__c\": \"9th CBSE (SD Card-Byjus) May-2022;10th CBSE (SD Card-Byjus) May-2023\",\r\n"
				+ "        \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "        \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "        \"Active_Order_Delivery_Date__c\": \"2020-05-17\",\r\n"
				+ "        \"K3_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "        \"Active_Order_Value__c\": \"45000\",\r\n"
				+ "        \"Status__c\": \"Confirmed\",\r\n"
				+ "        \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "        \"Active_Order_Shipped_Date__c\": \"2020-06-19\",\r\n"
				+ "        \"K12_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "        \"Order_Type__c\": \"Fresh order\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects/Order__c/Order_No__c\",\r\n"
				+ "      \"referenceId\": \"orderList\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Order__c\",\r\n"
				+ "              \"referenceId\": \"order1\"\r\n"
				+ "            },\r\n"
				+ "            \"Order_No__c\": \"STUSUBORD1"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Name\": \"Order : K12 course-1st Order\",\r\n"
				+ "            \"Course_Name__c\": \"9th Physics\",\r\n"
				+ "            \"Order_Start_Date__c\": \"2020-05-11\",\r\n"
				+ "            \"Board__c\": \"ICSE\",\r\n"
				+ "            \"Order_Product_name__c\": \"9th CBSE (SD Card-Byjus) May-2022\",\r\n"
				+ "            \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "            \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "            \"Active_Order_Delivery_Date__c\": \"2020-05-17\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"Active_Order_Value__c\": \"45000\",\r\n"
				+ "            \"Status__c\": \"Confirmed\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"K12_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"STUSALORD"+Premiumid+""+counter+"\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Order__c\",\r\n"
				+ "              \"referenceId\": \"order2\"\r\n"
				+ "            },\r\n"
				+ "            \"Order_No__c\": \"STUSUBORD2"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Name\": \"Order : K12 course-2nd Order\",\r\n"
				+ "            \"Course_Name__c\": \"9th Math\",\r\n"
				+ "            \"Order_Start_Date__c\": \"2020-05-11\",\r\n"
				+ "            \"Board__c\": \"ICSE\",\r\n"
				+ "            \"Order_Product_name__c\": \"10th CBSE (SD Card-Byjus) May-2023\",\r\n"
				+ "            \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "            \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "            \"Active_Order_Delivery_Date__c\": \"2020-05-17\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"Active_Order_Value__c\": \"20000\",\r\n"
				+ "            \"Status__c\": \"Confirmed\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"K12_Validity_End_Date__c\": \"2020-06-19\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"STUSALORD"+Premiumid+""+counter+"\"\r\n"
				+ "            }\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects/Payment__c/Payment_Reference_ID__c\",\r\n"
				+ "      \"referenceId\": \"paymentList\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Payment__c\",\r\n"
				+ "              \"referenceId\": \"payment1\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment_Reference_ID__c\": \"PAY1"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Payment_Amount__c\": \"4500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Down payment\",\r\n"
				+ "            \"Payment_Method__c\": \"PayU\",\r\n"
				+ "            \"Payment_Date__c\": \"2020-05-21\",\r\n"
				+ "            \"Tenurity__c\": \"\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"LoanPay1"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"STUSUBORD1"+Premiumid+""+counter+"\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"STUSALORD"+Premiumid+""+counter+"\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Payment__c\",\r\n"
				+ "              \"referenceId\": \"payment2\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment_Reference_ID__c\": \"PAY2"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Payment_Amount__c\": \"40500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Loan\",\r\n"
				+ "            \"Payment_Method__c\": \"IIFL\",\r\n"
				+ "            \"Payment_Date__c\": \"2020-05-21\",\r\n"
				+ "            \"Tenurity__c\": \"12\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"3375\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"LoanPay2"+Premiumid+""+counter+"\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"STUSUBORD1"+Premiumid+""+counter+"\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"STUSALORD"+Premiumid+""+counter+"\"\r\n"
				+ "            }\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Program__c/Student_Enrolment_ID__c/STUPRGM"+Premiumid+""+counter+"\",\r\n"
				+ "      \"referenceId\": \"STUPRGM"+Premiumid+""+counter+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Student_Sales_Order__c\": \"@{RefSTUSALORD"+Premiumid+""+counter+".id}\",\r\n"
				+ "        \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "        \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\",\r\n"
				+ "        \"Program__r\": {\r\n"
				+ "          \"Program_ID__c\": \"BCRPUCSY0001\"\r\n"
				+ "        },\r\n"
				+ "        \"Branch_Account__c\": \"0019G000002h3bhQAA\",\r\n"
				+ "        \"Status__c\": \"Active\",\r\n"
				+ "        \"Start_Date__c\": \"2022-04-27\",\r\n"
				+ "        \"Product_Type__c\": \"Trial\",\r\n"
				+ "        \"Class_Program_Type__c\":\"BTC Corporate Trial\",\r\n"
				+ "        \"End_Date__c\": \"2022-12-11\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"POST\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects\",\r\n"
				+ "      \"referenceId\": \"studentOrderList\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Orders__c\",\r\n"
				+ "              \"referenceId\": \"studentOrder1\"\r\n"
				+ "            },\r\n"
				+ "            \"Name\": \"Student Order : "+Premiumid+""+counter+" 1\",\r\n"
				+ "            \"Order__c\": \"@{orderList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\"\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Orders__c\",\r\n"
				+ "              \"referenceId\": \"studentOrder2\"\r\n"
				+ "            },\r\n"
				+ "            \"Name\": \"Student Order : "+Premiumid+""+counter+" 2\",\r\n"
				+ "            \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"POST\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects\",\r\n"
				+ "      \"referenceId\": \"RefPaymentPD1"+Premiumid+""+counter+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\"\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+""+counter+".id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		counter++;
		return bodycontent;
	}
	public static String AccountCreationResponseTest_UAT() {
		String val = "OAuth ";
		String StudentCreation="";
		StudentCreation = given().header("Authorization", val + Utils.Access_Token())
				.header("Content-Type", "application/json").body(bodycontent()).when()
				.post("https://byjusprod--byjusuat.my.salesforce.com/services/data/v48.0/composite").then().log().all()
				.extract().response().asString();
        //log.info("The response is: "+StudentCreation);
		return StudentCreation;

	}
	
	public static String AccountCreationResponseTestBTC_UAT() {
		String val = "OAuth ";
		String StudentCreation="";
		StudentCreation = given().header("Authorization", val + Utils.Access_Token())
				.header("Content-Type", "application/json").body(bodycontent_BTC()).when()
				.post("https://byjusprod--byjusuat.my.salesforce.com/services/data/v48.0/composite").then().log().all()
				.extract().response().asString();
        //log.info("The response is: "+StudentCreation);
		return StudentCreation;

	}
	
	public static String AccountidCreationResponseTestBTC_UAT() {

		String id="";
		JsonPath js1 = new JsonPath(AccountCreationResponseTestBTC_UAT());
		id = js1.get("compositeResponse[0].body.id");
		log.info("The value of id is: " + id);
		return id;

	}
	
	public static String AccountidCreationResponseTest_UAT() {

		String id="";
		JsonPath js1 = new JsonPath(AccountCreationResponseTest_UAT());
		id = js1.get("compositeResponse[0].body.id");
		log.info("The value of id is: " + id);
		return id;

	}
	
	public static String AccountCreationResponseTest_UATFC() {
		String val = "OAuth ";
		String StudentCreation="";
		StudentCreation = given().header("Authorization", val + Utils.Access_TokenUATFC())
				.header("Content-Type", "application/json").body(bodycontent()).when()
				.post("https://byjusprod--byjusuatfc.my.salesforce.com/services/data/v48.0/composite").then().log().all()
				.extract().response().asString();
        //log.info("The response is: "+StudentCreation);
		return StudentCreation;

	}
	
	public static String AccountidCreationResponseTest_UATFC() {

		String id="";
		JsonPath js1 = new JsonPath(AccountCreationResponseTest_UATFC());
		id = js1.get("compositeResponse[0].body.id");
		log.info("The value of id is: " + id);
		return id;

	}
	
	public static String AccountCreationResponseTest_Dev02() {
		String val = "OAuth ";
		String StudentCreation="";
		StudentCreation = given().header("Authorization", val + Utils.Access_TokenDev02())
				.header("Content-Type", "application/json").body(bodycontent()).when()
				.post("https://byjusprod--byjusdev02.my.salesforce.com/services/data/v48.0/composite").then().log().all()
				.extract().response().asString();
        //log.info("The response is: "+StudentCreation);
		return StudentCreation;

	}
	
	public static String AccountidCreationResponseTest_Dev02() {

		String id="";
		JsonPath js1 = new JsonPath(AccountCreationResponseTest_Dev02());
		id = js1.get("compositeResponse[0].body.id");
		log.info("The value of id is: " + id);
		return id;

	}
}

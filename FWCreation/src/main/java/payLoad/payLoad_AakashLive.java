package payLoad;

import static io.restassured.RestAssured.given;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import resources.Utils;

public class payLoad_AakashLive {

	
	public static Logger log = LogManager.getLogger(payLoad_BTLA.class.getName());
	static Faker faker = new Faker();
	static int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
	static String firstName = faker.name().firstName().replaceAll("'","");
	static String lastName = faker.name().lastName().replaceAll("'","");
	static String parentName = faker.name().fullName().replaceAll("'","");
	static String Auto="Dummy ";
	static long Premiumid= 52512495345L+randomNum;
	
	public static String bodycontent() {
		//Add "+randomNum+" to Premium Id and Student Enrollment Id
		//Add "+Auto+ ""+firstName+" to FirstName
		//Add "+lastName+" to LastName
		//Add "+parentName+" to ParentName
		
		String bodycontent="{\r\n"
				+ "  \"allOrNone\": true,\r\n"
				+ "  \"compositeRequest\": [\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Account/Premium_ID__c/"+Premiumid+"\",\r\n"
				+ "      \"referenceId\": \"RefStudent9837459734123\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"FirstName\": \""+Auto+""+firstName+"\",\r\n"
				+ "        \"LastName\": \""+lastName+"\",\r\n"
				+ "        \"Super_Status__c\": \"PE/Frist60\",\r\n"
				+ "        \"Status__c\": \"New\",\r\n"
				+ "        \"Sub_Status__c\": \"\",\r\n"
				+ "        \"Current_Class__c\": \"2\",\r\n"
				+ "        \"BillingState\": \"Karnataka (KA)\",\r\n"
				+ "        \"Expiry_date__c\": \"2022-08-09\",\r\n"
				+ "        \"Manager__c\": \"\",\r\n"
				+ "        \"Lead_Category__c\": \"Non-EMI\",\r\n"
				+ "        \"Type_Of_Student__c\": \"\",\r\n"
				+ "        \"Student_Type__c\": \"\",\r\n"
				+ "        \"Parent_Name__c\": \""+parentName+"\",\r\n"
				+ "        \"PersonEmail\": \"arabinda.kar@venerasdftesolutions.com\",\r\n"
				+ "        \"Phone\": \"9739739731\",\r\n"
				+ "        \"Board__c\": \"ICSE\",\r\n"
				+ "        \"Student_Email_ID__c\": \"std@std.com\",\r\n"
				+ "        \"Student_Phone_Number__c\": \"9739739732\",\r\n"
				+ "        \"Alternate_Number_1__c\": \"9739739733\",\r\n"
				+ "        \"Alternate_Number_2__c\": \"9739739734\",\r\n"
				+ "        \"Alternate_Number_3__c\": \"9739739735\",\r\n"
				+ "        \"Created_On__c\": \"2022-04-01\",\r\n"
				+ "        \"BillingStreet\": \"133\",\r\n"
				+ "        \"BillingCountry\": \"India\",\r\n"
				+ "        \"BillingCity\": \"\",\r\n"
				+ "        \"BillingPostalCode\": \"\",\r\n"
				+ "        \"ShippingStreet\": \"2212 18th main road\",\r\n"
				+ "        \"ShippingState\": \"Karnataka (KA)\",\r\n"
				+ "        \"ShippingCountry\": \"India\",\r\n"
				+ "        \"ShippingCity\": \"Bangalore\",\r\n"
				+ "        \"ShippingPostalCode\": \"560008\",\r\n"
				+ "        \"Parent_App_Code__c\": \"BS-1234\",\r\n"
				+ "        \"Session_1_Date__c\": \"2022-04-01\",\r\n"
				+ "        \"Shipped_Activity_Date__c\": \"2022-04-01\",\r\n"
				+ "        \"Order_Punched_By__c\": \"Byju Admin\",\r\n"
				+ "        \"E_Score__c\": \"\",\r\n"
				+ "        \"Version__c\": \"V1.2.1\",\r\n"
				+ "        \"High_Priority_PE_Date__c\": \"2022-04-01\",\r\n"
				+ "        \"Date_of_Birth__c\": \"1995-09-07\",\r\n"
				+ "        \"Last_online_time__c\": \"12:20 am\",\r\n"
				+ "        \"Back_End_Dashboard__c\": \"https://www.byju.com/resources/dashboard-examples\",\r\n"
				+ "        \"WhatsApp_OPTOUT__c\": true,\r\n"
				+ "        \"Creation_Agent__c\": \"Test Agent 11\",\r\n"
				+ "        \"Transfer_type__c\": \"\",\r\n"
				+ "        \"Notes__c\": \"24 test note\",\r\n"
				+ "        \"BHLP_BOCP__c\": \"BHLP\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Sales_Order__c/Sales_Order_No__c/SSORD99467465454387988\",\r\n"
				+ "      \"referenceId\": \"RefSSORD99467465454387988\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Name\": \"Order : K3 course-1st Order\",\r\n"
				+ "        \"Course_Name__c\": \"2nd Maths\",\r\n"
				+ "        \"Order_Start_Date__c\": \"2022-04-01\",\r\n"
				+ "        \"Board__c\": \"ICSE\",\r\n"
				+ "        \"Order_Product_name__c\": \"Product 1;Product 2;Product 3\",\r\n"
				+ "        \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "        \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "        \"Active_Order_Delivery_Date__c\": \"2022-04-01\",\r\n"
				+ "        \"K3_Validity_End_Date__c\": \"2022-04-01\",\r\n"
				+ "        \"Active_Order_Value__c\": \"45000\",\r\n"
				+ "        \"Status__c\": \"Confirmed\",\r\n"
				+ "        \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "        \"Active_Order_Shipped_Date__c\": \"2022-04-01\",\r\n"
				+ "        \"K3_Validity_End_Date__c\": \"2022-04-01\",\r\n"
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
				+ "            \"Order_No__c\": \"ORD12349837459734188\",\r\n"
				+ "            \"Name\": \"Order : K3 course-1st Order\",\r\n"
				+ "            \"Course_Name__c\": \"9th Physics\",\r\n"
				+ "            \"Order_Start_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"Board__c\": \"ICSE\",\r\n"
				+ "            \"Order_Product_name__c\": \"Product 1;Product 2;Product 3\",\r\n"
				+ "            \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "            \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "            \"Active_Order_Delivery_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"Active_Order_Value__c\": \"45000\",\r\n"
				+ "            \"Status__c\": \"Confirmed\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD99467465454387988\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Order__c\",\r\n"
				+ "              \"referenceId\": \"order2\"\r\n"
				+ "            },\r\n"
				+ "            \"Order_No__c\": \"ORD1239837459734123\",\r\n"
				+ "            \"Name\": \"Order : K3 course-2nd Order\",\r\n"
				+ "            \"Course_Name__c\": \"9th Math\",\r\n"
				+ "            \"Order_Start_Date__c\": \"2020-05-11\",\r\n"
				+ "            \"Board__c\": \"ICSE\",\r\n"
				+ "            \"Order_Product_name__c\": \"Product 1;\",\r\n"
				+ "            \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "            \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "            \"Active_Order_Delivery_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"Active_Order_Value__c\": \"20000\",\r\n"
				+ "            \"Status__c\": \"Confirmed\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD99467465454387988\"\r\n"
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
				+ "            \"Payment_Reference_ID__c\": \"PD234555239837459734123\",\r\n"
				+ "            \"Payment_Amount__c\": \"4500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Down payment\",\r\n"
				+ "            \"Payment_Method__c\": \"PayU\",\r\n"
				+ "            \"Payment_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"Tenurity__c\": \"\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"ORD12349837459734188\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD99467465454387988\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Payment__c\",\r\n"
				+ "              \"referenceId\": \"payment2\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment_Reference_ID__c\": \"PD198374597341231\",\r\n"
				+ "            \"Payment_Amount__c\": \"40500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Loan\",\r\n"
				+ "            \"Payment_Method__c\": \"IIFL\",\r\n"
				+ "            \"Payment_Date__c\": \"2022-04-01\",\r\n"
				+ "            \"Tenurity__c\": \"12\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"3375\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"92211464819048\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"ORD12349837459734188\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD99467465454387988\"\r\n"
				+ "            }\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Program__c/Student_Enrolment_ID__c/STUPRGM10099654123"+randomNum+"\",\r\n"
				+ "      \"referenceId\": \"STUPRGM100106\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Student_Sales_Order__c\": \"@{RefSSORD99467465454387988.id}\",\r\n"
				+ "        \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "        \"Student__c\": \"@{RefStudent9837459734123.id}\",\r\n"
				+ "        \"Program__r\": {\r\n"
				+ "          \"Program_ID__c\": \"ACOPKXSY0001\"\r\n"
				+ "        },\r\n"
				+ "        \"Status__c\": \"Active\",\r\n"
				+ "        \"Start_Date__c\": \"2022-04-01\",\r\n"
				+ "        \"End_Date__c\": \"2022-10-01\",\r\n"
				+ "        \"Product_Type__c\": \"Paid\",\r\n"
				+ "        \"Class_Program_Type__c\": \"Regular\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
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
				+ "            \"Name\": \"Student Order : 9837459734123\",\r\n"
				+ "            \"Order__c\": \"@{orderList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent9837459734123.id}\"\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Orders__c\",\r\n"
				+ "              \"referenceId\": \"studentOrder2\"\r\n"
				+ "            },\r\n"
				+ "            \"Name\": \"Student Order : 234555234444100\",\r\n"
				+ "            \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent9837459734123.id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects\",\r\n"
				+ "      \"referenceId\": \"RefPaymentPD198374597341232272727\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent9837459734123.id}\"\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent9837459734123.id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		
		return bodycontent;
	}
	
	public static String bodycontent_Prod() {
		//Add "+randomNum+" to Premium Id and Student Enrollment Id
		//Add "+Auto+ ""+firstName+" to FirstName
		//Add "+lastName+" to LastName
		//Add "+parentName+" to ParentName
		
		String bodycontent="{\r\n"
				+ "  \"allOrNone\": true,\r\n"
				+ "  \"compositeRequest\": [\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Account/Premium_ID__c/"+Premiumid+"\",\r\n"
				+ "      \"referenceId\": \"RefStudent"+Premiumid+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"FirstName\": \""+Auto+""+firstName+"\",\r\n"
				+ "        \"LastName\": \""+lastName+"\",\r\n"
				+ "        \"Super_Status__c\": \"PE/First60\",\r\n"
				+ "        \"Status__c\": \"New\",\r\n"
				+ "        \"Sub_Status__c\": \"\",\r\n"
				+ "        \"Current_Class__c\": \"9\",\r\n"
				+ "        \"BillingState\": \"Karnataka (KA)\",\r\n"
				+ "        \"Expiry_date__c\": \"2022-08-09\",\r\n"
				+ "        \"Manager__c\": \"\",\r\n"
				+ "        \"Lead_Category__c\": \"Non-EMI\",\r\n"
				+ "        \"Type_Of_Student__c\": \"\",\r\n"
				+ "        \"Student_Type__c\": \"\",\r\n"
				+ "        \"Parent_Name__c\": \""+parentName+"\",\r\n"
				+ "        \"PersonEmail\": \"arabinda.kar@venerasdftesolutions.com\",\r\n"
				+ "        \"Phone\": \"9739739731\",\r\n"
				+ "        \"Board__c\": \"ICSE\",\r\n"
				+ "        \"Student_Email_ID__c\": \"std@std.com\",\r\n"
				+ "        \"Student_Phone_Number__c\": \"9739739732\",\r\n"
				+ "        \"Alternate_Number_1__c\": \"9739739733\",\r\n"
				+ "        \"Alternate_Number_2__c\": \"9739739734\",\r\n"
				+ "        \"Alternate_Number_3__c\": \"9739739735\",\r\n"
				+ "        \"Created_On__c\": \"2022-03-03\",\r\n"
				+ "        \"BillingStreet\": \"133\",\r\n"
				+ "        \"BillingCountry\": \"IN\",\r\n"
				+ "        \"BillingCity\": \"\",\r\n"
				+ "        \"BillingPostalCode\": \"\",\r\n"
				+ "        \"ShippingStreet\": \"2212 18th main road\",\r\n"
				+ "        \"ShippingState\": \"Karnataka (KA)\",\r\n"
				+ "        \"ShippingCountry\": \"IN\",\r\n"
				+ "        \"ShippingCity\": \"Bangalore\",\r\n"
				+ "        \"ShippingPostalCode\": \"560008\",\r\n"
				+ "        \"Parent_App_Code__c\": \"BS-1234\",\r\n"
				+ "        \"Session_1_Date__c\": \"2022-01-21\",\r\n"
				+ "        \"Shipped_Activity_Date__c\": \"2022-03-10\",\r\n"
				+ "        \"Order_Punched_By__c\": \"Byju Admin\",\r\n"
				+ "        \"E_Score__c\": \"\",\r\n"
				+ "        \"Version__c\": \"V1.2.1\",\r\n"
				+ "        \"High_Priority_PE_Date__c\": \"2022-03-14\",\r\n"
				+ "        \"Date_of_Birth__c\": \"1995-09-07\",\r\n"
				+ "        \"Last_online_time__c\": \"12:20 am\",\r\n"
				+ "        \"Back_End_Dashboard__c\": \"https://www.byju.com/resources/dashboard-examples\",\r\n"
				+ "        \"WhatsApp_OPTOUT__c\": true,\r\n"
				+ "        \"Creation_Agent__c\": \"Test Agent 11\",\r\n"
				+ "        \"Transfer_type__c\": \"\",\r\n"
				+ "        \"Notes__c\": \"24 test note\",\r\n"
				+ "        \"BHLP_BOCP__c\": \"BHLP\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Sales_Order__c/Sales_Order_No__c/SSORD"+Premiumid+"\",\r\n"
				+ "      \"referenceId\": \"RefSSORD"+Premiumid+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Name\": \"Order : K12 course-1st Order\",\r\n"
				+ "        \"Course_Name__c\": \"9th Physics\",\r\n"
				+ "        \"Order_Start_Date__c\": \"2022-03-11\",\r\n"
				+ "        \"Board__c\": \"ICSE\",\r\n"
				+ "        \"Order_Product_name__c\": \"Product 1;Product 2;Product 3\",\r\n"
				+ "        \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "        \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "        \"Active_Order_Delivery_Date__c\": \"2022-03-17\",\r\n"
				+ "        \"K3_Validity_End_Date__c\": \"2022-03-19\",\r\n"
				+ "        \"Active_Order_Value__c\": \"45000\",\r\n"
				+ "        \"Status__c\": \"Confirmed\",\r\n"
				+ "        \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "        \"Active_Order_Shipped_Date__c\": \"2022-03-19\",\r\n"
				+ "        \"K12_Validity_End_Date__c\": \"2022-03-19\",\r\n"
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
				+ "            \"Order_No__c\": \"ORD1"+Premiumid+"\",\r\n"
				+ "            \"Name\": \"Order : K12 course-1st Order\",\r\n"
				+ "            \"Course_Name__c\": \"9th Physics\",\r\n"
				+ "            \"Order_Start_Date__c\": \"2022-03-11\",\r\n"
				+ "            \"Board__c\": \"ICSE\",\r\n"
				+ "            \"Order_Product_name__c\": \"Product 1;Product 2;Product 3\",\r\n"
				+ "            \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "            \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "            \"Active_Order_Delivery_Date__c\": \"2022-03-17\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"Active_Order_Value__c\": \"45000\",\r\n"
				+ "            \"Status__c\": \"Punched\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Punched\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"K12_Validity_End_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD"+Premiumid+"\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Order__c\",\r\n"
				+ "              \"referenceId\": \"order2\"\r\n"
				+ "            },\r\n"
				+ "            \"Order_No__c\": \"ORD2"+Premiumid+"\",\r\n"
				+ "            \"Name\": \"Order : K12 course-2nd Order\",\r\n"
				+ "            \"Course_Name__c\": \"9th Math\",\r\n"
				+ "            \"Order_Start_Date__c\": \"2022-03-11\",\r\n"
				+ "            \"Board__c\": \"ICSE\",\r\n"
				+ "            \"Order_Product_name__c\": \"Product 1;\",\r\n"
				+ "            \"Books__c\": \"Book 1;Book 2\",\r\n"
				+ "            \"Active_AWB_Number__c\": \"AWB-1236\",\r\n"
				+ "            \"Active_Order_Delivery_Date__c\": \"2022-03-17\",\r\n"
				+ "            \"K3_Validity_End_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"Active_Order_Value__c\": \"20000\",\r\n"
				+ "            \"Status__c\": \"Punched\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Punched\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"K12_Validity_End_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD"+Premiumid+"\"\r\n"
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
				+ "            \"Payment_Reference_ID__c\": \"PD1"+Premiumid+"\",\r\n"
				+ "            \"Payment_Amount__c\": \"4500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Down payment\",\r\n"
				+ "            \"Payment_Method__c\": \"PayU\",\r\n"
				+ "            \"Payment_Date__c\": \"2022-03-21\",\r\n"
				+ "            \"Tenurity__c\": \"\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"ORD1"+Premiumid+"\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD"+Premiumid+"\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Payment__c\",\r\n"
				+ "              \"referenceId\": \"payment2\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment_Reference_ID__c\": \"PD2"+Premiumid+"\",\r\n"
				+ "            \"Payment_Amount__c\": \"40500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Loan\",\r\n"
				+ "            \"Payment_Method__c\": \"IIFL\",\r\n"
				+ "            \"Payment_Date__c\": \"2022-03-21\",\r\n"
				+ "            \"Tenurity__c\": \"12\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"3375\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"9"+Premiumid+"\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"ORD2"+Premiumid+"\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD"+Premiumid+"\"\r\n"
				+ "            }\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Program__c/Student_Enrolment_ID__c/STUPRGM"+Premiumid+"\",\r\n"
				+ "      \"referenceId\": \"STUPRGM"+randomNum+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Student_Sales_Order__c\": \"@{RefSSORD"+Premiumid+".id}\",\r\n"
				+ "        \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "        \"Student__c\": \"@{RefStudent"+Premiumid+".id}\",\r\n"
				+ "        \"Program__r\": {\r\n"
				+ "          \"Program_ID__c\": \"ACOPKXSY0001\"\r\n"
				+ "        },\r\n"
				+ "        \"Status__c\": \"Active\",\r\n"
				+ "        \"Start_Date__c\": \"2022-04-27\",\r\n"
				+ "        \"End_Date__c\": \"2022-10-27\",\r\n"
				+ "        \"Product_Type__c\": \"Paid\",\r\n"
				+ "        \"Class_Program_Type__c\":\"Regular\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v52.0/composite/sobjects/Student_Orders__c/Reference_Id__c\",\r\n"
				+ "      \"referenceId\": \"studentOrderList\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Orders__c\",\r\n"
				+ "              \"referenceId\": \"studentOrder1\"\r\n"
				+ "            },\r\n"
				+ "            \"Name\": \"Student Order : 1"+Premiumid+"\",\r\n"
				+ "            \"Order__c\": \"@{orderList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+".id}\",\r\n"
				+ "            \"Reference_Id__c\": \"@{orderList[0].id}-@{RefStudent"+Premiumid+".id}\"\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Orders__c\",\r\n"
				+ "              \"referenceId\": \"studentOrder2\"\r\n"
				+ "            },\r\n"
				+ "            \"Name\": \"Student Order : 2"+Premiumid+"\",\r\n"
				+ "            \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+".id}\",\r\n"
				+ "            \"Reference_Id__c\": \"@{orderList[1].id}-@{RefStudent"+Premiumid+".id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v52.0/composite/sobjects/Student_Payment__c/SPayment_Reference_ID__c\",\r\n"
				+ "      \"referenceId\": \"RefPaymentPD1"+Premiumid+"\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+".id}\",\r\n"
				+ "            \"SPayment_Reference_ID__c\": \"@{paymentList[0].id}-@{RefStudent"+Premiumid+".id}\"\r\n"				
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent"+Premiumid+".id}\",\r\n"
				+ "            \"SPayment_Reference_ID__c\": \"@{paymentList[1].id}-@{RefStudent"+Premiumid+".id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		
		return bodycontent;
	}
	
	public static String AccountCreationResponse_UAT() {
		String val = "OAuth ";
		String StudentCreation="";
		StudentCreation = given().header("Authorization", val + Utils.Access_Token())
				.header("Content-Type", "application/json").body(bodycontent_Prod()).when()
				.post("https://byjusprod--byjusuat.my.salesforce.com/services/data/v52.0/composite").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+StudentCreation);
		return StudentCreation;

	}
	
	public static String AccountCreationResponse_UATFC() {
		String val = "OAuth ";
		String StudentCreation="";
		StudentCreation = given().header("Authorization", val + Utils.Access_TokenUATFC())
				.header("Content-Type", "application/json").body(bodycontent_Prod()).when()
				.post("https://byjusprod--byjusuatfc.my.salesforce.com/services/data/v52.0/composite").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+StudentCreation);
		return StudentCreation;

	}
	
	public static String AccountCreationResponse_QA() {
		String val = "OAuth ";
		String StudentCreation="";
		StudentCreation = given().header("Authorization", val + Utils.Access_TokenQA())
				.header("Content-Type", "application/json").body(bodycontent()).when()
				.post("https://byjusprod--byjusqa.my.salesforce.com/services/data/v52.0/composite").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+StudentCreation);
		return StudentCreation;

	}
	
	public static String AccountCreationResponse_Prod() {
		String val = "OAuth ";
		String StudentCreation="";
		StudentCreation = given().header("Authorization", val + Utils.Access_TokenProd())
				.header("Content-Type", "application/json").body(bodycontent_Prod()).when()
				.post("https://byjusprod.my.salesforce.com/services/data/v52.0/composite").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+StudentCreation);
		return StudentCreation;

	}
	
	public static String AccountidCreationResponse_UAT() {

		String id="";
		JsonPath js1 = new JsonPath(AccountCreationResponse_UAT());
		id = js1.get("compositeResponse[0].body.id");
		System.out.println("The value of id is: " + id);
		return id;

	}
	
	public static String AccountidCreationResponse_UATFC() {

		String id="";
		JsonPath js1 = new JsonPath(AccountCreationResponse_UATFC());
		id = js1.get("compositeResponse[0].body.id");
		System.out.println("The value of id is: " + id);
		return id;

	}
	
	public static String AccountidCreationResponse_QA() {

		String id="";
		JsonPath js1 = new JsonPath(AccountCreationResponse_QA());
		id = js1.get("compositeResponse[0].body.id");
		System.out.println("The value of id is: " + id);
		return id;

	}
	
	public static String AccountidCreationResponse_Prod() {

		String id="";
		JsonPath js1 = new JsonPath(AccountCreationResponse_Prod());
		id = js1.get("compositeResponse[0].body.id");
		System.out.println("The value of id is: " + id);
		return id;

	}
	
public static String SessionAttended() {
		
		String Session="[{\r\n"
				+ "    \"premiumId\": \""+Premiumid+"\",\r\n"
				+ "    \"activityTime\": 675167167111,\r\n"
				+ "    \"activityIdentifier\": \""+Premiumid+"_110009254\", \r\n"
				+ "    \"activityName\": \"Sessions_Attended\",\r\n"
				+ "    \"videoWatchedPercentage\": null,\r\n"
				+ "    \"last4SessionWatchTimePercentage\": null,\r\n"
				+ "    \"sessionJoinedTime\": null,\r\n"
				+ "    \"sessionStartTime\": \"2022-03-23T10:00:00.000+05:30\",\r\n"
				+ "    \"courseId\": null,\r\n"
				+ "    \"sessionNo\": 30,\r\n"
				+ "    \"sessionAttendedTillDate\": null,\r\n"
				+ "    \"last4SessionAttendancePercentage\": null,\r\n"
				+ "    \"subscriptionType\": null,\r\n"
				+ "    \"channelId\": null,\r\n"
				+ "    \"rolling7DayAttendancePercentage\": null,\r\n"
				+ "    \"bookedPercentage\": null,\r\n"
				+ "    \"sessionNoBooked\": null,\r\n"
				+ "    \"sessionType\": null,\r\n"
				+ "    \"attendancePercentage\": null,\r\n"
				+ "    \"classTitle\": \"Control and Coordination\",\r\n"
				+ "    \"productName\": null,\r\n"
				+ "    \"rolling15DayAttendancePercentage\": null,\r\n"
				+ "    \"smeEmailId\": \"test.123@byjus.com\",\r\n"
				+ "    \"subject\": \"Maths\",\r\n"
				+ "    \"programName\": \"Byju_Math\",\r\n"
				+ "    \"notes\": null,\r\n"
				+ "    \"batchid\": null,\r\n"
				+ "    \"bookingid\": null,\r\n"
				+ "    \"subStatus\": null,\r\n"
				+ "    \"reason\": null,\r\n"
				+ "    \"sessionBooked\": null,\r\n"
				+ "    \"sessionDateTime\": null,\r\n"
				+ "    \"delayedJoining\": true,\r\n"
				+ "    \"tutorJoiningTime\": null,\r\n"
				+ "    \"programType\": \"Regular\",\r\n"
				+ "    \"subBatchId\": 1221\r\n"
				+ " }]";
		return Session;
		
	}
	
	public static String SessionAttendedResponse_UAT() {
		String val = "OAuth ";
		String SessionAttended="";
		SessionAttended = given().header("Authorization", val + Utils.Access_Token())
				.header("Content-Type", "application/json").body(SessionAttended()).when()
				.post("https://byjusprod--byjusuat.my.salesforce.com/services/apexrest/action/SessionAttendedAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionAttended);
		return SessionAttended;

	}
	
	public static String SessionAttendedResponse_UATFC() {
		String val = "OAuth ";
		String SessionAttended="";
		SessionAttended = given().header("Authorization", val + Utils.Access_TokenUATFC())
				.header("Content-Type", "application/json").body(SessionAttended()).when()
				.post("https://byjusprod--byjusuatfc.my.salesforce.com/services/apexrest/action/SessionAttendedAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionAttended);
		return SessionAttended;

	}
	public static String SessionAttendedResponse_QA() {
		String val = "OAuth ";
		String SessionAttended="";
		SessionAttended = given().header("Authorization", val + Utils.Access_TokenQA())
				.header("Content-Type", "application/json").body(SessionAttended()).when()
				.post("https://byjusprod--byjusqa.my.salesforce.com/services/apexrest/action/SessionAttendedAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionAttended);
		return SessionAttended;

	}
	
	public static String SessionAttendedResponse_Prod() {
		String val = "OAuth ";
		String SessionAttended="";
		SessionAttended = given().header("Authorization", val + Utils.Access_TokenProd())
				.header("Content-Type", "application/json").body(SessionAttended()).when()
				.post("https://byjusprod.my.salesforce.com/services/apexrest/action/SessionAttendedAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionAttended);
		return SessionAttended;

	}
	
	public static String SessionAttendedidCreationResponse_UAT() {

		String id="";
		JsonPath js1 = new JsonPath(SessionAttendedResponse_UAT());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Attended id is: " + id);
		return id;

	}
	
	public static String SessionAttendedidCreationResponse_UATFC() {

		String id="";
		JsonPath js1 = new JsonPath(SessionAttendedResponse_UATFC());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Attended id is: " + id);
		return id;

	}
	
	public static String SessionAttendedidCreationResponse_QA() {

		String id="";
		JsonPath js1 = new JsonPath(SessionAttendedResponse_QA());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Attended id is: " + id);
		return id;

	}
	
	public static String SessionAttendedCreationResponse_Prod() {

		String id="";
		JsonPath js1 = new JsonPath(SessionAttendedResponse_Prod());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Attended id is: " + id);
		return id;

	}
	
	public static String SessionMissed() {
		
		String Session="[{\r\n"
				+ "  \"premiumId\": \""+Premiumid+"\",\r\n"
				+ "  \"activityTime\": 1646935222,\r\n"
				+ "  \"activityIdentifier\": \""+Premiumid+"_1097600\",\r\n"
				+ "  \"activityName\": \"Sessions_Missed\",\r\n"
				+ "  \"videoWatchedPercentage\": null,\r\n"
				+ "  \"last4SessionWatchTimePercentage\": 10.9,\r\n"
				+ "  \"sessionJoinedTime\": null,\r\n"
				+ "  \"sessionStartTime\": null,\r\n"
				+ "  \"sessionDateTime\": \"2022-03-10T15:30:00.000+05:30\",\r\n"
				+ "  \"sessionBooked\": \"Yes\",\r\n"
				+ "  \"courseId\": 3625,\r\n"
				+ "  \"sessionNo\": 20,\r\n"
				+ "  \"sessionAttendedTillDate\": 0,\r\n"
				+ "  \"last4SessionAttendancePercentage\": 0,\r\n"
				+ "  \"subscriptionType\": \"neo\",\r\n"
				+ "  \"channelId\": 329262,\r\n"
				+ "  \"rolling7DayAttendancePercentage\": 0,\r\n"
				+ "  \"bookedPercentage\": 100,\r\n"
				+ "  \"sessionNoBooked\": 20,\r\n"
				+ "  \"sessionType\": \"Mandatory\",\r\n"
				+ "  \"attendancePercentage\": 0,\r\n"
				+ "  \"classTitle\": \"Algebraic Expressions\",\r\n"
				+ "  \"productName\": \"7th Grade CBSE Neo Classes May 2022\",\r\n"
				+ "  \"rolling15DayAttendancePercentage\": 0,\r\n"
				+ "  \"smeEmailId\": \"no_tutor_assigned@byjus.com\",\r\n"
				+ "  \"subject\": \"Mathematics\",\r\n"
				+ "  \"programName\": \"Neo Classes\",\r\n"
				+ "  \"notes\": null,\r\n"
				+ "  \"batchid\": 3625,\r\n"
				+ "  \"bookingid\": null,\r\n"
				+ "  \"subStatus\": null,\r\n"
				+ "  \"reason\": null,\r\n"
				+ "  \"delayedJoining\": false,\r\n"
				+ "  \"tutorJoiningTime\": null,\r\n"
				+ "  \"programType\": \"Regular\",\r\n"
				+ "  \"subBatchId\": 1176\r\n"
				+ "}]";
		return Session;
		
	}
	
	public static String SessionMissedResponse_UAT() {
		String val = "OAuth ";
		String SessionMissed="";
		SessionMissed = given().header("Authorization", val + Utils.Access_Token())
				.header("Content-Type", "application/json").body(SessionMissed()).when()
				.post("https://byjusprod--byjusuat.my.salesforce.com/services/apexrest/action/SessionMissedAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionMissed);
		return SessionMissed;

	}
	
	public static String SessionMissedResponse_UATFC() {
		String val = "OAuth ";
		String SessionMissed="";
		SessionMissed = given().header("Authorization", val + Utils.Access_Token())
				.header("Content-Type", "application/json").body(SessionMissed()).when()
				.post("https://byjusprod--byjusuatfc.my.salesforce.com/services/apexrest/action/SessionMissedAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionMissed);
		return SessionMissed;

	}
	
	public static String SessionMissedResponse_QA() {
		String val = "OAuth ";
		String SessionMissed="";
		SessionMissed = given().header("Authorization", val + Utils.Access_TokenQA())
				.header("Content-Type", "application/json").body(SessionMissed()).when()
				.post("https://byjusprod--byjusqa.my.salesforce.com/services/apexrest/action/SessionMissedAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionMissed);
		return SessionMissed;

	}
	
	public static String SessionMissedResponse_Prod() {
		String val = "OAuth ";
		String SessionMissed="";
		SessionMissed = given().header("Authorization", val + Utils.Access_TokenProd())
				.header("Content-Type", "application/json").body(SessionMissed()).when()
				.post("https://byjusprod.my.salesforce.com/services/apexrest/action/SessionMissedAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionMissed);
		return SessionMissed;

	}
	
	public static String SessionMissedidCreationResponse_UAT() {

		String id="";
		JsonPath js1 = new JsonPath(SessionMissedResponse_UAT());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Missed id is: " + id);
		return id;

	}
	
	public static String SessionMissedidCreationResponse_UATFC() {

		String id="";
		JsonPath js1 = new JsonPath(SessionMissedResponse_UATFC());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Missed id is: " + id);
		return id;

	}
	
	public static String SessionMissedidCreationResponse_QA() {

		String id="";
		JsonPath js1 = new JsonPath(SessionMissedResponse_QA());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Missed id is: " + id);
		return id;

	}
	
	public static String SessionMissedCreationResponse_Prod() {

		String id="";
		JsonPath js1 = new JsonPath(SessionMissedResponse_Prod());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Missed id is: " + id);
		return id;

	}
	
	public static String SessionFeedback() {
		System.out.println("The value of Premium id entering is:"+Premiumid);
		String Session="[{\r\n"
				+ "\"premiumId\":\""+Premiumid+"\",\r\n"
				+ "\"activityTime\":1646821970,\r\n"
				+ "\"activityIdentifier\":\""+Premiumid+"_1033526\",\r\n"
				+ "\"activityName\":\"Sessions_Feedback\",\r\n"
				+ "\"classRating\":null,\r\n"
				+ "\"classFeedback\":null,\r\n"
				+ "\"last4SessionRatingAverage\":null,\r\n"
				+ "\"channelId\":328458,\r\n"
				+ "\"classTitle\":\"7TH-Comparing Quantities-2\",\r\n"
				+ "\"productName\":\"7th Grade CBSE Neo Classes May 2022\",\r\n"
				+ "\"subject\":\"Mathematics\",\"programName\":\"Neo Classes\",\r\n"
				+ "\"teacherRating\":2,\"teacherFeedback\":\"Content Quality,\r\n"
				+ " Doubt Resolution, Teaching Quality, Tutor Behaviour\",\r\n"
				+ " \"last4TutorRatingAverage\":null,\r\n"
				+ " \"type\":null,\r\n"
				+ " \"bookingid\":null,\"batchid\":null,\r\n"
				+ " \"videoWatchedPercentage\":null,\r\n"
				+ " \"last4SessionWatchTimePercentage\":null,\r\n"
				+ " \"sessionJoinedTime\":null,\r\n"
				+ " \"sessionStartTime\":null,\r\n"
				+ " \"sessionDateTime\":null,\r\n"
				+ " \"sessionBooked\":null,\r\n"
				+ " \"courseId\":null,\r\n"
				+ " \"sessionNo\":null,\r\n"
				+ " \"sessionAttendedTillDate\":null,\r\n"
				+ " \"last4SessionAttendancePercentage\":null,\r\n"
				+ " \"subscriptionType\":null,\r\n"
				+ " \"rolling7DayAttendancePercentage\":null,\r\n"
				+ " \"bookedPercentage\":null,\r\n"
				+ " \"sessionNoBooked\":null,\r\n"
				+ " \"sessionType\":null,\"attendancePercentage\":null,\r\n"
				+ " \"rolling15DayAttendancePercentage\":null,\r\n"
				+ " \"smeEmailId\":null,\"notes\":null,\r\n"
				+ " \"subStatus\":null,\r\n"
				+ " \"reason\":null,\r\n"
				+ " \"delayedJoining\":false,\r\n"
				+ " \"tutorJoiningTime\":\"2022-03-09T15:29:29.000+05:30\",\r\n"
				+ " \"programType\":\"Regular\",\r\n"
				+ " \"last4SessionAverageRating\":null,\r\n"
				+ " \"subBatchId\":659\r\n"
				+ " }]";
		return Session;
		
	}
	
	public static String SessionFeedbackResponse_UAT() {
		String val = "OAuth ";
		String SessionFeedback="";
		SessionFeedback = given().header("Authorization", val + Utils.Access_Token())
				.header("Content-Type", "application/json").body(SessionFeedback()).when()
				.post("https://byjusprod--byjusuat.my.salesforce.com/services/apexrest/action/SessionFeedbackAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionFeedback);
		return SessionFeedback;

	}
	
	public static String SessionFeedbackResponse_UATFC() {
		String val = "OAuth ";
		String SessionFeedback="";
		SessionFeedback = given().header("Authorization", val + Utils.Access_TokenUATFC())
				.header("Content-Type", "application/json").body(SessionFeedback()).when()
				.post("https://byjusprod--byjusuatfc.my.salesforce.com/services/apexrest/action/SessionFeedbackAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionFeedback);
		return SessionFeedback;

	}
	
	public static String SessionFeedbackResponse_QA() {
		String val = "OAuth ";
		String SessionFeedback="";
		SessionFeedback = given().header("Authorization", val + Utils.Access_TokenQA())
				.header("Content-Type", "application/json").body(SessionFeedback()).when()
				.post("https://byjusprod--byjusqa.my.salesforce.com/services/apexrest/action/SessionFeedbackAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionFeedback);
		return SessionFeedback;

	}
	
	public static String SessionFeedbackResponse_Prod() {
		String val = "OAuth ";
		String SessionFeedback="";
		SessionFeedback = given().header("Authorization", val + Utils.Access_TokenProd())
				.header("Content-Type", "application/json").body(SessionFeedback()).when()
				.post("https://byjusprod.my.salesforce.com/services/apexrest/action/SessionFeedbackAPI").then().log().all()
				.extract().response().asString();
        log.info("The response is: "+SessionFeedback);
		return SessionFeedback;

	}
	
	public static String SessionFeedbackidCreationResponse_UAT() {

		String id="";
		JsonPath js1 = new JsonPath(SessionFeedbackResponse_UAT());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Feedback id is: " + id);
		return id;

	}
	
	public static String SessionFeedbackidCreationResponse_UATFC() {

		String id="";
		JsonPath js1 = new JsonPath(SessionFeedbackResponse_UATFC());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Feedback id is: " + id);
		return id;

	}
	
	public static String SessionFeedbackidCreationResponse_QA() {

		String id="";
		JsonPath js1 = new JsonPath(SessionFeedbackResponse_QA());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Feedback id is: " + id);
		return id;

	}
	
	public static String SessionFeedbackCreationResponse_Prod() {

		String id="";
		JsonPath js1 = new JsonPath(SessionFeedbackResponse_Prod());
		id = js1.get("transactionIdentifier");
		System.out.println("The value of Session Feedback id is: " + id);
		return id;

	}
}

package payLoad;
import static io.restassured.RestAssured.given;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import resources.Utils;


public class payLoad_Logistics{
	
	public static Logger log = LogManager.getLogger(payLoad_Logistics.class.getName());
	static Faker faker = new Faker();
	static int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
	static String firstName = faker.name().firstName().replaceAll("'","");
	static String lastName = faker.name().lastName().replaceAll("'","");
	static String parentName = faker.name().fullName().replaceAll("'","");
	static String Auto="Dummy ";
	static long Premiumid= 52512495345L+randomNum;
	
	public static String bodycontent() {
		//Add "+Premiumid+" to Premium Id and "+randomNum+" to Student Enrollment Id
		//Add "+Auto+ ""+firstName+" to FirstName
		//Add "+lastName+" to LastName
		//Add "+parentName+" to ParentName
		
		String bodycontent="{\r\n"
				+ "  \"allOrNone\": true,\r\n"
				+ "  \"compositeRequest\": [\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Account/Premium_ID__c/"+Premiumid+"\",\r\n"
				+ "      \"referenceId\": \"RefStudent12312211\",\r\n"
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
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Sales_Order__c/Sales_Order_No__c/SSORD994674654121\",\r\n"
				+ "      \"referenceId\": \"RefSSORD994674654121\",\r\n"
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
				+ "            \"Order_No__c\": \"ORD123498374678\",\r\n"
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
				+ "            \"Status__c\": \"Confirmed\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"K12_Validity_End_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD994674654121\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Order__c\",\r\n"
				+ "              \"referenceId\": \"order2\"\r\n"
				+ "            },\r\n"
				+ "            \"Order_No__c\": \"ORD1234983732466\",\r\n"
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
				+ "            \"Status__c\": \"Confirmed\",\r\n"
				+ "            \"OMS_Order_Status__c\": \"Confirmed\",\r\n"
				+ "            \"Active_Order_Shipped_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"K12_Validity_End_Date__c\": \"2022-03-19\",\r\n"
				+ "            \"Order_Type__c\": \"Fresh order\",\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD994674654121\"\r\n"
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
				+ "            \"Payment_Reference_ID__c\": \"PD234555234564565\",\r\n"
				+ "            \"Payment_Amount__c\": \"4500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Down payment\",\r\n"
				+ "            \"Payment_Method__c\": \"PayU\",\r\n"
				+ "            \"Payment_Date__c\": \"2022-03-21\",\r\n"
				+ "            \"Tenurity__c\": \"\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"ORD123498374678\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD994674654121\"\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Payment__c\",\r\n"
				+ "              \"referenceId\": \"payment2\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment_Reference_ID__c\": \"PD2345552394562282\",\r\n"
				+ "            \"Payment_Amount__c\": \"40500\",\r\n"
				+ "            \"Payment_Type__c\": \"EMI\",\r\n"
				+ "            \"Payment_Category__c\": \"Loan\",\r\n"
				+ "            \"Payment_Method__c\": \"IIFL\",\r\n"
				+ "            \"Payment_Date__c\": \"2022-03-21\",\r\n"
				+ "            \"Tenurity__c\": \"12\",\r\n"
				+ "            \"Estimated_Monthly_EMI__c\": \"3375\",\r\n"
				+ "            \"Loan_Reference_Id__c\": \"92211464819048\",\r\n"
				+ "            \"Order__r\": {\r\n"
				+ "              \"Order_No__c\": \"ORD1234983732466\"\r\n"
				+ "            },\r\n"
				+ "            \"Student_Sales_Order__r\": {\r\n"
				+ "              \"Sales_Order_No__c\": \"SSORD994674654121\"\r\n"
				+ "            }\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/sobjects/Student_Program__c/Student_Enrolment_ID__c/STUPRGM8767"+randomNum+"66734123\",\r\n"
				+ "      \"referenceId\": \"STUPRGM100106\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"Student_Sales_Order__c\": \"@{RefSSORD994674654121.id}\",\r\n"
				+ "        \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "        \"Student__c\": \"@{RefStudent12312211.id}\",\r\n"
				+ "        \"Program__r\": {\r\n"
				+ "          \"Program_ID__c\": \"BCRPUCSY0001\"\r\n"
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
				+ "            \"Name\": \"Student Order : 12312211\",\r\n"
				+ "            \"Order__c\": \"@{orderList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent12312211.id}\"\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Orders__c\",\r\n"
				+ "              \"referenceId\": \"studentOrder2\"\r\n"
				+ "            },\r\n"
				+ "            \"Name\": \"Student Order : 234555234444100\",\r\n"
				+ "            \"Order__c\": \"@{orderList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent12312211.id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"method\": \"PATCH\",\r\n"
				+ "      \"url\": \"/services/data/v48.0/composite/sobjects\",\r\n"
				+ "      \"referenceId\": \"RefPaymentPD1123122112272727\",\r\n"
				+ "      \"body\": {\r\n"
				+ "        \"allOrNone\": true,\r\n"
				+ "        \"records\": [\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[0].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent12312211.id}\"\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"attributes\": {\r\n"
				+ "              \"type\": \"Student_Payment__c\"\r\n"
				+ "            },\r\n"
				+ "            \"Payment__c\": \"@{paymentList[1].id}\",\r\n"
				+ "            \"Student__c\": \"@{RefStudent12312211.id}\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		
		return bodycontent;
	}
	
	public static String bodycontent_Prod() {
		//Add "+Premiumid+" to Premium Id and "+randomNum+" to Student Enrollment Id
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
				+ "          \"Program_ID__c\": \"BCRPUCSY0001\"\r\n"
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
}

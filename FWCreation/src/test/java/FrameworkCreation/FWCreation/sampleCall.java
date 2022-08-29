package FrameworkCreation.FWCreation;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resources.Utils;

public class sampleCall {
	


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		

		 // driver.get("https://www.google.com/");
		 
		
				Thread.sleep(5000);
		  System.setProperty("webdriver.chrome.driver",
		  "C:\\Users\\Tnluser\\eclipse-workspace\\FWCreation\\src\\main\\java\\resources\\chromedriver.exe"
		  ); 
		  WebDriver driver=new ChromeDriver(); driver.manage().window().maximize();
		//driver.get("https://byjusprod--byjusuat.lightning.force.com/lightning/r/Account/"+id+"/view");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("abul.kalam@byjus.com.byjusuat");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Bangalore@1");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Log In to Sandbox']")).click();
		

	}



}

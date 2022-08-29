package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import resources.base;

public class UserDetailPO extends base{
	WebDriver driver;

	private String btnUserDetailxpath = "//div[text()='User Detail']";

	// Declaring Constructor
	public UserDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	//Clicking on User Detail Button
	public void ClickUserDetailbutton() throws InterruptedException {
		driver.findElement(By.xpath(btnUserDetailxpath)).click();
		Thread.sleep(5000);
	}

	
	//Logout code
	public void Logout() throws InterruptedException {
		
		driver.findElement(By.xpath("//span[@class='uiImage']")).click();
		Thread.sleep(1200);
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		Thread.sleep(1500);
		
	}

}

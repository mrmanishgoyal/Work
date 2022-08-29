package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import resources.base;

public class FollowUpPTPPO extends base {
	WebDriver driver;

	private String lnkAssignedToxpath = "//div[text()='Assigned To']/following-sibling::div/span";
	
	// Declaring Constructor
	public FollowUpPTPPO(WebDriver driver) {
		this.driver = driver;
	}

	//Capture the value of assigned to
	public String CaptureAssignedTo() throws InterruptedException {
	String AssignedToOwner = driver.findElement(By.xpath(lnkAssignedToxpath)).getText();
	Thread.sleep(800);
	return AssignedToOwner;
	}
	
	
	
}

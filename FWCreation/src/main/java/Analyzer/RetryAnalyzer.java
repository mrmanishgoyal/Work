package Analyzer;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import resources.base;

public class RetryAnalyzer  extends base implements IRetryAnalyzer{
	public WebDriver driver;
	
	int counter = 0;
	int retryLimit = 1;
	
	/*
	 * @Override public boolean retry(ITestResult result){ if(counter < retryLimit){
	 * counter++; try {
	 * 
	 * } catch (Throwable t) { // TODO Auto-generated catch block
	 * t.printStackTrace(); } return true; } return false; }
	 */

	 @Override
	    public boolean retry(ITestResult iTestResult) {
	        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
	            if (counter < retryLimit) {                            //Check if maxtry count is reached
	            	counter++;                                     //Increase the maxTry count by 1
	                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
	                return true;                                 //Tells TestNG to re-run the test
	            } else {
	                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
	            }
	        } else {
	            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
	        }
	        return false;
	    }
	
	
}
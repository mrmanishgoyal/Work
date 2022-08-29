package testCases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.junit.Assert;
import org.testng.annotations.Test;

public class DockerrunStop{
	
	@Test
	public void Stopfile() throws IOException, InterruptedException {
		
		Runtime runtime=Runtime.getRuntime();
		runtime.exec("cmd /c start DockerDown.bat");
		String f="output1.txt";
		boolean flag=false;
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.SECOND, 30);
		long stopnow=cal.getTimeInMillis();
		Thread.sleep(3000);
		
		while(System.currentTimeMillis()<stopnow) {
			
			if(flag) {
				break;
			}
			
			BufferedReader reader=new BufferedReader(new FileReader(f));
			String currline=reader.readLine();
		while(currline!=null && !flag) {
			
			if(currline.contains("selenium-hub exited")) {
				
				System.out.println("Found the text");
				flag=true;
				break;
				
			}
			currline=reader.readLine();
			
		}
		reader.close();
		}
		//Assert.assertTrue(flag);
		
	}
	

}

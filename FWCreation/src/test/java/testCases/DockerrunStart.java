package testCases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.junit.Assert;
import org.testng.annotations.Test;

public class DockerrunStart{
	
	@Test
	public void Startfile() throws IOException, InterruptedException {
		
		Runtime runtime=Runtime.getRuntime();
		runtime.exec("cmd /c start DockerStart.bat");
		String f="output.txt";
		boolean flag=false;
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.SECOND, 45);
		long stopnow=cal.getTimeInMillis();
		Thread.sleep(3000);
		
		while(System.currentTimeMillis()<stopnow) {
			
			if(flag) {
				break;
			}
			
			BufferedReader reader=new BufferedReader(new FileReader(f));
			String currline=reader.readLine();
		while(currline!=null && !flag) {
			
			if(currline.contains("Node has been added")) {
				
				System.out.println("The docker server has been started");
				flag=true;
				break;
				
			}
			currline=reader.readLine();
			
		}
		reader.close();
		}
		//Assert.assertTrue(flag);
		Thread.sleep(10000);
		runtime.exec("cmd /c start scale.bat");	
		Thread.sleep(5000);
	}
	

}

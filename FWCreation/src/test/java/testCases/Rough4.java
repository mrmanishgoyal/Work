package testCases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class Rough4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Date date = new Date();
		 //String date1= dateFormat.format(date);
		 //System.out.println(date1);
		 
		 Date date = DateUtils.addDays(new Date(), -7);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 System.out.println(sdf.format(date));

	}

}

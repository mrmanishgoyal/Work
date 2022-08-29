package resources;

public class Queries {
	
	public static String Chatcount(String val) {
		String Query="SELECT COUNT(Id)recordCount, OwnerId, Action_Type__c\r\n"
				+ "FROM Task\r\n"
				+ "WHERE Status = 'Open'\r\n"
				+ "AND Action_Type__c IN ('Student One-to-One', 'Parent One-to-One', 'Group Chat')\r\n"
				+ "AND OwnerId IN ('"+val+"')\r\n"
				+ "GROUP BY OwnerId, Action_Type__c\r\n"
				+ "ORDER BY COUNT(Id)";
		return Query;
	}

}

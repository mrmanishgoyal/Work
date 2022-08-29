package resources;

public class Batches {
	
	public static String MKBatch() {
		String MKBatch="Database.executeBatch(new BatchPostStudentClassAssignmentReRun(), 500);";
		return MKBatch;
	}

}

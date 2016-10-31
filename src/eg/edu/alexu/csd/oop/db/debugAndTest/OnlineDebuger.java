package eg.edu.alexu.csd.oop.db.debugAndTest;

public class OnlineDebuger {

	public static final String LOG_FILE_NAME = "/debug/jdbc15.log";
	

	public static void log(String str, boolean delete) {
		try {
//			if (delete)
//				new File(LOG_FILE_NAME).delete();
//			java.nio.file.Files.write(java.nio.file.Paths.get(LOG_FILE_NAME), str.getBytes(),
//					new File(LOG_FILE_NAME).exists() ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
		} catch (Throwable e1) {
//			e1.printStackTrace();
		}
	}
	
	public static void logln(String str, boolean delete){
		log(str + "\n", delete);
	}
	
	public static void logln(Object str, boolean delete){
		log(str.toString() + "\n", delete);
	}
	
	public static String exceptionToString(Throwable e) {
		String str = "";
		for (StackTraceElement ee : e.getStackTrace())
			str += ee.getFileName() + " " + ee.getMethodName() + " " + ee.getLineNumber() + "\n";
		if (e.getCause() != null)
			str += exceptionToString(e.getCause());
		return str;
	}

}

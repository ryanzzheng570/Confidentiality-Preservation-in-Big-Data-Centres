package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class LogController {
	private String directoryPath = "D:\\test";	//FIXME: change directory
	
	public boolean writeLog(JSONObject query) {
		String userName = (String) query.get("userName");
		String timeStamp = (String) query.get("timeStamp");
		String queryColumns = (String) query.get("queryColumns");
		
		//Find the user's log
		File f = new File(directoryPath);	
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith(userName) && name.endsWith("log");
			}
		});
		
		//This user does not have log. Create one
		if (matchingFiles.length == 0) {
			f = new File(directoryPath + "/" + userName);
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Write into the log
		String logMessage = userName + "$" + timeStamp + "$" + queryColumns + "\n";
		FileWriter fr = null;
		try {
			fr = new FileWriter(f, true);
			fr.write(logMessage);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	//TODO: add policy controller to params
	//public JSONObject readLogs(String user) {
	//}
	
//	public static void main(String[] args) throws Exception {
//		JSONObject obj = new JSONObject();
//		obj.put("userName", "bobsmith");
//		obj.put("timeStamp", "1234");
//		obj.put("queryColumns", "DateOfEntry,DateOfLeave,Duration");
//		writeLog(obj);
//		
//		JSONObject obj2 = new JSONObject();
//		obj2.put("userName", "abc");
//		obj2.put("timeStamp", "1234");
//		obj2.put("queryColumns", "DateOfEntry,DateOfLeave,Duration");
//		writeLog(obj2);
//		
//		JSONObject obj3 = new JSONObject();
//		obj3.put("userName", "bobsmith");
//		obj3.put("timeStamp", "345345");
//		obj3.put("queryColumns", "Test1,Test2,Duration");
//		writeLog(obj3);
//	}
}

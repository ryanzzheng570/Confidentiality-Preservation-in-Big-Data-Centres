package api;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ServerClientThread implements Runnable {

	private Socket nodeServer;
	private WebApi web;
	
	ServerClientThread(Socket inSocket, WebApi web) {
		this.nodeServer = inSocket;
		this.web=web;
	}

	public void run() {
		try {

			System.out.println("Waiting for client Request");			
			InputStream input= nodeServer.getInputStream();

			while (input!=null) {
				// parse to JSON
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(input, "UTF-8"));

				System.out.println("Request Received: " + jsonObject.toString());
				web.setJSONObject(jsonObject);
			}
			
			input.close();
			nodeServer.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.out.println("Exit!");
		}
	}
}

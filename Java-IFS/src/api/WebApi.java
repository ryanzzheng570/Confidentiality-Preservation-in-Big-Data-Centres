package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WebApi{

	// port number
	private final static int port = 1010;

	// static ServerSocket variable
	private static ServerSocket server;

	// static webServer variable
	private static Socket serverClient;

	// input variable
	private InputStream input;

	// output var
	private OutputStream output;
	
	//JSON object
	private JSONObject obj;

	public WebApi() {
		// create socket server object
		try {
			obj = null;
			server = new ServerSocket(port);
			System.out.println("Server started...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listen();

	}

	public void listen() {

		try {
			// create socket and get socket request
			serverClient = server.accept();
			System.out.println("Client server connected");
			//start new Client thread
			ServerClientThread sct = new ServerClientThread(serverClient, this);
			sct.run();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
	}
	
	public void setJSONObject(JSONObject obj) {
		this.obj = obj;
		
	}
	
	public JSONObject receiveFromWeb() {
		return obj;
	}
	

	public boolean sendToWeb(JSONObject obj) {

		try {
			System.out.println("Sending to Web..");
			// create socket and get socket request
			serverClient = server.accept();
			//get output stream
			output = serverClient.getOutputStream();
			//get bytes
			byte[] message = obj.toString().getBytes();
			System.out.println("Sending...");
			//write to outputstream
			output.write(message);
			System.out.println("Sent: " + obj.toString());
			output.flush();

			serverClient.close();
			output.close();
			return true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 


	}


	public static String inputStreamAsString(InputStream stream) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		StringBuilder sb = new StringBuilder();
		String line = null;

		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}

		br.close();
		return sb.toString();
	}



}

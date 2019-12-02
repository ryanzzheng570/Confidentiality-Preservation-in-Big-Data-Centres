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
	private static Socket nodeServer;

	// input variable
	private InputStream input;

	// output var
	private OutputStream output;

	public WebApi() {
		// create socket server object
		try {
			server = new ServerSocket(port);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listen();

	}

	public JSONObject listen() {

		try {
			System.out.println("Waiting for client Request");
			// create socket and get socket request
			nodeServer = server.accept();
			// read from socket
			input = nodeServer.getInputStream();
			// parse to JSON
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(input, "UTF-8"));

			System.out.println("Request Received: " + jsonObject.toString());

			nodeServer.close();
			input.close();
			return jsonObject;

		} catch (IOException|ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 


	}
	

	public boolean sendToWeb(JSONObject obj) {

		try {
			System.out.println("Sending to Web..");
			// create socket and get socket request
			nodeServer = server.accept();
			//get output stream
			output = nodeServer.getOutputStream();
			//get bytes
			byte[] message = obj.toString().getBytes();
			System.out.println("Sending...");
			//write to outputstream
			output.write(message);
			System.out.println("Sent: " + obj.toString());
			output.flush();

			nodeServer.close();
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

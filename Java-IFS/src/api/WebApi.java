package api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WebApi {
	final static int port = 1010;
	
	 public static void main(String[] args) {
		 
	        ServerSocket server;
	        Socket nodeServer;
	        InputStream input;

	        try {
	            server = new ServerSocket(port);
	            nodeServer = server.accept();

	            input = nodeServer.getInputStream();
	            
	            JSONParser jsonParser = new JSONParser();
	            
	            JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(input, "UTF-8"));
	            
	            
	            String inputString = WebApi.inputStreamAsString(input);

	            System.out.println(jsonObject.toString());

	            nodeServer.close();
	            server.close();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
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

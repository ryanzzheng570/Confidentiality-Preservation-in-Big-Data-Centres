import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class WebApi {
	 public static void main(String[] args) {
	        ServerSocket server;
	        Socket nodeServer;
	        InputStream input;

	        try {
	            server = new ServerSocket(1010);
	            nodeServer = server.accept();

	            input = nodeServer.getInputStream();
	            String inputString = WebApi.inputStreamAsString(input);

	            System.out.println(inputString);

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
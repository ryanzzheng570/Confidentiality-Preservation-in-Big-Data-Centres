import api.SQLWrapper;

public class StartUp {
	
	private static boolean isDBConnected = false;
	public static void main(String[] args) throws Exception {
		SQLWrapper connection = new SQLWrapper();
		isDBConnected = connection.tryConnection();
		if(isDBConnected) {
			
		}
	}

}

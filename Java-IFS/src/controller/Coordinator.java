package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import api.SQLWrapper;
import api.WebApi;

public class Coordinator {
	
	private LogController log;
	private WebApi web;
	private SQLWrapper db;
	
	
	public Coordinator() {
		//initialize sql wrapper
		db = new SQLWrapper();
		try {
			db.tryConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//initialize webApi
		web = new WebApi();
		
		//initialize logController
		log = new LogController();
		
	}
	
	public JSONObject receive() {
		
		return web.receiveFromWeb();
		
	}
	
	public boolean send(JSONObject obj) {
		
		return web.sendToWeb(obj);
	}

	public ResultSet searchDB(JSONObject obj) {
		String query = obj.toString();
		ResultSet results;
		try {
			results = db.retrieveDataFromDataBase(query);
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}




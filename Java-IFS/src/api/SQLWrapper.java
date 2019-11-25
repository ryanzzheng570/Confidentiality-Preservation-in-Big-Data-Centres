package api;
// Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class SQLWrapper {
	
  private Connection connect = null;
  private Statement statement = null;
  private ResultSet resultSet = null;

  final private String url = "jdbc:mysql://localhost:3306/sysc-4907";
  final private String user = "root";
  final private String passwd = "sysc4907";
  
  public boolean tryConnection() throws ClassNotFoundException, SQLException{
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.cj.jdbc.Driver");    
     // Setup the connection with the DB
      connect = DriverManager.getConnection(url, user, passwd); 
      return true;
    } catch (ClassNotFoundException classNotFound) {
        throw classNotFound;
    } finally {
      close();
    }
  }
  
  public ResultSet retrieveDataFromDataBase(String query) throws SQLException {
	  try {
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement.executeQuery(query);
	      
	      return resultSet;
	    } catch (SQLException e) {
	      throw e;
	    } finally {
	      close();
	    }
  }
  
  //For testing purposes, the resultSet contains the data returned for the data
  private void writeMetaData(ResultSet resultSet) throws SQLException {
	  System.out.println("The columns in the table are");
	  System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	  
	  for(int i=1; i<= resultSet.getMetaData().getColumnCount(); i++) {
		  System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
	  }
  }

  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

}
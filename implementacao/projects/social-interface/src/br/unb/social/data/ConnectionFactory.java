package br.unb.social.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.unb.social.util.PropertiesLoader;

public class ConnectionFactory {

	private static PropertiesLoader loader;
	
	static{
		loader = PropertiesLoader.getInstance();
		
		try{

			Class.forName(loader.getDriver());

		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}
	
	public static Connection createConnection() throws SQLException{
		return DriverManager.getConnection(loader.getUrl(), loader.getUser(), loader.getPassword());
	}
	
}

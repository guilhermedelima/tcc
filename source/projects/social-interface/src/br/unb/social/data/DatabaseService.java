package br.unb.social.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unb.social.util.PropertiesLoader;


public class DatabaseService {
	
	private PropertiesLoader loader;

	public DatabaseService(PropertiesLoader loader){
		this.loader = loader;

		try{

			Class.forName(this.loader.getDriver());

		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}
	
	public Integer execQuerySource(String query, String countLabel){
		
		Connection connection;
		Statement statement;
		ResultSet result;
		Integer allPosts;
		
		connection = null;
		statement = null;
		result = null;
		
		allPosts = 0;
		
		try{
			connection = DriverManager.getConnection(loader.getUrl(), loader.getUser(), loader.getPassword());
			statement = connection.createStatement();
			
			result = statement.executeQuery(query);
			
			while( result.next() ){
				allPosts = result.getInt(countLabel);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
			allPosts = 0;
		}finally{
			try{ if(result != null) result.close(); }catch(Exception e) {}
			try{ if(statement != null) statement.close(); }catch(Exception e) {}
			try{ if(connection != null) connection.close(); }catch(Exception e) {}
		}
		
		return allPosts;
	}

	public List<Integer> execQueryPoliticianTable(String query, Integer size, String monthLabel, String countLabel){

		Connection connection;
		Statement statement;
		ResultSet result;
		List<Integer> countList;
		Integer month, count;

		countList = new ArrayList<Integer>();
		
		for(int i=0; i<size; i++)
			countList.add(0);
		
		connection = null;
		statement = null;
		result = null;

		try{
			connection = DriverManager.getConnection(loader.getUrl(), loader.getUser(), loader.getPassword());
			statement = connection.createStatement();

			result = statement.executeQuery(query);

			while( result.next() ){
				month = result.getInt(monthLabel);
				count = result.getInt(countLabel);

				countList.set(month, count);
			}

		}catch(SQLException ex){
			ex.printStackTrace();
			countList.clear();
		}finally{
			try{ if(result != null) result.close(); }catch(Exception e) {}
			try{ if(statement != null) statement.close(); }catch(Exception e) {}
			try{ if(connection != null) connection.close(); }catch(Exception e) {}
		}

		return countList;
	}

}

package br.unb.social.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unb.social.model.SourceTable;
import br.unb.social.model.SourceTableRow;
import br.unb.social.model.SourceType;

public class SourceDAO {

	public SourceDAO() {
	}

	public SourceTable searchSourceTable(){

		List<SourceTableRow> rowsList;
		String query;
		
		rowsList = new ArrayList<SourceTableRow>();
		
		for(SourceType source : SourceType.values()){
			query = buildQuery(source);
			rowsList.add( searchSourceTableRow(source, query) );
		}

		return new SourceTable(rowsList);
	}
	
	private SourceTableRow searchSourceTableRow(SourceType source, String query){
		Connection connection;
		Statement statement;
		ResultSet result;
		Integer allPosts;
		
		connection = null;
		statement = null;
		result = null;
		
		allPosts = 0;
		
		try{
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();
			
			result = statement.executeQuery(query);
			
			while( result.next() ){
				allPosts = result.getInt(DatabaseValues.COUNT_FIELD);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
			allPosts = 0;
		}finally{
			try{ if(result != null) result.close(); }catch(Exception e) {}
			try{ if(statement != null) statement.close(); }catch(Exception e) {}
			try{ if(connection != null) connection.close(); }catch(Exception e) {}
		}
		
		return new SourceTableRow(source, allPosts);
	}

	private String buildQuery(SourceType source){

		String query = "select " +DatabaseValues.SOURCE_TYPE_FIELD+ ", sum(" +DatabaseValues.COUNT_FIELD+ ") as " +DatabaseValues.COUNT_FIELD+ " "  +
			"from " + DatabaseValues.RESULT_TABLE_NAME + " " +
			"where " + DatabaseValues.SOURCE_TYPE_FIELD + " rlike '" + source.getName() + "'";

		return query;
	}

}

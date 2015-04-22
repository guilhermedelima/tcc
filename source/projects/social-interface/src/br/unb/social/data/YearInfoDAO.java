package br.unb.social.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unb.social.model.AreaChart;
import br.unb.social.model.AreaChartRow;
import br.unb.social.model.ClassificationType;
import br.unb.social.model.PoliticianType;
import br.unb.social.model.Table;
import br.unb.social.model.TableRow;
import br.unb.social.model.YearInformation;

public class YearInfoDAO {

	public YearInfoDAO(){
	}

	public YearInformation seachYearInfo(PoliticianType politician, Integer year){

		List<TableRow> tableRowList;
		List<AreaChartRow> chartRowList;
		Table table;
		AreaChart chart;
		String query;
		
		tableRowList = new ArrayList<TableRow>();
		
		for(ClassificationType classification : ClassificationType.values()){
			query = buildQuery(politician, year, classification);
			tableRowList.add( searchTableRow(classification, query) );
		}
		
		chartRowList = convertTableRowsToChartRows(year, tableRowList);
		
		table = new Table(tableRowList);
		chart = new AreaChart(chartRowList);

		return new YearInformation(year, table, chart);
	}
	
	private TableRow searchTableRow(ClassificationType classification, String query){
		
		Integer sum;
		List<Integer> countList;
		Connection connection;
		Statement statement;
		ResultSet result;
		Integer month, count;
		
		sum = 0;
		countList = new ArrayList<Integer>();
		
		for(int i=0; i<TableRow.MONTH_LIST_SIZE; i++)
			countList.add(0);
		
		connection = null;
		statement = null;
		result = null;
		
		try{
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();

			result = statement.executeQuery(query);

			while( result.next() ){
				month = result.getInt(DatabaseValues.MONTH_FIELD);
				count = result.getInt(DatabaseValues.COUNT_FIELD);

				countList.set(month, count);
				
				sum += count;
			}

		}catch(SQLException ex){
			ex.printStackTrace();
			countList.clear();
		}finally{
			try{ if(result != null) result.close(); }catch(Exception e) {}
			try{ if(statement != null) statement.close(); }catch(Exception e) {}
			try{ if(connection != null) connection.close(); }catch(Exception e) {}
		}

		return new TableRow(classification, countList, sum);
	}
	
	private List<AreaChartRow> convertTableRowsToChartRows(Integer year, List<TableRow> simpleRows){
		List<AreaChartRow> chartRowList;
		
		chartRowList = new ArrayList<AreaChartRow>();
		
		for(int i=1; i<=12; i++){
			chartRowList.add(new AreaChartRow(year + "-" + String.format("%02d", i), 0, 0));	
		}
		
		for(TableRow simpleRow : simpleRows){
			for(int i=0; i<simpleRow.getMonthCountList().size(); i++){
				switch( simpleRow.getClassification() ){
				case POSITIVE:	chartRowList.get(i).setPositiveValues( simpleRow.getMonthCountList().get(i) );
								break;
				case NEGATIVE:	chartRowList.get(i).setNegativeValues( simpleRow.getMonthCountList().get(i) );
				}
			}
		}
		
		return chartRowList;
	}

	private String buildQuery(PoliticianType politician, Integer year, ClassificationType classification){

		String query = "select " + DatabaseValues.MONTH_FIELD + ", sum(" + DatabaseValues.COUNT_FIELD + ") as " + DatabaseValues.COUNT_FIELD + " "  + 
				"from " + DatabaseValues.RESULT_TABLE_NAME + " " +
				"where " + DatabaseValues.POLITICIAN_FIELD + " like '" + politician.getName() + "' and " +
				DatabaseValues.YEAR_FIELD + " = " + year + " and " +
				DatabaseValues.CLASSIFICATION_FIELD + " like '" + classification.getName() + "' " +
				"group by " + DatabaseValues.MONTH_FIELD;                                                  

		return query;
	}

}

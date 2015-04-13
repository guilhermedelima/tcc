package br.unb.social.data;

import java.util.ArrayList;
import java.util.List;

import br.unb.social.model.ClassificationType;
import br.unb.social.model.PoliticianType;
import br.unb.social.model.Table;
import br.unb.social.model.TableRow;

public class TableDAO {
	
	private DatabaseService service;

	public TableDAO(DatabaseService service){
		this.service = service;
	}

	public Table seachTable(PoliticianType politician, Integer year){

		List<TableRow> rowsList;
		String query;
		
		rowsList = new ArrayList<TableRow>();
		
		for(ClassificationType classification : ClassificationType.values()){
			query = buildQuery(politician, year, classification);
			rowsList.add( convertToTableRow(classification, query) );
		}

		return new Table(year, rowsList);
	}
	
	private TableRow convertToTableRow(ClassificationType classification, String query){
		
		Integer sum;
		List<Integer> countList;
		
		sum = 0;
		countList = service.execQueryPoliticianTable(query, TableRow.MONTH_LIST_SIZE, DatabaseValues.MONTH_FIELD, DatabaseValues.COUNT_FIELD);
		
		for(Integer val : countList){
			sum += val;
		}
		
		return new TableRow(classification, countList, sum);
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

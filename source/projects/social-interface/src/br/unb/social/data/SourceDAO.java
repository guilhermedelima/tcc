package br.unb.social.data;

import java.util.ArrayList;
import java.util.List;

import br.unb.social.model.PoliticianType;
import br.unb.social.model.SourceTable;
import br.unb.social.model.SourceTableRow;
import br.unb.social.model.SourceType;

public class SourceDAO {

	private DatabaseService service;

	private SourceDAO(DatabaseService service) {
		this.service = service;
	}

	public SourceTable searchSourceTable(PoliticianType politician, Integer year){

		List<SourceTableRow> rowsList;
		Integer allPosts;
		String query;
		
		rowsList = new ArrayList<SourceTableRow>();
		
		for(SourceType source : SourceType.values()){
			query = buildQuery(politician, source, year);
			allPosts = service.execQuerySource(query, DatabaseValues.COUNT_FIELD);
			
			rowsList.add(new SourceTableRow(source, allPosts));
		}

		return new SourceTable(year, rowsList);
	}

	public String buildQuery(PoliticianType politician, SourceType source, Integer year){

		String query = "select " +DatabaseValues.SOURCE_TYPE_FIELD+ ", sum(" +DatabaseValues.COUNT_FIELD+ ") as " +DatabaseValues.COUNT_FIELD+ " "  +
			"from " + DatabaseValues.RESULT_TABLE_NAME + " " +
			"where " + DatabaseValues.POLITICIAN_FIELD + " rlike '" + politician.getName() + "' and " +
			DatabaseValues.SOURCE_TYPE_FIELD + " rlike '" + source.getName() + "' and " +
			DatabaseValues.YEAR_FIELD + " = " + year + " " +
			"group by " + DatabaseValues.MONTH_FIELD;

		return query;
	}

}

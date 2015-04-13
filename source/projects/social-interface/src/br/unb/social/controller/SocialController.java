package br.unb.social.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.unb.social.data.DatabaseService;
import br.unb.social.data.TableDAO;
import br.unb.social.model.PoliticianType;
import br.unb.social.model.Table;
import br.unb.social.model.TableRow;
import br.unb.social.util.PropertiesLoader;

@Resource
public class SocialController {

	private static final Integer START_YEAR = 2014;
	private static final Integer END_YEAR = 2015;
	
	private final Result result;
	private final Validator validator;
	
	private final List<String> MONTH_LIST;
	private final List<Integer> YEAR_LIST;

	public SocialController(Result result, Validator validator) {
		this.result = result;
		this.validator = validator;
		
		this.MONTH_LIST = new ArrayList<String>();
		this.MONTH_LIST.add("");
		this.MONTH_LIST.add("Jan");
		this.MONTH_LIST.add("Fev");
		this.MONTH_LIST.add("Mar");
		this.MONTH_LIST.add("Abr");
		this.MONTH_LIST.add("Mai");
		this.MONTH_LIST.add("Jun");
		this.MONTH_LIST.add("Jul");
		this.MONTH_LIST.add("Ago");
		this.MONTH_LIST.add("Set");
		this.MONTH_LIST.add("Out");
		this.MONTH_LIST.add("Nov");
		this.MONTH_LIST.add("Dez");
		this.MONTH_LIST.add("Total");
		
		this.YEAR_LIST = new ArrayList<Integer>();
		
		for(int i=START_YEAR; i<=END_YEAR; i++){
			YEAR_LIST.add(i);
		}
	}

	@Path("/")
	public void index() {
		result.include("politician", null);
		result.include("enumList", Arrays.asList(PoliticianType.values()));
	}
	
	@Get
	@Path("/politician/{politicianID:[a-zA-Z]{3,8}}")
	public void politician(String politicianID){
		
		PoliticianType politician;
		List<Table> politicianTables;
		Table table;
		TableDAO dao;
		PropertiesLoader loader;
		DatabaseService service;
		Integer allPositive, allNegative;
		
		if( (politician = PoliticianType.getPoliticianByID(politicianID)) == null ){
			//TODO: Implementar ERRO
			result.redirectTo(SocialController.class).index();
			//
			
			return;
		}
		
		loader = PropertiesLoader.getInstance();
		service = new DatabaseService(loader);
		dao = new TableDAO(service);
		
		politicianTables = new ArrayList<Table>();
		allPositive = 0;
		allNegative = 0;
		
		for(int i=START_YEAR; i<=END_YEAR; i++){
			table = dao.seachTable(politician, i);
			
			for(TableRow row : table.getRows()){
				switch(row.getClassification()){
				case POSITIVE:	allPositive += row.getSum();
								break;
				case NEGATIVE:	allNegative += row.getSum();
								break;
				}
			}
			
			politicianTables.add( dao.seachTable(politician, i) );
		}
		
		result.include("monthList", MONTH_LIST);
		result.include("politician", politician);
		result.include("enumList", Arrays.asList(PoliticianType.values()));
		result.include("tableList", politicianTables);
		result.include("negativeSum", allNegative);
		result.include("positiveSum", allPositive);
	}

}

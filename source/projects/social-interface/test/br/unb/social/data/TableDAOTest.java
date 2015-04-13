package br.unb.social.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unb.social.model.PoliticianType;
import br.unb.social.model.Table;
import br.unb.social.model.TableRow;
import br.unb.social.util.PropertiesLoader;

public class TableDAOTest {

	private DatabaseService service;
	private PropertiesLoader loader;
	private TableDAO dao;
	
	@Before
	public void setUp(){
		loader = PropertiesLoader.getInstance();
		service = new DatabaseService(loader);
		dao = new TableDAO(service);
	}
	
	@Test
	public void testSearch2014AecioValues() {
		Table table;
		
		table = dao.seachTable(PoliticianType.AECIO, 2014);
		
		Assert.assertNotNull(table);
		Assert.assertEquals(Integer.valueOf(2014), table.getYear());
		Assert.assertNotNull(table.getRows());
		
		Assert.assertEquals(2, table.getRows().size());
		
		Assert.assertEquals(12, table.getRows().get(0).getMonthCountList().size());
		Assert.assertTrue(table.getRows().get(0).getSum() >= 0);
		
		Assert.assertEquals(12, table.getRows().get(1).getMonthCountList().size());
		Assert.assertTrue(table.getRows().get(1).getSum() >= 0);
		
		for(TableRow row : table.getRows()){
			System.out.println(row.getClassification().getName() + " = " + row.getSum());
		}
	}

}

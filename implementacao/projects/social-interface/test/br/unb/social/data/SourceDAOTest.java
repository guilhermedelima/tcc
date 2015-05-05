package br.unb.social.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unb.social.model.SourceTable;
import br.unb.social.model.SourceTableRow;

public class SourceDAOTest {

	private SourceDAO dao;
	
	@Before
	public void setup(){
		dao = new SourceDAO();
	}
	
	@Test
	public void testFacebookTwitter() {
		SourceTable table;
		
		table = dao.searchSourceTable();
		
		Assert.assertNotNull(table);
		
		for(SourceTableRow row : table.getRows()){
			System.out.println(row.getSource().getName() + " = " + row.getAllValues());
		}
	}

}

package br.unb.social.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unb.social.model.AreaChart;
import br.unb.social.model.AreaChartRow;
import br.unb.social.model.PoliticianType;
import br.unb.social.model.Table;
import br.unb.social.model.TableRow;
import br.unb.social.model.YearInformation;

public class YearInfoDAOTest {

	private YearInfoDAO dao;
	
	@Before
	public void setUp(){
		dao = new YearInfoDAO();
	}
	
	@Test
	public void testSearch2014AecioValues() {
		YearInformation info;
		Table simpleTable;
		AreaChart chart;
		
		info = dao.seachYearInfo(PoliticianType.AECIO, 2014);
		
		Assert.assertNotNull(info);
		Assert.assertEquals(Integer.valueOf(2014), info.getYear());
		
		simpleTable = info.getSimpleTable();
		chart = info.getAreaChart();

		Assert.assertNotNull(simpleTable);
		Assert.assertNotNull(chart);
		
		//Table
		Assert.assertNotNull(simpleTable.getRows());
		Assert.assertEquals(2, simpleTable.getRows().size());
		
		Assert.assertEquals(12, simpleTable.getRows().get(0).getMonthCountList().size());
		Assert.assertTrue(simpleTable.getRows().get(0).getSum() >= 0);
		
		Assert.assertEquals(12, simpleTable.getRows().get(1).getMonthCountList().size());
		Assert.assertTrue(simpleTable.getRows().get(1).getSum() >= 0);
		
		for(TableRow row : simpleTable.getRows()){
			System.out.println(row.getClassification().getName() + " = " + row.getSum());
			
			for(Integer val : row.getMonthCountList()){
				System.out.print(val + " ");
			}
			
			System.out.println();
		}
		
		//Chart
		Assert.assertNotNull(chart.getRows());
		Assert.assertEquals(12, chart.getRows().size());
		
		for(AreaChartRow row : chart.getRows()){
			System.out.println(row.getMonth() + " = " + row.getPositiveValues() + " | " + row.getNegativeValues());
		}
	}

}

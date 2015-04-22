package br.unb.social.model;

public class YearInformation {
	
	private Integer year;
	private Table simpleTable;
	private AreaChart areaChart;
	
	public YearInformation(Integer year, Table simpleTable, AreaChart areaChart) {
		this.year = year;
		this.simpleTable = simpleTable;
		this.areaChart = areaChart;
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Table getSimpleTable() {
		return simpleTable;
	}

	public void setSimpleTable(Table simpleTable) {
		this.simpleTable = simpleTable;
	}

	public AreaChart getAreaChart() {
		return areaChart;
	}

	public void setAreaChart(AreaChart areaChart) {
		this.areaChart = areaChart;
	}
	
}

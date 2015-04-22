package br.unb.social.model;

import java.util.List;

public class AreaChart {

	private List<AreaChartRow> rows;

	public AreaChart(List<AreaChartRow> rows) {
		this.rows = rows;
	}

	public List<AreaChartRow> getRows() {
		return rows;
	}

	public void setRows(List<AreaChartRow> rows) {
		this.rows = rows;
	}
	
}

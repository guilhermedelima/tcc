package br.unb.social.model;

import java.util.List;

public class SourceTable {
	
	private Integer year;
	private List<SourceTableRow> rows;
	
	public SourceTable(Integer year, List<SourceTableRow> rows) {
		this.year = year;
		this.rows = rows;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<SourceTableRow> getRows() {
		return rows;
	}

	public void setRows(List<SourceTableRow> rows) {
		this.rows = rows;
	}
	
}

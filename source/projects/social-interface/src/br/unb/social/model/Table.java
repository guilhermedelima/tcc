package br.unb.social.model;

import java.util.List;

public class Table{
	
	private Integer year;
	private List<TableRow> rows;
	
	public Table(Integer year, List<TableRow> rows) {
		this.year = year;
		this.rows = rows;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<TableRow> getRows() {
		return rows;
	}

	public void setRows(List<TableRow> rows) {
		this.rows = rows;
	}

}

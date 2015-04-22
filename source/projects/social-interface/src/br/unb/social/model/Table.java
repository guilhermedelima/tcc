package br.unb.social.model;

import java.util.List;

public class Table{
	
	private List<TableRow> rows;

	public Table(List<TableRow> rows) {
		this.rows = rows;
	}

	public List<TableRow> getRows() {
		return rows;
	}

	public void setRows(List<TableRow> rows) {
		this.rows = rows;
	}

}

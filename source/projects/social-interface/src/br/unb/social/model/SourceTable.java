package br.unb.social.model;

import java.util.List;

public class SourceTable {
	
	private List<SourceTableRow> rows;

	public SourceTable(List<SourceTableRow> rows) {
		this.rows = rows;
	}

	public List<SourceTableRow> getRows() {
		return rows;
	}

	public void setRows(List<SourceTableRow> rows) {
		this.rows = rows;
	}
	
}

package br.unb.social.model;

public class SourceTableRow {
	
	private SourceType source;
	private Integer allValues;
	
	public SourceTableRow(SourceType source, Integer allValues) {
		this.source = source;
		this.allValues = allValues;
	}

	public SourceType getSource() {
		return source;
	}

	public void setSource(SourceType source) {
		this.source = source;
	}

	public Integer getAllValues() {
		return allValues;
	}

	public void setAllValues(Integer allValues) {
		this.allValues = allValues;
	}

}

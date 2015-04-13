package br.unb.social.model;

public class SourceTableRow {
	
	private SourceType source;
	private Integer allValue;
	
	public SourceTableRow(SourceType source, Integer allValue) {
		this.source = source;
		this.allValue = allValue;
	}

	public SourceType getSource() {
		return source;
	}
	
	public void setSource(SourceType source) {
		this.source = source;
	}
	
	public Integer getAllValue() {
		return allValue;
	}
	
	public void setAllValue(Integer allValue) {
		this.allValue = allValue;
	}

}

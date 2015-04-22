package br.unb.social.model;

public class AreaChartRow {
	
	private String month;
	private Integer positiveValues;
	private Integer negativeValues;
	
	public AreaChartRow(){
	}
	
	public AreaChartRow(String month, Integer positiveValues, Integer negativeValues) {
		this.month = month;
		this.positiveValues = positiveValues;
		this.negativeValues = negativeValues;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getPositiveValues() {
		return positiveValues;
	}

	public void setPositiveValues(Integer positiveValues) {
		this.positiveValues = positiveValues;
	}

	public Integer getNegativeValues() {
		return negativeValues;
	}

	public void setNegativeValues(Integer negativeValues) {
		this.negativeValues = negativeValues;
	}

}

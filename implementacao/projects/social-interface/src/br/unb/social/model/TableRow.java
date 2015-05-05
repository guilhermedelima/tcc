package br.unb.social.model;

import java.util.List;

public class TableRow {

	public static final Integer MONTH_LIST_SIZE = 12;
	
	private ClassificationType classification;
	private List<Integer> monthCountList;
	private Integer sum;
	
	public TableRow(){
	}

	public TableRow(ClassificationType classification, List<Integer> monthCountList, Integer sum) {
		this.classification = classification;
		this.monthCountList = monthCountList;
		this.sum = sum;
	}

	public ClassificationType getClassification() {
		return classification;
	}

	public void setClassification(ClassificationType classification) {
		this.classification = classification;
	}

	public List<Integer> getMonthCountList() {
		return monthCountList;
	}

	public void setMonthCountList(List<Integer> monthCountList) {
		this.monthCountList = monthCountList;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}
	
}

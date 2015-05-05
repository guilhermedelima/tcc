package br.unb.bayes.social;

public enum PoliticianType {
	DILMA("dilma", "Dilma Rousseff", "/positive_dilma.txt", "/negative_dilma.txt"),
	AECIO("aecio", "Aécio Neves", "/positive_aecio.txt", "/negative_aecio.txt")
	;

	private final String id;
	private final String name;
	private final String positiveFile;
	private final String negativeFile;
	
	private PoliticianType(String id, String name, String positive, String negative){
		this.id = id;
		this.name = name;
		this.positiveFile = positive;
		this.negativeFile = negative;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getPositiveFile() {
		return positiveFile;
	}

	public String getNegativeFile() {
		return negativeFile;
	}
	
	public static PoliticianType getTypeFromID(String id){
		return DILMA.getId().equals(id) ? DILMA : AECIO;
	}
}
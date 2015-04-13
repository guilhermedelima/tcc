package br.unb.social.model;

public enum PoliticianType {
	DILMA("dilma", "Dilma Rousseff", "Candidata pelo PT"),
	AECIO("aecio", "Aécio Neves", "Candidato pelo PSDB")
	;

	private final String id;
	private final String name;
	private final String description;

	private PoliticianType(String id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public static PoliticianType getPoliticianByID(String id){
		if( id == null )
			return null;
		
		return id.equals(AECIO.getId()) ? AECIO : (id.equals(DILMA.getId()) ? DILMA : null); 
	}

}

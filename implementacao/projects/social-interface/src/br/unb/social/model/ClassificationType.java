package br.unb.social.model;

public enum ClassificationType {
	POSITIVE("Positivo"),
	NEGATIVE("Negativo")
	;
	
	private String name;
	
	private ClassificationType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}

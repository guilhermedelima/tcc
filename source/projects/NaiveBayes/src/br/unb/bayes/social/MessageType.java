package br.unb.bayes.social;

public enum MessageType {
	POSITIVE("Positivo"), 
	NEGATIVE("Negativo")
	;
	
	private final String name;
	
	private MessageType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}

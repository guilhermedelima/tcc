package br.unb.social.model;

public enum SourceType {
	FACEBOOK("Facebook"),
	TWITTER("Twitter"),
	;
	
	private String name;
	
	private SourceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}

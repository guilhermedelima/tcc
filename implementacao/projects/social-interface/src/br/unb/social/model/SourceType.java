package br.unb.social.model;

public enum SourceType {
	FACEBOOK("Facebook", "facebook"),
	TWITTER("Twitter", "twitter"),
	;
	
	private String name;
	private String id;
	
	private SourceType(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
}

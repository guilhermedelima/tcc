package br.unb.flume.source;

public enum SocialNetworkSourceType {
	TWITTER_REST("Twitter Rest Source"), 
	TWITTER_STREAM("Twitter Stream Source"),
	FACEBOOK_GRAPH("Facebook Source")
	;
	
	private final String name;
	
	private SocialNetworkSourceType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}

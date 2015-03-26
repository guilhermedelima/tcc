package br.unb.flume.service.twitter;

import org.apache.flume.Context;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration {
	
	public static final String PROPERTIES_FILE = "twitter.properties";
	public static final String CONSUMER_KEY = "consumerKey";
	public static final String CONSUMER_SECRET = "consumerSecret";
	public static final String TOKEN = "accessToken";
	public static final String TOKEN_SECRET = "accessTokenSecret";
	private ConfigurationBuilder builder;
	
	public TwitterConfiguration(Context context) {
		builder = new ConfigurationBuilder();
		
		builder.setOAuthConsumerKey(context.getString(CONSUMER_KEY));
		builder.setOAuthConsumerSecret(context.getString(CONSUMER_SECRET));
		builder.setOAuthAccessToken(context.getString(TOKEN));
		builder.setOAuthAccessTokenSecret(context.getString(TOKEN_SECRET));
		builder.setJSONStoreEnabled(true);
		builder.setIncludeMyRetweetEnabled(false);
	}
	
	public Configuration getValue(){
		return builder.build();
	}

}

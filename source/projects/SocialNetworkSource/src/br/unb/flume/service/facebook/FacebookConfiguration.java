package br.unb.flume.service.facebook;

import org.apache.flume.Context;

import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

public class FacebookConfiguration {
	
	private static final String APP_ID = "appId";
	private static final String APP_SECRET = "appSecret";
	private static final String APP_ACCESS_TOKEN = "appAccessToken";
	
	private ConfigurationBuilder builder;
	
	public FacebookConfiguration(Context context) {
		
		builder = new ConfigurationBuilder();
		
		builder.setOAuthAppId(context.getString(APP_ID));
		builder.setOAuthAppSecret(context.getString(APP_SECRET));
		builder.setOAuthAccessToken(context.getString(APP_ACCESS_TOKEN));
	}

	public Configuration getValue(){
		return builder.build();
	}
	
}

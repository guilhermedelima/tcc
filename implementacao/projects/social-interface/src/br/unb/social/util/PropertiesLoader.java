package br.unb.social.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	private static final String FILE_NAME = "/database.properties";

	private static final String DATABASE_DRIVER = "driver";
	private static final String DATABASE_URL = "url";
	private static final String DATABASE_USER = "user";
	private static final String DATABASE_PASSWORD = "password";

	private static PropertiesLoader instance;

	private String driver;
	private String url;
	private String user;
	private String password;
	
	static{
		instance = null;
	}

	private PropertiesLoader(){
		InputStream is;
		Properties properties;

		try{
			is = PropertiesLoader.class.getResourceAsStream(FILE_NAME);
			properties = new Properties();
			properties.load(is);
			
			driver = properties.getProperty(DATABASE_DRIVER);
			url = properties.getProperty(DATABASE_URL);
			user = properties.getProperty(DATABASE_USER);
			password = properties.getProperty(DATABASE_PASSWORD);
			
		}catch(Exception ex){
			ex.printStackTrace();
			
			driver = null;
			url = null;
			user = null;
			password = null;
		}
	}
	
	public static PropertiesLoader getInstance(){
		if( instance == null )
			instance = new PropertiesLoader();
		
		return instance;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
}

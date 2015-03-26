package br.unb.flume.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.JSONObject;
import br.unb.bayes.social.MessageType;
import br.unb.bayes.social.PoliticianType;
import br.unb.flume.source.SocialNetworkSourceType;

public class SourceUtil {
	
	private static final String AUTHOR = "author";
	private static final String TIMESTAMP = "timestamp";
	private static final String SOURCE = "type";
	private static final String MESSAGE = "message";
	private static final String POLITICIAN = "politician";
	private static final String CLASSIFICATION = "classification";

	private static Logger LOGGER = LoggerFactory.getLogger(SourceUtil.class); 
	
	//Format yyyy-MM-dd
	public static Date convertDate(String pattern){
		SimpleDateFormat formatter;
		Date date;
		
		try {
			
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.parse(pattern);
			
		} catch (ParseException e) {
			LOGGER.error("Unix Date error: ", e);
			date = null;
		}
		
		return date;
	}
	
	public static JSONObject convertToJsonSN(String author, Date date, 
			SocialNetworkSourceType type, String message,
			PoliticianType politician, MessageType classification){
		
		HashMap<String, String> obj;
		
		obj = new HashMap<String, String>();
		obj.put(AUTHOR, author);
		obj.put(TIMESTAMP, date.toString());
		obj.put(SOURCE, type.getName());
		obj.put(MESSAGE, message);
		obj.put(POLITICIAN, politician.getName());
		obj.put(CLASSIFICATION, classification.getName());
		
		return new JSONObject(obj);
	}

}

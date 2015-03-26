package br.unb.flume.source.facebook;

import java.util.Date;

import org.apache.flume.Context;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptstemmer.exceptions.PTStemmerException;
import br.unb.bayes.social.PoliticianType;
import br.unb.bayes.social.SocialNetworkClassifier;
import br.unb.flume.service.facebook.FacebookConfiguration;
import br.unb.flume.service.facebook.FacebookGraphService;
import br.unb.flume.util.SourceUtil;

public class FacebookSource extends AbstractSource implements EventDrivenSource, Configurable {

	private static final String DATE_INIT = "since";
	private static final String DATE_END = "until";
	private static final String LIMIT_POST = "limit";
	private static final String PAGES = "pages";
	private static final String POLITICIAN = "politician";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookSource.class);
	private Context context;
	private FacebookGraphService service;
	private Date since, until;
	private Integer limit;
	private String[] pageNames;
	private SocialNetworkClassifier classifier;
	
	@Override
	public void configure(Context context) {
		String dateInit, dateEnd;
		PoliticianType politicianType;
		
		this.context = context;
		
		dateInit = context.getString(DATE_INIT, "");
		dateEnd = context.getString(DATE_END, "");
		
		since = SourceUtil.convertDate(dateInit);
		until = SourceUtil.convertDate(dateEnd);
		
		limit = context.getInteger(LIMIT_POST, -1);
		
		pageNames = context.getString(PAGES).replace(",", " ").split("\\s+");
		
		politicianType = PoliticianType.getTypeFromID( context.getString(POLITICIAN) );
		
		try {
			
			classifier = new SocialNetworkClassifier(politicianType);
			
		} catch (PTStemmerException ex) {
			LOGGER.error("Facebook Source Error: ", ex);
		}
	}
	
	@Override
	public synchronized void start() {
		FacebookConfiguration config;
		
		config = new FacebookConfiguration(context);
		service = new FacebookGraphService(config, getChannelProcessor(), classifier);
		
		LOGGER.info("Starting Facebook Source");
		
		for(String name : pageNames){
			
			LOGGER.info("Facebook Source: searching for page " + name);
	
			service.searchFeeds(name, since, until, limit);
		}	
		
		LOGGER.info("Facebook Source: Finished Start method ");
	}
	
	@Override
	public synchronized void stop() {
		LOGGER.info("Finishing Facebook Source");
		super.stop();
	}

}
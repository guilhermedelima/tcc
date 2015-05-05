package br.unb.flume.source.twitter;

import org.apache.flume.Context;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ptstemmer.exceptions.PTStemmerException;

import br.unb.bayes.social.PoliticianType;
import br.unb.bayes.social.SocialNetworkClassifier;
import br.unb.flume.service.twitter.TwitterConfiguration;
import br.unb.flume.service.twitter.TwitterListener;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterStreamSource extends AbstractSource implements EventDrivenSource, Configurable {

	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterStreamSource.class);
	private static final String KEYWORDS_NAME = "keywords";
	private static final String POLITICIAN = "politician";
	
	private TwitterStream twitter;
	private String keywords[];
	private SocialNetworkClassifier classifier;
	
	@Override
	public void configure(Context context) {
		TwitterConfiguration config;
		String keyword;
		PoliticianType politicianType;
		
		config = new TwitterConfiguration(context);
		twitter = new TwitterStreamFactory(config.getValue()).getInstance();
		
		keyword = context.getString(KEYWORDS_NAME);
		keywords = keyword.split(", ");
		
		politicianType = PoliticianType.getTypeFromID( context.getString(POLITICIAN) );
		
		try {

			classifier = new SocialNetworkClassifier(politicianType);

		} catch (PTStemmerException ex) {
			LOGGER.error("Twitter Stream Source Error: ", ex);
		}
	}

	@Override
	public synchronized void start() {
		
		LOGGER.info("Starting Twitter Stream Source");
		
		FilterQuery filter;
		TwitterListener listener;
		
		listener = new TwitterListener(getChannelProcessor(), classifier);
		
		filter = new FilterQuery();
		filter.track(keywords);
		
		twitter.addListener(listener);
		twitter.filter(filter);
		
		LOGGER.info("End Twitter Stream Source initialization");
	}
	
	@Override
	public synchronized void stop() {
		super.stop();
		LOGGER.info("Finishing Twitter Stream Source");
	}
}

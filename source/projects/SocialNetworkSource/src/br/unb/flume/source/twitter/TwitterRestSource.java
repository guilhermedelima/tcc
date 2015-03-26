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
import br.unb.flume.service.twitter.TwitterRestService;

public class TwitterRestSource extends AbstractSource implements EventDrivenSource, Configurable {

	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterRestSource.class);
	private static final String DATE_INIT = "since";
	private static final String DATE_END = "until";
	private static final String LIMIT_TWEETS = "limit";
	private static final String KEYWORDS_NAME = "keywords";
	private static final Integer DEFAULT_LIMIT = 10000;
	private static final String POLITICIAN = "politician";

	private TwitterRestService service;
	private Context context;
	private String keywords;
	private String since, until;
	private Integer limit;
	private SocialNetworkClassifier classifier;

	@Override
	public void configure(Context context) {
		PoliticianType politicianType;

		since = context.getString(DATE_INIT, "");
		until = context.getString(DATE_END, "");
		limit = context.getInteger(LIMIT_TWEETS, DEFAULT_LIMIT);

		keywords = context.getString(KEYWORDS_NAME).replaceAll(",", " OR ");

		politicianType = PoliticianType.getTypeFromID( context.getString(POLITICIAN) );

		try {

			classifier = new SocialNetworkClassifier(politicianType);

		} catch (PTStemmerException ex) {
			LOGGER.error("Twitter Rest Source Error: ", ex);
		}
	}

	@Override
	public synchronized void start() {
		TwitterConfiguration config;
		Integer count;

		config = new TwitterConfiguration(context);
		service = new TwitterRestService(config, getChannelProcessor(), limit, classifier);

		LOGGER.info("Starting Twitter Rest Source");
		LOGGER.info("Twitter Rest Source: searching for " + keywords);

		while( (count=service.searchTweets(keywords, since, until)) > 0 ){

			LOGGER.info("Twitter Rest Source: Wrote " + count + " Events at channel");
		}

		LOGGER.info("Twitter Rest Source: Finished Start method ");
	}

	@Override
	public synchronized void stop() {
		super.stop();
		LOGGER.info("Finishing Twitter Rest Source");
	}
}

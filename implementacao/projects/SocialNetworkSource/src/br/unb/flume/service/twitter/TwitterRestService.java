package br.unb.flume.service.twitter;

import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.event.EventBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import br.unb.bayes.social.SocialNetworkClassifier;
import br.unb.flume.source.SocialNetworkSourceType;
import br.unb.flume.util.SourceUtil;


public class TwitterRestService {

	private static final String QUERY_FLAGS = "-filter:links exclude:retweets ";
	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterRestService.class);
	private final Integer MAX_QUERY;

	private Integer request_counter;
	private Twitter twitter;
	private ChannelProcessor channel;
	private SocialNetworkClassifier classifier;

	public TwitterRestService(TwitterConfiguration configuration, ChannelProcessor flumeChannel, 
			Integer maxQuery, SocialNetworkClassifier socialClassifier) {

		MAX_QUERY = maxQuery;
		request_counter = 0;

		channel = flumeChannel;
		twitter = new TwitterFactory(configuration.getValue()).getInstance();
		classifier = socialClassifier;
	}

	public Integer searchTweets(String keywords, String since, String until){

		if( request_counter > MAX_QUERY )
			return -1;

		QueryResult result;
		Query query;
		String queryText;
		JSONObject message;
		Integer fetchTweets;

		queryText = QUERY_FLAGS;

		query = new Query(queryText + keywords);
		query.setCount(100);

		if( !since.isEmpty() ){
			query.since(since);
		}

		if( !until.isEmpty() ){
			query.until(until);
		}

		fetchTweets = 0;

		try{

			do{
				result = twitter.search(query);

				for(Status status : result.getTweets()){
					message = SourceUtil.convertToJsonSN(
							status.getUser().getName(), 
							status.getCreatedAt(), 
							SocialNetworkSourceType.TWITTER_REST, 
							status.getText(),
							classifier.getType(),
							classifier.getClassification(status.getText()));

					channel.processEvent(EventBuilder.withBody(message.toString().getBytes()));
					
//					channel.processEvent(EventBuilder.withBody(status.getText().toString().getBytes()));
				}

				fetchTweets += result.getTweets().size();
				request_counter += result.getTweets().size();

			}while( (query = result.nextQuery()) != null && request_counter <= MAX_QUERY );

		}catch(TwitterException ex){
			LOGGER.error("Twitter Rest Source error: ", ex);

			request_counter = MAX_QUERY+1;
		}

		return fetchTweets;
	}

	public void reset(){
		this.request_counter = 0;
	}
}

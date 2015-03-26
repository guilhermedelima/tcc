package br.unb.flume.service.facebook;

import java.util.Date;

import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.event.EventBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.JSONObject;
import br.unb.bayes.social.SocialNetworkClassifier;
import br.unb.flume.source.SocialNetworkSourceType;
import br.unb.flume.util.SourceUtil;
import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PagableList;
import facebook4j.Paging;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;

public class FacebookGraphService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookGraphService.class);

	private Facebook facebook;
	private ChannelProcessor channel;
	private SocialNetworkClassifier classifier;

	public FacebookGraphService(FacebookConfiguration config, ChannelProcessor channel, SocialNetworkClassifier classifier) {
		this.facebook = new FacebookFactory(config.getValue()).getInstance();
		this.channel = channel;
		this.classifier = classifier;
	}

	public void searchFeeds(String pageName, Date since, Date until, Integer limit){

		ResponseList<Post> feeds;
		Paging<Post> paging;
		Reading reading;

		reading = new Reading();

		if( since != null && until != null )
			reading.since(since).until(until);

		if( limit > 0 )
			reading.limit(limit);

		try{
			feeds = facebook.getFeed(pageName, reading);

			do{
				for(Post feed : feeds){
					searchPostComments(feed);
				}

				paging = feeds.getPaging();

			}while( (paging != null) && ((feeds = facebook.fetchNext(paging)) != null) );

		}catch(FacebookException e){
			LOGGER.error("Facebook Graph Api error: ", e);
		}
	}

	public void searchPostComments(Post post){

		PagableList<Comment> comments;
		Paging<Comment> page;
		JSONObject message;
		Integer count;

		try {
			comments = post.getComments();

			count = 0;

			do{
				for(Comment comment : comments){
					message = SourceUtil.convertToJsonSN(
							comment.getFrom().getName(), 
							comment.getCreatedTime(), 
							SocialNetworkSourceType.FACEBOOK_GRAPH, 
							comment.getMessage(),
							classifier.getType(),
							classifier.getClassification( comment.getMessage() ));
					
					channel.processEvent(EventBuilder.withBody(message.toString().getBytes()));

//					channel.processEvent(EventBuilder.withBody(comment.getMessage().getBytes()));
				}

				page = comments.getPaging();

				count += comments.size();

			}while( (page != null) && ((comments=facebook.fetchNext(page)) != null) );

			LOGGER.info("Facebook Source: Wrote " +count+ " Events at Channel");

		} catch (FacebookException e) {
			LOGGER.error("Facebook Graph Api error: ", e);
		}
	}

}
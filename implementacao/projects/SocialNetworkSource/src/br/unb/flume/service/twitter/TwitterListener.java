package br.unb.flume.service.twitter;

import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.event.EventBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.JSONObject;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import br.unb.bayes.social.SocialNetworkClassifier;
import br.unb.flume.source.SocialNetworkSourceType;
import br.unb.flume.util.SourceUtil;

public class TwitterListener implements StatusListener{

	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterListener.class);
	private ChannelProcessor channel;
	private SocialNetworkClassifier classifier;

	public TwitterListener(ChannelProcessor channel, SocialNetworkClassifier classifier) {
		this.channel = channel;
		this.classifier = classifier;
	}

	@Override
	public void onException(Exception ex) {
		LOGGER.error("Twitter Stream Source error: ", ex);
	}

	@Override
	public void onStatus(Status status) {
		JSONObject message;

		message = SourceUtil.convertToJsonSN(
				status.getUser().getName(), 
				status.getCreatedAt(), 
				SocialNetworkSourceType.TWITTER_STREAM, 
				status.getText(),
				classifier.getType(),
				classifier.getClassification(status.getText()));

		channel.processEvent(EventBuilder.withBody(message.toString().getBytes()));

//		channel.processEvent(EventBuilder.withBody(status.getText().getBytes()));
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) { }

	@Override
	public void onScrubGeo(long arg0, long arg1) { }

	@Override
	public void onStallWarning(StallWarning arg0) { }

	@Override
	public void onTrackLimitationNotice(int arg0) { }

}

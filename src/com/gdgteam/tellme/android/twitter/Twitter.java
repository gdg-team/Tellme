package com.gdgteam.tellme.android.twitter;

import java.io.IOException;

import com.twitterapime.rest.Credential;
import com.twitterapime.rest.GeoLocation;
import com.twitterapime.rest.TweetER;
import com.twitterapime.rest.UserAccountManager;
import com.twitterapime.search.LimitExceededException;
import com.twitterapime.search.Tweet;
import com.twitterapime.xauth.Token;

public class Twitter {

	private static final String TOKEN =
			"1122414870-XERNlYXJ9PD3JF3PyQx5iz2KKsc0tndXrOGgViw";
	private static final String TOKEN_SECRET =
			"9HH0GwNJSZod1RReE3DFCnEJ8dhbiDau9IKYDhOa18";
	private static final String CONSUMER_KEY =
			"w3kkY16xZWsIiJ2jiaaFmw";
	private static final String CONSUMER_SECRET =
			"9gqw8KlmcBr3DveaqL1hA54Mm7EYTf2g3xwJGmKKg";

	public static void tweet(String text, String latitude, String longitude)
			throws IOException, LimitExceededException {
		text += " [geo:" + latitude + "," + longitude + "]";
		Token token = new Token(TOKEN, TOKEN_SECRET);
		Credential c = new Credential(CONSUMER_KEY, CONSUMER_SECRET, token);
		UserAccountManager mgr = UserAccountManager.getInstance(c);
		if (mgr.verifyCredential()) {
			GeoLocation loc = new GeoLocation(latitude, longitude);
			Tweet tweet = new Tweet(text, loc);
			TweetER tweeter = TweetER.getInstance(mgr);
			tweet = tweeter.post(tweet);
		}
	}
}
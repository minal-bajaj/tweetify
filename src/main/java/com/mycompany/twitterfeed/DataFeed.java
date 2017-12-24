package com.mycompany.twitterfeed;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class DataFeed {
	
	private final static int PAGE_SIZE = 200;
	
	Twitter twitter = null;
	
	public DataFeed() {
		// This factory instance is thread-safe and re-usable.
		this.twitter = TwitterFactory.getSingleton();
	}
	
	public List<Status> getTweets(String userId, int tweetsToAnalyze) {
		try {
			
			List<Status> tweets = new ArrayList<>();
			
			if (tweetsToAnalyze < PAGE_SIZE) {
				tweets.addAll(this.twitter.getUserTimeline(userId, new Paging(1, tweetsToAnalyze)));
				return tweets;
			}
			
			int page = 1;
			int tweetsDone = 0;
			
			while (tweetsDone < tweetsToAnalyze) {
				
				int pageSize = tweetsToAnalyze - tweetsDone > PAGE_SIZE ? PAGE_SIZE : (tweetsToAnalyze - tweetsDone);
				
				tweets.addAll(this.twitter.getUserTimeline(userId, new Paging(page, pageSize)));
				tweetsDone += pageSize;
				page += 1;
			}
			
			return tweets;
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Status> getReTweets(long tweetId) {
		try {
			
			return this.twitter.getRetweets(tweetId);
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Status> getFavorites(long tweetId) {
		try {
			
			return this.twitter.getFavorites(tweetId);
		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
	}
}
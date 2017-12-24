package com.mycompany.twitterfeed;

import java.util.Arrays;
import java.util.List;

import twitter4j.Status;

public enum Entity {
	
	TWEET ("tweet", (DataFeed feed, Status status) -> (Arrays.asList(status))),
	RETWEET ("retweet", (DataFeed feed, Status status) -> (feed.getReTweets(status.getId()))),
	FAV ("fav", (DataFeed feed, Status status) -> (feed.getFavorites(status.getId())));
	
	private String name;
	private Tweets elements;
	
	private Entity(String name, Tweets countable) {
		this.name = name;
		this.elements = countable;
	}
	
	public List<Status> details(DataFeed feed, Status status) {
		return this.elements.details(feed, status);
	}
	
	interface Tweets {
		List<Status> details(DataFeed feed, Status status);
	}
}
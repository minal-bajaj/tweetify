package com.mycompany.twitterfeed.aggregation;

import twitter4j.Status;

public interface Aggregator {

	public void feed(Status status);
	public int[] result();
	public String[] getLegends();
}

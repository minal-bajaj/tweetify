/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitterfeed;

import java.util.List;
import java.util.TimeZone;

import com.mycompany.twitterfeed.aggregation.Aggregator;
import com.mycompany.twitterfeed.aggregation.AggregatorFactory;
import com.mycompany.twitterfeed.aggregation.NHourlyAggregator;

import twitter4j.Status;

/**
 *
 * @author Minal.bajaj
 */
public enum Analyzer {
	
	INSTANCE;
	
	DataFeed dataFeed = null;
	
	private Analyzer() {
		this.dataFeed = new DataFeed();
	}
	
	public Response analyze(String userId, int interval, Entity groupBy, TimeZone timeZone, String aggregateBy, int tweetsToA) {
		List<Status> statuses = this.dataFeed.getTweets(userId, tweetsToA);
		
		Aggregator aggregator = AggregatorFactory.INSTANCE.getAggregator(aggregateBy, groupBy, timeZone, dataFeed);
		
		if (statuses == null) {
			return null;
		}
		
		for (Status status : statuses) {
			aggregator.feed(status);
		}
		
		return new Response(aggregator.result(), interval, aggregator.getLegends());
	}
	
	public static void main(String[] args) {
//		int[] result = Analyzer.INSTANCE.analyze(295218901l, 1, Entity.TWEET, TimeZone.getTimeZone("Asia/Kolkata"));
//		
//		for (int i = 0; i < result.length; i++) {
//			System.out.println(i + ": " + result[i]);
//		}
	}
}
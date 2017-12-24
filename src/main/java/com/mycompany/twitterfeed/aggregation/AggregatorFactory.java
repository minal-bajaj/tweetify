package com.mycompany.twitterfeed.aggregation;

import java.util.TimeZone;

import com.mycompany.twitterfeed.DataFeed;
import com.mycompany.twitterfeed.Entity;

public enum AggregatorFactory {
	INSTANCE;
	
	public Aggregator getAggregator(String aggregateBy, Entity entity, TimeZone timezone, DataFeed dataFeed) {
		switch (aggregateBy) {
		case "HOURLY":
			return new NHourlyAggregator(entity, timezone, dataFeed);

		case "WEEKLY":
			return new WeeklyAggregator(entity, timezone, dataFeed);
			
		default:
			return null;
		}
	}
}

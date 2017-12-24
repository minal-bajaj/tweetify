package com.mycompany.twitterfeed.aggregation;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.mycompany.twitterfeed.DataFeed;
import com.mycompany.twitterfeed.Entity;

import twitter4j.Status;

/**
 * NHourlyAggregator aggregates data on the basis of hour of the day.
 * NHourlyAggregator is flexible and can aggregate with different N values, like you can aggregate data with 1 hour or 2 hours.
 * 
 * @author Minal.Bajaj
 */
public class NHourlyAggregator implements Aggregator {
	
	private final static String[] legends = new String[26];
	
	static {
		for (int i = 0; i < 24; i++) {
			legends[i] = i + "";
		}
	}
	
	private Entity entity;
	
	private DataFeed dataFeed;
	private int[] aggregatedData;
	private Calendar calendar;
	
	/**
	 * This constructor accepts interval in hour and initializes NHourlyAggregator.
	 * @param interval
	 */
	public NHourlyAggregator(Entity entity, TimeZone timezone, DataFeed dataFeed) {
		this.entity = entity;
		
		this.dataFeed = dataFeed;
		this.aggregatedData = new int[24];
		this.calendar = Calendar.getInstance(timezone);
	}
	
	/**
	 * This method takes input for aggregator.
	 * This method is not thread-safe, should used in synchronous manner or external synchronization is required.
	 * @param creationTime
	 */
	public void feed(Status status) {
		
		List<Status> detailedStatuses = this.entity.details(this.dataFeed, status);
		
		if (detailedStatuses == null) {
			return;
		}
		
		for (Status dStatus : detailedStatuses) {
			calendar.setTime(dStatus.getCreatedAt());
			
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			
			this.aggregatedData[hour] = this.aggregatedData[hour] + 1;
		}
	}
	
	/**
	 * This method returns aggregated data with specified interval. 
	 * Aggregated values are stored in returned integer array and each value in array partitioned with interval.
	 * @return aggregated data
	 */
	public int[] result() {
		return this.aggregatedData;
	}
	
	@Override
	public String[] getLegends() {
		return legends;
	}
}

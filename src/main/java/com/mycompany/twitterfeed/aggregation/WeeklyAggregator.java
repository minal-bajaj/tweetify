package com.mycompany.twitterfeed.aggregation;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.mycompany.twitterfeed.DataFeed;
import com.mycompany.twitterfeed.Entity;

import twitter4j.Status;

/**
 * WeeeklyAggregator aggregates data on the basis of day of week.
 * 
 * @author Minal.Bajaj
 */
public class WeeklyAggregator implements Aggregator {
	
	private final static String[] legends = {"\"Sunday\"" ,"\"Monday\"", "\"Tuesday\"",
			"\"Wednesday\"", "\"Thursday\"", "\"Friday\"", "\"Saturday\""};
	
	private Entity entity;
	
	private DataFeed dataFeed;
	private int[] aggregatedData;
	private Calendar calendar;
	
	/**
	 * This constructor accepts entity, timeZone and dataFeed.
	 * @param interval
	 */
	public WeeklyAggregator(Entity entity, TimeZone timeZone, DataFeed dataFeed) {
		this.entity = entity;
		
		this.dataFeed = dataFeed;
		this.aggregatedData = new int[7];
		this.calendar = Calendar.getInstance(timeZone);
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
			
			int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			
			this.aggregatedData[day] = this.aggregatedData[day] + 1;
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

package com.mycompany.twitterfeed;

public class Response {
	
	private int[] data;
	private int interval;
	private String[] legends;
	
	public Response(int[] data, int interval, String[] legends) {
		this.data = data;
		this.interval = interval;
		this.legends = legends;
	}

	public int[] getData() {
		return data;
	}
	
	public void setData(int[] data) {
		this.data = data;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public String[] getLegends() {
		return legends;
	}
	
	public void setLegends(String[] legends) {
		this.legends = legends;
	}
	
	public String getDataStr() {
		StringBuffer outputBuffer = new StringBuffer("[ ");
		
		for (int i = 0; i < this.data.length; i++) {
			if (i != 0) {
				outputBuffer.append(", ");
			}
			
			outputBuffer.append(this.data[i]);
		}
		
		outputBuffer.append("]");
		return outputBuffer.toString();
	}
	
	public String getLegendsStr() {
		StringBuffer outputBuffer = new StringBuffer("[ ");
		
		for (int i = 0; i < this.legends.length; i++) {
			if (i != 0) {
				outputBuffer.append(", ");
			}
			
			outputBuffer.append(this.legends[i]);
		}
		
		outputBuffer.append("]");
		return outputBuffer.toString();
	}
}

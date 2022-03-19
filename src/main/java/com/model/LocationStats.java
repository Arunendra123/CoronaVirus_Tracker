package com.model;

public class LocationStats {

	private String state;
	private String country;
	private int totalcases;
	private int diff;
	
	
	public LocationStats(String state, String country, int totalcases, int diff) {
		super();
		this.state = state;
		this.country = country;
		this.totalcases = totalcases;
		this.diff = diff;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalcases() {
		return totalcases;
	}
	public void setTotalcases(int totalcases) {
		this.totalcases = totalcases;
	}
	public int getDiff() {
		return diff;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}
}

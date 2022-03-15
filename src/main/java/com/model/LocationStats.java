package com.model;

public class LocationStats {

	private String state;
	private String country;
	private int totalcases;
		
	public LocationStats(String state, String country, int totalcases) {
		super();
		this.state = state;
		this.country = country;
		this.totalcases = totalcases;
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
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", totalcases=" + totalcases + "]";
	}

}

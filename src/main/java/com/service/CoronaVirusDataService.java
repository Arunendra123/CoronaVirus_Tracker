package com.service;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.model.LocationStats;

@Service
public class CoronaVirusDataService {
	
	 private String CORONA_VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
     
	 private List<LocationStats> globalList=new ArrayList<LocationStats>();
	 
	 @PostConstruct
	 @Scheduled(cron="* * * 1 * *")     // per hours
	 public void fetchData() throws IOException, InterruptedException {
    	 HttpClient client=HttpClient.newHttpClient();
    	 HttpRequest request=HttpRequest.newBuilder().uri(URI.create(CORONA_VIRUS_DATA_URL)).build();
    	 HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
    	
    	 
    	 List<LocationStats> localList=new ArrayList<LocationStats>();
    	 Reader in=new StringReader(response.body());
    	 Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
    	 for (CSVRecord record : records) {
    		 int totalcases= Integer.parseInt(record.get(record.size()-1));
    		 int diff=Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2));
    		 localList.add(new LocationStats(record.get("Province/State"),record.get("Country/Region"),totalcases,diff));
    	 }
    	 
    	 //updating global just avoid concurrency issue
    	 globalList=localList;
     }

	public List<LocationStats> getGlobalList() {
		return globalList;
	}	 
}

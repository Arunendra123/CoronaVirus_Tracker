package com.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.model.LocationStats;
import com.service.CoronaVirusDataService;

@Controller
public class CoroncaVirus {

	@Autowired
	private CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model) throws IOException, InterruptedException {
		//coronaVirusDataService.fetchData();
		List<LocationStats> globalList=coronaVirusDataService.getGlobalList();
		int totalCases=globalList.stream().mapToInt(stats->stats.getDiff()).sum();
		int totalReportedCases=globalList.stream().mapToInt(stats->stats.getTotalcases()).sum();
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalCases", totalCases);
		model.addAttribute("globalList", globalList);
		return "home";
	}
}

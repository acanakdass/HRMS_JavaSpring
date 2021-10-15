package javaProjects.HRMS.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaProjects.HRMS.business.abstracts.JobTitleService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.entities.concretes.JobTitle;

@RestController
@RequestMapping("/api/jobTitles")
public class JobTitlesControllers {

	private JobTitleService jobTitleService;	
	
	@Autowired
	public JobTitlesControllers(JobTitleService jobTitleService) {
		super();
		this.jobTitleService = jobTitleService;
	}


	@GetMapping("/getAll")
	public DataResult<List<JobTitle>> getAll(){
		return this.jobTitleService.getAll();
	}
}

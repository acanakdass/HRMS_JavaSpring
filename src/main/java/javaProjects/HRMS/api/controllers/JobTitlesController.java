package javaProjects.HRMS.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import javaProjects.HRMS.business.abstracts.JobTitleService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobTitle;
import javaProjects.HRMS.entities.dtos.JobTitleAddDto;

@RestController
@RequestMapping("/api/jobTitles")
public class JobTitlesController {

	private JobTitleService jobTitleService;	
	
	@Autowired
	public JobTitlesController(JobTitleService jobTitleService) {
		super();
		this.jobTitleService = jobTitleService;
	}


	@GetMapping("/getAll")
	public DataResult<List<JobTitle>> getAll(){
		return this.jobTitleService.getAll();
	}
	
	@PostMapping("/add")
    public Result add(@RequestBody JobTitleAddDto jobTitleAddDto){
        return this.jobTitleService.add(jobTitleAddDto);
    }
}

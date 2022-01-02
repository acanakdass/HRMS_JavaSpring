package javaProjects.HRMS.api.controllers.Resume;

import java.util.List;

import javaProjects.HRMS.entities.concretes.Users.Candidate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.CityService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.City;
import javaProjects.HRMS.entities.concretes.Users.Employer;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	private final CityService cityService;

	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<City>> getAll(){
		return this.cityService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<City> getById(@RequestParam int id){
		return this.cityService.getById(id);
	}
}

package javaProjects.HRMS.api.controllers.Resume;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.CityService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.City;
import javaProjects.HRMS.entities.concretes.Users.Employer;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	private CityService cityService;

	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<City>> getAll(){
		return this.cityService.getAll();
	}
}

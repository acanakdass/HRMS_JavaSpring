package javaProjects.HRMS.business.abstracts;

import java.util.List;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.entities.concretes.City;

public interface CityService {
	DataResult<List<City>> getAll();
	DataResult<City> getById(int id);
}

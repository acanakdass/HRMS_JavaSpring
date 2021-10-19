package javaProjects.HRMS.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.CityService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.dataAccess.abstracts.CityDao;
import javaProjects.HRMS.entities.concretes.City;

@Service
public class CityManager implements CityService {

	private CityDao cityDao;

	public CityManager(CityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>(this.cityDao.findAll(), "Şehirler Listelendi");
	}

	@Override
	public DataResult<City> getById(int id) {
		City city = this.cityDao.findById(id).get();
		if (city == null) {
			return new ErrorDataResult<City>("Şehir Bulunamadı");
		}
		return new SuccessDataResult<City>(this.cityDao.findById(id).get(), "Şehir Listelendi");
	}

}

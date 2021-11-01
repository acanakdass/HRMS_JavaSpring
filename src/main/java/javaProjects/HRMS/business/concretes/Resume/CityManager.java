package javaProjects.HRMS.business.concretes.Resume;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.CityService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.dataAccess.abstracts.JobAdvertisement.CityDao;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.City;

@Service
public class CityManager extends BaseManager<CityDao, City, Integer> implements CityService {

	@Autowired
	public CityManager(CityDao cityDao) {
		super(cityDao, "City");
		// TODO Auto-generated constructor stub
	}


	

	

//	@Override
//	public DataResult<List<City>> getAll() {
//		return new SuccessDataResult<List<City>>(this.cityDao.findAll(), "Şehirler Listelendi");
//	}
//
//	@Override
//	public DataResult<City> getById(int id) {
//		City city = this.cityDao.findById(id).get();
//		if (city == null) {
//			return new ErrorDataResult<City>("Şehir Bulunamadı");
//		}
//		return new SuccessDataResult<City>(this.cityDao.findById(id).get(), "Şehir Listelendi");
//	}

}

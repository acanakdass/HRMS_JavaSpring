package javaProjects.HRMS.business.concretes;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.CityService;
import javaProjects.HRMS.business.abstracts.EmployerService;
import javaProjects.HRMS.business.abstracts.JobAdvertisementService;
import javaProjects.HRMS.business.abstracts.JobTitleService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.EmployerDao;
import javaProjects.HRMS.dataAccess.abstracts.JobAdvertisementDao;
import javaProjects.HRMS.dataAccess.abstracts.JobTitleDao;
import javaProjects.HRMS.entities.concretes.City;
import javaProjects.HRMS.entities.concretes.Employer;
import javaProjects.HRMS.entities.concretes.JobAdvertisement;
import javaProjects.HRMS.entities.concretes.JobTitle;
import javaProjects.HRMS.entities.dtos.JobAdvertisementAddDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;
	private CityService cityService;
	private EmployerService employerService;
	private JobTitleService jobTitleService;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, CityService cityService,
			EmployerService employerService, JobTitleService jobTitleService) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.cityService = cityService;
		this.employerService = employerService;
		this.jobTitleService = jobTitleService;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(), "İlanlar Listelendi");
	}

	@Override
	public Result add(JobAdvertisementAddDto jobAdvertisementAddDto) {
		JobAdvertisement jobAdvert = new JobAdvertisement();

		jobAdvert.setDescription(jobAdvertisementAddDto.getDescription());
		jobAdvert.setActive(jobAdvertisementAddDto.isActive());
		jobAdvert.setQuota(jobAdvertisementAddDto.getQuota());
		jobAdvert.setMinSalary(jobAdvertisementAddDto.getMinSalary());
		jobAdvert.setMaxSalary(jobAdvertisementAddDto.getMaxSalary());
		jobAdvert.setExpirationDate(jobAdvertisementAddDto.getExpirationDate());
		jobAdvert.setReleaseDate(new Date(System.currentTimeMillis()));

		City city = this.cityService.getById(jobAdvertisementAddDto.getCityId()).getData();
		jobAdvert.setCity(city);

		JobTitle jobTitle = this.jobTitleService.getById(jobAdvertisementAddDto.getJobTitleId()).getData();
		jobAdvert.setJobTitle(jobTitle);

		Employer employer = this.employerService.getById(jobAdvertisementAddDto.getEmployerId()).getData();
		jobAdvert.setEmployer(employer);

		this.jobAdvertisementDao.save(jobAdvert);
		return new SuccessResult("İş İlanı Eklendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActive() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.GetAllActive(), "Aktif İlanlar Listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveByAscReleaseDate() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllActiveByAscReleaseDate(), "Aktif İlanlar Eskiden Yeniye Listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveByCompanyName(String companyName) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllActiveByCompanyName(companyName),"İş İlanları Şirket İsmine Göre Listelendi");
	}

	@Override
	public Result setActive(int jobAdvertisementId) {
		JobAdvertisement jobAdvert = this.jobAdvertisementDao.findById(jobAdvertisementId).get();
		if(jobAdvert==null) {
			return new ErrorResult("İlan Bulunamadı");
		}
		jobAdvert.setActive(true);
		jobAdvertisementDao.save(jobAdvert);
		return new SuccessResult("İlan Aktif Hale Getirildi");
	}
	
	@Override
	public Result setPassive(int jobAdvertisementId) {
		JobAdvertisement jobAdvert = this.jobAdvertisementDao.findById(jobAdvertisementId).get();
		if(jobAdvert==null) {
			return new ErrorResult("İlan Bulunamadı");
		}
		jobAdvert.setActive(false);
		jobAdvertisementDao.save(jobAdvert);
		return new SuccessResult("İlan Pasif Hale Getirildi");
	}

}

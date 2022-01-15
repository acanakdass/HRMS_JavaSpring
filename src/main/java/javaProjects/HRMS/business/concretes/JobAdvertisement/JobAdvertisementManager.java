package javaProjects.HRMS.business.concretes.JobAdvertisement;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.CityService;
import javaProjects.HRMS.business.abstracts.JobAdvertisement.JobAdvertisementService;
import javaProjects.HRMS.business.abstracts.JobAdvertisement.JobTitleService;
import javaProjects.HRMS.business.abstracts.JobAdvertisement.WorkTimeService;
import javaProjects.HRMS.business.abstracts.JobAdvertisement.WorkTypeService;
import javaProjects.HRMS.business.abstracts.Users.EmployerService;
import javaProjects.HRMS.business.abstracts.Users.SystemEmployeeService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.core.constants.Messages;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.JobAdvertisement.JobAdvertisementDao;
import javaProjects.HRMS.dataAccess.abstracts.Users.SystemEmployeeDao;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobAdvertisement;
import javaProjects.HRMS.entities.abstracts.SystemEmployeeConfirm;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.City;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobTitle;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.WorkTime;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.WorkType;
import javaProjects.HRMS.entities.concretes.Users.Employer;
import javaProjects.HRMS.entities.concretes.Users.SystemEmployee;
import javaProjects.HRMS.entities.dtos.JobAdvertisementAddDto;

@Slf4j
@Service
public class JobAdvertisementManager extends BaseManager<JobAdvertisementDao, JobAdvertisement, Integer>  implements JobAdvertisementService {

	private final JobAdvertisementDao jobAdvertisementDao;
	private final CityService cityService;
	private final EmployerService employerService;
	private final JobTitleService jobTitleService;
	private final WorkTypeService workTypeService;
	private final WorkTimeService workTimeService;
	private final SystemEmployeeService systemEmployeeService;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, CityService cityService,
			EmployerService employerService, JobTitleService jobTitleService,
			WorkTypeService workTypeService,WorkTimeService workTimeService,
			SystemEmployeeService systemEmployeeService) {
		super(jobAdvertisementDao,"Job Advertisement");
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.cityService = cityService;
		this.employerService = employerService;
		this.jobTitleService = jobTitleService;
		this.workTimeService = workTimeService;
		this.workTypeService = workTypeService;
		this.systemEmployeeService = systemEmployeeService;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(), "İlanlar Listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvertisement>> getAll(int pageNo,int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(pageable).getContent(), "İlanlar Listelendi");
	}

	@Override
	public Result add(JobAdvertisementAddDto jobAdvertisementAddDto) {
		log.info(String.valueOf(jobAdvertisementAddDto));
		System.out.println(jobAdvertisementAddDto);
		JobAdvertisement jobAdvert = new JobAdvertisement();

		jobAdvert.setDescription(jobAdvertisementAddDto.getDescription());
		jobAdvert.setActive(jobAdvertisementAddDto.isActive());
		jobAdvert.setQuota(jobAdvertisementAddDto.getQuota());
		jobAdvert.setMinSalary(jobAdvertisementAddDto.getMinSalary());
		jobAdvert.setMaxSalary(jobAdvertisementAddDto.getMaxSalary());
		jobAdvert.setExpirationDate(jobAdvertisementAddDto.getExpirationDate());
		jobAdvert.setReleaseDate(new Date(System.currentTimeMillis()));
		
		SystemEmployeeConfirm systemEmployeeConfirm= new SystemEmployeeConfirm();
		systemEmployeeConfirm.setConfirmed(false);
		jobAdvert.setSystemEmployeeConfirm(systemEmployeeConfirm);

		City city = this.cityService.getById(jobAdvertisementAddDto.getCityId()).getData();
		System.out.println(city);
		jobAdvert.setCity(city);

		JobTitle jobTitle = this.jobTitleService.getById(jobAdvertisementAddDto.getJobTitleId()).getData();
		jobAdvert.setJobTitle(jobTitle);

		Employer employer = this.employerService.getById(jobAdvertisementAddDto.getEmployerId()).getData();
		jobAdvert.setEmployer(employer);
		
		WorkType workType = this.workTypeService.getById(jobAdvertisementAddDto.getWorkTypeId()).getData();
		jobAdvert.setWorkType(workType);
		
		WorkTime workTime = this.workTimeService.getById(jobAdvertisementAddDto.getWorkTimeId()).getData();
		jobAdvert.setWorkTime(workTime);
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
		Optional<JobAdvertisement> jobAdvert = this.jobAdvertisementDao.findById(jobAdvertisementId);
		if(jobAdvert.isEmpty()) {
			return new ErrorResult("İlan Bulunamadı");
		}
		jobAdvert.get().setActive(true);
		jobAdvertisementDao.save(jobAdvert.get());
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
		@Override
		public Result  setConfirmed(int jobAdvertisementId, Integer systemEmployeeId) {
	        SystemEmployee sysytemEmployee= this.systemEmployeeService.getById(systemEmployeeId).getData();
	        
			
/*			SystemEmployeeConfirm systemEmployeeConfirm = new SystemEmployeeConfirm();
			systemEmployeeConfirm.setConfirmed(true);		
			systemEmployeeConfirm.setSystemEmployee(sysytemEmployee);
			systemEmployeeConfirm.setVerifiedDate(new Date(System.currentTimeMillis()));		*/
			
			JobAdvertisement jobAdvert = this.getById(jobAdvertisementId).getData();
			jobAdvert.getSystemEmployeeConfirm().setConfirmed(true);
			jobAdvert.getSystemEmployeeConfirm().setSystemEmployee(sysytemEmployee);
			jobAdvert.getSystemEmployeeConfirm().setVerifiedDate(new Date(System.currentTimeMillis()));
			this.update(jobAdvert);
			return new SuccessResult("İş İlanı Başarıyla Onaylandı");
		}
	

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveByDescReleaseDate() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllActiveByDescReleaseDate(), "Aktif İlanlar Eskiden Yeniye Listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAndConfirmed() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.GetAllActiveAndConfirmed(), "Aktif ve onaylanmış ilanlar listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAndConfirmedByPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		List<JobAdvertisement> data = this.jobAdvertisementDao.GetAllActiveAndConfirmed(pageable);
		return new SuccessDataResult<List<JobAdvertisement>>(data, "İlanlar Listelendi");
	}

}

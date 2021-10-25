package javaProjects.HRMS.business.concretes.JobAdvertisement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.CityService;
import javaProjects.HRMS.business.abstracts.JobTitleService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.core.constants.Messages;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.ErrorResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.CityDao;
import javaProjects.HRMS.dataAccess.abstracts.JobTitleDao;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.City;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobTitle;
import javaProjects.HRMS.entities.dtos.JobTitleAddDto;
import net.bytebuddy.asm.Advice.This;

@Service
public class JobTitleManager extends BaseManager<JobTitleDao, JobTitle, Integer> implements JobTitleService {

	
	private JobTitleDao jobTitleDao;

	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super(jobTitleDao, "Job Title");
		this.jobTitleDao = jobTitleDao;
	}


	@Override
	public DataResult<JobTitle> getByTitle(String title) {
		return new SuccessDataResult<JobTitle>(this.jobTitleDao.getByTitle(title),Messages.alreadyExists("Job Title"));

	}

	@Override
	public Result add(JobTitleAddDto jobTitleAddDto) {
		JobTitle jobTitle = new JobTitle();
		jobTitle.setTitle(jobTitleAddDto.getTitle());
		if (CheckIfJobTitleExists(jobTitle)==false) {
			this.jobTitleDao.save(jobTitle);
			return new SuccessResult(Messages.added("Job Title"));
		}
		return new ErrorResult(Messages.alreadyExists("Job Title"));
	}

	// Business Rules Methods
	private boolean CheckIfJobTitleExists(JobTitle jobTitle) {
		DataResult<JobTitle> jobTitleResult = this.getByTitle(jobTitle.getTitle());
		if (jobTitleResult.getData() == null) {
			return false;
		}
		return true;
	}

//	@Override
//	public DataResult<JobTitle> getById(int id) {
//		JobTitle jobTitle = this.jobTitleDao.findById(id).get();
//		if (jobTitle == null) {
//			return new ErrorDataResult<JobTitle>("İş Pozisyonu Bulunamadı");
//		}
//		return new SuccessDataResult<JobTitle>(this.jobTitleDao.findById(id).get(), "İş Pozisyonu Listelendi");
//	}

}

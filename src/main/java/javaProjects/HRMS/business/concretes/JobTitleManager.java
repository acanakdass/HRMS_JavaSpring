package javaProjects.HRMS.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.JobTitleService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.ErrorResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.JobTitleDao;
import javaProjects.HRMS.entities.concretes.City;
import javaProjects.HRMS.entities.concretes.JobTitle;
import javaProjects.HRMS.entities.dtos.JobTitleAddDto;

@Service
public class JobTitleManager implements JobTitleService {

	private JobTitleDao jobTitleDao;

	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll(), "Listed");
	}

	@Override
	public DataResult<JobTitle> getByTitle(String title) {

		return new SuccessDataResult<JobTitle>(this.jobTitleDao.getByTitle(title), "İş Pozisyonu Listelendi");
	}

	@Override
	public Result add(JobTitleAddDto jobTitleAddDto) {
		JobTitle jobTitle = new JobTitle();
		jobTitle.setTitle(jobTitleAddDto.getTitle());
		if (CheckIfJobTitleExists(jobTitle)==false) {
			this.jobTitleDao.save(jobTitle);
			return new SuccessResult("İş Posizyonu Başarıyla Eklendi");
		}
		return new ErrorResult("İş Pozisyonu Zaten Mevcut");
	}

	// Business Rules Methods
	private boolean CheckIfJobTitleExists(JobTitle jobTitle) {
		DataResult<JobTitle> jobTitleResult = this.getByTitle(jobTitle.getTitle());
		if (jobTitleResult.getData() == null) {
			return false;
		}
		return true;
	}

	@Override
	public DataResult<JobTitle> getById(int id) {
		JobTitle jobTitle = this.jobTitleDao.findById(id).get();
		if (jobTitle == null) {
			return new ErrorDataResult<JobTitle>("İş Pozisyonu Bulunamadı");
		}
		return new SuccessDataResult<JobTitle>(this.jobTitleDao.findById(id).get(), "İş Pozisyonu Listelendi");
	}

}

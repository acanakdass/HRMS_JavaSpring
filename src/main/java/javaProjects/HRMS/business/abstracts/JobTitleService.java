package javaProjects.HRMS.business.abstracts;

import java.util.List;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.JobTitle;
import javaProjects.HRMS.entities.dtos.JobTitleAddDto;

public interface JobTitleService {
	DataResult<List<JobTitle>> getAll();
	DataResult<JobTitle> getByTitle(String title);
	DataResult<JobTitle> getById(int id);
	Result add(JobTitleAddDto jobTitle); 
}

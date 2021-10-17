package javaProjects.HRMS.business.abstracts;

import java.util.List;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.entities.concretes.JobTitle;

public interface JobTitleService {
	DataResult<List<JobTitle>> getAll();
}

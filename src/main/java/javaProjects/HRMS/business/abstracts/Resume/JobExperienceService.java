package javaProjects.HRMS.business.abstracts.Resume;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Resume.JobExperience;

public interface JobExperienceService {
	DataResult<JobExperience> getAll();
	Result add(JobExperience experience);
}

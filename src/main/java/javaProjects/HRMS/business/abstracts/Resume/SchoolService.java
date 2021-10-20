package javaProjects.HRMS.business.abstracts.Resume;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Resume.School;

public interface SchoolService {
	
	DataResult<School> getAll();
	Result add(School school);

}

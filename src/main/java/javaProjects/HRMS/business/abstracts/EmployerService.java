package javaProjects.HRMS.business.abstracts;



import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.entities.concretes.Users.Employer;

public interface EmployerService extends BaseService<Employer, Integer> {
	
	DataResult<Employer> getByEmail(String email);
	void save(Employer employer);
}

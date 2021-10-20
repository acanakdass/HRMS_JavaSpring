package javaProjects.HRMS.business.abstracts;
import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.entities.concretes.Candidate;

public interface CandidateService extends BaseService<Candidate,Integer> {

	DataResult<Candidate> getByIdentityNumber(String identityNumber);
	DataResult<Candidate> getByEmail(String email);
	void save(Candidate candidate);
}

package javaProjects.HRMS.business.abstracts.Users;
import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.entities.concretes.Users.Candidate;

public interface CandidateService extends BaseService<Candidate,Long> {

	DataResult<Candidate> getByIdentityNumber(String identityNumber);
	DataResult<Candidate> getByEmail(String email);
	DataResult<Candidate> getById(Long id);
	void save(Candidate candidate);
	DataResult<Long> add(Candidate candidate);
}

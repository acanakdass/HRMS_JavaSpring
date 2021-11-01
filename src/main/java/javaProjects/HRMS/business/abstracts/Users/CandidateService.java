package javaProjects.HRMS.business.abstracts.Users;
import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.entities.concretes.Users.Candidate;

public interface CandidateService extends BaseService<Candidate,Integer> {

	DataResult<Candidate> getByIdentityNumber(String identityNumber);
	DataResult<Candidate> getByEmail(String email);
	DataResult<Candidate> getById(int id);
	void save(Candidate candidate);
	DataResult<Integer> add(Candidate candidate);
}

package javaProjects.HRMS.business.abstracts;

import java.util.List;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Candidate;

public interface CandidateService {
	DataResult<List<Candidate>> getAll();
	DataResult<Candidate> getByIdentityNumber(String identityNumber);
	DataResult<Candidate> getByEmail(String email);
	Result add(Candidate candidate);
}

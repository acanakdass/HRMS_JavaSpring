package javaProjects.HRMS.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Candidate;

public interface CandidateService {
	DataResult<List<Candidate>> getAll();
	DataResult<Candidate> getByIdentityNumber(String identityNumber);
	DataResult<Candidate> getByEmail(String email);
	Result add(Candidate candidate);
}

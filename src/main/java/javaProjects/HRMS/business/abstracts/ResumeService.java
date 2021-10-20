package javaProjects.HRMS.business.abstracts;

import java.util.List;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Resume.Resume;

public interface ResumeService {
	DataResult<List<Resume>> getAll();
	DataResult<Resume> getByCandidateId(int id);
	Result add(Resume resume);
}

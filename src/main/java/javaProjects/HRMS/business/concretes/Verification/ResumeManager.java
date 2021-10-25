package javaProjects.HRMS.business.concretes.Verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.CandidateService;
import javaProjects.HRMS.business.abstracts.ResumeService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessResult;

import javaProjects.HRMS.dataAccess.abstracts.ResumeDao;
import javaProjects.HRMS.entities.concretes.Resume.Resume;
import javaProjects.HRMS.entities.concretes.Users.Candidate;

@Service
public class ResumeManager extends BaseManager<ResumeDao, Resume, Integer> implements ResumeService {

	private ResumeDao resumeDao;
	private CandidateService candidateService;

	@Autowired
	public ResumeManager(ResumeDao resumeDao, CandidateService candidateService) {
		super(resumeDao, "Resume");
		this.resumeDao = resumeDao;
		this.candidateService = candidateService;
	}

	@Override
	public DataResult<Resume> getByCandidateId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(Resume resume) {

		if (resume.getLanguages().size() > 0) {
			resume.getLanguages().stream().forEach(language -> {
				language.setResume(resume);
			});
		}
		
		//Set Resume for Candidate and save
		Candidate candidate = this.candidateService.getById(resume.getCandidateId()).getData();
		candidate.setResume(resume);

		
		
		if (resume.getLanguages().size() > 0) {
			resume.getLanguages().stream().forEach(language -> {
				language.setResume(resume);
			});
		}

		if (resume.getJobExperiences().size() > 0) {
			resume.getJobExperiences().stream().forEach(experience -> {
				experience.setResume(resume);
			});
		}

		if (resume.getSkills().size() > 0) {
			resume.getSkills().stream().forEach(skill -> {
				skill.setResume(resume);
			});
		}

		if (resume.getSchools().size() > 0) {
			resume.getSchools().stream().forEach(school -> {
				school.setResume(resume);
			});
		}

		this.resumeDao.save(resume);
		this.candidateService.save(candidate);
		return new SuccessResult("Özgeçmiş Eklendi");
	}

	@Override
	public void save(Resume resume) {
		this.resumeDao.save(resume);	
	}

}

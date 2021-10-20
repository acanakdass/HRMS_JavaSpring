package javaProjects.HRMS.business.concretes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.CandidateService;
import javaProjects.HRMS.business.abstracts.ResumeService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.CandidateDao;
import javaProjects.HRMS.dataAccess.abstracts.ResumeDao;
import javaProjects.HRMS.entities.concretes.Resume.JobExperience;
import javaProjects.HRMS.entities.concretes.Resume.Language;
import javaProjects.HRMS.entities.concretes.Resume.Resume;
import javaProjects.HRMS.entities.concretes.Resume.School;
import javaProjects.HRMS.entities.concretes.Resume.Skill;

@Service
public class ResumeManager implements ResumeService {

	private ResumeDao resumeDao;
	private CandidateService candidateService;

	@Autowired
	public ResumeManager(ResumeDao resumeDao, CandidateService candidateService) {
		super();
		this.resumeDao = resumeDao;
		this.candidateService = candidateService;
	}

	@Override
	public DataResult<List<Resume>> getAll() {
		List<Resume> resumes = this.resumeDao.findAll();
		if (resumes.isEmpty()) {
			return new ErrorDataResult<>("Özgeçmiş Bulunamadı");
		}
		return new SuccessDataResult<List<Resume>>(resumes, "Özgeçmişler Listelendi");
	}

	@Override
	public DataResult<Resume> getByCandidateId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(Resume resume1) {

		Resume resume = new Resume();
		resume.setCandidate(this.candidateService.getAll().getData().get(1));
		resume.setCoverLetter("test cover letter.");
		resume.setGithubAddress("acanakdas");
		resume.setLinkedinAddress("acanakdass");
		
		
		List<JobExperience> experiences = new ArrayList<JobExperience>();
//experiences
		JobExperience jobExperience1 = new JobExperience();
		jobExperience1.setCompany("Geossoft");
		jobExperience1.setPosition("Junior Full Stack Developer");
		jobExperience1.setStartDate("2020-11-01");
		jobExperience1.setQuitDate("2021-12-01");
		
		JobExperience jobExperience2 = new JobExperience();
		jobExperience2.setCompany("TRENDYOL");
		jobExperience2.setPosition("Full Stack Developer");
		jobExperience2.setStartDate("2022-01-10");
		jobExperience2.setQuitDate("2022-12-01");
		experiences.add(jobExperience1);
		experiences.add(jobExperience2);
		resume.setJobExperiences(experiences);
//schools
		List<School> schools=new ArrayList<School>();
		
		School school1 = new School();
		school1.setSchoolName("SDÜ");

		School school2 = new School();
		school2.setSchoolName("sttal");
		
		schools.add(school1);
		schools.add(school2);

		resume.setSchools(schools);
		System.out.println("Schoolsssssssssssssssss");
		System.out.println(schools);
		
//Language		
List<Language> languages=new ArrayList<Language>();
		
		Language language1 = new Language();
		language1.setLanguageName("English");;
		language1.setLevel(4);

		Language language2 = new Language();
		language2.setLanguageName("German");;
		language2.setLevel(2);
		
		languages.add(language1);
		languages.add(language2);
		
		resume.setLanguages(languages);

//Skills		
List<Skill> skills= new ArrayList<Skill>();
		
		Skill skill1 = new Skill();
		skill1.setName("Javascript");
		Skill skill2 = new Skill();
		skill2.setName("C# Java");
		
		skills.add(skill1);
		skills.add(skill2);

		resume.setSkills(skills);
//System.out.println(resume);
		this.resumeDao.save(resume1);
		return new SuccessResult("Özgeçmiş Eklendi");
	}

}

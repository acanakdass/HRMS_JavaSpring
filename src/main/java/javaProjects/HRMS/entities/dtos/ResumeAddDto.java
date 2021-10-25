package javaProjects.HRMS.entities.dtos;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javaProjects.HRMS.entities.concretes.Resume.JobExperience;
import javaProjects.HRMS.entities.concretes.Resume.Language;
import javaProjects.HRMS.entities.concretes.Resume.School;
import javaProjects.HRMS.entities.concretes.Resume.Skill;
import javaProjects.HRMS.entities.concretes.Users.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeAddDto {
	private String githubaddress;
	
	
	private String linkedinAddress;


	private String photoUrl;


	private String coverLetter;



	private int candidateId;
	

	private List<JobExperience> jobExperiences;
	

	private List<Skill> skills;
	

	private List<Language> languages;
	

	private List<School> schools;

}

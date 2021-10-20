package javaProjects.HRMS.entities.concretes.Resume;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javaProjects.HRMS.entities.concretes.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resumes")
public class Resume {
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;



	@Column(name="github_address")
	private String githubAddress;

	@Column(name="linkedin_address")
	private String linkedinAddress;

	@Column(name="photo_url")
	private String photoUrl;

	@Column(name="cover_letter")
	private String coverLetter;

//	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
	private Candidate candidate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "resume")
	private List<JobExperience> jobExperiences;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "resume")
	private List<Skill> skills;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "resume")
	private List<Language> languages;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "resume")
	private List<School> schools;

}

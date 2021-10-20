package javaProjects.HRMS.entities.concretes.Resume;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javaProjects.HRMS.entities.concretes.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

	@JsonIgnore
	@OneToOne(mappedBy="resume",cascade = CascadeType.ALL)
	private Candidate candidate;
	

	
//	@JsonIgnore
	@OneToMany(mappedBy = "resume",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<JobExperience> jobExperiences;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "resume",cascade = CascadeType.ALL)
	private List<Skill> skills;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
	@OneToMany(mappedBy = "resume",cascade = CascadeType.ALL)
	private List<Language> languages;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "resume",cascade = CascadeType.ALL)
	private List<School> schools;
	
	@Transient
	private int candidateId;
}

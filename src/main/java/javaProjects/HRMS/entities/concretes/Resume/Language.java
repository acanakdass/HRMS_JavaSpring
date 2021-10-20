package javaProjects.HRMS.entities.concretes.Resume;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="resume_languages")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resume"})
public class Language {
	
	@Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String languageName;
	
	@Max(5)
	@Min(1)
	private int level;
	
	
	@JoinColumn(name = "resume_id")
	@ManyToOne
	private Resume resume;
}

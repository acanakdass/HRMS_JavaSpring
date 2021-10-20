package javaProjects.HRMS.entities.concretes.Resume;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="resume_job_experiences")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resume"})

public class JobExperience {
	@Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String company;
	
	private String position;
	private String startDate;
	private String quitDate;

//	@NotNull
	@JoinColumn(name = "resume_id")
	@ManyToOne
	private Resume resume;

}

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
@Table(name="resume_schools")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resume"})

public class School {
	
	@Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String schoolName;

    private String department;

    private Date graduateDate;

    private Date startedDate;

    private Date finishedDate;
    
    @JoinColumn(name = "resume_id")
	@ManyToOne
	private Resume resume;
}
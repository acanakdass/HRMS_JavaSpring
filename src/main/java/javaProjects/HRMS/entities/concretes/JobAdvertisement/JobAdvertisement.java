package javaProjects.HRMS.entities.concretes.JobAdvertisement;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javaProjects.HRMS.entities.abstracts.SystemEmployeeConfirm;
import javaProjects.HRMS.entities.concretes.Application;
import javaProjects.HRMS.entities.concretes.Users.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="job_advertisements")
public class JobAdvertisement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	

	@Column(columnDefinition="TEXT",name = "description")
	private String description;
	

	@Column(name="quota")
	private int quota;
	
	@Column(name="min_salary")
	private int minSalary;
	
	@Column(name="max_salary")
	private int maxSalary;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "release_date")
    private Date releaseDate;
    
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	@ManyToOne
	@JoinColumn(name="job_title_id")
	private JobTitle jobTitle;

	@ManyToOne
	@JoinColumn(name="work_type_id")
	private WorkType workType;

	@ManyToOne
	@JoinColumn(name="work_time_id")
	private WorkTime workTime;
	
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
    
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "system_employee_confirm_id")
	private SystemEmployeeConfirm systemEmployeeConfirm;

	@OneToMany(mappedBy = "jobAdvert")
	@JsonIgnore
	private Collection<Application> applications;
	
}

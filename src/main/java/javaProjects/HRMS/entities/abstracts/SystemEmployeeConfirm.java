package javaProjects.HRMS.entities.abstracts;


import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobAdvertisement;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.WorkTime;
import javaProjects.HRMS.entities.concretes.Users.Employer;
import javaProjects.HRMS.entities.concretes.Users.SystemEmployee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "system_employee_confirms")
public class SystemEmployeeConfirm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "is_confirmed")
	private boolean isConfirmed;
	
	@Column(name = "confirm_date")
	private Date verifiedDate;

	@JsonIgnore
	@OneToOne(mappedBy="systemEmployeeConfirm",cascade = CascadeType.ALL)
	private Employer employer;
	
	@JsonIgnore
	@OneToOne(mappedBy="systemEmployeeConfirm",cascade = CascadeType.ALL)
	private JobAdvertisement jobAdvertisement;
	
	@ManyToOne
	@JoinColumn(name="system_employee_id")
	private SystemEmployee systemEmployee;
}
 

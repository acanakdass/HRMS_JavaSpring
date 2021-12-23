package javaProjects.HRMS.entities.concretes.Users;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javaProjects.HRMS.entities.abstracts.SystemEmployeeConfirm;
import javaProjects.HRMS.core.entities.User;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobAdvertisement;
import javaProjects.HRMS.entities.concretes.Verification.VerificationCodeEmployer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAdvertisements"})
public class Employer extends User {

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "website")
	private String webAddress;

	@Column(name = "phone_number")
	private String phoneNumber;


	@OneToMany(mappedBy = "employer")
	private List<JobAdvertisement> jobAdvertisements;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "verification_id")
	private VerificationCodeEmployer verificationCodeEmployer;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "system_employee_confirm_id")
	private SystemEmployeeConfirm systemEmployeeConfirm;
}

package javaProjects.HRMS.entities.concretes.Verification;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javaProjects.HRMS.entities.abstracts.VerificationCode;
import javaProjects.HRMS.entities.concretes.Users.Candidate;
import javaProjects.HRMS.entities.concretes.Users.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verification_code_employers")
public class VerificationCodeEmployer extends VerificationCode{

	
	@Column(name = "employer_id")
	private int employerId;
	
	@JsonIgnore
	@OneToOne(mappedBy="verificationCodeEmployer",cascade = CascadeType.ALL)
	private Employer employer;
}
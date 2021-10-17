package javaProjects.HRMS.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javaProjects.HRMS.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employers")

public class Employer extends User {


	@Column(name = "company_name")
	private String companyName;

	@Column(name = "website")
	private String webAddress;

	@Column(name = "phone_number")
	private String phoneNumber;
}

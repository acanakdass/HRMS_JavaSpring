package javaProjects.HRMS.entities.concretes.Users;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.OneToMany;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiParam;
import javaProjects.HRMS.entities.abstracts.SystemEmployeeConfirm;
import javaProjects.HRMS.entities.abstracts.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "system_employee")
public class SystemEmployee extends User{

	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@JsonIgnore
	@ApiParam(hidden = true)
	@OneToMany(mappedBy = "systemEmployee")
	private List<SystemEmployeeConfirm> systemEmployeeConfirms;
	
}
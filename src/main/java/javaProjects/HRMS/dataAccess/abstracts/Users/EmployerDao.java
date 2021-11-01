package javaProjects.HRMS.dataAccess.abstracts.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.Users.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	Employer getByEmail(String email);
}

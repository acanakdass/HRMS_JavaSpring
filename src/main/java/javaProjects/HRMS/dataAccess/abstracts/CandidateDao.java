package javaProjects.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.Users.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {

	Candidate getByIdentityNumber(String identityNumber);
	Candidate getByEmail(String email);
}

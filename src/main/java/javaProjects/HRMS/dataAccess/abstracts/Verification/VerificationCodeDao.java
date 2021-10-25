package javaProjects.HRMS.dataAccess.abstracts.Verification;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.abstracts.VerificationCode;

public interface VerificationCodeDao extends JpaRepository<VerificationCode, Integer> {

}

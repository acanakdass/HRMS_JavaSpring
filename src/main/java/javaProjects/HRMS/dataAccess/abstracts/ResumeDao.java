package javaProjects.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.Resume.Resume;

public interface ResumeDao extends JpaRepository<Resume, Integer> {

}

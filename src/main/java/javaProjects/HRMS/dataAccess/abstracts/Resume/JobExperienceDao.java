package javaProjects.HRMS.dataAccess.abstracts.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.Resume.JobExperience;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {

}

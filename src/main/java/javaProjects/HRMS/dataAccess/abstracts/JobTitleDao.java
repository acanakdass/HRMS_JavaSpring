package javaProjects.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobTitle;

public interface JobTitleDao extends JpaRepository<JobTitle, Integer> {
	JobTitle getByTitle(String title);
}

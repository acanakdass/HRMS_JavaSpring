package javaProjects.HRMS.dataAccess.abstracts.JobAdvertisement;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.JobAdvertisement.WorkType;


public interface WorkTypeDao extends JpaRepository<WorkType, Integer> {


}

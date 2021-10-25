package javaProjects.HRMS.dataAccess.abstracts.JobAdvertisement;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.JobAdvertisement.WorkTime;

public interface WorkTimeDao extends JpaRepository<WorkTime, Integer> {

}

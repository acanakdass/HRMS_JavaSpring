package javaProjects.HRMS.dataAccess.abstracts.JobAdvertisement;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.JobAdvertisement.City;

public interface CityDao extends JpaRepository<City, Integer> {

}

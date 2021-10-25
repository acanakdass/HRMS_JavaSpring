package javaProjects.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.JobAdvertisement.City;

public interface CityDao extends JpaRepository<City, Integer> {

}

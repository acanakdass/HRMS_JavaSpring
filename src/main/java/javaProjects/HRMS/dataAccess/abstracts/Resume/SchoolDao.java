package javaProjects.HRMS.dataAccess.abstracts.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.Resume.School;

public interface SchoolDao extends JpaRepository<School, Integer>{

}

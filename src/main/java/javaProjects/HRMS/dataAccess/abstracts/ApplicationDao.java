package javaProjects.HRMS.dataAccess.abstracts;

import javaProjects.HRMS.entities.concretes.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationDao extends JpaRepository<Application, Integer> {
}

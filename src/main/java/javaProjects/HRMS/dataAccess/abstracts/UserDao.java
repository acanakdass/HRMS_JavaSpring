package javaProjects.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.abstracts.User;

public interface UserDao extends JpaRepository<User, Integer> {

}

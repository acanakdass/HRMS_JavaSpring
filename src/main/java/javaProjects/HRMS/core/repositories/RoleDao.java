package javaProjects.HRMS.core.repositories;


import javaProjects.HRMS.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
    Role findByName(String name);

}

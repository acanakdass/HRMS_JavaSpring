package javaProjects.HRMS.dataAccess.abstracts.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.Resume.Skill;

public interface SkillDao extends JpaRepository<Skill, Integer> {

}

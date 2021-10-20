package javaProjects.HRMS.dataAccess.abstracts.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProjects.HRMS.entities.concretes.Resume.Language;

public interface LanguageDao extends JpaRepository<Language, Integer> {

}

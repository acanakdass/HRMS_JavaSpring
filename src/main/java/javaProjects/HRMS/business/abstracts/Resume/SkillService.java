package javaProjects.HRMS.business.abstracts.Resume;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Resume.Skill;

public interface SkillService {
	DataResult<Skill> getAll();
	Result add(Skill skill);
}

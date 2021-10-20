package javaProjects.HRMS.business.abstracts.Resume;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Resume.Language;

public interface LanguageService {
	DataResult<Language> getAll();
	Result add(Language language);
}

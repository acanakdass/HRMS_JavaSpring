package javaProjects.HRMS.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.EmployerService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.ErrorResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.EmployerDao;
import javaProjects.HRMS.entities.concretes.Candidate;
import javaProjects.HRMS.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private final EmployerDao employerDao;

	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "İşverenler Listelendi");
	}

	@Override
	public Result add(Employer employer) {

		if (CheckIfEmailExists(employer.getEmail())) {
			this.employerDao.save(employer);
			return new SuccessResult("Kullanıcı başarıyla eklendi");
		}
		return new ErrorResult("Email Zaten Mevcut");
	}

	@Override
	public DataResult<Employer> getByEmail(String email) {
		Employer employer = this.employerDao.getByEmail(email);
		if (employer == null) {
			return new ErrorDataResult<Employer>("Email'e ait kullanıcı bulunamadı");
		}
		return new SuccessDataResult<Employer>(employer, "Kullanıcı Bulundu");
	}

//	Business Rule Methods	
	private boolean CheckIfEmailExists(String email) {
		if (this.getByEmail(email).isSuccess()) {
			return false;
		}
		return true;
	}

}
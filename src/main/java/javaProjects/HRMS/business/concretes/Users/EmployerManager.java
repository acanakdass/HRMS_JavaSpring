package javaProjects.HRMS.business.concretes.Users;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.Users.EmployerService;
import javaProjects.HRMS.business.abstracts.Verification.VerificationCodeService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.ErrorResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.Users.EmployerDao;
import javaProjects.HRMS.entities.concretes.Users.Employer;
import javaProjects.HRMS.entities.concretes.Verification.VerificationCodeEmployer;

@Service
public class EmployerManager extends BaseManager<EmployerDao, Employer, Long> implements EmployerService {

	private EmployerDao employerDao;

	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super(employerDao, "Employer");
		this.employerDao = employerDao;
	}

	@Override
	public Result add(Employer employer) {

		if (CheckIfEmailExists(employer.getEmail())) {
			if (CheckIfEmailMatchesWithWebsite(employer)) {
				this.employerDao.save(SetVerification(employer));
				return new SuccessResult("Kullanıcı başarıyla eklendi");
			}
			return new ErrorResult("Email adresi şirket domain'i ile eşleşmiyor");
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

	@Override
	public void save(Employer employer) {
		this.employerDao.save(employer);
	}

	
	//	Business Rule Methods	
	private boolean CheckIfEmailExists(String email) {
		if (this.getByEmail(email).isSuccess()) {
			return false;
		}
		return true;
	}

	private boolean CheckIfEmailMatchesWithWebsite(Employer employer) {
		Pattern pattern = Pattern.compile(employer.getWebAddress(), Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(employer.getEmail());
		System.out.println(employer.getCompanyName());
		System.out.println(employer.getEmail());
		boolean matchFound = matcher.find();
		return matchFound;
	}

	
	private Employer SetVerification(Employer employer) {
		UUID uuid = UUID.randomUUID();
		VerificationCodeEmployer verificationCodeEmployer =new VerificationCodeEmployer();
		verificationCodeEmployer.setVerified(false);
		verificationCodeEmployer.setCode(uuid.toString());
		employer.setVerificationCodeEmployer(verificationCodeEmployer);
		return employer;
		
	} 
}
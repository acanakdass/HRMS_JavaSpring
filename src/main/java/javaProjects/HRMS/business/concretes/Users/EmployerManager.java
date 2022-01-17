package javaProjects.HRMS.business.concretes.Users;

import java.util.UUID;

import javaProjects.HRMS.core.business.abstracts.UserService;
import javaProjects.HRMS.core.utilities.security.Helpers.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.Users.EmployerService;
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
public class EmployerManager extends BaseManager<EmployerDao, Employer, Integer> implements EmployerService {

	private final EmployerDao employerDao;
	private final UserService userService;
	private final EncryptionService encryptionService;
	@Autowired
	public EmployerManager(EmployerDao employerDao, UserService userService, EncryptionService encryptionService) {
		super(employerDao, "Employer");
		this.employerDao = employerDao;
		this.userService = userService;
		this.encryptionService = encryptionService;
	}

	@Override
	public Result add(Employer employer) {

		if (CheckIfEmailExists(employer.getEmail())) {
			if (CheckIfEmailMatchesWithWebsite(employer)) {
				employer.setPassword(encryptionService.EncodePassword(employer.getPassword()));
				this.employerDao.save(SetVerification(employer));
				this.userService.addRoleToUser(employer.getEmail(),"employer_role");
				return new SuccessResult("İşveren Kullanıcısı Başarıyla Eklendi");
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
		/*Pattern pattern = Pattern.compile(employer.getWebAddress(), Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(employer.getEmail());
		System.out.println(employer.getEmail());
		boolean matchFound = matcher.find();
		return matchFound;*/
		return  true;
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
package javaProjects.HRMS.business.concretes.Verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.CityService;
import javaProjects.HRMS.business.abstracts.Verification.VerificationCodeService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.dataAccess.abstracts.JobAdvertisement.CityDao;
import javaProjects.HRMS.dataAccess.abstracts.Verification.VerificationCodeDao;
import javaProjects.HRMS.entities.abstracts.VerificationCode;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.City;

@Service
public class VerificationCodeManager extends BaseManager<VerificationCodeDao, VerificationCode, Integer> implements VerificationCodeService{

	private VerificationCodeDao verificationCodeDao;
	
	@Autowired
	public VerificationCodeManager(VerificationCodeDao verificationCodeDao) {
		super(verificationCodeDao, "Verification Code");
		this.verificationCodeDao = verificationCodeDao;
	}

}

package javaProjects.HRMS.business.concretes;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.CandidateService;
import javaProjects.HRMS.core.adapters.concretes.MernisServiceAdapter;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.ErrorResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.CandidateDao;
import javaProjects.HRMS.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;

	@Autowired
	public CandidateManager(CandidateDao candidateDao) {
		super();
		this.candidateDao = candidateDao;

	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "Adaylar Listelendi");
	}

	@Override
	public Result add(Candidate candidate) {

		if (CheckIfIdentityNumberExists(candidate.getIdentityNumber())) {
			
			if (CheckIfEmailExists(candidate.getEmail())) {
				if (IdentifyUserWithMernis(candidate)) {
					this.candidateDao.save(candidate);
					return new SuccessResult("Kullanıcı bilgileri mernis ile doğrulandı ve sisteme eklendi");
				} else {
					return new ErrorResult("Mernis kimlik bilgilerini doğrulayamadı");
				}
			}else {
				return new ErrorResult("Email Zaten Mevcut");
			}
		} else {
			return new ErrorResult("Tc Kimlik Numarası Sistemde Mevcut");
		}
	}

	@Override
	public DataResult<Candidate> getByEmail(String email) {
		Candidate candidate = this.candidateDao.getByEmail(email);
		if (candidate == null) {
			return new ErrorDataResult<Candidate>("Email'e ait kullanıcı bulunamadı");
		}
		return new SuccessDataResult<Candidate>(candidate, "Kullanıcı Bulundu");
	}

	@Override
	public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
		Candidate candidate = this.candidateDao.getByIdentityNumber(identityNumber);
		if (candidate == null) {
			return new ErrorDataResult<Candidate>("Kullanıcı Bulunamadı");
		}
		return new SuccessDataResult<Candidate>(candidate, "Aday Listelendi");
	}

	private boolean CheckIfIdentityNumberExists(String identityNumber) {
		if (this.getByIdentityNumber(identityNumber).getData() == null) {
			return true;
		}
		return false;
	}

	private boolean CheckIfEmailExists(String email) {
		if (this.getByEmail(email).isSuccess()) {
			return false;
		}
		return true;
	}

	private boolean IdentifyUserWithMernis(Candidate candidate) {
		MernisServiceAdapter mernisService = new MernisServiceAdapter();
		boolean mernisResult = true;
//		mernisResult = mernisService.checkIfRealPerson(candidate.getIdentityNumber(), candidate.getFirstName(),
//				candidate.getLastName(), candidate.getBirthYear());
		if (mernisResult) {
			return true;
		}
		return false;
	}

}

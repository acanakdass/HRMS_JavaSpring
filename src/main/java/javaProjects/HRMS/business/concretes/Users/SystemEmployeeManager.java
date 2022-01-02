package javaProjects.HRMS.business.concretes.Users;

import io.swagger.models.auth.In;
import javaProjects.HRMS.core.business.abstracts.UserService;
import javaProjects.HRMS.core.constants.Messages;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.security.Helpers.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.Users.SystemEmployeeService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.dataAccess.abstracts.Users.SystemEmployeeDao;
import javaProjects.HRMS.entities.concretes.Users.SystemEmployee;
@Slf4j
@Service
public class SystemEmployeeManager extends BaseManager<SystemEmployeeDao, SystemEmployee, Integer> implements SystemEmployeeService {


	private final SystemEmployeeDao systemEmployeeDao;
	private final EncryptionService encryptionService;
	private final UserService userService;

	@Autowired
	public SystemEmployeeManager(SystemEmployeeDao systemEmployeeDao, EncryptionService encryptionService, UserService userService) {
		super(systemEmployeeDao, "System Employee");
		this.systemEmployeeDao = systemEmployeeDao;
		this.encryptionService = encryptionService;
		this.userService = userService;
	}

	@Override
	public DataResult<SystemEmployee> add(SystemEmployee systemEmployee){
		log.info("Saving new candidate:{} to database",systemEmployee.getEmail());
		systemEmployee.setPassword(encryptionService.EncodePassword(systemEmployee.getPassword()));
		systemEmployeeDao.save(systemEmployee);
		userService.addRoleToUser(systemEmployee.getEmail(),"systememployee_role");
		return new SuccessDataResult<SystemEmployee>(systemEmployeeDao.
				findByEmail(systemEmployee.getEmail()), Messages.SystemEmployeeSaved(systemEmployee.getEmail()));
	}
//	@Override
//	public Result confirmJobAdvertisement(int jobAdvertisementId, int systemEmployeeId) {
//        SystemEmployee sysytemEmployee= this.getById(systemEmployeeId).getData();
//        
//		
//		SystemEmployeeConfirm systemEmployeeConfirm = new SystemEmployeeConfirm();
//		systemEmployeeConfirm.setConfirmed(true);		
//		systemEmployeeConfirm.setSystemEmployee(sysytemEmployee);
//		systemEmployeeConfirm.setVerifiedDate(new Date(System.currentTimeMillis()));		
//		
//		JobAdvertisement jobAdvert = this.jobAdvertisementService.getById(jobAdvertisementId).getData();
//		jobAdvert.setSystemEmployeeConfirm(systemEmployeeConfirm);
//		this.jobAdvertisementService.update(jobAdvert);
//		return new SuccessResult("İş İlanı Başarıyla Onaylandı");
//	}
}

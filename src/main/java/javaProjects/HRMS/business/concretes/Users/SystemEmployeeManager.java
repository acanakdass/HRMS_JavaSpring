package javaProjects.HRMS.business.concretes.Users;

import java.sql.Date;

import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.JobAdvertisementService;
import javaProjects.HRMS.business.abstracts.Users.SystemEmployeeService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.Users.SystemEmployeeDao;
import javaProjects.HRMS.entities.abstracts.SystemEmployeeConfirm;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobAdvertisement;
import javaProjects.HRMS.entities.concretes.Users.SystemEmployee;

@Service
public class SystemEmployeeManager extends BaseManager<SystemEmployeeDao, SystemEmployee, Long>
		implements SystemEmployeeService {


	private SystemEmployeeDao systemEmployeeDao;

	public SystemEmployeeManager(SystemEmployeeDao systemEmployeeDao) {
		super(systemEmployeeDao, "System Employee");
		this.systemEmployeeDao = systemEmployeeDao;
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

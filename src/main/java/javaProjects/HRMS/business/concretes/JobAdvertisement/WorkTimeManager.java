package javaProjects.HRMS.business.concretes.JobAdvertisement;

import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.WorkTimeService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.dataAccess.abstracts.JobAdvertisement.WorkTimeDao;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.WorkTime;

@Service
public class WorkTimeManager extends BaseManager<WorkTimeDao, WorkTime, Integer> implements WorkTimeService {

	public WorkTimeManager(WorkTimeDao entityDao) {
		super(entityDao, "Work Time");
	}

}

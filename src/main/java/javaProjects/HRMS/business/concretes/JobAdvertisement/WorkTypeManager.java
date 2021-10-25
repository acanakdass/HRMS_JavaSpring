package javaProjects.HRMS.business.concretes.JobAdvertisement;

import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.WorkTypeService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.dataAccess.abstracts.JobAdvertisement.WorkTypeDao;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.WorkType;

@Service
public class WorkTypeManager extends BaseManager<WorkTypeDao, WorkType, Integer> implements WorkTypeService{

	public WorkTypeManager(WorkTypeDao entityDao) {
		super(entityDao, "Work Type");
	}

}

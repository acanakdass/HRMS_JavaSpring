package javaProjects.HRMS.business.abstracts;

import java.util.List;

import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.JobAdvertisement;
import javaProjects.HRMS.entities.dtos.JobAdvertisementAddDto;

public interface JobAdvertisementService {
	DataResult<List<JobAdvertisement>> getAll();
	Result add(JobAdvertisementAddDto jobAdvertisementAddDto);
	Result setPassive(int jobAdvertisementId);
	Result setActive(int jobAdvertisementId);
	DataResult<List<JobAdvertisement>> getAllActive();
	DataResult<List<JobAdvertisement>> getAllActiveByAscReleaseDate();
	DataResult<List<JobAdvertisement>> getAllActiveByCompanyName(String companyName);

}

package javaProjects.HRMS.business.abstracts.JobAdvertisement;

import java.util.List;

import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobAdvertisement;
import javaProjects.HRMS.entities.dtos.JobAdvertisementAddDto;

public interface JobAdvertisementService extends BaseService<JobAdvertisement, Integer> {
	DataResult<List<JobAdvertisement>> getAll(int pageNo,int pageSize);
	Result add(JobAdvertisementAddDto jobAdvertisementAddDto);
	Result setPassive(int jobAdvertisementId);
	Result setActive(int jobAdvertisementId);
	DataResult<List<JobAdvertisement>> getAllActive();
	DataResult<List<JobAdvertisement>> getAllActiveByAscReleaseDate();
	DataResult<List<JobAdvertisement>> getAllActiveByDescReleaseDate();
	DataResult<List<JobAdvertisement>> getAllActiveByCompanyName(String companyName);
	DataResult<List<JobAdvertisement>> getAllActiveAndConfirmed();
	DataResult<Integer> getAllActiveAndConfirmedCount();
	DataResult<List<JobAdvertisement>> getAllActiveAndConfirmedByPage(int pageNo,int pageSize);
	DataResult<List<JobAdvertisement>> getAllByEmployerId(int employerId);
	Result setConfirmed(int jobAdvertisementId, Integer systemEmployeeId);

}

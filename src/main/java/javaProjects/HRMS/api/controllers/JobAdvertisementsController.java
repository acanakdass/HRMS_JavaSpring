package javaProjects.HRMS.api.controllers;

import java.util.List;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.WorkTimeService;
import javaProjects.HRMS.business.abstracts.JobAdvertisement.WorkTypeService;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.WorkTime;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.WorkType;
import javaProjects.HRMS.entities.dtos.ConfirmAdvertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaProjects.HRMS.business.abstracts.JobAdvertisement.JobAdvertisementService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobAdvertisement;
import javaProjects.HRMS.entities.dtos.JobAdvertisementAddDto;

@RestController
@RequestMapping("api/jobAdvertisements")
@CrossOrigin
public class JobAdvertisementsController {

	private final JobAdvertisementService jobAdvertisementService;
	private final WorkTimeService workTimeService;
	private final WorkTypeService workTypeService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService, WorkTimeService workTimeService, WorkTypeService workTypeService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
		this.workTimeService = workTimeService;
		this.workTypeService = workTypeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll(){
		return this.jobAdvertisementService.getAll();
	}
	
	@GetMapping("/getAllActiveAndConfirmedByPage")
	public DataResult<List<JobAdvertisement>> getAllActiveAndConfirmedByPage(int pageNo, int pageSize){
		return this.jobAdvertisementService.getAllActiveAndConfirmedByPage(pageNo,pageSize);
	}
	
	@GetMapping("/getallactive")
	public DataResult<List<JobAdvertisement>> getAllActive(){
		return this.jobAdvertisementService.getAllActive();
	}
	
	@GetMapping("/getallactiveandconfirmed")
	public DataResult<List<JobAdvertisement>> getAllActiveAndConfirmed(){
		return this.jobAdvertisementService.getAllActiveAndConfirmed();
	}
	
	@GetMapping("/getAllActiveByAscReleaseDate")
	public DataResult<List<JobAdvertisement>> getAllActiveByAscReleaseDate(){
		return this.jobAdvertisementService.getAllActiveByAscReleaseDate();
	}
	
	@GetMapping("/getAllActiveByDescReleaseDate")
	public DataResult<List<JobAdvertisement>> getAllActiveByDescReleaseDate(){
		return this.jobAdvertisementService.getAllActiveByDescReleaseDate();
	}
	
	@GetMapping("/getAllActiveByCompanyName")
	public DataResult<List<JobAdvertisement>> getAllActiveByCompanyName(String companyName){
		return this.jobAdvertisementService.getAllActiveByCompanyName(companyName);
	}
	
	@PostMapping("/add")
    public Result add(@RequestBody JobAdvertisementAddDto jobAdvertisementAddDto){
        return this.jobAdvertisementService.add(jobAdvertisementAddDto);
    }
	
	@PostMapping("/setActive")
    public Result setActive(@RequestParam int jobAdvertisementId){
        return this.jobAdvertisementService.setActive(jobAdvertisementId);
    }
	
	@PostMapping("/setPassive")
	public Result setPassive(@RequestParam int jobAdvertisementId){
		return this.jobAdvertisementService.setPassive(jobAdvertisementId);
	}
	
	@PostMapping("/setConfirmed")
	public Result setConfirmed(@RequestBody ConfirmAdvertDto confirmAdvertDto) {
		return this.jobAdvertisementService.setConfirmed(confirmAdvertDto.getJobAdvertId(), confirmAdvertDto.getSystemEmployeeId());
	}

	//Work Time
	@GetMapping("/getallworktimes")
	public DataResult<List<WorkTime>> getAllWorkTimes(){
		return this.workTimeService.getAll();
	}

	//Work Type
	@GetMapping("/getallworktypes")
	public DataResult<List<WorkType>> getAllWorkTypes(){
		return this.workTypeService.getAll();
	}

}
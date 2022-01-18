package javaProjects.HRMS.business.concretes;

import javaProjects.HRMS.business.abstracts.ApplicationService;
import javaProjects.HRMS.business.abstracts.JobAdvertisement.JobAdvertisementService;
import javaProjects.HRMS.business.abstracts.Users.CandidateService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.core.constants.Messages;
import javaProjects.HRMS.core.utilities.results.*;
import javaProjects.HRMS.dataAccess.abstracts.ApplicationDao;
import javaProjects.HRMS.entities.concretes.Application;
import javaProjects.HRMS.entities.dtos.ApplicationAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationManager extends BaseManager<ApplicationDao, Application, Integer> implements ApplicationService {

    private final ApplicationDao applicationDao;
    private final JobAdvertisementService jobAdvertisementService;
    private final CandidateService candidateService;

    @Autowired
    public ApplicationManager(ApplicationDao applicationDao, JobAdvertisementService jobAdvertisementService, CandidateService candidateService) {
        super(applicationDao,"Application");
        this.applicationDao = applicationDao;
        this.jobAdvertisementService = jobAdvertisementService;
        this.candidateService = candidateService;
    }


    @Override
    public Result add(ApplicationAddDto applicationAddDto) {
        var candidate = candidateService.getById(applicationAddDto.getCandidateId());
        var advert = jobAdvertisementService.getById(applicationAddDto.getJobAdvertId());
        if(checkIfAlreadyApplied(applicationAddDto)){
            return new ErrorResult(Messages.AlreadyApplied());
        }
        if(candidate.isSuccess() && advert.isSuccess()){
            Application application = new Application();
            application.setDate(new Date(System.currentTimeMillis()));
            application.setCandidate(candidate.getData());
            application.setJobAdvert(advert.getData());
            applicationDao.save(application);
            return new SuccessResult(Messages.Applied());
        }
        return new ErrorResult(Messages.notFound("Candidate or Advertisement"));
    }

    @Override
    public DataResult<List<Application>> getByAdvertId(Integer advertId) {
        var res  = applicationDao.findAll().stream().filter(a->a.getJobAdvert().getId()==advertId).collect(Collectors.toList());
        if(res.isEmpty()){
            return new ErrorDataResult<>(Messages.notFound("Application"));
        }
        return new SuccessDataResult<List<Application>>(res,Messages.Listed());
    }

    //Business Rules
    private boolean checkIfAlreadyApplied(ApplicationAddDto applicationAddDto){
        return applicationDao
                .findAll().stream().
                anyMatch(a->a.getCandidate().getId()==applicationAddDto.getCandidateId()
                        && a.getJobAdvert().getId()==applicationAddDto.getJobAdvertId());
    }
}

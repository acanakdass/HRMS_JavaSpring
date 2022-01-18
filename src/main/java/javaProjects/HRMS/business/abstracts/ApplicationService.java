package javaProjects.HRMS.business.abstracts;

import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Application;
import javaProjects.HRMS.entities.concretes.Users.Candidate;
import javaProjects.HRMS.entities.dtos.ApplicationAddDto;

import java.util.List;

public interface ApplicationService extends BaseService<Application,Integer> {
    Result add(ApplicationAddDto applicationAddDto);
    DataResult<List<Application>> getByAdvertId(Integer advertId);
}

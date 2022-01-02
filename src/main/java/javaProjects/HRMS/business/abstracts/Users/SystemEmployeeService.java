package javaProjects.HRMS.business.abstracts.Users;

import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.dataAccess.abstracts.Users.SystemEmployeeDao;
import javaProjects.HRMS.entities.concretes.Users.Candidate;
import javaProjects.HRMS.entities.concretes.Users.SystemEmployee;

public interface SystemEmployeeService  extends BaseService<SystemEmployee, Integer> {
    DataResult<SystemEmployee> add(SystemEmployee systemEmployee);

}

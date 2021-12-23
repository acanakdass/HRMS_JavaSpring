package javaProjects.HRMS.core.business.abstracts;

import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.entities.Role;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.entities.User;
import javaProjects.HRMS.core.utilities.results.Result;

public interface UserService extends BaseService<User,Long> {
	User saveUser(User user);
	DataResult<User> getByEmail(String email);
	Result addRoleToUser(String email, String roleName);
	DataResult<Role> saveRole(Role role);

}

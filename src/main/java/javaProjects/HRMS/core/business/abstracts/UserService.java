package javaProjects.HRMS.core.business.abstracts;

import javaProjects.HRMS.core.entities.Role;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.entities.User;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;

import java.util.List;

public interface UserService extends BaseService<User,Integer> {
	Result saveUser(User user);
	DataResult<User> findById(Integer id);
	DataResult<User> getByEmail(String email);
	DataResult<Role> saveRole(Role role);
	DataResult<List<Role>> getAllRoles();
	DataResult<Role> getRoleByName(String name);
	Result addRoleToUser(String email, String roleName);
	Result deleteUser(Integer id);

}

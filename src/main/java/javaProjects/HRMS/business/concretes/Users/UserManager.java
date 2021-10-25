package javaProjects.HRMS.business.concretes.Users;

import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.UserService;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.dataAccess.abstracts.UserDao;
import javaProjects.HRMS.entities.abstracts.User;

@Service
public class UserManager extends BaseManager<UserDao, User, Integer> implements UserService {

	private UserDao userDao;
	public UserManager(UserDao userDao) {
		super(userDao, "User");
		this.userDao = userDao;
	}

}

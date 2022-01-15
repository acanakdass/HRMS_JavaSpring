package javaProjects.HRMS.core.business.concretes;

import javaProjects.HRMS.core.business.abstracts.UserService;
import javaProjects.HRMS.core.constants.Messages;
import javaProjects.HRMS.core.entities.Role;
import javaProjects.HRMS.core.entities.User;
import javaProjects.HRMS.core.repositories.RoleDao;
import javaProjects.HRMS.core.repositories.UserDao;
import javaProjects.HRMS.core.utilities.results.*;
import javaProjects.HRMS.core.utilities.security.Helpers.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
/*@RequiredArgsConstructor*/
public class UserManager extends BaseManager<UserDao, User, Integer> implements UserService, UserDetailsService {

	private final UserDao userDao;
	private final RoleDao roleDao;
	private final EncryptionService encryptionService;


	@Autowired
	public UserManager(UserDao userDao, RoleDao roleDao, EncryptionService encryptionService) {
		super(userDao, "User");
		this.userDao = userDao;
		this.roleDao = roleDao;

		this.encryptionService = encryptionService;
	}
	@Override
	public DataResult<User> getByEmail(String email) {
		User user = this.userDao.getByEmail(email);
		if(user!=null) {
			return new SuccessDataResult<User>(user,"Kullanıcı Bulundu");
		}else {
			return new ErrorDataResult<>(Messages.notFound("User"));
		}
	}
	@Override
	public Result saveUser(User user) {
		log.info("Saving new user:{} to database",user.getEmail());

		user.setPassword(encryptionService.EncodePassword(user.getPassword()));
		return new SuccessDataResult<User>(userDao.save(user));
	}

	@Override
	public DataResult<User> findById(Integer id) {
		var user = userDao.getById(id);
		return  new SuccessDataResult<User>(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = this.userDao.getByEmail(email);
		if(user==null){
			/*log.error("User not found in database");*/
			throw  new UsernameNotFoundException("User not found in database");
		}else{
			log.info("User found in db {}",email);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities );
	}
	@Override
	public Result addRoleToUser(String email, String roleName) {
		log.info("Adding role : {} to user : {}",roleName,email);
		User user = this.userDao.getByEmail(email);
		log.info(user.getEmail());
		Role role = this.roleDao.findByName(roleName);
		var a =user.getRoles().add(role);
		this.userDao.save(user);
		log.info(String.valueOf(user.getRoles()));

		return new SuccessResult(Messages.RoleAddedToUser(roleName,email));
	}

	@Override
	public Result deleteUser(Integer id) {
		var user = this.findById(id);
		if(!user.isSuccess()){
			return  new ErrorResult(Messages.UserNotFound());
		}
		userDao.delete(user.getData());
		return new SuccessResult(Messages.deleted("User with id "+id));
	}

	@Override
	public DataResult<Role> saveRole(Role role) {
		log.info("Saving new role {} to database",role.getName());
		return new SuccessDataResult<Role>(roleDao.save(role),Messages.RoleCreated(role.getName()));
	}

	@Override
	public DataResult<List<Role>> getAllRoles() {
		return new SuccessDataResult<List<Role>>(roleDao.findAll(),Messages.Listed());
	}

	@Override
	public DataResult<Role> getRoleByName(String name) {
		return new SuccessDataResult<Role>(roleDao.findByName(name),Messages.Listed());
	}

}

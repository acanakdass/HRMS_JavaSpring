package javaProjects.HRMS;

import javaProjects.HRMS.core.business.abstracts.UserService;
import javaProjects.HRMS.core.entities.Role;
import javaProjects.HRMS.core.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
@Slf4j
@SpringBootApplication
public class HrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return  args -> {

			/*userService.saveUser(new User(null,"testuser1@mail.com","testuser",null,new ArrayList<>()));
			userService.addRoleToUser("testuser1@mail.com","superadmin_role");*/
			if(userService.getAll().getData().isEmpty()){
				userService.saveRole(new Role(null,"superadmin_role"));
				userService.saveRole(new Role(null,"employer_role"));
				userService.saveRole(new Role(null,"candidate_role"));
				userService.saveRole(new Role(null,"systememployee_role"));

				userService.saveUser(new User(null,"acanakdas@hrms.com","acanakdas","https://res.cloudinary.com/acanakdas/image/upload/v1635524680/a3cgocxdmkzsniqtnzwp.jpg",new ArrayList<>()));
				userService.saveUser(new User(null,"employer@hrms.com","employer",null,new ArrayList<>()));
				userService.saveUser(new User(null,"candidate@hrms.com","candidate",null,new ArrayList<>()));
				userService.saveUser(new User(null,"systememployee@hrms.com","systememployee",null,new ArrayList<>()));


				userService.addRoleToUser("acanakdas@hrms.com","superadmin_role");
				userService.addRoleToUser("employer@hrms.com","employer_role");
				userService.addRoleToUser("candidate@hrms.com","candidate_role");
				userService.addRoleToUser("systememployee@hrms.com","systememployee_role");

			}
		};
	}
}

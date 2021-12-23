package javaProjects.HRMS.api.controllers.Users;


import javaProjects.HRMS.core.business.abstracts.UserService;
import javaProjects.HRMS.core.entities.Dtos.RoleToUserDto;
import javaProjects.HRMS.core.entities.Role;
import javaProjects.HRMS.core.entities.User;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;


    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getAll().getData());
    }

    @PostMapping("/save")
    public ResponseEntity<Result> saveUser(@RequestBody User user){
        URI uri = URI.create((ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString()));
        return (ResponseEntity<Result>) ResponseEntity.created(uri).body(userService.add(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<DataResult<Role>> saveRole(@RequestBody Role role){
        URI uri = URI.create((ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString()));
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDto roleToUserDto){
        userService.addRoleToUser(roleToUserDto.email,roleToUserDto.roleName);
        return ResponseEntity.ok().build();
    }
}


package javaProjects.HRMS.core.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "photoUrl")
	private String photoUrl;

	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles =new ArrayList<>();

}
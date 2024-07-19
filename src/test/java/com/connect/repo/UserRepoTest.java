package com.connect.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.connect.domain.Role;
import com.connect.domain.User;
import com.connect.enums.Gender;
import com.connect.repository.RoleRepository;
import com.connect.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional(propagation =Propagation.NOT_SUPPORTED)
public class UserRepoTest {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private RoleRepository repository;
	
	private Role adminRole, staffRole, userRole;
	
	@BeforeEach()
	void loadUserRole() {
		adminRole=repository.findByRole("ROLE_ADMIN");
		staffRole=repository.findByRole("ROLE_STAFF");
		userRole=repository.findByRole("ROLE_USER");
		assertNotNull(adminRole);assertNotNull(staffRole);assertNotNull(userRole);
	}
	
	@Test
	void deleteUser_thenFindUserById_returnUser() {
		repo.deleteById(4);
		
		User user= repo.findById(4).get();
		assertNotNull(user,"User account is required.");
		assertEquals(4,user.getId());
	}
	
	void findUserById_whenExistsUpdate_returnUpdatedUser() {
		User user= repo.findById(4).get();
		assertNotNull(user,"User account is required.");
		assertEquals(4,user.getId());
		
		user.setBlocked(true);
		user.setBlocked_at(LocalDateTime.now());
		user.setGender(Gender.M);
		
		User updatedUser = repo.save(user);
		assertNotNull(updatedUser);
	}
	
	void findUserByUsername_whenExists_returnUser() {
		User user = repo.findByUsername("staff");
		assertNotNull(user);
	}
	
	void findUserByEmail_whenExists_returnUser() {
		User user = repo.findByEmail("user@m.com");
		assertNotNull(user);
	}
	
	void findUserById_whenExists_returnUser() {
		User user = repo.findById(3).get();
		
		assertNotNull(user);
		assertEquals(user.getId(), 3);
	}
	
	void saveUser_returnUser() {
		User user=new User("user1", "user1","user1","use1r@m.com","password");
		user.setRole(userRole);
		user.setActive(true);
		User savedU= repo.save(user);
		assertNotNull(savedU);
	}
	
	void saveListOfUser_returnUsers() {
		User admin=new User("admin", "admin","admin","admin@m.com","password");
		admin.setRole(adminRole);
		
		User staff=new User("staff", "staff","staff","staff@m.com","password");
		staff.setRole(staffRole);
		
		User user=new User("user", "user","user","user@m.com","password");
		user.setRole(userRole);
		
		List<User> savedUsers = repo.saveAll(List.of(admin, staff, user));
		assertNotNull(savedUsers);
		assertEquals(3, savedUsers.size());
	}
	
	void init() { System.err.println("==? "+ adminRole);  fail(); }
}

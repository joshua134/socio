package com.connect.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.connect.domain.Role;
import com.connect.repository.RoleRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional(propagation =Propagation.NOT_SUPPORTED)
public class RoleRepoTest {
	
	@Autowired
	private RoleRepository repo;
	
	@Autowired
	private TestEntityManager manager;

	@Test
	void saveRole_thenReturnRole() {
		Role r=new Role("ROLE_USER");
		Role savedRole=repo.save(r);
		assertNotNull(savedRole);
		assertEquals(r.getId(), savedRole.getId());
	}
	
	void deleteRole_thenFindRole() {
		repo.deleteById(4);
		
		Role role=repo.findById(4).get();
		System.err.println(role);
		assertNotNull(role, "Role is already deleted");
	}
	
	void findRoleById_whenExistsUpdate_returnUpdatedRole() {
		Optional<Role> role= repo.findById(4);
		
		assertTrue(role.isPresent());
		assertNotNull(role);
		
		Role foundRole = role.get();
		System.err.println("=< "+foundRole);
		
		foundRole.setRole("ROLE_USR");
		
		Role updatedRole = repo.save(foundRole);
		
		System.err.println("==== "+ updatedRole);
		assertNotNull(updatedRole);
		assertEquals(role.get().getId(), updatedRole.getId());
	}
	
	void findRoleByRole_whenExists_returnRole() {
		Role role=repo.findByRole("ROLE_USER");
		System.err.println(role);
		assertNotNull(role);
		assertEquals(4,role.getId());
	}
	
	void findRoleById_whenExists_returnRole() {
		Optional<Role> role = repo.findById(2);
		
		assertNotNull(role.get());
		assertTrue(role.isPresent());
		assertFalse(role.isEmpty());
		assertEquals(2, role.get().getId());
	}
	
	void findAllRoles() {
		List<Role> roles=repo.findAll();
		assertNotNull(roles);
	}

	
	void saveListOfRoles_whenSaved_returnRoles() {
		Role a = new Role("ROLE_ADMIN");
		Role s = new Role("ROLE_STAFF");
		Role u = new Role("ROLE_USER");
		
		List<Role> savedRoles= repo.saveAll(List.of(a,s,u));
		
		assertNotNull(savedRoles);
		assertEquals(3,savedRoles.size());
		
	}
	
	void init() {  }
	
}

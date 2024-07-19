package com.connect.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.connect.domain.Post;
import com.connect.domain.User;
import com.connect.repository.PostRepository;
import com.connect.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional(propagation =Propagation.NOT_SUPPORTED)
public class PostRepositoryTest {

	@Autowired
	private PostRepository repo;
	
//	@Autowired
//	private UserRepository repository;
	
//	private User user1;
//	
//	@BeforeEach()
//	void loadUser() {
//		user1 = repository.findByUsername("user");
//		System.out.println(user1);
//		assertNotNull(user1);
//	}
	
	
	
	void deletePost() {
		repo.deleteById(7);
		
		Post p = repo.findById(7).get();
		System.err.println(p);
		assertNotNull(p);
	}
	
	void findPostById_wenExistsUpdate_returnUpdatedPosts() {
		Post p = repo.findById(5).get();
		assertNotNull(p);
		
		p.setContent_updated(p.getContent().concat(" \n\n\n Update: \n Tis is an updated content."));
		
		Post updatedP = repo.save(p);
		System.err.println(updatedP);
		assertNotNull(updatedP);
	}
		
	@Test
	void countNonArchieved() {
		long c=repo.countByIsArchieved(false);
		System.err.println(c);
	}
	
	void countArchieved() {
		long c=repo.countByIsArchieved(true);
		System.err.println(c);
	}
	
	void count() {
		long c = repo.count();
		System.err.println(c);
	}
	
	void findPostById_wenExists_returnPost() {
		Optional<Post> po = repo.findById(5);
		System.err.println("skjcn");
		System.err.println("===========< "+po.toString());
		assertTrue(po.isPresent());
		assertFalse(po.isEmpty());
		//assertNotNull(po);
	}
	
//	void saveListOfPosts() {
//		Post p1 = new Post("Post one 1", user1);
//		Post p2 = new Post("Post one 2", user1);
//		Post p3 = new Post("Post one 3", user1);
//		Post p4 = new Post("Post one 4", user1);
//		Post p5 = new Post("Post one 5", user1);
//		Post p6 = new Post("Post one 6", user1);
//		Post p7 = new Post("Post one 7", user1);
//		Post p8 = new Post("Post one 8", user1);
//		Post p9 = new Post("Post one 9", user1);
//		Post p11 = new Post("Post one 10", user1);
//		Post p12 = new Post("Post one 11", user1);
//		Post p13 = new Post("Post one 12", user1);
//		
//		List<Post> p= repo.saveAll(List.of(p1,p2,p3,p4,p5,p6,p7,p8,p9,p11,p12,p13));
//		
//		assertNotNull(p);
//		assertEquals(12, p.size());
//	}
//	
	void init() {  }
	
	
}

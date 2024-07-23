package com.connect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connect.domain.User;
import com.connect.enums.Gender;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	User findByUsername(String uname);
	
	Page<User> findAll(Pageable p);
	Page<User> findByGender(Gender gender, Pageable p);

	Page<User> findAllByGender(Gender g, Pageable p);
	Page<User> findAllByRole(Role r, Pageable p);

	Page<Post> findAllPostsByUser(User u, Pageable p);
	// or
	/**
 	*	@Query("SELECT p FROM Post p WHERE p.creator.id = userId")
  	*	Page<Post> findAllPostByUser(@Param("userId") int userId);
   	*/
	
	long countPostByUserId(User u);
	// or 
	/**
	*	@Query("SELECT COUNT(p) FROM Post p WHERE p.creator.id = userId")
	*	long countAllPostsByUser(@Param("userId") int userId);
	*/
}

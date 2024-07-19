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
}

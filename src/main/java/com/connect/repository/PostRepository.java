package com.connect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.connect.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
		
	Page<Post> findAll(Pageable p);
	
	@Query("SELECT p FROM Post p WHERE content LIKE %:searchTerm% ")
	Page<Post> findPostBySeachTerm(@Param("searchTerm")String searchTerm, Pageable p);
	
	long countByIsArchieved(boolean archieved);

	long countByLikesPostId(int postId);
	// or
	/**
	*	@Query("SELECT COUNT(l) FROM Like l where l.post.id = :postId ")
	*	long countLikesForPost(@Param("postId") int postId)
 	*/

	long countByUnlikePostId(int postId);
	// or
	/**
	*	@Query("SELECT COUNT(l) FROM Unlike ul where ul.post.id = :postId ")
	*	long countUnLikesForPost(@Param("postId") int postId)
 	*/
}

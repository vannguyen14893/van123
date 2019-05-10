package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Post;
import com.example.demo.utils.Contans;

public interface PostRepository extends JpaRepository<Post, Integer> {
	@Query(value = Contans.FIND_BY_USER_POSTED_BY)
	List<Post> findByUser_Posted_By(Integer userId);

	List<Post> findByProduct_Id(Integer productId);
}

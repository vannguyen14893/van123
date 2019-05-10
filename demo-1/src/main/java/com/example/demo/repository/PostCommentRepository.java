package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {
	
	public List<PostComment> findByPost_PostId(Integer postId);
	
}

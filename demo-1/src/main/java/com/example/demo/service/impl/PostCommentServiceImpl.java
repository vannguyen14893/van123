package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PostComment;
import com.example.demo.repository.PostCommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class PostCommentServiceImpl {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostCommentRepository postCommentRepository;

	public List<PostComment> getAll(Integer postId) {
		return postCommentRepository.findByPost_PostId(postId);
	}

	public void addPostComment(Integer postId, PostComment postComment) {

		PostComment postComment2 = new PostComment();
		postComment2.setMessage(postComment.getMessage());
		postComment2.setCommentDate(new Date());
		postComment2.setCommentBy(userRepository.findById(postComment.getCommentBy().getUserId()).get());
		postComment2.setPost(postRepository.findById(postComment.getPost().getPostId()).get());
		postCommentRepository.save(postComment2);
	}

	public void editPostComment(PostComment postComment, Integer userId, Integer postId) {
		PostComment postComment2 = postCommentRepository.findById(postComment.getCommentId()).get();
		if (postComment2 != null) {
			postComment2.setCommentId(postComment.getCommentId());
			postComment2.setMessage(postComment.getMessage());
			postComment2.setCommentDate(new Date());
			postComment2.setCommentBy(userRepository.findById(postComment.getCommentBy().getUserId()).get());
			postComment2.setPost(postRepository.findById(postComment.getCommentId()).get());
			postCommentRepository.save(postComment2);
		}
	}
}

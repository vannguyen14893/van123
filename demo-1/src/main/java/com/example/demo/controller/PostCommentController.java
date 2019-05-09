package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PostComment;
import com.example.demo.repository.PostCommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.impl.PostCommentServiceImpl;
import com.example.demo.utils.ResourceNotFoundException;

@RestController
public class PostCommentController {
	@Autowired
	private PostCommentServiceImpl postCommentServiceImpl;
	@Autowired
	PostRepository postRepository;
	@Autowired
	private PostCommentRepository postCommentRepository;

	@PostMapping(value = "/postComment/post/{postId}")
	public PostComment addPostComment(@PathVariable("postId") Integer postId, @RequestBody PostComment postComment) {
		return postRepository.findById(postId).map(post -> {
			postComment.setCommentDate(new Date());
			postComment.setPost(post);
			return postCommentRepository.save(postComment);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}

	@GetMapping(value = "/postComment/{postId}")
	public List<PostComment> getAll(@PathVariable("postId") Integer postId) {
		return postCommentServiceImpl.getAll(postId);
	}

	@PostMapping(value = "/postComment/{postId}")
	public void add(@PathVariable("postId") Integer postId, @RequestBody PostComment postComment) {
		postCommentServiceImpl.addPostComment(postId, postComment);
	}

	@PutMapping(value = "/postComment/user/{userId}/post/{postId}")
	public void editPostComment(@PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId,
			@RequestBody PostComment postComment) {
		postCommentServiceImpl.editPostComment(postComment, userId, postId);
	}
}

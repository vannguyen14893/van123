package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Post;
import com.example.demo.service.impl.PostServiceImpl;

@RestController
public class PostController {
	@Autowired
	private PostServiceImpl postServiceImpl;

	@GetMapping(value = "/posts/user/{userId}")
	public List<Post> getAll(@PathVariable("userId") Integer userId) {
		return postServiceImpl.getAllByUserId(userId);
	}
	@GetMapping(value = "/posts/product/{productId}")
	public List<Post> getAllByProductId(@PathVariable("productId") Integer productId) {
		return postServiceImpl.getAllByProductId(productId);
	}
	@PostMapping(value = "/posts/{userId}")
	public void addPost(@PathVariable("userId") Integer userId, @RequestBody Post post) {
		postServiceImpl.addPost(userId, post);
	}

	@PutMapping(value = "/post/{userId}")
	public void editPost(@PathVariable("userId") Integer userId, @RequestBody Post post) {
		postServiceImpl.editPost(post, userId);
	}

	@PostMapping(value = "/post/delete/{userId}")
	public void deletePost(@PathVariable("userId") Integer userId, @RequestBody Post post) {
		postServiceImpl.deletePost(userId, post.getPostId());
	}
}

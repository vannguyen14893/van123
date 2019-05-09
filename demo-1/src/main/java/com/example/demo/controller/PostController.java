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
import com.example.demo.repository.PostRepository;
import com.example.demo.service.impl.PostServiceImpl;

@RestController
public class PostController {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostServiceImpl postServiceImpl;

	@GetMapping(value = "/posts")
	public List<Post> getAll() {
		return postRepository.findAll();
	}

	@PostMapping(value = "/post")
	public void addPost(@RequestBody Post post) {
		postServiceImpl.addPost(post);
	}
	@PutMapping(value = "/post/{userId}")
	public void editPost(@PathVariable("userId") Integer userId, @RequestBody Post post) {
		postServiceImpl.editPost(post, userId);
	}
}

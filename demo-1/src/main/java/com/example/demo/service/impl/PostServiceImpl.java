package com.example.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class PostServiceImpl {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;

	public void addPost(Post post) {
		Post post2 = new Post();
		post2.setMessage(post.getMessage());
		post2.setPostedDate(new Date());
		post2.setPostedBy(userRepository.findById(post.getPostedBy().getUserId()).get());
		postRepository.save(post2);
	}
	public void editPost(Post post,Integer userId) {
		Post post2=postRepository.findById(post.getPostId()).get();
		if(post2!=null) {	
				post2.setPostId(post.getPostId());
				post2.setMessage(post.getMessage());
				post2.setPostedDate(new Date());
				post2.setPostedBy(userRepository.getOne(post.getPostedBy().getUserId()));
				postRepository.save(post2);
			}
		}
	}



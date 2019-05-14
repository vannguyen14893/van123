package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class PostServiceImpl {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;

	public List<Post> getAllByProductId(Integer productId) {
		return postRepository.findByProduct_Id(productId);
	}

	public List<Post> getAllByUserId(Integer userId) {
		return postRepository.findByUser_Posted_By(userId);
	}

	public void addPost(Integer userId, Post post) {
		Post post2 = new Post();
		post2.setMessage(post.getMessage());
		post2.setPostedDate(new Date());
		post2.setPostedBy(userRepository.findById(post.getPostedBy().getUserId()).get());
		post2.setProduct(productRepository.findById(post.getProduct().getId()).get());
		postRepository.save(post2);
	}

	public void editPost(Post post, Integer userId) {
		Post post2 = postRepository.findById(post.getPostId()).get();
		if (post2 != null) {
			post2.setPostId(post.getPostId());
			post2.setMessage(post.getMessage());
			post2.setPostedDate(new Date());
			post2.setPostedBy(userRepository.getOne(post.getPostedBy().getUserId()));
			post2.setProduct(productRepository.findById(post.getProduct().getId()).get());
			postRepository.save(post2);
		}
	}

	public Boolean check(Integer userId,Integer postId) {
		List<Post> posts = postRepository.findByUser_Posted_By(userId);
		for (Post post : posts) {
			if(!(post.getPostId()==postId)) {
				continue;
			}
			//postRepository.deleteById(postId);
			return true;
		}
		return false;

	}
}

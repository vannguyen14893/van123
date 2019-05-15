package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.Post;

public class PostDto {
	private Integer postId;
	private String message;
	private Date postedDate;
	private List<PostCommentDto> postComments = new ArrayList<PostCommentDto>();

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public List<PostCommentDto> getPostComments() {
		return postComments;
	}

	public void setPostComments(List<PostCommentDto> postComments) {
		this.postComments = postComments;
	}

	public PostDto(Post post) {
		this.postId = post.getPostId();
		this.message = post.getMessage();
		this.postedDate = post.getPostedDate();
		this.postComments = post.getPostComments().stream().map(PostCommentDto::new).collect(Collectors.toList());
	}

	public PostDto() {
		super();
	}

}

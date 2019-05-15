package com.example.demo.model;

import java.util.Date;

import com.example.demo.entity.PostComment;

public class PostCommentDto {
	private Integer commentId;
	private String message;
	private Date commentDate;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public PostCommentDto(PostComment comment) {
		this.commentId = comment.getCommentId();
		this.message = comment.getMessage();
		this.commentDate = comment.getCommentDate();
		
	}

	public PostCommentDto() {
		super();
	}

}

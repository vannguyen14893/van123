package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String content;
	private Integer isTrue;
	@Column(name = "list_answer_check")
	private String listAnswerCheck;
	@ManyToOne
	@JoinColumn(name = "question_id")
	@JsonIgnoreProperties("answers")
	private Question question;
	private Integer point;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Integer isTrue) {
		this.isTrue = isTrue;
	}

	public String getListAnswerCheck() {
		return listAnswerCheck;
	}

	public void setListAnswerCheck(String listAnswerCheck) {
		this.listAnswerCheck = listAnswerCheck;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Answer(Integer id, String content, Integer isTrue, String listAnswerCheck, Question question,
			Integer point) {
		super();
		this.id = id;
		this.content = content;
		this.isTrue = isTrue;
		this.listAnswerCheck = listAnswerCheck;
		this.question = question;
		this.point = point;
	}

	public Answer() {
		super();
	}

}

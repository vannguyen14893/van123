package com.example.demo.model;

import java.util.List;

public class ExamDto {
	private Integer id;
	private String name;
	private Integer total;
	private String time;
	private String type;
	private List<String> namQuestion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<String> getNamQuestion() {
		return namQuestion;
	}

	public void setNamQuestion(List<String> namQuestion) {
		this.namQuestion = namQuestion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ExamDto(Integer id, String name, Integer total, String time, List<String> namQuestion) {
		super();
		this.id = id;
		this.name = name;
		this.total = total;
		this.time = time;
		this.namQuestion = namQuestion;
	}

	public ExamDto() {
		super();
	}

}

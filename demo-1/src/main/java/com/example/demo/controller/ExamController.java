package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ExamDto;
import com.example.demo.service.impl.ExamServiceImpl;

@RestController
public class ExamController {
	@Autowired
	private ExamServiceImpl impl;

	@GetMapping(value = "/list-exam")
	public List<ExamDto> fillAll() {
		return impl.fillAll();
	}
	@GetMapping(value = "/get-exam/{examId}")
	public ExamDto get(@PathVariable("examId") Integer examId) {
		return impl.getExam(examId);
	}
}

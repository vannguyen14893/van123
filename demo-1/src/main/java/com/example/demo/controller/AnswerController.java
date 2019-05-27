package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.service.impl.AnswerServiceImpl;

@RestController
public class AnswerController {
	@Autowired
	private AnswerServiceImpl impl;
    @GetMapping(value="/list-answer/{questionId}")
	public List<Answer> fillAllByQuestionId(@PathVariable("questionId") Integer questionId) {
		return impl.fillAllByQuestionId(questionId);
	}
//    @GetMapping(value="/list-question/random/{numberQuestionRadom}")
//    public List<Question> fillAllRandom(@PathVariable("numberQuestionRadom") Integer numberQuestionRadom){
//    	return impl.randomQuestionAndAnswer(numberQuestionRadom);
//    }
}

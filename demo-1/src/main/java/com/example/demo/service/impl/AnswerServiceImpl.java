package com.example.demo.service.impl;

import java.lang.annotation.ElementType;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;

@Service
@Transactional
public class AnswerServiceImpl {
	@Autowired
	private AnswerRepository repository;
	@Autowired
	private QuestionRepository questionRepository;

	public List<Answer> fillAllByQuestionId(Integer questionId) {
		return repository.findByQuestion_Id(questionId);
	}

	public void list(List<Question> questions) {
		questions.forEach(item->System.out.println(item.getId()));
	}

	public void randomQuestionAndAnswer(Integer numberRandomQuestion, List<Question> questions) {
		questions = questionRepository.findAll();
		List<Question> randomSeries = questions.subList(0, numberRandomQuestion);
		Collections.shuffle(questions);
		for (Question question : randomSeries) {
			List<Answer> answers = question.getAnswers();
			Collections.shuffle(answers);
		}
	}

}

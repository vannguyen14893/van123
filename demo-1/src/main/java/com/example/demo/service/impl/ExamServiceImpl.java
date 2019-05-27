package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Answer;
import com.example.demo.entity.Exam;
import com.example.demo.entity.Question;
import com.example.demo.model.ExamDto;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.QuestionRepository;

@Service
@Transactional
public class ExamServiceImpl {
	@Autowired
	private ExamRepository repository;
	private QuestionRepository questionRepository;

	public List<ExamDto> fillAll() {

		List<ExamDto> examDtos = new ArrayList<ExamDto>();
		List<Exam> exams = repository.findAll();
		for (Exam exam : exams) {
			ExamDto examDto = new ExamDto();
			examDto.setId(exam.getId());
			examDto.setName(exam.getName());
			examDto.setTotal(exam.getTotal());
			examDto.setTime(exam.getTime());
			examDto.setType(exam.getType());
			examDtos.add(examDto);
		}
		return examDtos;
	}

	public ExamDto getExam(Integer examId) {
		Exam exam = repository.getOne(examId);
		ExamDto examDto = new ExamDto();
		examDto.setId(exam.getId());
		examDto.setName(exam.getName());
		examDto.setTotal(exam.getTotal());
		examDto.setTime(exam.getTime());
		examDto.setType(exam.getType());
		List<Question> questions = exam.getQuestions();
		List<String> name = new ArrayList<String>();
		questions.forEach(item -> name.addAll(Arrays.asList(item.getName())));
		examDto.setNamQuestion(name);
		return examDto;
	}

	public void editExam(Exam exam) {
		Exam exam2 = repository.getOne(exam.getId());
		if (exam2 != null) {
			exam2.setId(exam.getId());
			exam2.setName(exam.getName());
			exam2.setTime(exam.getTime());
			exam2.setTotal(exam.getTotal());
			exam2.setType(exam.getType());
		}
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

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	List<Answer> findByQuestion_Id(Integer questionId);
}

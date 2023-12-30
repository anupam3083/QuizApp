package com.anupam.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anupam.quizapp.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer>{

}

package com.anupam.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anupam.quizapp.dao.QuestionDao;
import com.anupam.quizapp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestion(){
		try {
		return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
	}

	public Optional<Question> getQuestionById(int id) {
		// TODO Auto-generated method stub
		return questionDao.findById(id);
	}

	public List<Question> getQuestionByCategory(String category) {
		// TODO Auto-generated method stub
		return questionDao.findQuestionByCategory(category);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDao.save(question);
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
		
	}

	public String deleteQuestionById(int id) {
		// TODO Auto-generated method stub
		questionDao.deleteById(id);
		
		return "Question Deleted";
	}

}

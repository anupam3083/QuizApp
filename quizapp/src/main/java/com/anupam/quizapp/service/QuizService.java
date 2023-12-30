package com.anupam.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anupam.quizapp.dao.QuestionDao;
import com.anupam.quizapp.dao.QuizDao;
import com.anupam.quizapp.model.Question;
import com.anupam.quizapp.model.QuestionWrapper;
import com.anupam.quizapp.model.Quiz;
import com.anupam.quizapp.model.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;

	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		// TODO Auto-generated method stub
		List<Question> questions= questionDao.findRandomQuestionByCategory(category,numQ);
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Succes",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizService(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Question> questionFromDB=quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser=new ArrayList<>();
		
		for(Question q: questionFromDB) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionForUser.add(qw);
		}
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
		
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		// TODO Auto-generated method stub
		Quiz quiz=quizDao.findById(id).get();
		
		List<Question> questions=quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response res:responses) {
			if(res.getResponse().equals(questions.get(i).getCorrectAns())){
				right+=1;
			}
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
}

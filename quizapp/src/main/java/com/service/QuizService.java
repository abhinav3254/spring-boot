package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dao.QuestionDao;
import com.dao.QuizDao;
import com.pojo.Question;
import com.pojo.QuestionWrapper;
import com.pojo.Quiz;
import com.pojo.Response;


@Service
public class QuizService {
	
	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		try {
			
			List<Question> questions = questionDao.findRandomQuestionByCategory(category,numQ);
			
			Quiz quiz = new Quiz();
			
			quiz.setTitle(title);
			quiz.setQuestions(questions);
			
			quizDao.save(quiz);
			
			return new ResponseEntity<String>("created",HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	public List<QuestionWrapper> getQuizQuestion(Integer id) {
		try {
			Optional<Quiz> list = quizDao.findById(id);
			
			if (list.isEmpty()) {
				return null;
			} else {
				
				List<Question> questions = list.get().getQuestions();
				List<QuestionWrapper> questionWrapperList  = new ArrayList<>();
				
				for (Question q:questions) {
					QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
					questionWrapperList.add(questionWrapper);
				}
				
				return questionWrapperList;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responseList) {
		try {
			Optional<Quiz> quizOptional = quizDao.findById(id);
			
			if (quizOptional.isPresent()) {
				Quiz quiz = quizOptional.get();
				
				List<Question> questions = quiz.getQuestions();
				int right = 0;
				int i = 0;
				for (Response response:responseList) {
					if (response.getResponse().equals(questions.get(i).getRightAnswer()))
						right++;
						i++;
				}
				return ResponseEntity.status(HttpStatus.OK).body(right);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

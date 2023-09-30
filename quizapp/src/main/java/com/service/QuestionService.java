package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dao.QuestionDao;
import com.pojo.Question;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<List<Question>>(questionDao.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Question>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public String insertAll(List<Question> questions) {
		try {
			for (int i = 0;i<questions.size();i++) {
				questionDao.save(questions.get(i));
			}
			return "inserted";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "failed to insert!!";
	}

	public List<Question> getQuestionsByCategory(String category) {
		try {
			return questionDao.findByCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
			questionDao.save(question);
			return new ResponseEntity<>("inserted",HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("not inserted",HttpStatus.BAD_REQUEST);
	}

	public Question updateQuestion(Question question) {
		try {
			return questionDao.save(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResponseEntity<String> deleteQuestionById(Integer id) {
		try {
			questionDao.deleteById(id);
			return new ResponseEntity<String>("deleted",HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("not deleted",HttpStatus.BAD_REQUEST);
	}

}

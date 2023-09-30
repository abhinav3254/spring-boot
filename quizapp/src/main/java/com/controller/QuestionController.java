package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pojo.Question;
import com.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	
	@PostMapping("/insert/all")
	public String insertAll(@RequestBody(required = true)List<Question> questions) {
		return questionService.insertAll(questions);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> addQuestion(@RequestBody(required = true)Question question) {
		return questionService.addQuestion(question);
	}

	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{category}")
	public List<Question> getQuestionsByCategory(@PathVariable("category") String category) {
		return questionService.getQuestionsByCategory(category);
	}
	
	@PatchMapping("/update")
	public Question updateQuestion(@RequestBody(required = true) Question question) {
		return questionService.updateQuestion(question);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id) {
		return questionService.deleteQuestionById(id);
	}

}

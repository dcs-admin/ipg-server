package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Expense;
import com.rama.ipg.repository.ExpenseRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class ExpenseController {
	
	private Logger logger = LoggerFactory.getLogger(ExpenseController.class);
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@PostMapping("/expenses") 
	public Expense saveExpense(@RequestBody Expense  expense) {
		
		expense = expenseRepository.save(expense);
		System.out.println("obj::"+expense); 
		
		return expense;
	}
	
	

}

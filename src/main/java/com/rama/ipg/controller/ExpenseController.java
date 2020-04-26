package com.rama.ipg.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.constants.IPGConstants;
import com.rama.ipg.model.Expense;
import com.rama.ipg.repository.ExpenseRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class ExpenseController { 

	private Logger logger = LoggerFactory.getLogger(ExpenseController.class);
	
	//@Autowired
	//private ExpenseService expenseService;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@PostMapping("/expenses") 
	public Expense saveExpense(@RequestBody Expense  expense) {
		logger.info("In::"); 
		
		expense = expenseRepository.save(expense);
		
		//expenseService.sendAlerts(expense);
		
		logger.info("Out::"+expense); 
		
		return expense;
	}
	
	
	@DeleteMapping("/expenses/{eid}")
	public ResponseEntity<?> deleteExpense(@PathVariable Long eid) {
		logger.info("IN::deleteTenant::" + eid);

		expenseRepository.findById(eid).ifPresent(expense ->{ 
			
			expenseRepository.deleteById(eid); 
			
		}); 
		
		return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/expenses/{role}") 
	public List<Expense> getExpenses(
			@PathVariable("role") String role,
			@RequestParam("ownerId") Long ownerId ,
			@RequestParam("supervisorId") Long supervisorId,
			@RequestParam("year") int year ,
			@RequestParam("month") int month
			) {
		
		logger.info("In::/expenses/{role}"+role+";o"+ownerId+";s"+supervisorId+";y"+year+";m"+month); 
		
		List<Expense> expenses = new ArrayList<Expense>();
		if(role.equals(IPGConstants.OWNER_CODE)){
			
			expenses = expenseRepository.getExpensesToShowForOwner(ownerId, month, year);
			
		}else if(role.equals(IPGConstants.SUPERVISOR_CODE)){
			
			expenses = expenseRepository.getExpensesToShowForSupervisor(ownerId, supervisorId, month, year);
			
		} 
		
		logger.info("Out::"+expenses);
		return expenses;
	} 
	
	

}

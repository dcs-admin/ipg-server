package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	  
 }

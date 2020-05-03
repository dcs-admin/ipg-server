package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.PaymentGateway;

@Repository
public interface PaymentGatewayRepository extends JpaRepository<PaymentGateway, Long> {
 
 }

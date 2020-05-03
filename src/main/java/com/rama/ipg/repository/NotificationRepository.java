package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
 
 }

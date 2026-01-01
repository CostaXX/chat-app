package com.cwa.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwa.chatapp.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findTop50ByOrderByTimestampDesc();
    List<Message> findAllByOrderByTimestampDesc();
    
}

package com.example.demospringboot.cronjobscheduler.interfaces;

import com.example.demospringboot.cronjobscheduler.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "chatRepository")
public interface ChatRepository extends JpaRepository<Chat, Integer> {
}

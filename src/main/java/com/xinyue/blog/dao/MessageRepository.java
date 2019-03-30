package com.xinyue.blog.dao;

import com.xinyue.blog.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findById(int id);

    List<Message> findAllByOrderByIdDesc();
}

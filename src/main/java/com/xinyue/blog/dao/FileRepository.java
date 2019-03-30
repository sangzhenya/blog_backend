package com.xinyue.blog.dao;

import com.xinyue.blog.model.CustomerFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<CustomerFile, Integer> {
    CustomerFile findById(int id);
}

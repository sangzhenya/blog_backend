package com.xinyue.blog.dao;

import com.xinyue.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findByNameLike(String name);

    List<Tag> findByName(String name);
}

package com.xinyue.blog.dao;

import com.xinyue.blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(Integer id);

    @Override
    List<Category> findAll();

    List<Category> findByNameLike(String name);

    List<Category> findByName(String name);

}

package com.xinyue.blog.dao.mybaits;

import com.xinyue.blog.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sangz
 * @date 2019/7/7 11:24
 */
@Repository
public interface CategoryMapper {
    List<Category> findAll();

    Category findById(int id);
}

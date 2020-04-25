package com.xinyue.blog.dao.mybaits;

import com.xinyue.blog.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {
    List<Tag> findByArticleId(int id);
}

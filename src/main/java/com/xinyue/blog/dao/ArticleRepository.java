package com.xinyue.blog.dao;

import com.xinyue.blog.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author sangz
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Article findByIdAndDeleteFlagFalse(Integer id);

    Article findArticleById(Integer id);

    List<Article> findByTitleLikeAndDeleteFlagFalse(String title);

    Page<Article> findAllByDeleteFlagFalse(Pageable pageable);

    Page<Article> findArticleByIdBefore(Integer id, Pageable pageable);

    Page<Article> findArticleByIdAfter(Integer id, Pageable pageable);
}

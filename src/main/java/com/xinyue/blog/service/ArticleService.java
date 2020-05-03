package com.xinyue.blog.service;

import com.xinyue.blog.model.Article;
import com.xinyue.blog.vo.ArticlePageVO;
import com.xinyue.blog.vo.ArticleVO;
import org.springframework.cache.annotation.Cacheable;

public interface ArticleService {
    String saveArticle(ArticleVO articleVO);

    void saveArticle(ArticleVO articleVO, Article article);

    void convertBasicArticleInfo(ArticleVO articleVO, Article article);

    String deleteArticle(int id);

    ArticlePageVO getArticleListByPage(Integer page);

    ArticleVO getArticleById(Integer id);
}

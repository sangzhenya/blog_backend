package com.xinyue.blog.dao.mybaits;

import com.xinyue.blog.model.Article;
import com.xinyue.blog.vo.PageVO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sangz
 * @date 2019/7/7 11:24
 */
@Repository
public interface ArticleMapper {
    List<Article> findArticleByPageExcludeDeleted(PageVO page);

    Integer getTotalCountExcludeDeleted();

    List<Article> findArticleByCategoryId(Integer id);

    Article findNextArticle(Integer id);

    Article findPreArticle(Integer id);

    List<Article> findArticleByPage(PageVO pageVO);

    Integer getTotalCount();

    Article findById(Integer id);

    List<Article> findByTitle(String name);

    void enableArticle(Integer id);

    void disableArticle(Integer id);
}

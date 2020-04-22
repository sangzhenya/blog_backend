package com.xinyue.blog.dao.mybaits;

import com.xinyue.blog.model.Article;
import com.xinyue.blog.vo.PageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sangz
 * @date 2019/7/7 11:24
 */
@Repository
public interface ArticleMapper {
    List<Article> findArticleByPage(PageVO page);

    Integer getTotalCount();

    List<Article> findArticleByCategoryId(Integer id);

    Article findNextArticle(Integer id);

    Article findPreArticle(Integer id);

}

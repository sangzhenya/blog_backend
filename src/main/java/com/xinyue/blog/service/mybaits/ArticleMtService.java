package com.xinyue.blog.service.mybaits;

import com.xinyue.blog.vo.ArticlePageVO;
import com.xinyue.blog.vo.ArticleVO;
import com.xinyue.blog.vo.PageVO;

public interface ArticleMtService {
    ArticlePageVO getArticleListByPageExcludeDeleted(PageVO pageVO);

    ArticlePageVO getArticleListByPage(PageVO pageVO);

    ArticleVO searchArticle(String keyword);

    ArticleVO enableArticle(Integer id);

    ArticleVO disableArticle(Integer id);
}

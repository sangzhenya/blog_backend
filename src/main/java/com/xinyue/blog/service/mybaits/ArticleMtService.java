package com.xinyue.blog.service.mybaits;

import com.xinyue.blog.vo.ArticlePageVO;
import com.xinyue.blog.vo.ArticleVO;
import com.xinyue.blog.vo.PageVO;

import java.util.List;

public interface ArticleMtService {
    ArticlePageVO getArticleListByPageExcludeDeleted(PageVO pageVO);

    ArticlePageVO getArticleListByPage(PageVO pageVO);

    List<ArticleVO> searchArticle(String keyword);

    ArticleVO enableArticle(Integer id);

    ArticleVO disableArticle(Integer id);
}

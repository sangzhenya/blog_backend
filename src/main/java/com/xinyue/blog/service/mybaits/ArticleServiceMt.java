package com.xinyue.blog.service.mybaits;

import com.xinyue.blog.convert.ArticleConvert;
import com.xinyue.blog.dao.mybaits.ArticleMapper;
import com.xinyue.blog.model.Article;
import com.xinyue.blog.service.CategoryService;
import com.xinyue.blog.utils.PageUtils;
import com.xinyue.blog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sangz
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class ArticleServiceMt {
    private final ArticleMapper articleMapper;
    private final CategoryServiceMt categoryServiceMt;


    @Autowired
    public ArticleServiceMt(ArticleMapper articleMapper, CategoryServiceMt categoryServiceMt) {
        this.articleMapper = articleMapper;
        this.categoryServiceMt = categoryServiceMt;
    }


    @Cacheable(cacheNames = "article", key = "'article_page' + #page")
    public ArticlePageVO getArticleListByPage(Integer page) {
        PageVO pageVO = new PageVO(page);
        List<Article> articleList = articleMapper.findArticleByPage(pageVO);
        ArticlePageVO articlePageVO = new ArticlePageVO();
        List<ArticleVO> articleVOList = new ArrayList<>();
        for (Article article : articleList) {
            article.setCategory(categoryServiceMt.getCategoryById(article.getCategory().getId()));
            articleVOList.add(ArticleConvert.convertArticle2VO(article));
        }
        articlePageVO.setArticles(articleVOList);
        articlePageVO.setPage(new PageVO(page, PageUtils.calculateTotalPage(articleMapper.getTotalCount())));
        return articlePageVO;
    }
}

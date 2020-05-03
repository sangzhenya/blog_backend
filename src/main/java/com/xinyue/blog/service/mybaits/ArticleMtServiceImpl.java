package com.xinyue.blog.service.mybaits;

import com.xinyue.blog.convert.ArticleConvert;
import com.xinyue.blog.dao.mybaits.ArticleMapper;
import com.xinyue.blog.model.Article;
import com.xinyue.blog.utils.NumberUtils;
import com.xinyue.blog.utils.PageUtils;
import com.xinyue.blog.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author sangz
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class ArticleMtServiceImpl implements ArticleMtService {
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleMtServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }


    @Override
    @Cacheable(cacheNames = "article", key = "'article_page' + #pageVO.page")
    public ArticlePageVO getArticleListByPageExcludeDeleted(PageVO pageVO) {
        List<Article> articleList = articleMapper.findArticleByPageExcludeDeleted(pageVO);
        ArticlePageVO articlePageVO = new ArticlePageVO();
        articlePageVO.setArticles(articleList.stream().map(ArticleConvert::convertArticle2VO).collect(Collectors.toList()));
        articlePageVO.setPage(new PageVO(pageVO.getPage(), PageUtils.calculateTotalPage(articleMapper.getTotalCountExcludeDeleted())));
        return articlePageVO;
    }

    @Override
    public ArticlePageVO getArticleListByPage(PageVO pageVO) {
        List<Article> articleList = articleMapper.findArticleByPage(pageVO);
        ArticlePageVO articlePageVO = new ArticlePageVO();
        articlePageVO.setArticles(articleList.stream().map(ArticleConvert::convertArticle2VO).collect(Collectors.toList()));
        pageVO.setTotalCount(articleMapper.getTotalCount());
        articlePageVO.setPage(pageVO);
        return articlePageVO;
    }

    @Override
    public List<ArticleVO> searchArticle(String keyword) {
        List<Article> articleList = new ArrayList<>();
        if (!StringUtils.isEmpty(keyword)) {
            Integer id = NumberUtils.convert2Int(keyword);
            if (id != null) {
                articleList.add(articleMapper.findById(id));
            } else {
                articleList = articleMapper.findByTitle(keyword);
            }
        }
        return articleList.stream().filter(Objects::nonNull)
                .map(ArticleConvert::convertArticle2VO).collect(Collectors.toList());
    }

    @Override
    public ArticleVO enableArticle(Integer id) {
        if (id != null && id > 0) {
            articleMapper.enableArticle(id);
            Article article = articleMapper.findById(id);
            return ArticleConvert.convertArticle2VO(article);
        }
        return null;
    }

    @Override
    public ArticleVO disableArticle(Integer id) {
        if (id != null && id > 0) {
            articleMapper.disableArticle(id);
            Article article = articleMapper.findById(id);
            return ArticleConvert.convertArticle2VO(article);
        }
        return null;
    }
}

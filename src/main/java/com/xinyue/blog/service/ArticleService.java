package com.xinyue.blog.service;

import com.xinyue.blog.constant.MessageEnum;
import com.xinyue.blog.constant.QueryConstant;
import com.xinyue.blog.constant.UserConstant;
import com.xinyue.blog.convert.ArticleConvert;
import com.xinyue.blog.dao.ArticleRepository;
import com.xinyue.blog.dao.mybaits.ArticleMapper;
import com.xinyue.blog.model.Article;
import com.xinyue.blog.utils.*;
import com.xinyue.blog.vo.ArticlePageVO;
import com.xinyue.blog.vo.ArticleVO;
import com.xinyue.blog.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sangz
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;
    private final TagService tagService;
    private final CategoryService categoryService;

    @Autowired
    public ArticleService(ArticleMapper articleMapper, ArticleRepository articleRepository,
                          TagService tagService, CategoryService categoryService) {
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
        this.tagService = tagService;
        this.categoryService = categoryService;
    }

    public String saveArticle(ArticleVO articleVO) {
        if (articleVO != null) {
            if (!NumberUtils.isEmptyInt(articleVO.getId())) {
                saveArticle(articleVO, articleRepository.findArticleById(articleVO.getId()));
            } else {
                saveArticle(articleVO, null);
            }
        }
        return MessageEnum.SUCCESS.getDesc();
    }

    private void saveArticle(ArticleVO articleVO, Article article) {
        if (article == null) {
            article = new Article();
            article.setCreator(UserConstant.USER_NAME);
            article.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        }
        convertBasicArticleInfo(articleVO, article);
        articleRepository.save(article);
    }

    private void convertBasicArticleInfo(ArticleVO articleVO, Article article) {
        article.setTitle(articleVO.getTitle());
        article.setSummary(articleVO.getSummary());
        article.setContent(articleVO.getContent());
        article.setLastUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
        article.setDeleteFlag(false);
        article.setLastUpdater(UserConstant.USER_NAME);
        article.setCategory(categoryService.getCategoryByName(articleVO.getCategory()));
        article.setTags(tagService.getTagsByNames(articleVO.getTags()));
    }

    public String deleteArticle(int id) {
        if (!NumberUtils.isEmptyInt(id)) {
            Article article = articleRepository.findArticleById(id);
            if (article != null) {
                article.setDeleteFlag(true);
                articleRepository.save(article);
            }
        }
        return MessageEnum.SUCCESS.getDesc();
    }

    public ArticlePageVO getArticleListByPage(Integer page) {
        Pageable pageable = PageRequest.of(page, QueryConstant.PAGE_SIZE,
                new Sort(Sort.Direction.DESC, "lastUpdateDate"));
        Page<Article> articlePage = articleRepository.findAllByDeleteFlagFalse(pageable);
        ArticlePageVO articlePageVO = new ArticlePageVO();
        List<ArticleVO> articleVOList = new ArrayList<>();
        for (Article article : articlePage.getContent()) {
            articleVOList.add(ArticleConvert.convertArticle2VO(article));
        }
        articlePageVO.setArticles(articleVOList);
        articlePageVO.setPage(new PageVO(page, articlePage.getTotalPages()));
        return articlePageVO;
    }

    @Cacheable(cacheNames = "article", key = "'article_' + #id")
    public ArticleVO getArticleById(Integer id) {
        Article article = articleRepository.findByIdAndDeleteFlagFalse(id);
        ArticleVO articleVO;
        if (article == null) {
            return null;
        }
        articleVO = ArticleConvert.convertArticle2VO(article);
        Article preArticle = articleMapper.findPreArticle(id);
        Article nextArticle = articleMapper.findNextArticle(id);
        if (preArticle != null) {
            ArticleVO preArticleVO = new ArticleVO();
            preArticleVO.setId(preArticle.getId());
            preArticleVO.setTitle(preArticle.getTitle());
            articleVO.setPreArticle(preArticleVO);
        }
        if (nextArticle != null) {
            ArticleVO aftArticleVO = new ArticleVO();
            aftArticleVO.setId(nextArticle.getId());
            aftArticleVO.setTitle(nextArticle.getTitle());
            articleVO.setNextArticle(aftArticleVO);
        }
        return articleVO;
    }
}

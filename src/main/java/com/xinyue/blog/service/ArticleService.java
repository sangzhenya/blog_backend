package com.xinyue.blog.service;

import com.xinyue.blog.constant.MessageEnum;
import com.xinyue.blog.constant.QueryConstant;
import com.xinyue.blog.constant.UserConstant;
import com.xinyue.blog.convert.ArticleConvert;
import com.xinyue.blog.dao.ArticleRepository;
import com.xinyue.blog.model.Article;
import com.xinyue.blog.utils.*;
import com.xinyue.blog.vo.ArticlePageVO;
import com.xinyue.blog.vo.ArticleVO;
import com.xinyue.blog.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    private final ArticleRepository articleRepository;
    private final TagService tagService;
    private final CategoryService categoryService;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, TagService tagService, CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.tagService = tagService;
        this.categoryService = categoryService;
    }

    public ArticleVO searchArticle(String keyword) {
        Article article = null;
        if (!StringUtils.isEmpty(keyword)) {
            Integer id = NumberUtils.convert2Int(keyword);
            if (id != null) {
                article = articleRepository.findByIdAndDeleteFlagFalse(id);
            } else {
                article = ListUtils.getFirstElement(articleRepository.findByTitleLikeAndDeleteFlagFalse(StringUtils.buildSearchKey(keyword)));
            }
        }
        if (article != null) {
            return ArticleConvert.convertArticle2VO(article, false);
        }
        return null;
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
            article.setCreateDate(LocalDateTime.now());
        }
        convertBasicArticleInfo(articleVO, article);
        articleRepository.save(article);
    }

    private void convertBasicArticleInfo(ArticleVO articleVO, Article article) {
        article.setTitle(articleVO.getTitle());
        article.setSummary(articleVO.getSummary());
        article.setContent(articleVO.getContent());
        article.setLastUpdateDate(LocalDateTime.now());
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
            articleVOList.add(ArticleConvert.convertArticle2VO(article, false));
        }
        articlePageVO.setArticles(articleVOList);
        articlePageVO.setPage(new PageVO(page, articlePage.getTotalPages()));
        return articlePageVO;
    }

    public ArticleVO getArticleById(Integer id) {
        Article article = articleRepository.findByIdAndDeleteFlagFalse(id);
        ArticleVO articleVO = new ArticleVO();
        if (article != null) {
            articleVO = ArticleConvert.convertArticle2VO(article, true);
        }
        Page<Article> preArticlePage = articleRepository.findArticleByIdBefore(id, PageUtils.getPageable4Before());
        Page<Article> aftArticlePage = articleRepository.findArticleByIdAfter(id, PageUtils.getPageable4After());

        if (!CollectionUtils.isEmpty(preArticlePage.getContent())) {
            Article preArticle = preArticlePage.getContent().get(0);
            ArticleVO preArticleVO = new ArticleVO();
            preArticleVO.setId(preArticle.getId());
            preArticleVO.setTitle(preArticle.getTitle());
            articleVO.setPreArticle(preArticleVO);
        }
        if (!CollectionUtils.isEmpty(aftArticlePage.getContent())) {
            Article aftArticle = aftArticlePage.getContent().get(0);
            ArticleVO aftArticleVO = new ArticleVO();
            aftArticleVO.setId(aftArticle.getId());
            aftArticleVO.setTitle(aftArticle.getTitle());
            articleVO.setNextArticle(aftArticleVO);
        }
        return articleVO;
    }
}
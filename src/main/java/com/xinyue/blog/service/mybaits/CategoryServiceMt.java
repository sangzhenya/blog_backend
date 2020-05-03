package com.xinyue.blog.service.mybaits;

import com.xinyue.blog.convert.ArticleConvert;
import com.xinyue.blog.dao.mybaits.ArticleMapper;
import com.xinyue.blog.dao.mybaits.CategoryMapper;
import com.xinyue.blog.model.Article;
import com.xinyue.blog.model.Category;
import com.xinyue.blog.vo.ArticleVO;
import com.xinyue.blog.vo.CategoryPageVO;
import com.xinyue.blog.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sangz
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class CategoryServiceMt {
    private final CategoryMapper categoryMapper;
    private final ArticleMapper articleMapper;

    @Autowired
    public CategoryServiceMt(CategoryMapper categoryMapper, ArticleMapper articleMapper) {
        this.categoryMapper = categoryMapper;
        this.articleMapper = articleMapper;
    }

    @Cacheable(cacheNames = {"category"}, key = "'categories'")
    public CategoryPageVO getCategories() {
        List<Category> categoryList = categoryMapper.findAll();
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryVOList.add(new CategoryVO(category.getId(), category.getName(), category.getArticleCount()));
        }
        return new CategoryPageVO(categoryVOList);
    }

    @Cacheable(cacheNames = {"category"}, key = "'category_' + #id")
    public Category getCategoryById(int id) {
        return categoryMapper.findById(id);
    }

    @Cacheable(cacheNames = "category", key = "'categroy_full_' + #id")
    public CategoryVO getCategoryById(Integer id) {
        Category category = categoryMapper.findById(id);
        List<Article> articleList = articleMapper.findArticleByCategoryId(id);
        CategoryVO categoryVO = new CategoryVO(category.getId(), category.getName(), articleList.size());
        if (!CollectionUtils.isEmpty(articleList)) {
            List<ArticleVO> articleVOList = new ArrayList<>();
            for (Article article : articleList) {
                articleVOList.add(ArticleConvert.convertArticle2VO(article));
            }
            articleVOList.sort((o1, o2) -> o2.getUpdateDate().compareTo(o1.getUpdateDate()));
            categoryVO.setArticleList(articleVOList);
        }
        return categoryVO;
    }
}

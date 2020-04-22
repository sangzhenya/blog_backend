package com.xinyue.blog.service;

import com.xinyue.blog.constant.MessageEnum;
import com.xinyue.blog.convert.ArticleConvert;
import com.xinyue.blog.dao.CategoryRepository;
import com.xinyue.blog.model.Article;
import com.xinyue.blog.model.Category;
import com.xinyue.blog.utils.CollectionUtils;
import com.xinyue.blog.utils.StringUtils;
import com.xinyue.blog.vo.ArticleVO;
import com.xinyue.blog.vo.CategoryPageVO;
import com.xinyue.blog.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.util.Comparator.*;

/**
 * @author sangz
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryVO> searchCategory(String searchKey) {
        List<CategoryVO> categoryVOList = new ArrayList<>();
        if (!StringUtils.isEmpty(searchKey)) {
            List<Category> categoryList = categoryRepository.findByNameLike(StringUtils.buildSearchKey(searchKey));
            if (!CollectionUtils.isEmpty(categoryList)) {
                for (Category category : categoryList) {
                    categoryVOList.add(new CategoryVO(category.getId(), category.getName()));
                }
            }
        }
        return categoryVOList;
    }

    public Category getCategoryByName(CategoryVO category) {
        if (category != null && !StringUtils.isEmpty(category.getName())) {
            List<Category> categoryList = categoryRepository.findByName(category.getName());
            if (!CollectionUtils.isEmpty(categoryList)) {
                return categoryList.get(0);
            }
        }
        return categoryRepository.findCategoryById(1);
    }

    public List<CategoryVO> listCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryVO> categoryVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (Category category : categoryList) {
                categoryVOList.add(new CategoryVO(category.getId(), category.getName()));
            }
        }
        return categoryVOList;
    }

    public String saveCategory(String categoryName) {
        if (!StringUtils.isEmpty(categoryName)) {
            Category category = new Category();
            category.setName(categoryName);
            categoryRepository.save(category);
            return MessageEnum.SUCCESS.getDesc();
        }
        return MessageEnum.ERROR.getDesc();
    }

    public CategoryPageVO getCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryVOList.add(new CategoryVO(category.getId(), category.getName(), category.getArticles().size()));
        }
        return new CategoryPageVO(categoryVOList);
    }

    public CategoryVO getCategoryById(Integer id) {
        Category category = categoryRepository.findCategoryById(id);
        CategoryVO categoryVO = new CategoryVO(category.getId(), category.getName(), category.getArticles().size());
        if (!CollectionUtils.isEmpty(category.getArticles())) {
            Set<Article> articleSet = category.getArticles();
            List<ArticleVO> articleVOList = new ArrayList<>();
            for (Article article : articleSet) {
                articleVOList.add(ArticleConvert.convertArticle2VO(article));
            }
            articleVOList.sort((o1, o2) -> o2.getUpdateDate().compareTo(o1.getUpdateDate()));
            categoryVO.setArticleList(articleVOList);
        }
        return categoryVO;
    }
}

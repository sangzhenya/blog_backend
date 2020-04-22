package com.xinyue.blog.service;

import com.xinyue.blog.model.Category;
import com.xinyue.blog.service.mybaits.ArticleServiceMt;
import com.xinyue.blog.service.mybaits.CategoryServiceMt;
import com.xinyue.blog.vo.CategoryPageVO;
import com.xinyue.blog.vo.CategoryVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author sangz
 * @date 2019/7/7 11:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceMtTest {
    @Autowired
    private CategoryServiceMt categoryServiceMt;
    @Autowired
    private ArticleServiceMt articleServiceMt;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void getAllCategoriesTest() {
        CategoryPageVO categoryPageVO = categoryServiceMt.getCategories();
        System.out.println(categoryPageVO.getCategoryVOList());
    }

    @Test
    public void getCategoryById() {
        Category category = categoryServiceMt.getCategoryById(1);
        System.out.println(category);
    }

    @Test
    public void getArticlePage() {
        System.out.println(articleServiceMt.getArticleListByPage(1));
    }

    @Test
    public void testBeanContains() {
        System.out.println(applicationContext.containsBean("dataSource"));;
        if (applicationContext.containsBean("dataSource")) {
            System.out.println(applicationContext.getBean("dataSource"));
        }
    }
}

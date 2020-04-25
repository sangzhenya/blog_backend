package com.xinyue.blog.mapper;

import com.xinyue.blog.dao.mybaits.ArticleMapper;
import com.xinyue.blog.dao.mybaits.CategoryMapper;
import com.xinyue.blog.model.Article;
import com.xinyue.blog.vo.PageVO;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
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
public class MapperTest {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void testFindArticleByPage() {
        PageVO pageVO = new PageVO(1);
        List<Article> articleList = articleMapper.findArticleByPageExcludeDeleted(pageVO);
        System.out.println(articleList);
    }

    @Test
    public void testFindArticleByCategoryId() {
        List<Article> articleList = articleMapper.findArticleByCategoryId(2);
        System.out.println(articleList);
    }

    @Test
    public void findNextArticleTest() {
        Article article = articleMapper.findPreArticle(5);
        System.out.println(article);
    }

    @Test
    public void testClearMyBaitsCache() {
        Configuration config = sqlSessionFactory.getConfiguration();
        config.getCaches();
    }

    @Test
    public void testUpdateArticle() {
        articleMapper.disableArticle(122);
    }
}

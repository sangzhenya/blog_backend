package com.xinyue.blog.controller;

import com.xinyue.blog.service.ArticleService;
import com.xinyue.blog.service.CategoryService;
import com.xinyue.blog.utils.PageUtils;
import com.xinyue.blog.vo.ResultVO;
import com.xinyue.blog.vo.requestVO.RequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sangz
 */
@RestController
@RequestMapping(value = "/public", produces= "application/json;charset=UTF-8")
public class PublicController {
    private final Logger logger = LoggerFactory.getLogger(PublicController.class);

    private final ArticleService articleService;
    private final CategoryService categoryService;

    @RequestMapping("/")
    public String info() {
        logger.info("Start to visit index page, Log test ");
        return "Hello World";
    }

    @Autowired
    public PublicController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @ResponseBody
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResultVO getPageArticle(@RequestBody RequestVO requestVO) {
        return new ResultVO(articleService.getArticleListByPage(PageUtils.getRealPageIndex(requestVO.getPage())));
    }

    @ResponseBody
    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public ResultVO getArticle(@RequestBody RequestVO requestVO) {
        return new ResultVO(articleService.getArticleById(requestVO.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public ResultVO getPageCategory() {
        return new ResultVO(categoryService.getCategories());
    }

    @ResponseBody
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ResultVO getCategory(@RequestBody RequestVO requestVO) {
        return new ResultVO(categoryService.getCategoryById(requestVO.getId()));
    }

}

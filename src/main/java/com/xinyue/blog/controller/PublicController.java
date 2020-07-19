package com.xinyue.blog.controller;

import com.xinyue.blog.service.ArticleServiceImpl;
import com.xinyue.blog.service.CategoryService;
import com.xinyue.blog.service.ManagerService;
import com.xinyue.blog.service.mybaits.ArticleMtServiceImpl;
import com.xinyue.blog.service.mybaits.CategoryServiceMt;
import com.xinyue.blog.vo.PageVO;
import com.xinyue.blog.vo.ResultVO;
import com.xinyue.blog.vo.requestVO.RequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

/**
 * @author sangz
 */
@RestController
@RequestMapping(value = "/public", produces= "application/json;charset=UTF-8")
public class PublicController {
    private final Logger logger = LoggerFactory.getLogger(PublicController.class);

    private final ArticleServiceImpl articleServiceImpl;
    private final CategoryService categoryService;
    private final CategoryServiceMt categoryServiceMt;
    private final ArticleMtServiceImpl articleMtServiceImpl;
    private final ManagerService managerService;
    @Autowired
    private CacheManager cacheManager;


    @RequestMapping("/")
    public String info() {
        logger.info("Start to visit index page, Log test ");
        return "Hello World";
    }

    @Autowired
    public PublicController(ArticleServiceImpl articleServiceImpl, CategoryService categoryService, CategoryServiceMt categoryServiceMt, ArticleMtServiceImpl articleMtServiceImpl, ManagerService managerService) {
        this.articleServiceImpl = articleServiceImpl;
        this.categoryService = categoryService;
        this.categoryServiceMt = categoryServiceMt;
        this.articleMtServiceImpl = articleMtServiceImpl;
        this.managerService = managerService;
    }

    @ResponseBody
    @RequestMapping(value = "/page02", method = RequestMethod.POST)
    public ResultVO getPageArticle(@RequestBody RequestVO requestVO) {
        return new ResultVO(articleServiceImpl.getArticleListByPage(requestVO.getPage()));
    }

    @ResponseBody
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResultVO getPageArticle02(@RequestBody RequestVO requestVO) {
        logger.info(String.valueOf(cacheManager.getCacheNames()));
        return new ResultVO(articleMtServiceImpl.getArticleListByPageExcludeDeleted(new PageVO(requestVO.getPage())));
    }

    @ResponseBody
    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public ResultVO getArticle(@RequestBody RequestVO requestVO) {
        return new ResultVO(articleServiceImpl.getArticleById(requestVO.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/categories02", method = RequestMethod.POST)
    public ResultVO getPageCategory() {
        return new ResultVO(categoryService.getCategories());
    }

    @ResponseBody
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public ResultVO getPageCategory02() {
        return new ResultVO(categoryServiceMt.getCategories());
    }

    @ResponseBody
    @RequestMapping(value = "/category02", method = RequestMethod.POST)
    public ResultVO getCategory(@RequestBody RequestVO requestVO) {
        return new ResultVO(categoryService.getCategoryById(requestVO.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ResultVO getCategory02(@RequestBody RequestVO requestVO) {
        return new ResultVO(categoryServiceMt.getCategoryById(requestVO.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/clear/cache", method = RequestMethod.GET)
    public ResultVO clearCache() {
        return new ResultVO(managerService.clearCache());
    }

}

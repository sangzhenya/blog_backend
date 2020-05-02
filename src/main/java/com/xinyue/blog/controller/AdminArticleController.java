package com.xinyue.blog.controller;

import com.xinyue.blog.service.mybaits.ArticleMtService;
import com.xinyue.blog.utils.PageUtils;
import com.xinyue.blog.vo.*;
import com.xinyue.blog.vo.requestVO.RequestVO;
import com.xinyue.blog.vo.requestVO.SearchVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sangz
 */
@RestController
@RequestMapping(value = "/admin/article")
public class AdminArticleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ArticleMtService ArticleMtService;

    public AdminArticleController(ArticleMtService ArticleMtService) {
        this.ArticleMtService = ArticleMtService;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultVO deleteMessage(@RequestBody RequestVO requestVO) {
        return new ResultVO(ArticleMtService.getArticleListByPage(PageUtils.buildPageVOByRequestVO(requestVO)));
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<ArticleVO> searchArticle(@RequestBody SearchVO searchVO) {
        return ArticleMtService.searchArticle(searchVO.getSearchKey());
    }

    @ResponseBody
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public ArticleVO enableArticle(@RequestBody RequestVO requestVO) {
        return ArticleMtService.enableArticle(requestVO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public ArticleVO disableArticle(@RequestBody RequestVO requestVO) {
        return ArticleMtService.disableArticle(requestVO.getId());
    }

}

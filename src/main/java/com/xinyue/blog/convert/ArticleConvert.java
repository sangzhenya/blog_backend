package com.xinyue.blog.convert;


import com.xinyue.blog.model.Article;
import com.xinyue.blog.model.Category;
import com.xinyue.blog.model.Tag;
import com.xinyue.blog.utils.CollectionUtils;
import com.xinyue.blog.vo.ArticleVO;
import com.xinyue.blog.vo.CategoryVO;
import com.xinyue.blog.vo.TagVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArticleConvert {
    public static ArticleVO convertArticle2VO(Article article, boolean withContent) {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setId(article.getId());
        articleVO.setTitle(article.getTitle());
        articleVO.setSummary(article.getSummary());
        if (withContent) {
            articleVO.setContent(article.getContent());
        }
        articleVO.setCreateDate(article.getCreateDate());
        articleVO.setUpdateDate(article.getLastUpdateDate());
        Category category = article.getCategory();
        if (category != null) {
            articleVO.setCategory(new CategoryVO(article.getCategory().getId(), article.getCategory().getName()));
        }
        Set<Tag> tagSet = article.getTags();
        if (!CollectionUtils.isEmpty(tagSet)) {
            List<TagVO> tagVOList = new ArrayList<>();
            for (Tag tag : tagSet) {
                tagVOList.add(new TagVO(tag.getId(), tag.getName()));
            }
            articleVO.setTags(tagVOList);
        }
        return articleVO;
    }
}

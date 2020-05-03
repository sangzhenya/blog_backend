package com.xinyue.blog.service;

import com.xinyue.blog.constant.MessageEnum;
import com.xinyue.blog.dao.TagRepository;
import com.xinyue.blog.model.Tag;
import com.xinyue.blog.vo.TagVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sangz
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagVO> searchTag(String searchKey) {
        List<TagVO> tagVOList = new ArrayList<>();
        if (!StringUtils.isEmpty(searchKey)) {
            List<Tag> tagList = tagRepository.findByNameLike("%" + searchKey + "%");
            if (!CollectionUtils.isEmpty(tagList)) {
                for (Tag tag : tagList) {
                    tagVOList.add(new TagVO(tag.getId(), tag.getName()));
                }
            }
        }
        return tagVOList;
    }

    public Set<Tag> getTagsByNames(List<TagVO> tags) {
        Set<Tag> newTagSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(tags)) {
            Set<String> oldTagSet = new HashSet<>();
            for (TagVO tag : tags) {
                oldTagSet.add(tag.getName());
            }
            for (String tagName : oldTagSet) {
                List<Tag> tagList = tagRepository.findByName(tagName);
                if (!CollectionUtils.isEmpty(tagList)) {
                    newTagSet.add(tagList.get(0));
                }
            }
        }
        return newTagSet;
    }

    public List<TagVO> listTag() {
        List<TagVO> tagVOList = new ArrayList<>();
        List<Tag> tagList = tagRepository.findAll();
        if (!CollectionUtils.isEmpty(tagList)) {
            for (Tag tag : tagList) {
                tagVOList.add(new TagVO(tag.getId(), tag.getName()));
            }
        }
        return tagVOList;
    }

    public String saveTag(String tagName) {
        if (!StringUtils.isEmpty(tagName)) {
            Tag tag = new Tag();
            tag.setName(tagName);
            tagRepository.save(tag);
            return MessageEnum.SUCCESS.getDesc();
        }
        return MessageEnum.ERROR.getDesc();
    }
}

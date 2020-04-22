package com.xinyue.blog.service;

import com.xinyue.blog.utils.CollectionUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author sangz
 * @date 2019/7/15 22:12
 */
@Service
public class ManagerService {
    private final SqlSessionFactory sqlSessionFactory;
    private final CacheManager cacheManager;


    public ManagerService(SqlSessionFactory sqlSessionFactory, CacheManager cacheManager) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.cacheManager = cacheManager;
    }

    public String clearCache() {
        if (!CollectionUtils.isEmpty(cacheManager.getCacheNames())) {
            cacheManager.getCacheNames().parallelStream().filter(name -> cacheManager.getCache(name) != null).forEach(name -> cacheManager.getCache(name).clear());
        }
        if (!CollectionUtils.isEmpty(sqlSessionFactory.getConfiguration().getCaches())) {
            sqlSessionFactory.getConfiguration().getCaches().parallelStream().forEach(Cache::clear);
        }
        return "Success";
    }
}

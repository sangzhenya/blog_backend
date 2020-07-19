package com.xinyue.blog.aop;

import com.xinyue.blog.utils.DeviceUtil;
import com.xinyue.blog.utils.IPUtils;
import com.xinyue.blog.vo.DeviceInfo;
import com.xinyue.blog.vo.requestVO.RequestVO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sangz
 * @date 2019/7/7 10:48
 */
@Aspect
@Component
public class DataAspects {
    private final Logger logger = LoggerFactory.getLogger(DataAspects.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @After("execution(public * com.xinyue.blog.controller.PublicController.getArticle(..))")
    public void logArticle(JoinPoint joinPoint) {
        RequestVO requestVO = (RequestVO) joinPoint.getArgs()[0];
        incrScore("articleRank", String.valueOf(requestVO.getId()));
    }

    @Before("execution(public * com.xinyue.blog.controller.PublicController.*(..))")
    public void logIp() {
        incrScore("ipRank", IPUtils.getRemoteIp(getRequest()));
    }

    @Before("execution(public * com.xinyue.blog.controller.PublicController.*(..))")
    public void deviceInfo() {
        DeviceInfo deviceInfo = DeviceUtil.getDeviceInfo(getRequest());
        incrScore("deviceScore", StringUtils.defaultString(deviceInfo.getDevice(), ""));
        incrScore("browserScore", StringUtils.defaultString(deviceInfo.getBrowser(), ""));
        incrScore("browserEditionScore", StringUtils.defaultString(deviceInfo.getBrowser(), "") + StringUtils.defaultString(deviceInfo.getEdition(), ""));
        incrScore("languageScore", StringUtils.defaultString(deviceInfo.getLanguage(), ""));
        incrScore("systemScore", StringUtils.defaultString(deviceInfo.getSystem(), ""));
        incrScore("kernelScore", StringUtils.defaultString(deviceInfo.getKernel(), ""));
        logger.info(deviceInfo.toString());
    }

    private void incrScore(String zKey, String key) {
        Double articleScore = stringRedisTemplate.opsForZSet().score(zKey, key);
        if (articleScore == null) {
            stringRedisTemplate.opsForZSet().add(zKey, key, 1D);
        } else {
            stringRedisTemplate.opsForZSet().incrementScore(zKey, key, 1D);
        }
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}

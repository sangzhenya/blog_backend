package com.xinyue.blog.aop;

import com.xinyue.blog.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author sangz
 * @date 2019/7/7 10:48
 */
@Aspect
@Component
public class LogAspects {
    private final Logger logger = LoggerFactory.getLogger(LogAspects.class);

    @Pointcut("execution(public * com.xinyue.blog.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(IPUtils.getRemoteIp(request) + "::" + joinPoint.getSignature().getName() + "运行@" + LocalDateTime.now());
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(IPUtils.getRemoteIp(request) + "::" + joinPoint.getSignature().getName() + "结束@" + LocalDateTime.now());
    }
}

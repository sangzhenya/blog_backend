package com.xinyue.blog.controller;

import com.xinyue.blog.aop.LogAspects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author sangz
 * @date 2019/7/6 16:38
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public String info(HttpServletRequest request) {
        return "Hello:" + request.getRequestURI() + "If you see this means server start success, " + LocalDateTime.now();
    }
}

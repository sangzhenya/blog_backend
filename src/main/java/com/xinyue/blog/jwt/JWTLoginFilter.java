package com.xinyue.blog.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinyue.blog.constant.MessageEnum;
import com.xinyue.blog.constant.StatusEnum;
import com.xinyue.blog.model.security.SysUser;
import com.xinyue.blog.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sangz
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        //允许跨域访问的域
        res.setHeader("Access-Control-Allow-Origin","*");
        //允许使用的请求方法
        res.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE,PUT");
        res.setHeader("Access-Control-Expose-Headers","*");
        //允许使用的请求方法
        res.setHeader("Access-Control-Allow-Headers", "x-requested-with,Cache-Control,Pragma,Content-Type,Authorization");
        //是否允许请求带有验证信息
        res.setHeader("Access-Control-Allow-Credentials","true");

        // JSON反序列化成 AccountCredentials
        SysUser sysUser;
        try {
            sysUser = new ObjectMapper().readValue(req.getInputStream(), SysUser.class);
        } catch (IOException e) {
            logger.error("Login meet error: " + e.getMessage());
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken("", ""));
        }

        // 返回一个验证令牌
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(sysUser.getUsername(),
                sysUser.getPassword())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        TokenAuthenticationService.addAuthentication(response, authResult.getName(), authResult.getAuthorities());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(JSONResult.fillResultString(StatusEnum.LOGIN_FAILED.getStatus(),
                MessageEnum.LOGIN_FAILED.getDesc(), ""));
    }
}

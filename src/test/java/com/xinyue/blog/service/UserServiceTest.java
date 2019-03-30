package com.xinyue.blog.service;

import com.xinyue.blog.model.security.SysUser;
import com.xinyue.blog.service.security.CustomUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private CustomUserServiceImpl customUserService;

    @Test
    public void existsOrCreateUser() {
        String userName = "xinyue";
        String password = "xinyue";
        String role = "ROLE_ADMIN";

        UserDetails findUser = customUserService.loadUserByUsername(userName);
        if (findUser == null) {
            SysUser insertUser = customUserService.createUser(userName, password, role);
            findUser = customUserService.loadUserByUsername(userName);
            Assert.notNull(findUser, "User should not be null");
            Assert.isTrue(insertUser.getUsername().equals(findUser.getUsername()), "User Name Should be Same");
        } else {
            Assert.isTrue(userName.equals(findUser.getUsername()));
        }
    }
}

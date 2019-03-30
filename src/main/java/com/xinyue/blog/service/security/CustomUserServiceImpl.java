package com.xinyue.blog.service.security;

import com.xinyue.blog.dao.security.SysRoleRepository;
import com.xinyue.blog.dao.security.SysUserRepository;
import com.xinyue.blog.model.security.SysRole;
import com.xinyue.blog.model.security.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserServiceImpl implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(CustomUserServiceImpl.class);

    private final SysUserRepository userRepository;
    private final SysRoleRepository sysRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserServiceImpl(SysUserRepository userRepository, SysRoleRepository sysRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sysRoleRepository = sysRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = null;
        try {
            user = userRepository.findByUsername(s);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    public SysUser createUser(String username, String password, String roleName) {
        String encodedPassword = passwordEncoder.encode(password);
        SysUser sysUser = new SysUser();
        SysRole sysRole = sysRoleRepository.findByName(roleName);
        if (sysRole == null) {
            sysRole = new SysRole();
            sysRole.setName(roleName);
            sysRoleRepository.save(sysRole);
        }

        sysUser.setUsername(username);
        sysUser.setPassword(encodedPassword);
        sysUser.setRoles(Collections.singletonList(sysRole));
        return userRepository.save(sysUser);
    }
}
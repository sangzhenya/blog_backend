package com.xinyue.blog.dao.security;

import com.xinyue.blog.model.security.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
    SysRole findByName(String name);
}
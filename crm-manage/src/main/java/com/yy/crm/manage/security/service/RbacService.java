package com.yy.crm.manage.security.service;


import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * rbac权限控制
 * @author luyuanyuan on 2018/3/1.
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}

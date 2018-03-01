package com.yy.crm.manage.security.service.impl;

import com.yy.crm.common.Const;
import com.yy.crm.manage.security.service.RbacService;
import com.yy.crm.service.service.SysCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author luyuanyuan on 2018/3/1.
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    @Autowired
    private SysCoreService sysCoreService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails) {
            //到这一步经过security重重身份验证，所以username不为null
            String username = ((UserDetails) principal).getUsername();
            if(Const.ADMIN.equals(username)) {//是管理员
                return true;
            }
            //读取用户所拥有权限的所有URL
            Set<String> urls = new HashSet<>();
            boolean hasPermission = false;
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
                return hasPermission;
            }
        }
        return false;
    }
}

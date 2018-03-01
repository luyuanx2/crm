package com.yy.crm.service.service.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.yy.crm.common.Const;
import com.yy.crm.security.common.util.JsonUtils;
import com.yy.crm.service.common.RequestHolder;
import com.yy.crm.service.mapper.SysAclMapper;
import com.yy.crm.service.mapper.SysRoleAclMapper;
import com.yy.crm.service.mapper.SysRoleUserMapper;
import com.yy.crm.service.model.SysAcl;
import com.yy.crm.service.model.SysUser;
import com.yy.crm.service.service.BaseCacheService;
import com.yy.crm.service.service.SysCoreService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyuanyuan on 2018/2/24.
 */
@Service
public class SysCoreServiceImpl implements SysCoreService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;
    @Autowired
    private SysAclMapper sysAclMapper;
    @Autowired
    private BaseCacheService redisCacheService;

    @Override
    public List<SysAcl> getCurrentUserAclList() {
        int userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);
    }

    @Override
    public List<SysAcl> getRoleAclList(int roleId) {
        List<Integer> aclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.newArrayList(roleId));
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(aclIdList);
    }

    private String generateCacheKey(Const.CacheKey prefix, String... keys) {
        String key = prefix.name();
        if (keys != null && keys.length > 0) {
            key += "_" + Joiner.on("_").join(keys);
        }
        return key;
    }

    @Override
    public List<String> getCurrentUserAclUrlListFromCache() {
        int userId = RequestHolder.getCurrentUser().getId();
        String cacheValue = redisCacheService.get(generateCacheKey(Const.CacheKey.USER_ACLS,String.valueOf(userId)));
        if (StringUtils.isBlank(cacheValue)) {

            List<String> urls = new ArrayList<>();
            getCurrentUserAclList().forEach(item -> {
                if (item.getStatus() == 0 && Const.Acl.BOTTON.equals(item.getType())) {
                    urls.add(item.getUrl());
                }
            });

            if (CollectionUtils.isNotEmpty(urls)) {
                redisCacheService.setEx(generateCacheKey(Const.CacheKey.USER_ACLS,String.valueOf(userId)), JsonUtils.obj2String(urls), 600);
            }
            return urls;
        }
        return JsonUtils.string2List(cacheValue, String.class);
    }

    private List<SysAcl> getUserAclList(int userId) {
        if (isSuperAdmin()) {
            return sysAclMapper.selectAll();
        }
        List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Integer> userAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(userAclIdList);
    }

    private boolean isSuperAdmin() {
        // 自己定义了一个假的超级管理员规则，实际中要根据项目进行修改
        // 可以是配置文件获取，可以指定某个用户，也可以指定某个角色
        SysUser sysUser = RequestHolder.getCurrentUser();
        if (sysUser.getUsername().contains("admin")) {
            return true;
        }
        return false;
    }
}

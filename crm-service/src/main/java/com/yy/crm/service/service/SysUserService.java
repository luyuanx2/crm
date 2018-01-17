package com.yy.crm.service.service;

import com.github.pagehelper.PageInfo;
import com.yy.crm.service.model.SysUser;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.UserParam;

/**
 * @author luyuanyuan on 2018/1/17.
 */
public interface SysUserService {
    void save(UserParam param);

    void update(UserParam param);

    PageInfo<SysUser> getPageByDeptId(int deptId, PageQuery pageQuery);
}

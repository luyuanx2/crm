package com.yy.crm.service.service;

import com.github.pagehelper.PageInfo;
import com.yy.crm.service.model.SysRole;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.RoleParam;

/**
 * @author luyuanyuan on 2018/2/24.
 */
public interface SysRoleService {

    void save(RoleParam param);

    void update(RoleParam param);

    PageInfo<SysRole> getAll(PageQuery pageQuery);

    void delete(int roleId);
}

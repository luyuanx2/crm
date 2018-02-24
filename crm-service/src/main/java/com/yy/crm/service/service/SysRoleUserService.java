package com.yy.crm.service.service;

import com.yy.crm.service.model.SysUser;

import java.util.List;

/**
 * @author luyuanyuan on 2018/2/24.
 */
public interface SysRoleUserService {
    List<SysUser> getListByRoleId(int roleId);

    void changeRoleUsers(int roleId, List<Integer> userIdList);
}

package com.yy.crm.service.service;

import com.yy.crm.service.model.SysAcl;

import java.util.List;

/**
 * @author luyuanyuan on 2018/2/24.
 */
public interface SysCoreService {

    List<SysAcl> getCurrentUserAclList(int userId);

    List<SysAcl> getRoleAclList(int roleId);

    List<String> getCurrentUserAclUrlListFromCache(int userId);
}

package com.yy.crm.service.service;

import java.util.List;

/**
 * @author luyuanyuan on 2018/2/24.
 */
public interface SysRoleAclService {

    void changeRoleAcls(int roleId, List<Integer> aclIdList);
}

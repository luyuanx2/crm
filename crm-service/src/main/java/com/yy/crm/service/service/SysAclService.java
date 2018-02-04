package com.yy.crm.service.service;

import com.yy.crm.service.param.AclParam;

/**
 * @author 鲁源源 on 2018/2/4.
 */
public interface SysAclService {
    void save(AclParam param);
    void update(AclParam param);
    void delete(int aclId);
}

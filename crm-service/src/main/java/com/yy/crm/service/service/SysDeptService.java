package com.yy.crm.service.service;

import com.yy.crm.service.param.DeptParam;

/**
 * @author 鲁源源 on 2018/1/10.
 */
public interface SysDeptService{

    Integer save(DeptParam deptParam);

    void update(DeptParam param);

    void delete(int deptId);
}

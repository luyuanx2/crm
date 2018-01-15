package com.yy.crm.service.service;

import com.yy.crm.service.param.DeptParam;

/**
 * @author 鲁源源 on 2018/1/10.
 */
public interface SysDeptService{

    void saveDept(DeptParam deptParam);

    void update(DeptParam param);
}

package com.yy.crm.service.mapper;

import com.yy.crm.service.common.MyMapper;
import com.yy.crm.service.model.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends MyMapper<SysUser> {
    int countByDeptId(@Param("deptId") int deptId);
}
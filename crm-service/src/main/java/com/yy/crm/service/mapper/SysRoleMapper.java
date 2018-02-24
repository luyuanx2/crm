package com.yy.crm.service.mapper;

import com.yy.crm.service.common.MyMapper;
import com.yy.crm.service.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends MyMapper<SysRole> {
    int countByName(@Param("name") String name, @Param("id") Integer id);

    List<SysRole> getAll();
}
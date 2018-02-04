package com.yy.crm.service.mapper;

import com.yy.crm.service.common.MyMapper;
import com.yy.crm.service.model.SysAcl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclMapper extends MyMapper<SysAcl> {
    void batchUpdateLevel(@Param("aclList") List<SysAcl> aclList);
}
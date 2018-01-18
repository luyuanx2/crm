package com.yy.crm.service.mapper;

import com.yy.crm.service.common.MyMapper;
import com.yy.crm.service.model.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends MyMapper<SysUser> {
    int countByDeptId(@Param("deptId") int deptId);

    int countByMail(@Param("mail") String mail, @Param("id") Integer id);

    int countByTelephone(@Param("telephone") String telephone, @Param("id") Integer id);

}
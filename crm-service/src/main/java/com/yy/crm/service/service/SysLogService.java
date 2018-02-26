package com.yy.crm.service.service;

import com.github.pagehelper.PageInfo;
import com.yy.crm.service.model.SysAcl;
import com.yy.crm.service.model.SysDept;
import com.yy.crm.service.model.SysLog;
import com.yy.crm.service.model.SysRole;
import com.yy.crm.service.model.SysUser;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.SearchLogParam;

import java.util.List;

/**
 * @author luyuanyuan on 2018/2/26.
 */
public interface SysLogService {

    void recover(int id);

    void saveDeptLog(SysDept before, SysDept after);

    void saveUserLog(SysUser before, SysUser after);

    void saveAclLog(SysAcl before, SysAcl after);

    void saveRoleLog(SysRole before, SysRole after);

    void saveRoleAclLog(int roleId, List<Integer> before, List<Integer> after);

    void saveRoleUserLog(int roleId, List<Integer> before, List<Integer> after);

    PageInfo<SysLog> searchList(SearchLogParam param, PageQuery page);
}

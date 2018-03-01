package com.yy.crm.service.service;

import com.github.pagehelper.PageInfo;
import com.yy.crm.service.dto.SysUserDto;
import com.yy.crm.service.model.SysUser;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.SearchUserParam;
import com.yy.crm.service.param.SysUserParam;

import java.util.List;

/**
 * @author luyuanyuan on 2018/1/17.
 */
public interface SysUserService {
    void save(SysUserParam param);

    void update(SysUserParam param);

    PageInfo<SysUserDto> getPageByDeptId(SearchUserParam param, PageQuery pageQuery);

    SysUser findByUsername(String username);

    List<SysUser> getAll();

    List<SysUser> findByStatusAndUsable(Integer status, Boolean usable);

    void delete(int id);
}

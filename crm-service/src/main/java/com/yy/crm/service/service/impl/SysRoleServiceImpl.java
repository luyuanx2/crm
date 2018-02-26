package com.yy.crm.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.yy.crm.security.common.exception.ParamException;
import com.yy.crm.security.common.util.BeanValidator;
import com.yy.crm.service.common.RequestHolder;
import com.yy.crm.service.mapper.SysRoleMapper;
import com.yy.crm.service.model.SysRole;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.RoleParam;
import com.yy.crm.service.service.SysLogService;
import com.yy.crm.service.service.SysRoleService;
import com.yy.crm.service.service.base.BaseService;
import com.yy.crm.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author luyuanyuan on 2018/2/24.
 */
@Service
public class SysRoleServiceImpl extends BaseService<SysRole> implements SysRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysLogService sysLogService;
    @Override
    public void save(RoleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole role = SysRole.builder().name(param.getName())
                .status(param.getStatus()).type(param.getType())
                .remark(param.getRemark()).build();
        role.setOperator(RequestHolder.getCurrentUser().getUsername());
        role.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        role.setOperateTime(LocalDateTime.now());
        sysRoleMapper.insertSelective(role);
        sysLogService.saveRoleLog(null, role);
    }

    @Override
    public void update(RoleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole before = sysRoleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的角色不存在");

        SysRole after = SysRole.builder().id(param.getId()).name(param.getName())
                .status(param.getStatus()).type(param.getType())
                .remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(LocalDateTime.now());
        sysRoleMapper.updateByPrimaryKeySelective(after);
        sysLogService.saveRoleLog(before, after);
    }

    @Override
    public PageInfo<SysRole> getAll(PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
        List<SysRole> list = sysRoleMapper.getAll();
        return new PageInfo<>(list);
    }

    private boolean checkExist(String name, Integer id) {
        return sysRoleMapper.countByName(name, id) > 0;
    }
}

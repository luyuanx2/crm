package com.yy.crm.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.yy.crm.common.Const;
import com.yy.crm.common.LogType;
import com.yy.crm.security.common.exception.ParamException;
import com.yy.crm.security.common.util.BeanValidator;
import com.yy.crm.security.common.util.JsonUtils;
import com.yy.crm.service.common.RequestHolder;
import com.yy.crm.service.mapper.*;
import com.yy.crm.service.model.*;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.SearchLogParam;
import com.yy.crm.service.service.SysLogService;
import com.yy.crm.service.service.SysRoleAclService;
import com.yy.crm.service.service.SysRoleUserService;
import com.yy.crm.service.service.base.BaseService;
import com.yy.crm.utils.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author luyuanyuan on 2018/2/26.
 */
@Service
public class SysLogServiceImpl extends BaseService<SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysAclMapper sysAclMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleAclService sysRoleAclService;
    @Autowired
    private SysRoleUserService sysRoleUserService;

    public static void main(String[] args) {
        SysUser afterDept = JsonUtils.string2Obj("{\"id\":10,\"realName\":null,\"username\":\"sdfasf\",\"jobNum\":null,\"telephone\":\"15678954862\",\"mail\":\"sadf@qq.com\",\"password\":\"$2a$10$dtX1IgMhp694S2NCB1fTJO6u.L8tTv1s8nKQjoKMStJGgwnt9BxAq\",\"deptId\":2,\"status\":1,\"usable\":null,\"remark\":\"\",\"operator\":\"admin\",\"operateTime\":\"2018-02-27 11:45:15\",\"operateIp\":\"127.0.0.1\"}",SysUser.class);
        System.out.println(afterDept);
    }
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void recover(int id) {
        SysLog sysLog = sysLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(sysLog, "待还原的记录不存在");
        if(Const.LogStatus.RECOVERED == sysLog.getStatus()) {
            throw new ParamException("该条记录已经进行过还原操作，请勿重复操作！");
        }
        switch (sysLog.getType()){
            case LogType.TYPE_DEPT:
                SysDept beforeDept = sysDeptMapper.selectByPrimaryKey(sysLog.getTargetId());
                Preconditions.checkNotNull(beforeDept, "待还原的部门已经不存在了");
                if (StringUtils.isBlank(sysLog.getNewValue())  || StringUtils.isBlank(sysLog.getOldValue())) {
                    throw new ParamException("新增和删除操作不做还原");
                }
                SysDept afterDept = JsonUtils.string2Obj(sysLog.getOldValue(),SysDept.class);
                afterDept.setOperator(RequestHolder.getCurrentUser().getUsername());
                afterDept.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterDept.setOperateTime(LocalDateTime.now());
                sysDeptMapper.updateByPrimaryKeySelective(afterDept);
                saveDeptLog(beforeDept, afterDept);
                break;
            case LogType.TYPE_USER:
                SysUser beforeUser = sysUserMapper.selectByPrimaryKey(sysLog.getTargetId());
                Preconditions.checkNotNull(beforeUser, "待还原的用户已经不存在了");
                if (StringUtils.isBlank(sysLog.getNewValue())  || StringUtils.isBlank(sysLog.getOldValue())) {
                    throw new ParamException("新增和删除操作不做还原");
                }
                SysUser afterUser = JsonUtils.string2Obj(sysLog.getOldValue(), SysUser.class);
                afterUser.setOperator(RequestHolder.getCurrentUser().getUsername());
                afterUser.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterUser.setOperateTime(LocalDateTime.now());
                sysUserMapper.updateByPrimaryKeySelective(afterUser);
                saveUserLog(beforeUser, afterUser);
                break;
            case LogType.TYPE_ACL:
                SysAcl beforeAcl = sysAclMapper.selectByPrimaryKey(sysLog.getTargetId());
                Preconditions.checkNotNull(beforeAcl, "待还原的权限点已经不存在了");
                if (StringUtils.isBlank(sysLog.getNewValue())  || StringUtils.isBlank(sysLog.getOldValue())) {
                    throw new ParamException("新增和删除操作不做还原");
                }
                SysAcl afterAcl = JsonUtils.string2Obj(sysLog.getOldValue(), SysAcl.class);
                afterAcl.setOperator(RequestHolder.getCurrentUser().getUsername());
                afterAcl.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterAcl.setOperateTime(LocalDateTime.now());
                sysAclMapper.updateByPrimaryKeySelective(afterAcl);
                saveAclLog(beforeAcl, afterAcl);
                break;
            case LogType.TYPE_ROLE:
                SysRole beforeRole = sysRoleMapper.selectByPrimaryKey(sysLog.getTargetId());
                Preconditions.checkNotNull(beforeRole, "待还原的角色已经不存在了");
                if (StringUtils.isBlank(sysLog.getNewValue())  || StringUtils.isBlank(sysLog.getOldValue())) {
                    throw new ParamException("新增和删除操作不做还原");
                }
                SysRole afterRole = JsonUtils.string2Obj(sysLog.getOldValue(), SysRole.class);
                afterRole.setOperator(RequestHolder.getCurrentUser().getUsername());
                afterRole.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterRole.setOperateTime(LocalDateTime.now());
                sysRoleMapper.updateByPrimaryKeySelective(afterRole);
                saveRoleLog(beforeRole, afterRole);
                break;
            case LogType.TYPE_ROLE_ACL:
                SysRole aclRole = sysRoleMapper.selectByPrimaryKey(sysLog.getTargetId());
                Preconditions.checkNotNull(aclRole, "角色已经不存在了");
                sysRoleAclService.changeRoleAcls(sysLog.getTargetId(), JsonUtils.string2List(sysLog.getOldValue(), Integer.class));
                break;
            case LogType.TYPE_ROLE_USER:
                SysRole userRole = sysRoleMapper.selectByPrimaryKey(sysLog.getTargetId());
                Preconditions.checkNotNull(userRole, "角色已经不存在了");
                sysRoleUserService.changeRoleUsers(sysLog.getTargetId(), JsonUtils.string2List(sysLog.getOldValue(), Integer.class));
                break;
            default:;
        }
        sysLog.setStatus(Const.LogStatus.RECOVERED);
        sysLogMapper.updateByPrimaryKey(sysLog);
    }

    @Override
    public void saveDeptLog(SysDept before, SysDept after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_DEPT);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldValue(before == null ? "" : JsonUtils.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonUtils.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperateTime(LocalDateTime.now());
        sysLog.setStatus(Const.LogStatus.UN_RECOVERED);
        sysLogMapper.insertSelective(sysLog);
    }

    @Override
    public void saveUserLog(SysUser before, SysUser after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_USER);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldValue(before == null ? "" : JsonUtils.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonUtils.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperateTime(LocalDateTime.now());
        sysLog.setStatus(Const.LogStatus.UN_RECOVERED);
        sysLogMapper.insertSelective(sysLog);
    }

    @Override
    public void saveAclLog(SysAcl before, SysAcl after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ACL);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldValue(before == null ? "" : JsonUtils.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonUtils.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperateTime(LocalDateTime.now());
        sysLog.setStatus(Const.LogStatus.UN_RECOVERED);
        sysLogMapper.insertSelective(sysLog);
    }

    @Override
    public void saveRoleLog(SysRole before, SysRole after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ROLE);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldValue(before == null ? "" : JsonUtils.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonUtils.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperateTime(LocalDateTime.now());
        sysLog.setStatus(Const.LogStatus.UN_RECOVERED);
        sysLogMapper.insertSelective(sysLog);
    }

    @Override
    public void saveRoleUserLog(int roleId, List<Integer> before, List<Integer> after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ROLE_USER);
        sysLog.setTargetId(roleId);
        sysLog.setOldValue(before == null ? "" : JsonUtils.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonUtils.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperateTime(LocalDateTime.now());
        sysLog.setStatus(Const.LogStatus.UN_RECOVERED);
        sysLogMapper.insertSelective(sysLog);
    }

    @Override
    public PageInfo<SysLog> searchList(SearchLogParam param, PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
        if (StringUtils.isNotBlank(param.getBeforeSeg())) {
            param.setBeforeSeg("%" + param.getBeforeSeg() + "%");
        }
        if (StringUtils.isNotBlank(param.getAfterSeg())) {
            param.setAfterSeg("%" + param.getAfterSeg() + "%");
        }
        if (StringUtils.isNotBlank(param.getOperator())) {
            param.setOperator("%" + param.getOperator() + "%");
        }
        List<SysLog> list = sysLogMapper.searchList(param);
        return new PageInfo<>(list);
    }

    @Override
    public void saveRoleAclLog(int roleId, List<Integer> before, List<Integer> after) {
        SysLog sysLog = new SysLog();
        sysLog.setType(LogType.TYPE_ROLE_ACL);
        sysLog.setTargetId(roleId);
        sysLog.setOldValue(before == null ? "" : JsonUtils.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonUtils.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperateTime(LocalDateTime.now());
        sysLog.setStatus(Const.LogStatus.UN_RECOVERED);
        sysLogMapper.insertSelective(sysLog);
    }
}

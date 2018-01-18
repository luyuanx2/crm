package com.yy.crm.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.yy.crm.common.response.PermissionCode;
import com.yy.crm.security.common.exception.ParamException;
import com.yy.crm.security.common.util.BeanValidator;
import com.yy.crm.service.common.RequestHolder;
import com.yy.crm.service.dto.SysUserDto;
import com.yy.crm.service.mapper.SysUserMapper;
import com.yy.crm.service.model.SysUser;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.SysUserParam;
import com.yy.crm.service.service.SysUserService;
import com.yy.crm.service.service.base.BaseService;
import com.yy.crm.utils.IpUtil;
import com.yy.crm.utils.YYUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luyuanyuan on 2018/1/17.
 */
@Service
public class SysUserServiceImpl extends BaseService<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(SysUserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException(PermissionCode.MOBILE_ALREADY_EXIST);
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException(PermissionCode.EMAIL_ALREADY_EXIST);
        }
        String password = YYUtil.randomPassword();
        //TODO:
        password = "12345678";
        String encodePassword = passwordEncoder.encode(password);
        SysUser user = SysUser.builder().username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .password(encodePassword).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        user.setOperator(RequestHolder.getCurrentUser().getUsername());
        user.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        user.setOperateTime(LocalDateTime.now());

        // TODO: sendEmail

        this.save(user);
        //sysLogService.saveUserLog(null, user);
    }

    @Override
    public void update(SysUserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException(PermissionCode.MOBILE_ALREADY_EXIST);
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException(PermissionCode.EMAIL_ALREADY_EXIST);
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername())
                .telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(LocalDateTime.now());
        sysUserMapper.updateByPrimaryKeySelective(after);
        //sysLogService.saveUserLog(before, after);
    }

    @Override
    public PageInfo<SysUserDto> getPageByDeptId(int deptId, PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        SysUser sysUser = SysUser.builder().deptId(deptId).build();
        PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
        List<SysUser> list = this.queryListByWhere(sysUser);
        List<SysUserDto> sysUserDtoList = list.stream()
                .map(this::assembleSysUserDto).collect(Collectors.toList());
        return new PageInfo<>(sysUserDtoList);
    }

    private SysUserDto assembleSysUserDto(SysUser user) {
        return SysUserDto.builder().id(user.getId()).deptId(user.getDeptId())
                .mail(user.getMail()).status(user.getStatus()).telephone(user.getTelephone())
                .remark(user.getRemark()).username(user.getUsername()).build();
    }

    public boolean checkEmailExist(String mail, Integer userId) {
        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    public boolean checkTelephoneExist(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }
}

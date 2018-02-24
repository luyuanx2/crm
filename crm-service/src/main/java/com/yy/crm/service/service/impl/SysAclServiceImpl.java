package com.yy.crm.service.service.impl;

import com.google.common.base.Preconditions;
import com.yy.crm.common.response.RbacCode;
import com.yy.crm.security.common.exception.ParamException;
import com.yy.crm.security.common.util.BeanValidator;
import com.yy.crm.service.common.RequestHolder;
import com.yy.crm.service.mapper.SysAclMapper;
import com.yy.crm.service.model.SysAcl;
import com.yy.crm.service.param.AclParam;
import com.yy.crm.service.service.SysAclService;
import com.yy.crm.service.service.base.BaseService;
import com.yy.crm.utils.IpUtil;
import com.yy.crm.utils.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 鲁源源 on 2018/2/4.
 */
@Service
public class SysAclServiceImpl extends BaseService<SysAcl> implements SysAclService {

    @Autowired
    private SysAclMapper sysAclMapper;
    @Override
    public void save(AclParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(),param.getName(),param.getId())) {
            throw new ParamException(RbacCode.ACL_ALREADY_EXIST);
        }

        SysAcl acl = SysAcl.builder().type(param.getType()).icon(param.getIcon()).url(param.getUrl())
                .name(param.getName()).parentId(param.getParentId()).seq(param.getSeq())
                .status(param.getStatus()).remark(param.getRemark()).build();
        acl.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        acl.setOperator(RequestHolder.getCurrentUser().getUsername());
        acl.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        acl.setOperateTime(LocalDateTime.now());
        this.save(acl);
        //sysLogService.saveAclModuleLog(null, aclModule);
    }

    @Override
    public void update(AclParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException(RbacCode.ACL_ALREADY_EXIST);
        }
        SysAcl before = sysAclMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的权限不存在");

        SysAcl after = SysAcl.builder().type(param.getType()).icon(param.getIcon()).url(param.getUrl())
                .name(param.getName()).parentId(param.getParentId()).seq(param.getSeq())
                .status(param.getStatus()).remark(param.getRemark()).build();

        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(LocalDateTime.now());

        updateWithChild(before, after);
        //sysLogService.saveAclModuleLog(before, after);
    }

    @Override
    public void delete(int aclId) {
        SysAcl acl = sysAclMapper.selectByPrimaryKey(aclId);
        Preconditions.checkNotNull(acl, "待删除的权限不存在，无法删除");
        if(countByParentId(acl.getId()) > 0) {
            throw new ParamException("当前权限下面有子权限，无法删除");
        }
        sysAclMapper.deleteByPrimaryKey(aclId);
    }

    private int countByParentId(Integer parentId) {
        Weekend<SysAcl> weekend = Weekend.of(SysAcl.class);
        WeekendCriteria<SysAcl, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(SysAcl::getParentId,parentId);
        return sysAclMapper.selectCountByExample(criteria);
    }

    @Transactional
    public void updateWithChild(SysAcl before, SysAcl after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            List<SysAcl> aclList = getChildAclListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(aclList)) {
                for (SysAcl acl : aclList) {
                    String level = acl.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        acl.setLevel(level);
                    }
                }
                sysAclMapper.batchUpdateLevel(aclList);
            }
        }
        sysAclMapper.updateByPrimaryKeySelective(after);
    }

    private List<SysAcl> getChildAclListByLevel(String level) {
        Weekend<SysAcl> weekend = Weekend.of(SysAcl.class);
        WeekendCriteria<SysAcl, Object> criteria = weekend.weekendCriteria();
        criteria.andLike(SysAcl::getLevel, level + ".%");
        return sysAclMapper.selectByExample(weekend);
    }

    private boolean checkExist(Integer parentId, String name, Integer id) {
        Weekend<SysAcl> weekend = Weekend.of(SysAcl.class);
        WeekendCriteria<SysAcl, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(SysAcl::getParentId,parentId);
        criteria.andEqualTo(SysAcl::getName,name);
        if(id != null) {
            criteria.andNotEqualTo(SysAcl::getId,id);
        }
        return sysAclMapper.selectCountByExample(criteria) > 0;
    }

    private String getLevel(Integer aclId) {
        SysAcl acl= sysAclMapper.selectByPrimaryKey(aclId);
        if (acl == null) {
            return null;
        }
        return acl.getLevel();
    }
}

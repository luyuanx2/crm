package com.yy.crm.service.service.impl;

import com.google.common.base.Preconditions;
import com.yy.crm.common.response.LevelUtil;
import com.yy.crm.security.common.exception.ParamException;
import com.yy.crm.security.common.util.BeanValidator;
import com.yy.crm.service.mapper.SysDeptMapper;
import com.yy.crm.service.model.SysDept;
import com.yy.crm.service.param.DeptParam;
import com.yy.crm.service.service.SysDeptService;
import com.yy.crm.service.service.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 鲁源源 on 2018/1/10.
 */
@Service
public class SysDeptServiceImpl extends BaseService<SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public void saveDept(DeptParam param) {
        BeanValidator.check(param);
        //不能有部门名称相同
        boolean flag = checkExist(param.getParentId(),param.getName(),param.getId());
        if(flag){
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept sysDept = SysDept.builder().name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark()).build();
        sysDept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        //todo 操作人
        sysDept.setOperator("system");
        sysDept.setOperateIp("127.0.0.1");
        sysDept.setOperateTime(LocalDateTime.now());
        mapper.insertSelective(sysDept);
    }

    @Override
    public void update(DeptParam param) {
        BeanValidator.check(param);
        //不能有部门名称相同
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        //判断原来的部门是否存在
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的部门不存在");

        SysDept after = SysDept.builder().id(param.getId()).name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));

        //todo 操作人
        after.setOperator("system");
        after.setOperateIp("127.0.0.1");
        after.setOperateTime(LocalDateTime.now());
        updateWithChild(before,after);
    }

    @Transactional(rollbackFor = Exception.class)
    private void updateWithChild(SysDept before,SysDept after){

        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        //更改子部门
        if(!after.getLevel().equals(before.getLevel())){
            //取出子部门
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(oldLevelPrefix);
            if(CollectionUtils.isNotEmpty(deptList)){
                deptList.forEach(dept -> {
                    String level = dept.getLevel();
                    if(level.indexOf(oldLevelPrefix) == 0){
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                });
                //批量更新子部门
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);

    }
    private boolean checkExist(Integer parentId, String name,Integer deptId) {
        int count = sysDeptMapper.countByNameAndParentId(parentId,name,deptId);
        return count > 0;
    }

    private String getLevel(Integer deptId){
        SysDept dept = mapper.selectByPrimaryKey(deptId);
        if(dept == null){
            return null;
        }
        return dept.getLevel();
    }
}

package com.yy.crm.service.service.impl;

import com.yy.crm.common.response.LevelUtil;
import com.yy.crm.security.common.exception.ParamException;
import com.yy.crm.security.common.util.BeanValidator;
import com.yy.crm.service.mapper.SysDeptMapper;
import com.yy.crm.service.model.SysDept;
import com.yy.crm.service.param.DeptParam;
import com.yy.crm.service.service.SysDeptService;
import com.yy.crm.service.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

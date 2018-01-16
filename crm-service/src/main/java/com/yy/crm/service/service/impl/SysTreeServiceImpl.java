package com.yy.crm.service.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.yy.crm.common.response.LevelUtil;
import com.yy.crm.service.dto.DeptLevelDto;
import com.yy.crm.service.mapper.SysDeptMapper;
import com.yy.crm.service.model.SysDept;
import com.yy.crm.service.service.SysTreeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 鲁源源 on 2018/1/11.
 */
@Service
public class SysTreeServiceImpl implements SysTreeService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<DeptLevelDto> deptTree() {
        List<SysDept> deptList = sysDeptMapper.selectAll();
        List<DeptLevelDto> dtoList = deptList.stream().map(DeptLevelDto::adapt).collect(Collectors.toList());
        return deptListToTree(dtoList);
    }

    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList){

        if(CollectionUtils.isEmpty(deptLevelList)){
            return Lists.newArrayList();
        }
        //level -> [dept1,dept2,...]
        Multimap<String, DeptLevelDto> levelDeptMap = ArrayListMultimap.create();

        List<DeptLevelDto> rootList = Lists.newArrayList();
        deptLevelList.forEach(dto ->{
            levelDeptMap.put(dto.getLevel(),dto);
            if(LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        });

        //按照seq排序,升序
        rootList.sort(Comparator.comparingInt(DeptLevelDto::getSeq));
        //递归生成树
        transformDeptTree(rootList,LevelUtil.ROOT,levelDeptMap);
        return rootList;
    }

    private void transformDeptTree(List<DeptLevelDto> deptLevelList, String level, Multimap<String, DeptLevelDto> levelDeptMap) {
        deptLevelList.forEach(dto ->{
            //遍历该层的每个元素
            //处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, dto.getId());
            //处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            if(!CollectionUtils.isEmpty(tempDeptList)){
                //排序
                tempDeptList.sort(Comparator.comparingInt(DeptLevelDto::getSeq));
                //设置下一层部门
                dto.setDeptList(tempDeptList);
                //进去下一层处理
                transformDeptTree(tempDeptList,nextLevel,levelDeptMap);
            }
        });
    }
}

package com.yy.crm.service.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.yy.crm.service.dto.AclLevelDto;
import com.yy.crm.service.dto.DeptLevelDto;
import com.yy.crm.service.mapper.SysAclMapper;
import com.yy.crm.service.mapper.SysDeptMapper;
import com.yy.crm.service.model.SysAcl;
import com.yy.crm.service.model.SysDept;
import com.yy.crm.service.service.SysTreeService;
import com.yy.crm.utils.LevelUtil;
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
    @Autowired
    private SysAclMapper sysAclMapper;

    @Override
    public List<DeptLevelDto> deptTree() {
        List<SysDept> deptList = sysDeptMapper.selectAll();
        List<DeptLevelDto> dtoList = deptList.stream().map(DeptLevelDto::adapt).collect(Collectors.toList());
        return deptListToTree(dtoList);
    }

    @Override
    public List<AclLevelDto> aclTree() {
        List<SysAcl> aclList = sysAclMapper.selectAll();
        List<AclLevelDto> dtoList = Lists.newArrayList();
        for (SysAcl acl : aclList) {
            dtoList.add(AclLevelDto.adapt(acl));
        }
        return aclListToTree(dtoList);
    }

    private List<AclLevelDto> aclListToTree(List<AclLevelDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        // level -> [acl1, acl2, ...] Map<String, List<Object>>
        Multimap<String, AclLevelDto> levelAclMap = ArrayListMultimap.create();
        List<AclLevelDto> rootList = Lists.newArrayList();

        for (AclLevelDto dto : dtoList) {
            levelAclMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        rootList.sort(Comparator.comparingInt(AclLevelDto::getSeq));
        transformAclTree(rootList, LevelUtil.ROOT, levelAclMap);
        return rootList;
    }

    private void transformAclTree(List<AclLevelDto> aclLevelList, String level, Multimap<String, AclLevelDto> levelAclMap) {

        aclLevelList.forEach(dto ->{
            //遍历该层的每个元素
            //处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, dto.getId());
            //处理下一层
            List<AclLevelDto> tempList = (List<AclLevelDto>) levelAclMap.get(nextLevel);
            if(!CollectionUtils.isEmpty(tempList)){
                //排序
                tempList.sort(Comparator.comparingInt(AclLevelDto::getSeq));
                //设置下一层部门
                dto.setAclList(tempList);
                //进去下一层处理
                transformAclTree(tempList,nextLevel,levelAclMap);
            }
        });
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

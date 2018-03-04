package com.yy.crm.service.service.impl;

import com.yy.crm.service.mapper.SysVisitorMapper;
import com.yy.crm.service.model.SysVisitor;
import com.yy.crm.service.service.SysVisitorService;
import com.yy.crm.service.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author 鲁源源 on 2018/3/4.
 */
@Service
@Slf4j
public class SysVisitorServiceImpl extends BaseService<SysVisitor> implements SysVisitorService {

    @Autowired
    private SysVisitorMapper sysVisitorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveVisitor(String remoteIp) {
        SysVisitor sysVisitor = sysVisitorMapper.selectByPrimaryKey(remoteIp);
        if(sysVisitor != null) {
            sysVisitor.setUpdateTime(LocalDateTime.now());
            sysVisitorMapper.updateByPrimaryKeySelective(sysVisitor);
        }else {
            log.info("new visitor ip is:"+ remoteIp);
            sysVisitor = new SysVisitor();
            sysVisitor.setIp(remoteIp);
            sysVisitor.setCreateTime(LocalDateTime.now());
            sysVisitor.setUpdateTime(LocalDateTime.now());
            sysVisitorMapper.insertSelective(sysVisitor);
        }
    }
}

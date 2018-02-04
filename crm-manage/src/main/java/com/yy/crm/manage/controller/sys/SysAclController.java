package com.yy.crm.manage.controller.sys;

import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.param.AclParam;
import com.yy.crm.service.service.SysAclService;
import com.yy.crm.service.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 鲁源源 on 2018/2/4.
 */
@RestController("/sys/acl")
@Slf4j
public class SysAclController {

    @Autowired
    private SysTreeService sysTreeService;
    @Autowired
    private SysAclService sysAclService;

    @PostMapping("/save")
    public ServerResponse saveAcl(AclParam param) {
        sysAclService.save(param);
        return ServerResponse.createBySuccess();
    }

    @GetMapping("/tree")
    public ServerResponse tree() {
        return ServerResponse.createBySuccess(sysTreeService.aclTree());
    }
}

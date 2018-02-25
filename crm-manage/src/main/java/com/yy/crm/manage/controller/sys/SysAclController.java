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
@RestController
@RequestMapping("/sys/acl")
@Slf4j
public class SysAclController {

    @Autowired
    private SysTreeService sysTreeService;
    @Autowired
    private SysAclService sysAclService;

    /**
     * 保存权限
     * @param param
     * @reurn
     */
    @PostMapping("/save")
    public ServerResponse saveAcl(AclParam param) {
        sysAclService.save(param);
        return ServerResponse.createBySuccess();
    }

    /**
     * 权限树
     * @return
     */
    @GetMapping("/tree")
    public ServerResponse tree() {
        return ServerResponse.createBySuccess(sysTreeService.aclTree());
    }

    /**
     * 更新权限
     * @param param
     * @return
     */
    @PutMapping("/update")
    public ServerResponse updateAcl(AclParam param) {
        sysAclService.update(param);
        return ServerResponse.createBySuccess();
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public ServerResponse delete(@RequestParam("id") int id) {
        sysAclService.delete(id);
        return ServerResponse.createBySuccess();
    }
}

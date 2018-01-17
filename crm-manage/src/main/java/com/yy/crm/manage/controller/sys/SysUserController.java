package com.yy.crm.manage.controller.sys;

import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.param.UserParam;
import com.yy.crm.service.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 * @author 鲁源源 on 2018/1/9.
 */
@RestController
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    /**
     * 添加部门
     * @param param
     * @return
     */
    @PostMapping("/addDept")
    private ServerResponse addDept(UserParam param){
        sysUserService.save(param);
        return ServerResponse.createBySuccess();
    }

}

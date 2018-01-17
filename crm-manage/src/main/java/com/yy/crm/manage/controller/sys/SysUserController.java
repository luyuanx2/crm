package com.yy.crm.manage.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.model.SysUser;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.UserParam;
import com.yy.crm.service.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 添加用户
     * @param param
     * @return
     */
    @PostMapping("/addUser")
    private ServerResponse addUser(UserParam param){
        sysUserService.save(param);
        return ServerResponse.createBySuccess();
    }

    /**
     * 更新用户
     * @param param
     * @return
     */
    @PutMapping("/update")
    public ServerResponse updateUser(UserParam param) {
        sysUserService.update(param);
        return ServerResponse.createBySuccess();
    }

    @GetMapping("/page.json")
    @ResponseBody
    public ServerResponse page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageInfo<SysUser> result = sysUserService.getPageByDeptId(deptId, pageQuery);
        return ServerResponse.createBySuccess(result);
    }

}

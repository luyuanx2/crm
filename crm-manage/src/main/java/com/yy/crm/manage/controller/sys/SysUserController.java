package com.yy.crm.manage.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.dto.SysUserDto;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.SysUserParam;
import com.yy.crm.service.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
     * 添加用户
     * @param param
     * @return
     */
    @PostMapping("/addUser")
    private ServerResponse addUser(SysUserParam param){
        sysUserService.save(param);
        return ServerResponse.createBySuccess();
    }

    /**
     * 更新用户
     * @param param
     * @return
     */
    @PutMapping("/update")
    public ServerResponse updateUser(SysUserParam param) {
        sysUserService.update(param);
        return ServerResponse.createBySuccess();
    }

    @GetMapping("/page.json")
    @ResponseBody
    public ServerResponse page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageInfo<SysUserDto> result = sysUserService.getPageByDeptId(deptId, pageQuery);
        return ServerResponse.createBySuccess(result);
    }

}

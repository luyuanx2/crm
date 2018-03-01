package com.yy.crm.manage.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.security.app.social.AppSignUpUtils;
import com.yy.crm.security.common.util.Assert;
import com.yy.crm.security.core.properties.SecurityProperties;
import com.yy.crm.service.dto.SysUserDto;
import com.yy.crm.service.model.SysUser;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.SearchUserParam;
import com.yy.crm.service.param.SysUserParam;
import com.yy.crm.service.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @PostMapping("/regist")
    public ServerResponse regist(String username, String password, HttpServletRequest request) {
        Assert.isBlank(username,"用户名不能为空");
        Assert.isBlank(password,"密码不能为空");
        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
        SysUser sysUser = sysUserService.findByUsername(username);
        if(sysUser == null || !passwordEncoder.matches(password,sysUser.getPassword())) {
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }
        appSignUpUtils.doPostSignUp(new ServletWebRequest(request), String.valueOf(sysUser.getId()));
        return ServerResponse.createBySuccessMessage("绑定成功,请重新扫码登录");
    }
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

    @GetMapping("/list")
    public ServerResponse list(SearchUserParam param, PageQuery pageQuery) {
        PageInfo<SysUserDto> result = sysUserService.getPageByDeptId(param, pageQuery);
        return ServerResponse.createBySuccess(result);
    }

    @DeleteMapping("/delete")
    public ServerResponse delete(@RequestParam("id") int id) {
        sysUserService.delete(id);
        return ServerResponse.createBySuccess();
    }

}

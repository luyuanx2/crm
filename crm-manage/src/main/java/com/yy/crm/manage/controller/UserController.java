package com.yy.crm.manage.controller;

import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.security.app.social.AppSignUpUtils;
import com.yy.crm.security.core.properties.SecurityProperties;
import com.yy.crm.service.common.RequestHolder;
import com.yy.crm.service.service.SysVisitorService;
import com.yy.crm.utils.IpUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luyuanyuan on 2017/10/19.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AppSignUpUtils appSignUpUtils;
    @Autowired
    private SysVisitorService sysVisitorService;


    @PostMapping("/regist")
    public void regist(String username, String password, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        //todo 如果是管理后台需要填写正确的用户名和密码进行绑定
        
        String userId = RandomStringUtils.random(6);
        appSignUpUtils.doPostSignUp(new ServletWebRequest(request),userId);
    }





    @GetMapping("/info")
    //public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
    public ServerResponse getCurrentUser(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {
        //保存新访客
        saveVisitor(request);
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2()
                .getJwtSigningKey().getBytes("UTF-8"))
        .parseClaimsJws(token).getBody();

        String company = (String) claims.get("company");
        System.out.println("--------"+company);
        Map<String,Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("admin");
        map.put("role",list);
        map.put("name", RequestHolder.getCurrentUser().getUsername());
        map.put("avatar","头像");
        map.put("introduction","介绍");

        return ServerResponse.createBySuccess(map);
    }

    private void saveVisitor(HttpServletRequest request) {
        String remoteIp = IpUtil.getRemoteIp(request);
        sysVisitorService.saveVisitor(remoteIp);
    }


}

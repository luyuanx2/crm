package com.yy.crm.manage.controller;

import com.yy.crm.manage.dto.User;
import com.yy.crm.security.app.social.AppSignUpUtils;
import com.yy.crm.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

/**
 * Created by luyuanyuan on 2017/10/19.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AppSignUpUtils appSignUpUtils;


    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = RandomStringUtils.random(6);
        appSignUpUtils.doPostSignUp(new ServletWebRequest(request),userId);
    }





    @GetMapping("/me")
    //public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
    public Object getCurrentUser(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2()
                .getJwtSigningKey().getBytes("UTF-8"))
        .parseClaimsJws(token).getBody();

        String company = (String) claims.get("company");
        System.out.println("--------"+company);
        return user;
    }


}
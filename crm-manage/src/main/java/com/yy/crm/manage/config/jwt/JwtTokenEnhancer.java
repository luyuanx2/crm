package com.yy.crm.manage.config.jwt;

import com.yy.crm.service.model.SysUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理后台的jwtToken增强器
 * @author 鲁源源 on 2017/12/6.
 */
@Component("jwtTokenEnhancer")
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {

        SysUser sysUser = (SysUser) oAuth2Authentication.getUserAuthentication().getPrincipal();
        Map<String,Object> info = new HashMap<>(16);
        info.put("company","lyy");
        info.put("userId",sysUser.getId());
        info.put("username",sysUser.getUsername());
        info.put("telephone",sysUser.getTelephone());
        info.put("mail",sysUser.getMail());
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}

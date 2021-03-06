package com.yy.crm.security.app;

import com.yy.crm.common.response.ResponseCode;
import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.security.app.social.AppSignUpUtils;
import com.yy.crm.security.core.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 鲁源源 on 2017/11/20.
 */
@RestController
public class AppSecurityController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @GetMapping("/social/signUp")
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ServerResponse<SocialUserInfo> getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());

        appSignUpUtils.saveConnectionData(new ServletWebRequest(request),connection.createData());
        return ServerResponse.createByEnumData(ResponseCode.UNAUTHORIZED,userInfo);
    }
}

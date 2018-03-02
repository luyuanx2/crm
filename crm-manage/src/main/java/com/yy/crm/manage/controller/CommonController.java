package com.yy.crm.manage.controller;

import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.security.core.properties.SecurityProperties;
import com.yy.crm.utils.MailUtil;
import com.yy.crm.utils.shell.ShellResult;
import com.yy.crm.utils.shell.ShellUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luyuanyuan on 2018/3/2.
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MailUtil mailUtil;

    @PostMapping("/pushCallback")
    public ServerResponse pushCallback(HttpServletRequest request,HttpServletResponse response) {
        String secret = request.getHeader(" X-Hub-Signature");
        String email = "694436921@qq.com";
        if(securityProperties.getOauth2().getJwtSigningKey().equals(secret)){
            ShellResult shellResult = ShellUtil.exceCommand("/home/crm/deploy.sh");
            if(shellResult != null && shellResult.getCode() != 0){
//                mailUtil.sendHtmlMessage(email,"项目启动错误",shellResult.getErrorInfoList().toString());
                return ServerResponse.createByErrorMessage("项目启动错误，错误信息："+shellResult.getErrorInfoList().toString());
            }
            return ServerResponse.createBySuccess();
        }else {
            log.error("pushCallback secret error");
            response.setStatus(HttpStatus.FORBIDDEN.value());
//            mailUtil.sendHtmlMessage(email,"pushCallback异常","secret错误");
            return ServerResponse.createByErrorMessage("secret错误，错误的secret为："+secret);
        }

    }

    @RequestMapping("/redirect")
    public void redir(String redirect, String code, String state, HttpServletResponse response,
                      HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException, ServletException {
//        response.setStatus(301);
//        String aaa = "window.opener.location.href = 'http://192.168.1.55:8999/login#code=" + code+"';";
//
////        String bbb = "<script>"+aaa+"</script>";
//        String bbb = "<!doctype html>" +
//                "<html><body><script>"+aaa+"window.close();</script></body></html>";
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().write(bbb);
        response.sendRedirect(redirect+"?code="+code);
        return;

    }
}

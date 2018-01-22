package com.yy.crm.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 鲁源源 on 2018/1/20.
 */
@Controller
public class RirController {

    @RequestMapping("/redirect")
    public void redir(String redirect, String code, String state, HttpServletResponse response,
                      HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
//        response.setStatus(302);
        String aaa = "window.opener.location.href = 'http://192.168.1.55:8999/login#code=" + code+"';";

//        String bbb = "<script>"+aaa+"</script>";
        String bbb = "<!doctype html>" +
                "<html><body><script>"+aaa+"window.close();</script></body></html>";
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(bbb);
//        response.sendRedirect(redirect+"?code="+code);
    }
}

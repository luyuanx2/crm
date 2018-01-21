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
        response.setStatus(302);
        //http://www.pinzhi365.com/manage/redirect?redirect=http://192.168.202.1:8999/login#/authredirect&code=011fTHn32qXz3M0XJBq32gtDn32fTHn3&state=

        response.sendRedirect("http://192.168.202.1:8999/login#/authredirect?code="+code);
    }
}

package com.yy.crm.manage.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yy.crm.common.response.ResponseCode;
import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.common.RequestHolder;
import com.yy.crm.service.model.SysUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("loginFilter")
public class LoginFilter implements Filter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String header = req.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        //todo 从token中解析出用户
        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null) {
            // 没有获取到用户信息
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(objectMapper.writeValueAsString(ServerResponse.createByEnum(ResponseCode.UNAUTHORIZED)));
            return;
        }
        RequestHolder.add(SysUser.builder().id((Integer) claims.get("userId")).username((String) claims.get("username"))
                .telephone((String)claims.get("telephone")).mail((String) claims.get("mail")).build());
        RequestHolder.add(req);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

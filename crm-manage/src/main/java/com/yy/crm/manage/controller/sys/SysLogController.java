package com.yy.crm.manage.controller.sys;

import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.SearchLogParam;
import com.yy.crm.service.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限操作记录
 * @author luyuanyuan on 2018/2/26.
 */
@RestController
@RequestMapping("/sys/log")
@Slf4j
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 还原操作
     * @param id
     * @return
     */
    @PostMapping("/recover")
    public ServerResponse recover(@RequestParam("id") int id) {
        sysLogService.recover(id);
        return ServerResponse.createBySuccess();
    }

    /**
     * 操作记录列表
     * @param param
     * @param page
     * @return
     */
    @GetMapping("/list")
    public ServerResponse list(SearchLogParam param, PageQuery page) {
        return ServerResponse.createBySuccess(sysLogService.searchList(param, page));
    }
}

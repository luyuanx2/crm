package com.yy.crm.manage.controller.sys;

import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.param.DeptParam;
import com.yy.crm.service.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 鲁源源 on 2018/1/9.
 */
@RestController
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping("/saveDept")
    private ServerResponse saveDept(DeptParam param){
        sysDeptService.saveDept(param);
        return ServerResponse.createBySuccess();
    }

}

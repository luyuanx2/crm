package com.yy.crm.manage.controller.sys;

import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.dto.DeptLevelDto;
import com.yy.crm.service.param.DeptParam;
import com.yy.crm.service.service.SysDeptService;
import com.yy.crm.service.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 鲁源源 on 2018/1/9.
 */
@RestController
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysTreeService sysTreeService;

    /**
     * 添加部门
     * @param param
     * @return
     */
    @PostMapping("/addDept")
    private ServerResponse addDept(DeptParam param){
        sysDeptService.saveDept(param);
        return ServerResponse.createBySuccess();
    }

    /**
     * 部门树
     * @return
     */
    @GetMapping("/listDept")
    public ServerResponse tree() {
        List<DeptLevelDto> dtoList = sysTreeService.deptTree();
        return ServerResponse.createBySuccess(dtoList);
    }

    @PostMapping("/update")
    public ServerResponse updateDept(DeptParam param) {
        sysDeptService.update(param);
        return ServerResponse.createBySuccess();
    }
}

package com.yy.crm.manage.controller.sys;

import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.dto.DeptLevelDto;
import com.yy.crm.service.param.DeptParam;
import com.yy.crm.service.service.SysDeptService;
import com.yy.crm.service.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门
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
        Integer deptId = sysDeptService.save(param);
        return ServerResponse.createBySuccess(deptId);
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

    @PutMapping("/update")
    public ServerResponse updateDept(DeptParam param) {
        sysDeptService.update(param);
        return ServerResponse.createBySuccess();
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public ServerResponse delete(@RequestParam("id") int id) {
        sysDeptService.delete(id);
        return ServerResponse.createBySuccess();
    }
}

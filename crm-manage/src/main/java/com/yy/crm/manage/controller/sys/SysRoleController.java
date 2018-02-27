package com.yy.crm.manage.controller.sys;

import com.google.common.collect.Maps;
import com.yy.crm.common.Const;
import com.yy.crm.common.response.ServerResponse;
import com.yy.crm.service.model.SysUser;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.RoleParam;
import com.yy.crm.service.service.*;
import com.yy.crm.utils.YYUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author luyuanyuan on 2018/2/24.
 */
@RestController
@RequestMapping("/sys/role")
@Slf4j
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysTreeService sysTreeService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysRoleAclService sysRoleAclService;

    /**
     * 保存角色
     * @param param
     * @return
     */
    @PostMapping("/save")
    public ServerResponse saveRole(RoleParam param) {
        sysRoleService.save(param);
        return ServerResponse.createBySuccess();
    }

    /**
     * 更新角色
     * @param param
     * @return
     */
    @PutMapping("/update")
    public ServerResponse updateRole(RoleParam param) {
        sysRoleService.update(param);
        return ServerResponse.createBySuccess();
    }

    /**
     * 角色列表
     * @param pageQuery
     * @return
     */
    @GetMapping("/list")
    public ServerResponse list(PageQuery pageQuery) {
        return ServerResponse.createBySuccess(sysRoleService.getAll(pageQuery));
    }

    /**
     * 角色权限树
     * @param roleId
     * @return
     */
    @GetMapping("/roleTree")
    public ServerResponse roleTree(@RequestParam("roleId") int roleId) {
        return ServerResponse.createBySuccess(sysTreeService.roleTree(roleId));
    }

    /**
     * 查找角色绑定的用户
     * @param roleId
     * @return
     */
    @GetMapping("/users")
    public ServerResponse users(@RequestParam("roleId") int roleId) {
        List<SysUser> selectedUserList = sysRoleUserService.getListByRoleId(roleId);
        List<SysUser> allUserList = sysUserService.findByStatusAndUsable(Const.Status.INVALID,true);
        Set<Integer> selectedUserIdSet = selectedUserList.stream().map(SysUser::getId).collect(Collectors.toSet());

        Map<String, Object> map = Maps.newHashMap();
        map.put("users", allUserList);
        map.put("selected", selectedUserIdSet);
        return ServerResponse.createBySuccess(map);
    }

    /**
     * 修改角色绑定的权限
     * @param roleId
     * @param aclIds
     * @return
     */
    @PostMapping("/changeAcls")
    public ServerResponse changeAcls(@RequestParam("roleId") int roleId, @RequestParam(value = "aclIds", required = false, defaultValue = "") String aclIds) {
        List<Integer> aclIdList = YYUtil.splitToListInt(aclIds);
        sysRoleAclService.changeRoleAcls(roleId, aclIdList);
        return ServerResponse.createBySuccess();
    }

    /**
     * 修改角色绑定的用户
     * @param roleId
     * @param userIds
     * @return
     */
    @PostMapping("/changeUsers")
    public ServerResponse changeUsers(@RequestParam("roleId") int roleId, @RequestParam(value = "userIds", required = false, defaultValue = "") String userIds) {
        List<Integer> userIdList = YYUtil.splitToListInt(userIds);
        sysRoleUserService.changeRoleUsers(roleId, userIdList);
        return ServerResponse.createBySuccess();
    }
}

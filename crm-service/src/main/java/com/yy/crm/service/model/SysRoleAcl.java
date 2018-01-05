package com.yy.crm.service.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Table(name = "sys_role_acl")
public class SysRoleAcl {
    /**
     * 角色权限关联表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 权限id
     */
    @Column(name = "acl_id")
    private Integer aclId;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 最后一次更新时间
     */
    @Column(name = "operate_time")
    private LocalDateTime operateTime;

    /**
     * 最后一次更新者ip
     */
    @Column(name = "operate_ip")
    private String operateIp;

    /**
     * 获取角色权限关联表id
     *
     * @return id - 角色权限关联表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置角色权限关联表id
     *
     * @param id 角色权限关联表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取权限id
     *
     * @return acl_id - 权限id
     */
    public Integer getAclId() {
        return aclId;
    }

    /**
     * 设置权限id
     *
     * @param aclId 权限id
     */
    public void setAclId(Integer aclId) {
        this.aclId = aclId;
    }

    /**
     * 获取操作者
     *
     * @return operator - 操作者
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作者
     *
     * @param operator 操作者
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取最后一次更新时间
     *
     * @return operate_time - 最后一次更新时间
     */
    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    /**
     * 设置最后一次更新时间
     *
     * @param operateTime 最后一次更新时间
     */
    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取最后一次更新者ip
     *
     * @return operate_ip - 最后一次更新者ip
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 设置最后一次更新者ip
     *
     * @param operateIp 最后一次更新者ip
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}
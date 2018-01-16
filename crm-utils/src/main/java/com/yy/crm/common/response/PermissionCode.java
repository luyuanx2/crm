package com.yy.crm.common.response;

import lombok.Getter;

/**
 * @author 鲁源源 on 2018/1/16.
 */
@Getter
public enum PermissionCode implements CommonEnum {
    DEPT_ALREADY_EXIST(4001,"同一层级下存在相同名称的部门"),
    EXIST_CHILDREN_DEPT(4002,"当前部门下面有子部门，无法删除"),
    EXIST_CHILDREN_SYSUSER(4003,"当前部门下面有用户，无法删除");

    private final Integer code;
    private final String message;

    PermissionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

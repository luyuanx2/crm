package com.yy.crm.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author luyuanyuan on 2018/1/18.
 */
@Getter
@Setter
@ToString
@Builder
public class SysUserDto {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 用户所在部门的id
     */
    private Integer deptId;

    /**
     * 状态，1：正常，2：冻结
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}

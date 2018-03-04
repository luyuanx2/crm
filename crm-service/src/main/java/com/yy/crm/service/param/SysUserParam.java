package com.yy.crm.service.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 用户参数
 * @author luyuanyuan on 2018/1/17.
 */
@Getter
@Setter
public class SysUserParam {
    private Integer id;

    //@NotBlank(message = "真实姓名不可以为空")
    //@Length(min = 1, max = 10, message = "真实姓名长度需要在10个字以内")
    private String realName;

    //@NotBlank(message = "工号不可以为空")
    //@Length(min = 1, max = 32, message = "工号长度需要在32个字以内")
    private String jobNum;

    @NotBlank(message = "用户名不可以为空")
    @Length(min = 1, max = 20, message = "用户名长度需要在20个字以内")
    private String username;

    @NotBlank(message = "电话不可以为空")
    @Length(min = 1, max = 13, message = "电话长度需要在13个字以内")
    private String telephone;

    @NotBlank(message = "邮箱不允许为空")
    @Length(min = 5, max = 50, message = "邮箱长度需要在50个字符以内")
    private String mail;

    @NotNull(message = "必须提供用户所在的部门")
    private Integer deptId;

    @NotNull(message = "必须指定用户的状态")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private Integer status;

    @Length(max = 200, message = "备注长度需要在200个字以内")
    private String remark = "";
}

package com.yy.crm.service.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 鲁源源 on 2018/2/4.
 */
@Getter
@Setter
@ToString
public class AclParam {

    private Integer id;

    @NotBlank(message = "权限名称不可以为空")
    @Length(min = 2, max = 20, message = "权限名称长度需要在2-20个字之间")
    private String name;

    @Length(min = 6, max = 100, message = "权限URL长度需要在6-100个字符之间")
    private String url;

    @NotNull(message = "必须指定权限的类型")
    @Min(value = 1, message = "权限类型不合法")
    @Max(value = 4, message = "权限类型不合法")
    private Integer type;

    @NotNull(message = "必须指定权限的状态")
    @Min(value = 0, message = "权限状态不合法")
    @Max(value = 1, message = "权限状态不合法")
    private Integer status;

    @NotNull(message = "必须指定权限的展示顺序")
    private Integer seq;

    @Length(max = 200, message = "权限备注长度需要在200个字符以内")
    private String remark;

    private Integer parentId = 0;

    @Length(max = 30, message = "图标名称长度需要在30个字符以内")
    private String icon;

}

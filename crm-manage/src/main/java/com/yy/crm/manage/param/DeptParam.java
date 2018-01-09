package com.yy.crm.manage.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 部门参数
 * @author 鲁源源 on 2018/1/9.
 */
@Getter
@Setter
@ToString
public class DeptParam {

    /**
     * 部门id
     */
    private Integer id;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Length(max = 15, min = 2, message = "部门名称长度需要2-15个字之间")
    private String name;

    /**
     * 上级部门id
     */
    private Integer parentId;

    /**
     * 部门在当前层级下的顺序，由小到大
     */
    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    /**
     * 备注
     */
    @Length(max = 150, message = "备注长度不能超过150个字")
    private String remark;
}

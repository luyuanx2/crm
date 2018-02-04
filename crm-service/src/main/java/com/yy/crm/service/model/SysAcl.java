package com.yy.crm.service.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "sys_acl")
public class SysAcl {
    /**
     * 权限id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限码
     */
    private String code;

    /**
     * 权限层级
     */
    private String level;

    /**
     * 上级权限模块id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 目录图标
     */
    private String icon;

    /**
     * 请求url，可以填正则表达式
     */
    private String url;

    /**
     * 类型，1：目录，2：菜单，3：按钮，4：其他
     */
    private Integer type;

    /**
     * 状态，1：正常，0：冻结
     */
    private Integer status;

    /**
     * 权限在当前层级的顺序，由小到大
     */
    private Integer seq;

    /**
     * 备注
     */
    private String remark;

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
     * 最后一次更新者的ip地址
     */
    @Column(name = "operate_ip")
    private String operateIp;

}
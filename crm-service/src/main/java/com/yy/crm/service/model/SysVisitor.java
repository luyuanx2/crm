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
@Table(name = "sys_visitor")
public class SysVisitor {
    /**
     * 访客ip地址
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ip;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
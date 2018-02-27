package com.yy.crm.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "sys_log")
public class SysLog {
    /**
     * 权限操作日志表
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限更新类型，1：部门，2：用户，3：权限，4：角色，5：角色用户关系，6：角色权限关系
     */
    private Integer type;

    /**
     * 基于type后指定的对象id，比如用户、权限、角色等表的主键
     */
    @Column(name = "target_id")
    private Integer targetId;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 最后一次更新的时间
     */
    @Column(name = "operate_time")
//    @JsonSerialize(using = LocalDateTime2LongSerializer.class)
    private LocalDateTime operateTime;

    /**
     * 最后一次更新者的ip地址
     */
    @Column(name = "operate_ip")
    private String operateIp;

    /**
     * 当前是否复原过，0：没有，1：复原过
     */
    private Integer status;

    /**
     * 旧值
     */
    @Column(name = "old_value")
    private String oldValue;

    /**
     * 新值
     */
    @Column(name = "new_value")
    private String newValue;

}
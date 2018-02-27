package com.yy.crm.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "sys_user")
public class SysUser implements SocialUserDetails {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 工号
     */
    @Column(name = "job_num")
    private String jobNum;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 用户所在部门的id
     */
    @Column(name = "dept_id")
    private Integer deptId;

    /**
     * 状态，1：正常，2：冻结
     */
    private Integer status;

    /**
     * 状态，0：删除，1：正常
     */
    private Boolean usable;

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

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER");
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.usable;
    }

    /**
     * 第三方登录id
     * @return
     */
    @Override
    @JsonIgnore
    public String getUserId() {
        return String.valueOf(getId());
    }
}
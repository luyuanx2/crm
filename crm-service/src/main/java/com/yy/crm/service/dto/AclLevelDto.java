package com.yy.crm.service.dto;

import com.google.common.collect.Lists;
import com.yy.crm.service.model.SysAcl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author 鲁源源 on 2018/2/4.
 */
@Getter
@Setter
@ToString
public class AclLevelDto extends SysAcl {

    // 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;

    private List<AclLevelDto> aclList = Lists.newArrayList();

    public static AclLevelDto adapt(SysAcl acl) {
        AclLevelDto dto = new AclLevelDto();
        BeanUtils.copyProperties(acl, dto);
        return dto;
    }
}

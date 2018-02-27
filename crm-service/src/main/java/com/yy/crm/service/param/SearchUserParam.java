package com.yy.crm.service.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author luyuanyuan on 2018/2/27.
 */
@Getter
@Setter
@ToString
public class SearchUserParam {

    private Integer deptId;

    private Integer status;

    private String username;

    private String telephone;
}

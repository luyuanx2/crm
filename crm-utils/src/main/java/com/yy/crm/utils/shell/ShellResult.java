package com.yy.crm.utils.shell;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luyuanyuan on 2018/3/2.
 */
@Getter
@Setter
public class ShellResult {

    private int code;

    private List<String> errorInfoList;

    private List<String> stdInfoList;
}

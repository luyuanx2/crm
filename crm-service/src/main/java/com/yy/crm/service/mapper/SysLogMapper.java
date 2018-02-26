package com.yy.crm.service.mapper;

import com.yy.crm.service.common.MyMapper;
import com.yy.crm.service.model.SysLog;
import com.yy.crm.service.param.PageQuery;
import com.yy.crm.service.param.SearchLogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogMapper extends MyMapper<SysLog> {

    List<SysLog> searchList(@Param("param") SearchLogParam param);
}
package com.yy.crm.service.param;

import com.yy.crm.utils.YYUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author luyuanyuan on 2018/2/26.
 */
@Getter
@Setter
@ToString
public class SearchLogParam {

    private Integer type; // LogType

    private String beforeSeg;

    private String afterSeg;

    private String operator;

    @DateTimeFormat(pattern = YYUtil.STANDARD_FORMAT)
    private LocalDateTime beginTime;//yyyy-MM-dd HH:mm:ss

    @DateTimeFormat(pattern = YYUtil.STANDARD_FORMAT)
    private LocalDateTime endTime;
}

package com.yy.crm.security.common.util;

import com.yy.crm.security.common.exception.ParamException;
import org.apache.commons.lang.StringUtils;

/**
 * 参数校验
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new ParamException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ParamException(message);
        }
    }
}

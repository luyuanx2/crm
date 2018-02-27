package com.yy.crm.common;

/**
 * Created by 鲁源源 on 2017/6/7.
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Status {
        Integer NORMAL = 1;
        Integer INVALID = 0;

    }
    public interface LogStatus{
        int RECOVERED = 1;
        int UN_RECOVERED = 0;

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
    public interface Acl{
        Integer BOTTON = 3; //按钮
    }

}

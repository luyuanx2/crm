package com.yy.crm.common;

/**
 * Created by 鲁源源 on 2017/6/7.
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public static final Integer NORMAL = 1;

    public interface Cart{
        int CHECKRD = 1;
        int UN_CHECKRD = 0;

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
    public interface Acl{
        Integer BOTTON = 3; //按钮
    }

}

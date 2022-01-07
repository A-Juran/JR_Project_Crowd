package com.juran.crowd.constant;

/**
 * 作者： Juran on 2022/1/2 14:33
 * 作者博客：iit.la
 */

/**
 * 创建常量类，便于将该常量运用到其他需要用到的地方，同时在使用常量同时如果常量名错误会有较好的提示。
 */
public class CrowdConstant {
    public static final String MESSAGES_LOGIN_FAILED = "抱歉！账号密码错误！请重新输入";
    public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "抱歉！这个账号已经被使用了！";
    public static final String MESSAGES_ACCESS_FORBID = "请登录以后再访问";
    public static final String MESSAGE_STRING_INVALIDATE = "字符串不合法！请不要传入空字符串。";
    public static String MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE = "系统错误。";

    public static final String ATTR_NAME_EXCEPTION= "exception";
    public static final String ATTR_NAME_LOGIN_ADMIN = "loginAdmin";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";

}

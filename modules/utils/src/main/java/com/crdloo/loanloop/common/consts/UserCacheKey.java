package com.crdloo.loanloop.common.consts;

/**
 * 用户模块缓存Key
 * 
 * @author kasim
 */
public class UserCacheKey extends BaseCacheKey {

    // 用户基本信息
    public static final String USER_INFO_PREFIX = VERSION + "user_user_";
    // 用户验证码
    public static final String USER_VALIDATECODE_PREFIX = VERSION + "user_validateCode_";
    /**
     * 城市+性别 ,用户信息列表
     */
    public static final String USERLIST_CITYCODE_GENDER_PREFIX = VERSION + "user_cityCode_gender_";
    /**
     * 城市，用户信息列表
     */
    public static final String USERLIST_CITYCODE_PREFIX = VERSION + "user_cityCode_";
    /**
     * 性别，用户信息列表
     */
    public static final String USERLIST_GENDER_PREFIX = VERSION + "user_gender_";
    /**
     * 用户信息列表
     */
    public static final String USERLIST = VERSION + "user_list";
    /**
     * 总页数
     */
    public static final String TOTAL = "_total";
}

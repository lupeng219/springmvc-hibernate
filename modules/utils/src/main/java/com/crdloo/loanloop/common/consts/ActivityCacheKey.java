package com.crdloo.loanloop.common.consts;

/**
 * 活动模块缓存key
 * 
 * @author lp
 *
 */
public class ActivityCacheKey extends BaseCacheKey {
    /**
     * 所有活动内容信息
     */
    public static final String ACTIVITY_INFO_PREFIX = VERSION + "activity_activity_";
    /**
     * 用户发起的活动列表信息
     */
    public static final String ACTIVITY_USER_LIST_PREFIX = VERSION + "activity_activity_user_list_";
    /**
     * 用户报名活动列表
     */
    public static final String ACTIVITY_USERMEM_LIST_PREFIX = VERSION
            + "activity_activity_usermem_list_";
    /**
     * 活动报名成员列表
     */
    public static final String ACTIVITY_MEMBER_LIST_PREFIX = VERSION + "activity_member_list_";
    /**
     * 活动发布次数
     */
    public static final String ACTIVITY_RELEASE_TOTAL = VERSION+"activity_release_total";



}

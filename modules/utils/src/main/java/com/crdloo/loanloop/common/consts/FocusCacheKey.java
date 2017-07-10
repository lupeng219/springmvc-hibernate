package com.crdloo.loanloop.common.consts;

/**
 * 用户模块缓存Key
 * 
 * @author kasim
 */
public class FocusCacheKey extends BaseCacheKey {

    // 关注信息
    public static final String FOCUS_COUNT_PREFIX = VERSION + "MY_FOCUS_COUNT_";
    // 被关注信息
    public static final String FOCUS_COUNT_NEW_PREFIX = VERSION + "MY_FOCUS_COUNT_NEW_";
    // 关注信息列表(我关注的)
    public static final String FOCUS_LIST = VERSION + "MY_FOCUS_LIST";
    // 关注信息列表(关注我的)
    public static final String FOCUS_LIST_TOME = VERSION + "TOME_FOCUS_LIST";
}

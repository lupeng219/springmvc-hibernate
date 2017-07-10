package com.crdloo.loanloop.common.consts;

/**
 * 有关动态的缓存key
 * 
 * @author lp
 *
 */
public class SnsCacheKey extends BaseCacheKey {

    /**
     * 动态信息,id
     */
    public static final String NEWS_PREFIX = VERSION + "sns_news_";
    /**
     * 关注用户,userid
     */
    public static final String FOLLOW_PREFIX = VERSION + "sns_follow_";
    /**
     * 点赞信息 ,id
     */
    public static final String GOOD_PREFIX = VERSION + "sns_good_";
    /**
     * 点赞列表,newsID
     */
    public static final String GOOD_LIST_PREFIX = VERSION + "sns_good_list_";
    /**
     * 最新点赞列表 ,userID
     */
    public static final String GOOD_LATEST_PREFIX = VERSION + "sns_good_latest_";
    /**
     * 评论信息,id
     */
    public static final String COMMENT_PREFIX = VERSION + "sns_comment_";
    /**
     * 评论列表,newsid
     */
    public static final String COMMENT_LIST_PREFIX = VERSION + "sns_comment_list_";
    /**
     * 最新评论列表,userid
     */
    public static final String COMMENT_LATEST_PREFIX = VERSION + "sns_comment_latest_";
}

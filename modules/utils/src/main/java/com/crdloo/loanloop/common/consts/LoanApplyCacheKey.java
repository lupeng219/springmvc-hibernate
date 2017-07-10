package com.crdloo.loanloop.common.consts;

/**
 * 申请单模块缓存Key
 * 
 * @author
 */
public class LoanApplyCacheKey extends BaseCacheKey {

    /**
     * 被拒绝 ,申请单信息列表
     */
    public static final String LOANAPPLYLIST_REFUSE_PREFIX = VERSION + "loanApplyList_refuse_";
    /**
     * 进行中，申请单信息列表
     */
    public static final String LOANAPPLYLIST_ING_PREFIX = VERSION + "loanApplyList_ing_";
    /**
     * 已完成，申请单信息列表
     */
    public static final String LOANAPPLYLIST_FINISH_PREFIX = VERSION + "loanApplyList_finish_";
    /**
     * 总页数
     */
    public static final String TOTAL = "_total";
    /**
     * 总借款金额
     */
    public static final String SUM_LOANAMOUNT = VERSION + "sum_loanAmount";
    /**
     * 借款记录
     */
    public static final String LOANAMOUNT_RECORD = VERSION + "loanamount_record";
    /**
     * 订单详情
     */
    public static final String LOANAPPLY_INFO = VERSION + "loanApply_info";
}

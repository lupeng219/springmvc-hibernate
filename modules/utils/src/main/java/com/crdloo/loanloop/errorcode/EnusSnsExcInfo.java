package com.crdloo.loanloop.errorcode;

public enum EnusSnsExcInfo implements IMessageCode {

    NEWS_INFO_ISNULL(30001, "动态信息为空"), NEWS_ID_ISNULL(30002, "动态ID为空"), NEWS_INFO_NOTFOUND(30003,
            "动态信息未找到"), USER_ID_ISNULL(30004, "用户ID为空"), COMMENT_INFO_ISNULL(30005,
                    "评论信息为空"), COMMENT_ID_ISNULL(30006, "评论ID为空"), COMMENT_INFO_NOTFOUND(30007,
                            "评论信息未找到"), GOOD_INFO_ISNULL(30008, "点赞信息未找到"),;

    private int code;
    private String message;

    private EnusSnsExcInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}

package com.crdloo.loanloop.errorcode;

public enum EnumActivityExcInfo implements IMessageCode {

    USER_ID_ISNULL(20001, "用户ID为空"), ACTIVITY_ISNULL(20002, "活动信息为空"), ACTIVITY_SAVE_ERROR(20003,
            "活动信息保存失败"), ACTIVITY_CITYCODE_ISNULL(20004, "活动城市编码为空"), ACTIVITY_ID_ISNULL(20005,
                    "活动ID为空"), ACTIVITY_IS_OPENED(20006, "活动已开始"), ACTIVITY_IS_OVERTOP(20007, 
                    		"每月仅限发布4次活动"),ACTIVITY_IS_SYSERR(20008, 
                            		"系统异常");

    private int code;
    private String message;

    private EnumActivityExcInfo(int code, String message) {
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

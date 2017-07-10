package com.crdloo.loanloop.errorcode;

public enum EnumConnectExcInfo implements IMessageCode {

    WX_SESSION_ERROR(1100001, "获取微信登录SESSION信息失败"), 
    BINDING_USER_ERROR(1100002, "绑定已有帐号失败"), 
    HAS_BINDED_ERROR(1100003, "当前帐号已与其它帐号绑定"), 
    ;

    private int code;
    private String message;

    private EnumConnectExcInfo(int code, String message) {
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

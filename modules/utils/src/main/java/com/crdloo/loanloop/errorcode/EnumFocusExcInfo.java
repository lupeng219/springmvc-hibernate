package com.crdloo.loanloop.errorcode;

public enum EnumFocusExcInfo implements IMessageCode {

    FOCUS_ID_ISNULL(70001, "关注ID为空"),;

    private int code;
    private String message;

    private EnumFocusExcInfo(int code, String message) {
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

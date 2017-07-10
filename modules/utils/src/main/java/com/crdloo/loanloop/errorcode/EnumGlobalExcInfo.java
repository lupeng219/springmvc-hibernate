package com.crdloo.loanloop.errorcode;

public enum EnumGlobalExcInfo implements IMessageCode {

    GLOBAL_MISSING_PARAMETER(900001, "缺少参数"), GLOBAL_MISSING_REGION(900002, "初始化省市参数异常");

    private int code;
    private String message;

    private EnumGlobalExcInfo(int code, String message) {
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

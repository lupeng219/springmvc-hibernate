package com.crdloo.loanloop.errorcode;

public enum EnumCustomerInfo implements IMessageCode {

    CUSTOMER_ISLOSS(610001, "缺少参数"), CUSTOMER_ISNULL(640004, "客户信息未找到"), CUSTOMER_IDCARDREPEAT(
            620002, "身份证重复"), CUSTOMER_USER_ERROR(650005, "用户信息异常"), CUSTOMER_SYS(630003, "系统异常");
    private int code;
    private String message;

    private EnumCustomerInfo(int code, String message) {
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

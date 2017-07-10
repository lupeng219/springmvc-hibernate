package com.crdloo.loanloop.errorcode;

public enum EnumUserExcInfo implements IMessageCode {

    USER_ISNULL(100001, "该用户不存在，请重新输入"), 
    USER_USERACCOUNT_REPEAT(100002,"该手机号已被注册"), 
    USER_VALIDATECODE_ERROR(100003, "获取验证码失败"), USER_CHECKVALIDATECODE_ERROR(
                    100004, "验证码错误，请重新输入"), USER_USERSTATC_DISABLE(100005,
                            "账户冻结，请联系客服"), USER_USERNAMEORPASSWRODERROR(100006, "用户名或密码错误"),

    FOCUS_USERMAXCOUNT(300001, "最多关注500个用户"), FOCUS_NOTMINEFOUCSMINE(300002, "不允许自己关注自己"),
    USER_ISSYSERROT(100009, "系统错误")
    
    ;

    private int code;
    private String message;

    private EnumUserExcInfo(int code, String message) {
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

package com.crdloo.loanloop.errorcode;

public enum EnumLoanApplyExcInfo implements IMessageCode {

    LOANAPPLY_MISSING_PARAMETER(500001, "缺少参数"), LOANAPPLY_ISNULL(500002, "订单信息为空"), LOANAPPLY_SYS(
            500003,
            "系统异常"), LOANAPPLY_APPLYSTATE_error(500004, "审核状态异常"), LOANAPPLY_PDF_ERROR(500005,
                    "创建申请单PDF文件失败"), LOANAPPLY_NOPERFECT(500006, "资料未完善"), LOANAPPLY_PIC_ISNULL(
                            500007, "订单资料图片为空"), LOANAPPLY_CREATE_PDF_ERROR(500008,
                                    "生成PDF文件失败"), LOANAPPLY_PDF_ADDPIC_ERROR(500009,
                                            "PDF添加图片文件失败"), LOANAPPLY_PDF_ADDMARK_ERROR(500010,
                                                    "PDF添加水印失败"), LOANAPPLY_PDF_PUT_ERROR(500011,
                                                            "申请单PDF文件上传失败"), LOANAPPLY_USER_ERROR(
                                                                    500012,
                                                                    "用户不匹配"), LOANAPPLY_PIC_PUT_ERROR(
                                                                            500013,
                                                                            "上传申请单资料失败"), LOANAPPLY_LOANRECORD_ERROR(
                                                                                    500010,
                                                                                    "读取配置文件错误"), LOANAPPLY_OPENCITY_ERROR(
                                                                                            500012,
                                                                                            "城市未开放交单功能"), LOAN_IDCARD_BLACK_ERROR(
                                                                                                    500014,
                                                                                                    "该用户不能在本平台贷款"), LOAN_IDCARD_ERROR(
                                                                                                            500015,
                                                                                                            "该客户已在找钱申请贷款，请勿重复提交");
    private int code;
    private String message;

    private EnumLoanApplyExcInfo(int code, String message) {
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

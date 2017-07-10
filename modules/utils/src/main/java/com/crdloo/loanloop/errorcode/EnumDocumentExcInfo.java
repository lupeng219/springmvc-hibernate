package com.crdloo.loanloop.errorcode;

public enum EnumDocumentExcInfo implements IMessageCode {

    PUT_DOCUMENT_ERROR(800001, "保存文件失败"), 
    CREATE_MARK_IMG_ERROR(800002, "生成水印图片失败"), 
    ;
    
    private int code;
    private String message;
    
    private EnumDocumentExcInfo(int code, String message) {
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
